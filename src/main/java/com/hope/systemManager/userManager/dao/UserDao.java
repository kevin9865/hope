package com.hope.systemManager.userManager.dao;

import java.util.List;

import com.hope.systemManager.userManager.model.User;

public interface UserDao {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public User userQuery(User user);
	public List<User> userQueryAll();
}
