package com.hope.systemManager.orgManager.dao;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.orgManager.model.Org;

public interface OrgDao {
	public void add(Org org);
	public void delete(Org org);
	public void update(Org org);
	public Org orgQuery(Org org);
	public List<Org> orgQueryAll();
}
