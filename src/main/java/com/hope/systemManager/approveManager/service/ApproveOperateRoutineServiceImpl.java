package com.hope.systemManager.approveManager.service;

import java.util.Date;

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
import com.hope.util.Tools;

public class ApproveOperateRoutineServiceImpl implements ApproveOperateService{
	
	private ApproveContentHeaderDao approveContentHeaderDao;
	private ApproveContentItemDao approveContentItemDao;
	private ApproveContentPersonDao approveContentPersonDao;
	
	private ApproveContentHeader approveContentHeader;
	private String url;
	private int index;

	public void setIndex(int index) {
		this.index = index;
	}

	public void setUrl(String url) {
		this.url = url;
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
		approveContentHeaderDao.add(cHeader);
		
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
			}
			approveContentPersonDao.add(cPerson);
		}
		
	}
	
	public void agree(ApproveContentHeader approveContentHeader,String name) {
		
	}
	
	public void refuse(ApproveContentHeader approveContentHeader,String name) {
		
	}

}
