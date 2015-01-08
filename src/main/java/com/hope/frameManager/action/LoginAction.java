package com.hope.frameManager.action;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;

import org.primefaces.expression.impl.ThisExpressionResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hope.userManager.model.User;
import com.hope.userManager.service.UserService;
import com.hope.userManager.service.UserServiceImpl;

public class LoginAction implements Serializable{	
	private UserService userService;
	
	private String username;
	private String password;
	private String msg;
	
	public LoginAction(){
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		msg=null;
		String skip=null;
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);

		String msg=userService.loginQuery(user);
		if(msg==null){
			skip="login";
		}else {
			this.msg=msg;
		}
		//skip="login";
		System.out.println(this.msg);
		return skip;
	}
	
	public void test(){
		System.out.println("用户名："+username);
	}
}
