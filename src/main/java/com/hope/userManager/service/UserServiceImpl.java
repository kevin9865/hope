package com.hope.userManager.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hope.userManager.dao.UserDao;
import com.hope.userManager.dao.UserDaoImpl;
import com.hope.userManager.model.User;


public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public void add(User user) {
		userDao.add(user);
	}

	public void delete(User user) {

	}

	public void update(User user) {

	}
	@Transactional
	public String loginQuery(User user) {
		String msg=null;
		User userTemp=userDao.userQuery(user);
		if(userTemp==null){
			msg="用户名或密码错误";
		}else {
			if(user.getPassword().equals(userTemp.getPassword())){
			}else {
				msg="用户名或密码错误";
			}
		}
		return msg;
	}
	
	
}
