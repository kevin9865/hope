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

	// public void userFuzzyQuery() {
	// User user = new User();
	// user.setUsercode(Tools.SpaceDisappear(this.usercode));
	// users = userService.userFuzzyQuery(user);
	// }

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

	public void addUser() {
		try {
			User user = new User();
			user.setUserId(userIdForm);
			user.setUsercode(usercodeForm);
			user.setUsername(usernameForm);
			user.setPassword(passwordForm);
			user.setEmail(emailForm);
			user.setPhone(phoneForm);
			user.setRoleId(roleIdForm);
			user.setOrgId(orgIdForm);
			user.setActive(activeForm);
			userService.add(user);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * addUserDialog表单
	 */
	private String userIdForm;
	private String usercodeForm;
	private String usernameForm;
	private String passwordForm;
	private String emailForm;
	private String phoneForm;
	private String roleIdForm;
	private String orgIdForm;
	private String activeForm;

	public String getUserIdForm() {
		return userIdForm;
	}

	public void setUserIdForm(String userIdForm) {
		this.userIdForm = userIdForm;
	}

	public String getUsercodeForm() {
		return usercodeForm;
	}

	public void setUsercodeForm(String usercodeForm) {
		this.usercodeForm = usercodeForm;
	}

	public String getUsernameForm() {
		return usernameForm;
	}

	public void setUsernameForm(String usernameForm) {
		this.usernameForm = usernameForm;
	}

	public String getPasswordForm() {
		return passwordForm;
	}

	public void setPasswordForm(String passwordForm) {
		this.passwordForm = passwordForm;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getPhoneForm() {
		return phoneForm;
	}

	public void setPhoneForm(String phoneForm) {
		this.phoneForm = phoneForm;
	}

	public String getRoleIdForm() {
		return roleIdForm;
	}

	public void setRoleIdForm(String roleIdForm) {
		this.roleIdForm = roleIdForm;
	}

	public String getOrgIdForm() {
		return orgIdForm;
	}

	public void setOrgIdForm(String orgIdForm) {
		this.orgIdForm = orgIdForm;
	}

	public String getActiveForm() {
		return activeForm;
	}

	public void setActiveForm(String activeForm) {
		this.activeForm = activeForm;
	}

}
