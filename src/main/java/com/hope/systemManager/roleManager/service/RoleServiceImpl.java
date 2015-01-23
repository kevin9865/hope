package com.hope.systemManager.roleManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.roleManager.dao.RoleDao;
import com.hope.systemManager.roleManager.model.Role;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Transactional
	public void add(Role role) {
		roleDao.add(role);
	}

	@Transactional
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Transactional
	public void update(Role role) {
		roleDao.update(role);
	}

	@Transactional
	public List<Role> roleQueryAll() {
		List<Role> list=roleDao.roleQueryAll();
		return list;
	}

	@Transactional
	public void deleteBatch(List<Role> role) {
		for(Role r:role){
			roleDao.delete(r);
		}
	}

}
