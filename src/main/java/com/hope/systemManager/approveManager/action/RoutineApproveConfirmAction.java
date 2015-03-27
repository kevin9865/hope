package com.hope.systemManager.approveManager.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import bsh.Interpreter;

import com.hope.systemManager.approveManager.dao.ApproveFlowHeaderDao;
import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.service.ApproveOperateService;
import com.hope.systemManager.approveManager.temp.ApproversTemp;
import com.hope.systemManager.approveManager.util.SessionTools;
import com.hope.util.Tools;

public class RoutineApproveConfirmAction {
	
	@PostConstruct
	public void init(){
		try {
			buttonDisabled=false;
			approveContentHeader = (ApproveContentHeader) SessionTools
					.getContext("contentHeader");
			title = approveContentHeader.getContentTitle();

			ApproveContentItem approveContentItem = approveContentHeader
					.getApproveContentItems().get(0);
			Map<String, Object> itemMap = Tools.readJson(approveContentItem
					.getContentItem());
			content = (String) itemMap.get("content");
			
			List<ApproveContentPerson> contentPersons=approveContentHeader.getApproveContentPersons();
			approvers=new ArrayList<ApproversTemp>();
			
			for(ApproveContentPerson acp:contentPersons){
				ApproversTemp approversTemp=new ApproversTemp();
				
				if(acp.getConditions().equals("0")){
					approversTemp.setSelect(true);
				}else {
					Interpreter in = new Interpreter();
					in.set("checkObject", this.approveContentHeader);
					in.eval(acp.getCheckScript());
					
					Boolean result = false;
					if(in.get("result")!=null){
						result = (Boolean) in.get("result");
					}
					
					approversTemp.setSelect(result);
				}
				approversTemp.setRoleName(acp.getNodeName());
				approversTemp.setName(acp.getName());
				approvers.add(approversTemp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ApproveOperateService approveOperateService;
	private ApproveContentHeader approveContentHeader;

	public ApproveOperateService getApproveOperateService() {
		return approveOperateService;
	}

	public void setApproveOperateService(ApproveOperateService approveOperateService) {
		this.approveOperateService = approveOperateService;
	}

	public ApproveContentHeader getApproveContentHeader() {
		return approveContentHeader;
	}

	public void setApproveContentHeader(
			ApproveContentHeader approveContentHeader) {
		this.approveContentHeader = approveContentHeader;
	}

	/**
	 * 提交日常审批
	 */
	public void submit() {
		try {
			approveOperateService.setUrl("./routine_approve_page.jsf?id=");
			approveOperateService.setApproveContentHeader(approveContentHeader);
			approveOperateService.setIndex(0);
			approveOperateService.submit();
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("alert('提交成功');");
			//addMessage("提交成功");
			buttonDisabled=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void addMessage(String summary) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }

	/**
	 * 表单
	 */
	public String title;
	public String content;
	public List<ApproversTemp> approvers;
	public boolean buttonDisabled;

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public List<ApproversTemp> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<ApproversTemp> approvers) {
		this.approvers = approvers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
