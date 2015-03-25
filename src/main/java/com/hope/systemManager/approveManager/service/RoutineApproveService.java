package com.hope.systemManager.approveManager.service;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;

public interface RoutineApproveService {
	/**
	 * 通过组织结构id查询审批流程
	 * @param orgId
	 * @return
	 */
	public ApproveFlowHeader query(String orgId);
}
