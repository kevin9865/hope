package com.hope.systemManager.approveManager.service;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface RoutineApprovePageService {
	/**
	 * 通过id查询审批信息
	 * @param contentHeaderId
	 * @return
	 */
	public ApproveContentHeader query(int contentHeaderId);
}
