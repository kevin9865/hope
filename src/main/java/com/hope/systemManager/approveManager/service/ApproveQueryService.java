package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveQueryService {
	/**
	 * 通过提交人查询审批信息
	 * @param submitter
	 * @return
	 */
	public List<ApproveContentHeader> query(String submitter);
}
