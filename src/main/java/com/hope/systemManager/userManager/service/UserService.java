package com.hope.systemManager.userManager.service;

import java.util.List;

import com.hope.systemManager.userManager.model.User;

public interface UserService {
	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void add(User user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	public void delete(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void update(User user);

	/**
	 * 更新用户权限
	 * 
	 * @param user
	 */
	public void updateUserAuth(User user);

	/**
	 * 用户登陆查询
	 * 
	 * @param user
	 * @return
	 */
	public User loginQuery(User user);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> userQueryAll();

	/**
	 * 模糊查询用户
	 * 
	 * @param user
	 * @return
	 */
	public List<User> userFuzzyQuery(User user);
}
