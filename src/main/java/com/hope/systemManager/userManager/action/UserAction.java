package com.hope.systemManager.userManager.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.event.RowEditEvent;

import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;

public class UserAction {
	
	@PostConstruct
	public void init(){
		initUserList();
	}
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void initUserList(){
		users=userService.userQueryAll();
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
