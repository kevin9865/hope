package com.hope.systemManager.roleManager.service;

import java.util.List;

import com.hope.systemManager.roleManager.model.Role;

public interface RoleService {
	public void add(Role role);
	public void delete(Role role);
	public void deleteBatch(List<Role> role);
	public void update(Role role);
	public List<Role> roleQueryAll();
	public String maxRoleId();
}
