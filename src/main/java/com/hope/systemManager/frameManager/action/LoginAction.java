package com.hope.systemManager.frameManager.action;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.expression.impl.ThisExpressionResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.systemManager.userManager.service.UserServiceImpl;

public class LoginAction implements Serializable{
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest httpRequest = (HttpServletRequest) context.getExternalContext().getRequest();
	HttpSession httpSession = httpRequest.getSession();
	
	private UserService userService;
	
	private String username;
	private String password;
	private String msg;
	
	public LoginAction(){
	}
	@PostConstruct
	public void init(){
		//httpSession.invalidate();
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
		msg=null;
		String skip=null;
		User userTemp=new User();
		userTemp.setUsercode(username);
		userTemp.setPassword(password);
		
		User user=userService.loginQuery(userTemp);
		if(user==null){
			this.msg="用户名或密码错误";
		}else {
			skip="login";
			this.httpSession.setAttribute("UserSession", user);
		}
		
		return skip;
	}
	
	public static User getCurrentUser(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null){
			return null;
		}
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		User user = null;
		try {
			user = (User) session.getAttribute("UserSession");
		} catch (Exception e) {			
			user = null;
		}
		return user;
	}
}
