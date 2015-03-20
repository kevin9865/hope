package com.hope.systemManager.approveManager.dao;

import com.hope.systemManager.approveManager.model.ApproveContentItem;

public interface ApproveContentItemDao {
	public void add(ApproveContentItem approveContentItem);
	public void delete(ApproveContentItem approveContentItem);
	public void update(ApproveContentItem approveContentItem);
	public int maxId();
}
