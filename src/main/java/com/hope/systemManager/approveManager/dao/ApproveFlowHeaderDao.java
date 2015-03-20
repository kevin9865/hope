package com.hope.systemManager.approveManager.dao;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;

public interface ApproveFlowHeaderDao {
	public void add(ApproveFlowHeader approveFlowHeader);
	public void delete(ApproveFlowHeader approveFlowHeader);
	public void update(ApproveFlowHeader approveFlowHeader);
	public ApproveFlowHeader query(ApproveFlowHeader approveFlowHeader);
	public List<ApproveFlowHeader> queryAll();
	public ApproveFlowHeader query(String orgId);
	public int maxId();
}
