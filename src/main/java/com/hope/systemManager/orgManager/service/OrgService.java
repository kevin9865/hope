package com.hope.systemManager.orgManager.service;

import java.util.List;

import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.orgManager.model.Org;

public interface OrgService {
	public void add(Org org);
	public void delete(Org org);
	public void deleteBatch(List<Org> list);
	public void update(Org org);
	public Org orgQuery(Org org);
	public List<Org> orgQueryAll();
}
