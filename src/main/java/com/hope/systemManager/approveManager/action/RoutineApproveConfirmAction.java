package com.hope.systemManager.approveManager.action;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;
import bsh.Interpreter;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.service.ApproveOperateService;
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
			
			approvers=approveContentHeader.getApproveContentPersons();
			
			for(ApproveContentPerson acp:approvers){
				if(acp.getConditions().equals("0")){
					acp.setSelect(true);
				}else {
					Interpreter in = new Interpreter();
					in.set("checkObject", this.approveContentHeader);
					in.eval(acp.getCheckScript());
					
					Boolean result = false;
					if(in.get("result")!=null){
						result = (Boolean) in.get("result");
					}
					acp.setSelect(result);
				}
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
			int flag=0;
			List<ApproveContentPerson> personTemp=approveContentHeader.getApproveContentPersons();
			for(ApproveContentPerson acp:personTemp){
				if(acp.isSelect()==false){
					approveContentHeader.getApproveContentPersons().remove(acp);
					flag=1;
				}
			}
			
			if(flag==1){
				int index=1;
				for(ApproveContentPerson acp:approveContentHeader.getApproveContentPersons()){
					acp.setNodeIndex(index);
					index++;
				}
			}
			
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
	public List<ApproveContentPerson> approvers;
	public boolean buttonDisabled;

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public List<ApproveContentPerson> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<ApproveContentPerson> approvers) {
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
