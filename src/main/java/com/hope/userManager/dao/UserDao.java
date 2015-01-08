package com.hope.userManager.dao;

import com.hope.userManager.model.User;

public interface UserDao {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public User userQuery(User user);
}
