package com.hope.systemManager.functionManager.dao;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunction;

public interface SysFunctionDao {
	/**
	 * 添加功能
	 * 
	 * @param sysFunction
	 */
	public void add(SysFunction sysFunction);

	/**
	 * 删除功能
	 * 
	 * @param sysFunction
	 */
	public void delete(SysFunction sysFunction);

	/**
	 * 更新功能
	 * 
	 * @param sysFunction
	 */
	public void update(SysFunction sysFunction);

	/**
	 * 查询功能
	 * 
	 * @param sysFunction
	 * @return
	 */
	public SysFunction sysFunctionQuery(SysFunction sysFunction);

	/**
	 * 查询所有功能
	 * 
	 * @return
	 */
	public List<SysFunction> sysFunctionQueryAll();

	/**
	 * 根据功能ID范围查询功能
	 * 
	 * @param list
	 * @return
	 */
	public List<SysFunction> sysFunctionQuery(List<String> list);
}
