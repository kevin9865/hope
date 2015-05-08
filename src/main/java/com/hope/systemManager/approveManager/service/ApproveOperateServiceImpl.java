package com.hope.systemManager.approveManager.service;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveContentHeaderDao;
import com.hope.systemManager.approveManager.dao.ApproveContentItemDao;
import com.hope.systemManager.approveManager.dao.ApproveContentPersonDao;
import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.util.ApproveStatus;
import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.userManager.model.User;
import com.hope.util.AESUtil;
import com.hope.util.MD5Util;
import com.hope.util.Tools;

public class ApproveOperateServiceImpl implements ApproveOperateService{

	private ApproveContentHeaderDao approveContentHeaderDao;
	private ApproveContentItemDao approveContentItemDao;
	private ApproveContentPersonDao approveContentPersonDao;
	
	private ApproveContentHeader approveContentHeader;
	private int index;
	private String url;
	private HttpServletRequest httpRequest;
	
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setApproveContentHeader(ApproveContentHeader approveContentHeader) {
		this.approveContentHeader = approveContentHeader;
	}
	
	public ApproveContentHeaderDao getApproveContentHeaderDao() {
		return approveContentHeaderDao;
	}

	public void setApproveContentHeaderDao(
			ApproveContentHeaderDao approveContentHeaderDao) {
		this.approveContentHeaderDao = approveContentHeaderDao;
	}

	public ApproveContentItemDao getApproveContentItemDao() {
		return approveContentItemDao;
	}

	public void setApproveContentItemDao(ApproveContentItemDao approveContentItemDao) {
		this.approveContentItemDao = approveContentItemDao;
	}

	public ApproveContentPersonDao getApproveContentPersonDao() {
		return approveContentPersonDao;
	}

	public void setApproveContentPersonDao(
			ApproveContentPersonDao approveContentPersonDao) {
		this.approveContentPersonDao = approveContentPersonDao;
	}
	
	@Transactional
	public void submit() {
		ApproveContentHeader cHeader=new ApproveContentHeader();
		cHeader.setContentHeaderId(approveContentHeaderDao.maxId());
		cHeader.setSubmittime(new Date());
		cHeader.setSubmitter(LoginAction.getCurrentUser().getUsername());
		cHeader.setStatus(ApproveStatus.APPROVING);
		cHeader.setContentHeader(approveContentHeader.getContentHeader());
		cHeader.setContentTitle(approveContentHeader.getContentTitle());
		cHeader.setHeaderId(approveContentHeader.getHeaderId());
		
		//AES加密  
		String content = String.valueOf(cHeader.getContentHeaderId());  
		String password = "qwe123asd789zxc";
		byte[] encryptResult = AESUtil.encrypt(content, password);  
		String encryptResultStr = AESUtil.parseByte2HexStr(encryptResult);
		cHeader.setUrl(url+encryptResultStr+"&login");
		
		for(ApproveContentItem cItem:approveContentHeader.getApproveContentItems()){
			cItem.setContentHeaderId(cHeader.getContentHeaderId());
			cItem.setContentItemId(approveContentItemDao.maxId());
			approveContentItemDao.add(cItem);
		}
		for(ApproveContentPerson cPerson:approveContentHeader.getApproveContentPersons()){
			cPerson.setContentHeaderId(cHeader.getContentHeaderId());
			cPerson.setContentPersonId(approveContentItemDao.maxId());
			if(cPerson.getNodeIndex()==(index+1)){
				cPerson.setCreateTime(new Date());
				cPerson.setStatus("Y");
				
				//设置当前用户名和开始时间
				cHeader.setCurrentApprover(cPerson.getUsername());
				cHeader.setStarttime(cPerson.getCreateTime());
			}
			approveContentPersonDao.add(cPerson);
		}
		
		approveContentHeaderDao.add(cHeader);
	}
	
	@Transactional
	public void agree(ApproveContentHeader approveContentHeader,String username,String remark) {
		List<ApproveContentPerson> list=approveContentHeader.getApproveContentPersons();
		int size=list.size();
		
		for(int i=0;i<size;i++){
			ApproveContentPerson person=list.get(i);
			if(person.getUsername().equals(username)){
				person.setEndTime(new Date());
				person.setApproveResult("同意");
				person.setRemark(remark);
				person.setStatus("N");
				approveContentPersonDao.update(person);
				
				
				if(size==person.getNodeIndex()){
					approveContentHeader.setCurrentApprover(null);
					approveContentHeader.setStarttime(null);
					approveContentHeader.setStatus(ApproveStatus.APPROVE_SUCCESS);
					approveContentHeaderDao.update(approveContentHeader);
					break;
				}else {
					ApproveContentPerson personNext=list.get(i+1);
					personNext.setCreateTime(new Date());
					personNext.setStatus("Y");
					approveContentPersonDao.update(personNext);
					
					approveContentHeader.setCurrentApprover(personNext.getUsername());
					approveContentHeader.setStarttime(personNext.getCreateTime());
					approveContentHeaderDao.update(approveContentHeader);
					//发邮件通知
				}
			}
		}
	}
	
	@Transactional
	public void refuse(ApproveContentHeader approveContentHeader,String username,String remark) {
		List<ApproveContentPerson> list=approveContentHeader.getApproveContentPersons();
		int size=list.size();
		
		for(int i=0;i<size;i++){
			ApproveContentPerson person=list.get(i);
			if(person.getUsername().equals(username)){
				person.setEndTime(new Date());
				person.setApproveResult("不同意");
				person.setRemark(remark);
				person.setStatus("N");
				approveContentPersonDao.update(person);
				
				approveContentHeader.setCurrentApprover(null);
				approveContentHeader.setStarttime(null);
				approveContentHeader.setStatus(ApproveStatus.APPROVE_FAIL);
				approveContentHeaderDao.update(approveContentHeader);
				break;
			}
		}
	}
	
	public void sendEmail(String addressee,String title,String content){
//		MailCarrier mc=new MailCarrier();
//		String itcode=addressee+"@digitalchina.com";
//		mc.SendHtml("scm@digitalchina.com",itcode , title, content);
	}
	
	public String getApproveUrl(String path){
		String url=httpRequest.getRequestURL()+"";
		String contextPath=httpRequest.getContextPath();
		String s[]=url.split(contextPath);
		String ip=s[0];
		String urlTemp=ip+contextPath+path+"?id=";
		return urlTemp;
	}

}
