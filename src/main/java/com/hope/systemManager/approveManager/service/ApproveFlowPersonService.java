package com.hope.systemManager.approveManager.service;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public interface ApproveFlowPersonService {
	/**
	 * 添加审批行项
	 * 
	 * @param approveFlowItem
	 */
	public void add(ApproveFlowItem approveFlowItem);

	/**
	 * 删除审批行项
	 * 
	 * @param approveFlowItem
	 */
	public void delete(ApproveFlowItem approveFlowItem);

	/**
	 * 批量删除审批行项
	 * 
	 * @param list
	 */
	public void deleteBatch(List<ApproveFlowItem> list);

	/**
	 * 
	 * @param approveFlowItem
	 */
	public void update(ApproveFlowItem approveFlowItem);

	/**
	 * 查询所有审批行项
	 * 
	 * @return
	 */
	public List<ApproveFlowItem> queryAll();

	/**
	 * 根据审批流ID查询所有审批行项
	 * 
	 * @param approveFlowItem
	 * @return
	 */
	public List<ApproveFlowItem> queryAll(
			ApproveFlowItem approveFlowItem);
	
	/**
	 * 获取审批行项最大Id
	 * 
	 * @return
	 */
	public int maxId();
}
