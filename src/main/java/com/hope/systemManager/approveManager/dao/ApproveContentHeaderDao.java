package com.hope.systemManager.approveManager.dao;

import java.util.List;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveContentHeaderDao {
	public void add(ApproveContentHeader approveContentHeader);
	public void delete(ApproveContentHeader approveContentHeader);
	public void update(ApproveContentHeader approveContentHeader);
	public ApproveContentHeader query(ApproveContentHeader approveContentHeader);
	public List<ApproveContentHeader> queryAll();
	public List<ApproveContentHeader> query(String currentApprover);
	public int maxId();
}
