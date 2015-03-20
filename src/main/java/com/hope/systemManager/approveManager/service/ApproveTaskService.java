package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveTaskService {
	public List<ApproveContentHeader> query(String currentApprover);
}
