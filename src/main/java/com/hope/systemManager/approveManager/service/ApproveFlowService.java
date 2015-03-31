package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;


public interface ApproveFlowService {
	/**
	 * 添加审批流
	 * 
	 * @param approveFlowHeader
	 */
	public void add(ApproveFlowHeader approveFlowHeader);

	/**
	 * 删除审批流
	 * 
	 * @param approveFlowHeader
	 */
	public void delete(ApproveFlowHeader approveFlowHeader);

	/**
	 * 批量删除审批流
	 * 
	 * @param list
	 */
	public void deleteBatch(List<ApproveFlowHeader> list);

	/**
	 * 更新审批流
	 * 
	 * @param approveFlowHeader
	 */
	public void update(ApproveFlowHeader approveFlowHeader);

	/**
	 * 查询所有审批流
	 * 
	 * @return
	 */
	public List<ApproveFlowHeader> approveFlowQueryAll();
	
	/**
	 * 获取系统审批流最大Id
	 * 
	 * @return
	 */
	public int maxId();
}
