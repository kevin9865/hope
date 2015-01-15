package com.hope.systemManager.userManager.service;

import java.util.List;

import com.hope.systemManager.userManager.model.User;

public interface UserService {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public String loginQuery(User user);
	public List<User> userQueryAll();
	public List<User> userFuzzyQuery(User user);
}
