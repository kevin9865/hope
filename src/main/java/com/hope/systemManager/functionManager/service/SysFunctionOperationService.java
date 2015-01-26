package com.hope.systemManager.functionManager.service;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public interface SysFunctionOperationService {
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
	 * 批量删除功能操作
	 * 
	 * @param list
	 */
	public void deleteBatch(List<SysFunctionOperation> list);

	/**
	 * 
	 * @param sysFunctionOperation
	 */
	public void update(SysFunctionOperation sysFunctionOperation);

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
}
