package com.hope.systemManager.approveManager.action;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.service.ApproveOperateService;
import com.hope.systemManager.approveManager.service.RoutineApprovePageService;
import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.util.Tools;

public class RoutineApprovePageAction {

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest httpRequest = (HttpServletRequest) context
			.getExternalContext().getRequest();

	/**
	 * 还原审批内容步骤 
	 * 按照基础数据、分析数据、提交操作进行分块显示 
	 * 提交人只能看到基础数据 
	 * 审批人可以看到全部数据，但审批人审批完成后无法看到提交操作
	 */
	@PostConstruct
	public void init() {
		try {

			String idTemp = httpRequest.getParameter("id");
			int id = 0;
			if (idTemp != null) {
				id = Integer.valueOf(idTemp);
			}

			// 获取审批数据
			approveContentHeader = routineApprovePageService.query(id);
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

			// 判断是否显示数据
			if (flag == false) {
				title = null;
				content = null;
				approvers = null;
			} else {
				// 获取抬头数据和行项目数据
				title = approveContentHeader.getContentTitle();

				ApproveContentItem approveContentItem = approveContentHeader
						.getApproveContentItems().get(0);
				Map<String, Object> itemMap = Tools.readJson(approveContentItem
						.getContentItem());
				content = (String) itemMap.get("content");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ApproveContentHeader approveContentHeader;
	private RoutineApprovePageService routineApprovePageService;
	private ApproveOperateService approveOperateService;

	public ApproveOperateService getApproveOperateService() {
		return approveOperateService;
	}

	public void setApproveOperateService(
			ApproveOperateService approveOperateService) {
		this.approveOperateService = approveOperateService;
	}

	public ApproveContentHeader getApproveContentHeader() {
		return approveContentHeader;
	}

	public void setApproveContentHeader(
			ApproveContentHeader approveContentHeader) {
		this.approveContentHeader = approveContentHeader;
	}

	public RoutineApprovePageService getRoutineApprovePageService() {
		return routineApprovePageService;
	}

	public void setRoutineApprovePageService(
			RoutineApprovePageService routineApprovePageService) {
		this.routineApprovePageService = routineApprovePageService;
	}

	/**
	 * 审批通过
	 */
	public void argee() {
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
	 * 表单
	 */
	public String title;
	public String content;
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
