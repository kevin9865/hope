package com.hope.systemManager.functionManager.dao;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public interface SysFunctionOperationDao {
	public void add(SysFunctionOperation sysFunctionOperation);
	public void delete(SysFunctionOperation sysFunctionOperation);
	public void update(SysFunctionOperation sysFunctionOperation);
	public SysFunctionOperation sysFunctionOperationQuery(SysFunctionOperation sysFunctionOperation);
	public List<SysFunctionOperation> sysFunctionOperationQueryAll();
	public List<SysFunctionOperation> sysFunctionOperationQueryAll(SysFunctionOperation sysFunctionOperation);
}
