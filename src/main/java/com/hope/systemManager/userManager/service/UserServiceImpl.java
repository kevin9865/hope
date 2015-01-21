package com.hope.systemManager.userManager.service;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.userManager.dao.UserDao;
import com.hope.systemManager.userManager.model.User;

@ApplicationScoped
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
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
		this.userDao.update(user);
	}

	@Transactional
	public String loginQuery(User user) {
		String msg = null;
		User userTemp = userDao.userQuery(user);
		if (userTemp == null) {
			msg = "用户名或密码错误";
		} else {
			if (user.getPassword().equals(userTemp.getPassword())) {
			} else {
				msg = "用户名或密码错误";
			}
		}
		return msg;
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

}
