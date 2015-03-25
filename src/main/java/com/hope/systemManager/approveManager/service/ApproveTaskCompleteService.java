package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveTaskCompleteService {
	/**
	 * 通过审批人查询审批信息
	 * @param approver
	 * @return
	 */
	public List<ApproveContentHeader> query(String approver);
}
