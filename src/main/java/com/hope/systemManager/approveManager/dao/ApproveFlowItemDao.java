package com.hope.systemManager.approveManager.dao;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public interface ApproveFlowItemDao {
	public void add(ApproveFlowItem approveFlowItem);
	public void delete(ApproveFlowItem approveFlowItem);
	public void update(ApproveFlowItem approveFlowItem);
	public List<ApproveFlowItem> queryAll(ApproveFlowItem approveFlowItem);
	public int maxId();
}
