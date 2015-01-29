package com.hope.systemManager.orgManager.dao;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.orgManager.model.Org;

public interface OrgDao {
	/**
	 * 添加组织结构
	 * 
	 * @param org
	 */
	public void add(Org org);

	/**
	 * 删除组织结构
	 * 
	 * @param org
	 */
	public void delete(Org org);

	/**
	 * 更新组织结构
	 * 
	 * @param org
	 */
	public void update(Org org);

	/**
	 * 查询组织结构
	 * 
	 * @param org
	 * @return
	 */
	public Org orgQuery(Org org);

	/**
	 * 查询所有组织结构
	 * 
	 * @return
	 */
	public List<Org> orgQueryAll();

	/**
	 * 获取组织结构最大orgLineId
	 * 
	 * @return
	 */
	public String maxOrgLineId();
}
