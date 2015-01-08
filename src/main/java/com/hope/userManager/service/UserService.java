package com.hope.userManager.service;

import com.hope.userManager.model.User;

public interface UserService {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public String loginQuery(User user);
}
