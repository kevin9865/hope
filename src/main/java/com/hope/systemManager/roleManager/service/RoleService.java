package com.hope.systemManager.roleManager.service;

import java.util.List;

import com.hope.systemManager.roleManager.model.Role;

public interface RoleService {
	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	public void add(Role role);

	/**
	 * 删除角色
	 * 
	 * @param role
	 */
	public void delete(Role role);

	/**
	 * 批量删除角色
	 * 
	 * @param role
	 */
	public void deleteBatch(List<Role> role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 */
	public void update(Role role);

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	public List<Role> roleQueryAll();

	/**
	 * 获取角色最大roleId
	 * 
	 * @return
	 */
	public int maxRoleId();
}
