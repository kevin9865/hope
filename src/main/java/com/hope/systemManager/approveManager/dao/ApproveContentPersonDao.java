package com.hope.systemManager.approveManager.dao;

import com.hope.systemManager.approveManager.model.ApproveContentPerson;

public interface ApproveContentPersonDao {
	/**
	 * 添加审批人
	 * 
	 * @param approveContentPerson
	 */
	public void add(ApproveContentPerson approveContentPerson);

	/**
	 * 删除审批人
	 * 
	 * @param approveContentPerson
	 */
	public void delete(ApproveContentPerson approveContentPerson);

	/**
	 * 更新审批人
	 * 
	 * @param approveContentPerson
	 */
	public void update(ApproveContentPerson approveContentPerson);

	/**
	 * 获取审批人最大id
	 * 
	 * @return
	 */
	public int maxId();
}
