package com.hope.systemManager.userManager.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.orgManager.dao.OrgDao;
import com.hope.systemManager.orgManager.model.Org;
import com.hope.systemManager.roleManager.dao.RoleDao;
import com.hope.systemManager.roleManager.model.Role;
import com.hope.systemManager.userManager.dao.UserDao;
import com.hope.systemManager.userManager.model.User;
import com.hope.util.MD5Util;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private RoleDao roleDao;
	private OrgDao orgDao;

	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

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

	@Transactional
	public void add(User user) {
		if (null == user.getRoleId()||user.getRoleId().equals("") ) {
		} else {
			Role role = new Role();
			role.setRoleId(user.getRoleId());
			Role roleQuery = roleDao.roleQuery(role);
			user.setRoleName(roleQuery.getRoleName());
			user.setOpeAuth(roleQuery.getOpeAuth());
		}

		if (null == user.getOrgId() || user.getOrgId().equals("")) {
		} else {
			Org org = new Org();
			org.setOrgId(user.getOrgId());
			Org orgQuery = orgDao.orgQuery(org);
			user.setOrgName(orgQuery.getOrgName());
		}

		userDao.add(user);
	}

	@Transactional
	public void delete(User user) {

	}

	@Transactional
	public void update(User user) {
		if (null == user.getRoleId()||user.getRoleId().equals("")) {
			user.setRoleId(null);
			user.setOpeAuth(null);
			user.setRoleName(null);
		} else {
			Role role = new Role();
			role.setRoleId(user.getRoleId());
			Role roleQuery = roleDao.roleQuery(role);
			if (null == roleQuery) {
				user.setRoleId(null);
				user.setOpeAuth(null);
				user.setRoleName(null);
			} else {
				User userQuery = userDao.userQuery(user);
				if (userQuery.getRoleId()!=user.getRoleId()) {
					user.setOpeAuth(roleQuery.getOpeAuth());
					user.setRoleName(roleQuery.getRoleName());
				}
			}
		}

		if (null == user.getOrgId() || user.getOrgId().equals("")) {
			user.setOrgName(null);
		} else {
			Org org = new Org();
			org.setOrgId(user.getOrgId());
			Org orgQuery = orgDao.orgQuery(org);
			if (null == orgQuery) {
				user.setOrgId(null);
				user.setOrgName(null);
			} else {
				user.setOrgName(orgQuery.getOrgName());
			}
		}

		userDao.update(user);
	}

	@Transactional
	public User loginQuery(User user) {
		User userTemp =null;
		try {
			userTemp = userDao.userQuery(user);
			if (userTemp != null) {
				if (MD5Util.validPassword(user.getPassword(), userTemp.getPassword())) {
				} else {
					userTemp=null;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return userTemp;
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
		User userTemp = userDao.userQuery(user);
		userTemp.setOpeAuth(user.getOpeAuth());
		userDao.update(userTemp);
	}

	@Transactional
	public User userQuery(User user) {
		User userQuery=userDao.userQuery(user);
		if(userQuery==null){
			return null;
		}else {
			return userQuery;
		}
	}

	@Transactional
	public String maxUserId() {
		if(null==userDao.maxUserId()){
			return "1";
		}else {
			return String.valueOf(Integer.valueOf(userDao.maxUserId())+1);
		}
	}

	@Transactional
	public void updatePwd(User user) {
		userDao.update(user);
	}

}
