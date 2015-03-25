package com.hope.systemManager.approveManager.action;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.service.ApproveTaskCompleteService;
import com.hope.systemManager.frameManager.action.LoginAction;

public class ApproveTaskCompleteAction {

	@PostConstruct
	public void init() {
		try {
			String approver = LoginAction.getCurrentUser().getUsername();
			approveContentHeaders = approveTaskCompleteService.query(approver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ApproveTaskCompleteService approveTaskCompleteService;
	private List<ApproveContentHeader> approveContentHeaders;
	private List<ApproveContentHeader> filteredApproveContentHeaders;

	public ApproveTaskCompleteService getApproveTaskCompleteService() {
		return approveTaskCompleteService;
	}

	public void setApproveTaskCompleteService(
			ApproveTaskCompleteService approveTaskCompleteService) {
		this.approveTaskCompleteService = approveTaskCompleteService;
	}

	public List<ApproveContentHeader> getApproveContentHeaders() {
		return approveContentHeaders;
	}

	public void setApproveContentHeaders(
			List<ApproveContentHeader> approveContentHeaders) {
		this.approveContentHeaders = approveContentHeaders;
	}

	public List<ApproveContentHeader> getFilteredApproveContentHeaders() {
		return filteredApproveContentHeaders;
	}

	public void setFilteredApproveContentHeaders(
			List<ApproveContentHeader> filteredApproveContentHeaders) {
		this.filteredApproveContentHeaders = filteredApproveContentHeaders;
	}

}
