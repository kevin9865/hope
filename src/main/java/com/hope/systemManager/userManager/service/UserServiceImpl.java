package com.hope.systemManager.userManager.service;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.roleManager.dao.RoleDao;
import com.hope.systemManager.roleManager.model.Role;
import com.hope.systemManager.userManager.dao.UserDao;
import com.hope.systemManager.userManager.model.User;

@ApplicationScoped
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(rollbackFor=Exception.class)
	public void add(User user) {
		userDao.add(user);
	}

	@Transactional
	public void delete(User user) {

	}

	@Transactional
	public void update(User user) {
		Role role=new Role();
		role.setRoleId(user.getRoleId());
		Role roleTemp=roleDao.roleQuery(role);
		if (null==roleTemp) {
			user.setRoleId(null);
			user.setOpeAuth(null);
			userDao.update(user);
		}else {
			user.setOpeAuth(roleTemp.getOpeAuth());
			userDao.update(user);
		}
	}

	@Transactional
	public User loginQuery(User user) {
		User userTemp = userDao.userQuery(user);
		if (userTemp == null) {
			return null;
		} else {
			if (user.getPassword().equals(userTemp.getPassword())) {
				return userTemp;
			} else {
				return null;
			}
		}
	}

	@Transactional
	public List<User> userQueryAll() {
		List<User> list = userDao.userQueryAll();
		return list;
	}

	@Transactional
	public List<User> userFuzzyQuery(User user) {
		List<User> list = userDao.userFuzzyQuery(user);
		return list;
	}

	@Transactional
	public void updateUserAuth(User user) {
		User userTemp=userDao.userQuery(user);
		userTemp.setOpeAuth(user.getOpeAuth());
		userDao.update(userTemp);
	}

}
