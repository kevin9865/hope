package com.hope.systemManager.functionManager.service;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunction;

public interface SysFunctionService {
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
	 * 批量删除功能
	 * 
	 * @param list
	 */
	public void deleteBatch(List<SysFunction> list);

	/**
	 * 更新功能
	 * 
	 * @param sysFunction
	 */
	public void update(SysFunction sysFunction);

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
	
	/**
	 * 获取系统功能最大Id
	 * 
	 * @return
	 */
	public int maxId();
}
