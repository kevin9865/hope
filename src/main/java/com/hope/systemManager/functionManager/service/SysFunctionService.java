package com.hope.systemManager.functionManager.service;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunction;

public interface SysFunctionService {
	public void add(SysFunction sysFunction);
	public void delete(SysFunction sysFunction);
	public void deleteBatch(List<SysFunction> list);
	public void update(SysFunction sysFunction);
	public List<SysFunction> sysFunctionQueryAll();
}
