package com.hope.functionManager.dao;

import java.util.List;

import com.hope.functionManager.model.SysFunction;

public interface SysFunctionDao {
	public void add(SysFunction sysFunction);
	public void delete(SysFunction sysFunction);
	public void update(SysFunction sysFunction);
	public SysFunction sysFunctionQuery(SysFunction sysFunction);
	public List<SysFunction> sysFunctionQueryAll();
}
