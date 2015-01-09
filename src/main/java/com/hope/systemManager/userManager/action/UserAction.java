package com.hope.systemManager.userManager.action;

import com.hope.systemManager.userManager.service.UserService;

public class UserAction {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;
	private String valid;
	
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		if(valid.equals("1")){
			this.valid="*验证信息错误";
		}else {
			this.valid = valid;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(){
		System.out.println("系统登录");
		
		return "login";
	}

}
