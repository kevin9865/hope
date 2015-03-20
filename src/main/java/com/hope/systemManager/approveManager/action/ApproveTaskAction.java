package com.hope.systemManager.approveManager.action;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.service.ApproveTaskService;
import com.hope.systemManager.frameManager.action.LoginAction;

public class ApproveTaskAction {

	@PostConstruct
	public void init() {
		String currentApprovers = LoginAction.getCurrentUser().getUsername();
		approveContentHeaders = approveTaskService.query(currentApprovers);

	}

	private ApproveTaskService approveTaskService;
	private List<ApproveContentHeader> approveContentHeaders;
	private List<ApproveContentHeader> filteredApproveContentHeaders;
	private List<ApproveContentHeader> selectedApproveContentHeaders;

	public List<ApproveContentHeader> getFilteredApproveContentHeaders() {
		return filteredApproveContentHeaders;
	}

	public void setFilteredApproveContentHeaders(
			List<ApproveContentHeader> filteredApproveContentHeaders) {
		this.filteredApproveContentHeaders = filteredApproveContentHeaders;
	}

	public List<ApproveContentHeader> getSelectedApproveContentHeaders() {
		return selectedApproveContentHeaders;
	}

	public void setSelectedApproveContentHeaders(
			List<ApproveContentHeader> selectedApproveContentHeaders) {
		this.selectedApproveContentHeaders = selectedApproveContentHeaders;
	}

	public List<ApproveContentHeader> getApproveContentHeaders() {
		return approveContentHeaders;
	}

	public void setApproveContentHeaders(
			List<ApproveContentHeader> approveContentHeaders) {
		this.approveContentHeaders = approveContentHeaders;
	}

	public ApproveTaskService getApproveTaskService() {
		return approveTaskService;
	}

	public void setApproveTaskService(ApproveTaskService approveTaskService) {
		this.approveTaskService = approveTaskService;
	}

	/**
	 * 选择功能Table
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			ApproveContentHeader cHeader = (ApproveContentHeader) event
					.getObject();
			System.out.println(cHeader.getContentTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
