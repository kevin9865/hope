package com.hope.systemManager.approveManager.action;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.service.ApproveQueryService;
import com.hope.systemManager.frameManager.action.LoginAction;

public class ApproveQueryAction {

	@PostConstruct
	public void init() {
		try {
			String submitter = LoginAction.getCurrentUser().getUsername();
			approveContentHeaders = approveQueryService.query(submitter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ApproveQueryService approveQueryService;
	private List<ApproveContentHeader> approveContentHeaders;
	private List<ApproveContentHeader> filteredApproveContentHeaders;

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

	public ApproveQueryService getApproveQueryService() {
		return approveQueryService;
	}

	public void setApproveQueryService(ApproveQueryService approveQueryService) {
		this.approveQueryService = approveQueryService;
	}

}
