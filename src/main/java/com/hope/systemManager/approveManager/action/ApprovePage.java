package com.hope.systemManager.approveManager.action;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.service.ApproveOperateService;
import com.hope.systemManager.approveManager.service.ApprovePageService;
import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.util.AESUtil;

public class ApprovePage {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest httpRequest = (HttpServletRequest) context
			.getExternalContext().getRequest();
	
	public ApproveContentHeader approveContentHeader;
	
	public boolean initBefore(){
		String idTemp = httpRequest.getParameter("id");
		int id = 0;
		if (idTemp != null) {
			// AES解密
			String password = "qwe123asd789zxc";
			byte[] decryptFrom = AESUtil.parseHexStr2Byte(idTemp);
			byte[] decryptResult = AESUtil.decrypt(decryptFrom, password);
			id = Integer.valueOf(new String(decryptResult));
		}

		// 获取审批数据
		approveContentHeader = approvePageService.query(id);
		// 获取当前用户
		String username = LoginAction.getCurrentUser().getUsername();

		boolean flag = false;// 是否显示基础数据
		buttonPanelRendered = false;// 是否显示提交操作

		// 提交人判断
		if (approveContentHeader.getSubmitter().equals(username)) {
			buttonPanelRendered = false;
			flag = true;
		}

		// 审批人判断
		approvers = approveContentHeader.getApproveContentPersons();
		for (ApproveContentPerson person : approvers) {
			if (person.getUsername().equals(username)) {
				flag = true;
				if (person.getStatus().equals("Y")) {
					// buttonDisabled = true;
					buttonPanelRendered = true;
					break;
				}
			}
		}
		return flag;
	}
	
	private ApproveOperateService approveOperateService;
	private ApprovePageService approvePageService;
	
	public ApprovePageService getApprovePageService() {
		return approvePageService;
	}

	public void setApprovePageService(ApprovePageService approvePageService) {
		this.approvePageService = approvePageService;
	}

	public ApproveOperateService getApproveOperateService() {
		return approveOperateService;
	}

	public void setApproveOperateService(ApproveOperateService approveOperateService) {
		this.approveOperateService = approveOperateService;
	}

	/**
	 * 审批通过
	 */
	public void agree() {
		try {
			approveOperateService.agree(approveContentHeader, LoginAction
					.getCurrentUser().getUsername(), remark);
			buttonDisabled = true;

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("alert('审批通过');");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 审批驳回
	 */
	public void refuse() {
		try {
			approveOperateService.refuse(approveContentHeader, LoginAction
					.getCurrentUser().getUsername(), remark);
			buttonDisabled = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加签
	 */
	public void countersigned() {
		try {
			RequestContext rc = RequestContext.getCurrentInstance();
			String[] approvers = approverField.split(";");
			String username = LoginAction.getCurrentUser().getUsername();
			String msg=approveOperateService.countersigned(approveContentHeader, approvers,
					typeField, username, remarkField);
			
			if(!msg.equals("")){
				rc.execute("alert('" + msg + "');");
				return;
			}
			
			buttonDisabled = true;
			
			//审批人列表重新赋值
			approveContentHeader = approvePageService
					.query((int) approveContentHeader.getContentHeaderId());
			this.approvers = approveContentHeader.getApproveContentPersons();
			
			rc.execute("PF('dlg1').hide()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化加签表单
	 */
	public void openDialog() {
		typeField = "";
		approverField = "";
		remarkField = "";
	}

	private String typeField;
	private String approverField;
	private String remarkField;

	public String getTypeField() {
		return typeField;
	}

	public void setTypeField(String typeField) {
		this.typeField = typeField;
	}

	public String getApproverField() {
		return approverField;
	}

	public void setApproverField(String approverField) {
		this.approverField = approverField;
	}

	public String getRemarkField() {
		return remarkField;
	}

	public void setRemarkField(String remarkField) {
		this.remarkField = remarkField;
	}

	public String remark;
	public boolean buttonDisabled;
	public boolean buttonPanelRendered;
	public List<ApproveContentPerson> approvers;

	public boolean isButtonPanelRendered() {
		return buttonPanelRendered;
	}

	public void setButtonPanelRendered(boolean buttonPanelRendered) {
		this.buttonPanelRendered = buttonPanelRendered;
	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ApproveContentPerson> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<ApproveContentPerson> approvers) {
		this.approvers = approvers;
	}

}
