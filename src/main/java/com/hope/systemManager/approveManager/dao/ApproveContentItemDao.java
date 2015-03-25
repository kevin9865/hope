package com.hope.systemManager.approveManager.dao;

import com.hope.systemManager.approveManager.model.ApproveContentItem;

public interface ApproveContentItemDao {
	/**
	 * 添加审批行项
	 * 
	 * @param approveContentItem
	 */
	public void add(ApproveContentItem approveContentItem);

	/**
	 * 删除审批行项
	 * 
	 * @param approveContentItem
	 */
	public void delete(ApproveContentItem approveContentItem);

	/**
	 * 更新审批行项
	 * 
	 * @param approveContentItem
	 */
	public void update(ApproveContentItem approveContentItem);

	/**
	 * 获取审批行项最大id
	 * 
	 * @return
	 */
	public int maxId();
}
