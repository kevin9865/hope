package com.hope.systemManager.userManager.action;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.util.Tools;

@ViewScoped
public class UserAction {
	
	@PostConstruct
	public void init(){
		System.out.println("用户查询初始化");
		initUserList();
	}
	
	public void initUserList(){
		users=userService.userQueryAll();
	}
	
	private UserService userService;
	private List<User> users;
	private List<User> filteredUsers;
	private String userCode;
	
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
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

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void userFuzzyQuery(){
		
		User user=new User();
		user.setUsercode(Tools.SpaceDisappear(this.userCode));
		System.out.println("usercode="+user.getUsercode());
		users=userService.userFuzzyQuery(user);
		System.out.println("模糊查询用户"+users.size());
	}
	
	public void onRowEdit(RowEditEvent event) {
		User user=(User)event.getObject();
		System.out.println("userCode="+user.getUsercode());
        //FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("email="+user.getEmail());
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initUserList();
    }
     
    public void onRowCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
