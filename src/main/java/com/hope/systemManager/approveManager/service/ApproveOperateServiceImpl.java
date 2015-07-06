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
import com.hope.systemManager.approveManager.util.ApproveUtil;
import com.hope.systemManager.emailManager.EmailUtil;
import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.userManager.dao.UserDao;
import com.hope.systemManager.userManager.model.User;
import com.hope.util.AESUtil;
import com.hope.util.MD5Util;
import com.hope.util.Tools;

public class ApproveOperateServiceImpl implements ApproveOperateService {

	private ApproveContentHeaderDao approveContentHeaderDao;
	private ApproveContentItemDao approveContentItemDao;
	private ApproveContentPersonDao approveContentPersonDao;
	private UserDao userDao;

	private ApproveContentHeader approveContentHeader;
	private int index;
	private String url;
	private HttpServletRequest httpRequest;
	private ApproveProcess approveProcess;
	private ApproveUtil approveUtil;

	public ApproveUtil getApproveUtil() {
		return approveUtil;
	}

	public void setApproveUtil(ApproveUtil approveUtil) {
		this.approveUtil = approveUtil;
	}

	public String getUrl() {
		return url;
	}

	public ApproveProcess getApproveProcess() {
		return approveProcess;
	}

	public void setApproveProcess(ApproveProcess approveProcess) {
		this.approveProcess = approveProcess;
	}

	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setApproveContentHeader(
			ApproveContentHeader approveContentHeader) {
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

	public void setApproveContentItemDao(
			ApproveContentItemDao approveContentItemDao) {
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
	public void initSubmit(HttpServletRequest httpRequest,
			ApproveContentHeader approveContentHeader) {
		this.httpRequest=httpRequest;
		this.approveProcess=approveUtil.getProcess(approveContentHeader);
		this.url=getApproveUrl(approveUtil.getUrl(approveContentHeader.getApproveName()));
		this.approveContentHeader=approveContentHeader;
		this.index=0;
		submit();
		
//		approveOperateService.setHttpRequest(httpRequest);
//		approveOperateService.setApproveProcess(approveUtil.getProcess(approveContentHeader));
//		//approveOperateService.setUrl(approveOperateService.getApproveUrl("/systemManager/approveManager/routine_approve_page.jsf"));
//		approveOperateService.setUrl(approveOperateService.getApproveUrl(approveUtil.getUrl(approveContentHeader.getApproveName())));
//		
//		approveOperateService.setApproveContentHeader(approveContentHeader);
//		approveOperateService.setIndex(0);
//		approveOperateService.submit();
		
	}

	
	public void submit() {
		ApproveContentHeader cHeader = new ApproveContentHeader();
		cHeader.setContentHeaderId(approveContentHeaderDao.maxId());
		cHeader.setSubmittime(new Date());
		cHeader.setSubmitter(LoginAction.getCurrentUser().getUsername());
		cHeader.setStatus(ApproveStatus.APPROVING);
		cHeader.setContentHeader(approveContentHeader.getContentHeader());
		cHeader.setContentTitle(approveContentHeader.getContentTitle());
		cHeader.setHeaderId(approveContentHeader.getHeaderId());

		// AES加密
		String content = String.valueOf(cHeader.getContentHeaderId());
		String password = "qwe123asd789zxc";
		byte[] encryptResult = AESUtil.encrypt(content, password);
		String encryptResultStr = AESUtil.parseByte2HexStr(encryptResult);
		cHeader.setUrl(url + encryptResultStr + "&login");

		for (ApproveContentItem cItem : approveContentHeader
				.getApproveContentItems()) {
			cItem.setContentHeaderId(cHeader.getContentHeaderId());
			cItem.setContentItemId(approveContentItemDao.maxId());
			approveContentItemDao.add(cItem);
		}
		for (ApproveContentPerson cPerson : approveContentHeader
				.getApproveContentPersons()) {
			cPerson.setContentHeaderId(cHeader.getContentHeaderId());
			cPerson.setContentPersonId(approveContentItemDao.maxId());
			if (cPerson.getNodeIndex() == (index + 1)) {
				cPerson.setCreateTime(new Date());
				cPerson.setStatus("Y");

				// 设置当前用户名和开始时间
				cHeader.setCurrentApprover(cPerson.getUsername());
				cHeader.setStarttime(cPerson.getCreateTime());
			}
			approveContentPersonDao.add(cPerson);
		}

		approveContentHeaderDao.add(cHeader);
		
		if(approveProcess!=null)
			approveProcess.startProcess();
		
		EmailUtil.sendEmail("", "", "");
	}

	@Transactional
	public void agree(ApproveContentHeader approveContentHeader,
			String username, String remark) {
		approveProcess=approveUtil.getProcess(approveContentHeader);
		
		List<ApproveContentPerson> list = approveContentHeader
				.getApproveContentPersons();
		int size = list.size();

		for (int i = 0; i < size; i++) {
			ApproveContentPerson person = list.get(i);
			if (person.getUsername().equals(username)&&person.getStatus().equals("Y")) {
				person.setEndTime(new Date());
				person.setApproveResult("同意");
				person.setRemark(remark);
				person.setStatus("N");
				approveContentPersonDao.update(person);

				if (size == person.getNodeIndex()) {
					approveContentHeader.setCurrentApprover(null);
					approveContentHeader.setStarttime(null);
					approveContentHeader
							.setStatus(ApproveStatus.APPROVE_SUCCESS);
					approveContentHeaderDao.update(approveContentHeader);
					if(approveProcess!=null)
						approveProcess.endProcess();
					
					EmailUtil.sendEmail("", "", "");
					break;
				} else {
					ApproveContentPerson personNext = list.get(i + 1);
					personNext.setCreateTime(new Date());
					personNext.setStatus("Y");
					approveContentPersonDao.update(personNext);

					approveContentHeader.setCurrentApprover(personNext
							.getUsername());
					approveContentHeader.setStarttime(personNext
							.getCreateTime());
					approveContentHeaderDao.update(approveContentHeader);
					// 发邮件通知
					EmailUtil.sendEmail("", "", "");
					break;
				}
			}
		}
	}

	@Transactional
	public void refuse(ApproveContentHeader approveContentHeader,
			String username, String remark) {
		approveProcess=approveUtil.getProcess(approveContentHeader);
		
		List<ApproveContentPerson> list = approveContentHeader
				.getApproveContentPersons();
		int size = list.size();

		for (int i = 0; i < size; i++) {
			ApproveContentPerson person = list.get(i);
			if (person.getUsername().equals(username)&&person.getStatus().equals("Y")) {
				person.setEndTime(new Date());
				person.setApproveResult("不同意");
				person.setRemark(remark);
				person.setStatus("N");
				approveContentPersonDao.update(person);

				approveContentHeader.setCurrentApprover(null);
				approveContentHeader.setStarttime(null);
				approveContentHeader.setStatus(ApproveStatus.APPROVE_FAIL);
				approveContentHeaderDao.update(approveContentHeader);
				
				if(approveProcess!=null)
					approveProcess.breakProcess();
				
				EmailUtil.sendEmail("", "", "");
				break;
			}
		}
	}

	public String getApproveUrl(String path) {
		String url = httpRequest.getRequestURL() + "";
		String contextPath = httpRequest.getContextPath();
		String s[] = url.split(contextPath);
		String ip = s[0];
		String urlTemp = ip + contextPath + path + "?id=";
		return urlTemp;
	}

	@Transactional
	public String countersigned(ApproveContentHeader approveContentHeader,
			String[] username, String flag, String currentApprover,
			String remark) {
		String msg = "";
		for (String approver : username) {
			User userTemp = new User();
			userTemp.setUsername(approver);
			User user = userDao.userQuery(userTemp);
			if (user == null) {
				msg = msg + approver + ",";
			}
		}
		if (!msg.equals("")) {
			msg = "用户" + msg + "不存在!";
			return msg;
		}

		if (flag.equals("before")) {
			int index = 1;
			for (ApproveContentPerson contentPerson : approveContentHeader
					.getApproveContentPersons()) {
				if (contentPerson.getUsername().equals(currentApprover)
						&& (contentPerson.getStatus() == null ? false
								: contentPerson.getStatus().equals("Y"))) {
					for (int i = 0; i < username.length; i++) {
						String name = username[i];
						ApproveContentPerson person = new ApproveContentPerson();
						person.setContentHeaderId(contentPerson
								.getContentHeaderId());
						person.setContentPersonId(approveContentPersonDao
								.maxId());
						person.setUsername(name);
						User user = new User();
						user.setUsername(name);
						User userQuery = userDao.userQuery(user);
						person.setName(userQuery.getName());
						person.setNodeName("加签");
						person.setNodeIndex(index);
						if (i == 0) {
							person.setCreateTime(new Date());
							person.setStatus("Y");
							
							approveContentHeader.setCurrentApprover(name);
							approveContentHeader.setStarttime(new Date());
							approveContentHeaderDao.update(approveContentHeader);
							// 邮件通知
							EmailUtil.sendEmail("", "", "");
						}
						approveContentPersonDao.add(person);
						index++;
					}

					contentPerson.setNodeIndex(index);
					contentPerson.setCreateTime(null);
					contentPerson.setStatus(null);
					approveContentPersonDao.update(contentPerson);
					index++;
				} else {
					contentPerson.setNodeIndex(index);
					approveContentPersonDao.update(contentPerson);
					index++;
				}
			}
		} else if (flag.equals("after")) {
			int index = 1;
			for (ApproveContentPerson contentPerson : approveContentHeader
					.getApproveContentPersons()) {
				if (contentPerson.getUsername().equals(currentApprover)
						&& (contentPerson.getStatus() == null ? false
								: contentPerson.getStatus().equals("Y"))) {
					contentPerson.setEndTime(new Date());
					contentPerson.setNodeIndex(index);
					contentPerson.setStatus("N");
					contentPerson.setRemark(remark);
					contentPerson.setApproveResult("同意");
					approveContentPersonDao.update(contentPerson);
					index++;

					for (int i = 0; i < username.length; i++) {
						String name = username[i];
						ApproveContentPerson person = new ApproveContentPerson();
						person.setContentHeaderId(contentPerson
								.getContentHeaderId());
						person.setContentPersonId(approveContentPersonDao
								.maxId());
						person.setUsername(name);
						User user = new User();
						user.setUsername(name);
						User userQuery = userDao.userQuery(user);
						person.setName(userQuery.getName());
						person.setNodeName("加签");
						person.setNodeIndex(index);
						if (i == 0) {
							person.setCreateTime(new Date());
							person.setStatus("Y");
							
							approveContentHeader.setCurrentApprover(name);
							approveContentHeader.setStarttime(new Date());
							approveContentHeaderDao.update(approveContentHeader);
							
							// 邮件通知
							EmailUtil.sendEmail("", "", "");
						}
						approveContentPersonDao.add(person);
						index++;
					}
				} else {
					contentPerson.setNodeIndex(index);
					approveContentPersonDao.update(contentPerson);
					index++;
				}
			}
		}
		return msg;
	}

}
