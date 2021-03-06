package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveTaskService {
	/**
	 * 通过当前审批人查询审批信息
	 * @param currentApprover
	 * @return
	 */
	public List<ApproveContentHeader> query(String currentApprover);
}
