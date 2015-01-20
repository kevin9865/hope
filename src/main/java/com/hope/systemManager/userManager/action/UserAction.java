package com.hope.systemManager.userManager.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.util.Tools;

public class UserAction implements Serializable {

	@PostConstruct
	public void init() {
		initUserList();
	}

	public void initUserList() {
		users = userService.userQueryAll();
	}

	private UserService userService;
	private List<User> users;
	private List<User> filteredUsers;

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUsers() {
		return users;
	}

//	public void userFuzzyQuery() {
//		User user = new User();
//		user.setUsercode(Tools.SpaceDisappear(this.usercode));
//		users = userService.userFuzzyQuery(user);
//	}

	public void onRowEdit(RowEditEvent event) {
		User user = (User) event.getObject();
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initUserList();
	}

	public void onRowCancel(RowEditEvent event) {
		// FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car)
		// event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void addUser(){
		System.out.println("添加用户");
		
		User user=new User();
		user.setUserId(userId);
		user.setUsercode(usercode);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRoleId(roleId);
		user.setOrgId(orgId);
		user.setActive(active);
		userService.add(user);
		
		
		RequestContext rc = RequestContext.getCurrentInstance();
	    rc.execute("PF('dlg1').hide()");
	    initUserList();
	}
	
	/**
	 * addUserDialog表单
	 */
	private String userId;
	private String usercode;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String roleId;
	private String orgId;
	private String active;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}
