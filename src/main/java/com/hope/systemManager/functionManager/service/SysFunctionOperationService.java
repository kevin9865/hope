package com.hope.systemManager.functionManager.service;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public interface SysFunctionOperationService {
	public void add(SysFunctionOperation sysFunctionOperation);
	public void delete(SysFunctionOperation sysFunctionOperation);
	public void deleteBatch(List<SysFunctionOperation> list);
	public void update(SysFunctionOperation sysFunctionOperation);
	public List<SysFunctionOperation> sysFunctionOperationQueryAll();
	public List<SysFunctionOperation> sysFunctionOperationQueryAll(SysFunctionOperation sysFunctionOperation);
}
