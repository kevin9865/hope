package com.hope.systemManager.userManager.service;

import com.hope.systemManager.userManager.model.User;

public interface UserService {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public String loginQuery(User user);
}
