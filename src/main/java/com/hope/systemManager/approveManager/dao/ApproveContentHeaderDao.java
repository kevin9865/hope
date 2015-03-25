package com.hope.systemManager.approveManager.dao;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveContentHeaderDao {
	/**
	 * 添加审批抬头
	 * 
	 * @param approveContentHeader
	 */
	public void add(ApproveContentHeader approveContentHeader);

	/**
	 * 删除审批抬头
	 * 
	 * @param approveContentHeader
	 */
	public void delete(ApproveContentHeader approveContentHeader);

	/**
	 * 更新审批抬头
	 * 
	 * @param approveContentHeader
	 */
	public void update(ApproveContentHeader approveContentHeader);

	/**
	 * 查询审批抬头
	 * 
	 * @param approveContentHeader
	 * @return
	 */
	public ApproveContentHeader query(ApproveContentHeader approveContentHeader);

	/**
	 * 查询所有审批信息
	 * 
	 * @return
	 */
	public List<ApproveContentHeader> queryAll();

	/**
	 * 根据当前审批人查询审批信息
	 * 
	 * @param currentApprover
	 * @return
	 */
	public List<ApproveContentHeader> currentApproverQuery(
			String currentApprover);

	/**
	 * 根据提交人查询审批信息
	 * 
	 * @param submitter
	 * @return
	 */
	public List<ApproveContentHeader> submitterQuery(String submitter);

	/**
	 * 根据审批人查询已审批信息
	 * 
	 * @param approver
	 * @return
	 */
	public List<ApproveContentHeader> approverQuery(String approver);

	/**
	 * 获取审批抬头最大id
	 * 
	 * @return
	 */
	public int maxId();
}
