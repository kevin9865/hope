package com.hope.systemManager.roleManager.dao;

import java.util.List;

import com.hope.systemManager.roleManager.model.Role;

public interface RoleDao {
	public void add(Role role);
	public void delete(Role role);
	public void update(Role role);
	public Role roleQuery(Role role);
	public List<Role> roleQueryAll();
}
