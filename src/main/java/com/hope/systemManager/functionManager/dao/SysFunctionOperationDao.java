package com.hope.systemManager.functionManager.dao;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public interface SysFunctionOperationDao {
	/**
	 * 添加功能操作
	 * 
	 * @param sysFunctionOperation
	 */
	public void add(SysFunctionOperation sysFunctionOperation);

	/**
	 * 删除功能操作
	 * 
	 * @param sysFunctionOperation
	 */
	public void delete(SysFunctionOperation sysFunctionOperation);

	/**
	 * 更新功能操作
	 * 
	 * @param sysFunctionOperation
	 */
	public void update(SysFunctionOperation sysFunctionOperation);

	/**
	 * 查询功能操作
	 * 
	 * @param sysFunctionOperation
	 * @return
	 */
	public SysFunctionOperation sysFunctionOperationQuery(
			SysFunctionOperation sysFunctionOperation);

	/**
	 * 查询所有功能操作
	 * 
	 * @return
	 */
	public List<SysFunctionOperation> sysFunctionOperationQueryAll();

	/**
	 * 根据功能ID查询所有功能操作
	 * 
	 * @param sysFunctionOperation
	 * @return
	 */
	public List<SysFunctionOperation> sysFunctionOperationQueryAll(
			SysFunctionOperation sysFunctionOperation);
	
	/**
	 * 获取系统功能最大Id
	 * 
	 * @return
	 */
	public int maxId();
}
