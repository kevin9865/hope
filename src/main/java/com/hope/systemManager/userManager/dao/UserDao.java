package com.hope.systemManager.userManager.dao;

import java.util.List;

import com.hope.systemManager.userManager.model.User;

public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user);
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(User user);
	/**
	 * 更新用户
	 * @param user
	 */
	public void update(User user);
	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	public User userQuery(User user);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> userQueryAll();
	/**
	 * 模糊查询用户
	 * @param user
	 * @return
	 */
	public List<User> userFuzzyQuery(User user);
}
