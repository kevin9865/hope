package com.hope.systemManager.approveManager.dao;

import com.hope.systemManager.approveManager.model.ApproveContentPerson;

public interface ApproveContentPersonDao {
	public void add(ApproveContentPerson approveContentPerson);
	public void delete(ApproveContentPerson approveContentPerson);
	public void update(ApproveContentPerson approveContentPerson);
	public int maxId();
}
