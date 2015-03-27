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

public class LoginAction implements Serializable {
	public LoginAction() {
	}

	@PostConstruct
	public void init() {
	}

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest httpRequest = (HttpServletRequest) context
			.getExternalContext().getRequest();
	HttpSession httpSession = httpRequest.getSession();
	private UserService userService;
	private String username;
	private String password;
	private String msg;

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

	/**
	 * 系统登录
	 * 
	 * @return
	 */
	public String login() {
		msg = null;
		String skip = null;
		User userTemp = new User();
		userTemp.setUsername(username);
		userTemp.setPassword(password);
		
		User user = userService.loginQuery(userTemp);

		if (user == null) {
			this.msg = "用户名或密码错误";
		} else {
			skip = "login";
			this.httpSession.setAttribute("UserContext", user);
			System.out.println("当前登录用户"
					+ LoginAction.getCurrentUser().getUsername());
			System.out.println("ip地址："+httpRequest.getRemoteAddr());
		}

		return skip;
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static User getCurrentUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null) {
			return null;
		}

		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User user = null;
		try {
			user = (User) session.getAttribute("UserContext");
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
}
