package com.hope.systemManager.userManager.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.hope.systemManager.frameManager.action.Car;
import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.util.Tools;

public class UserAction implements Serializable{
	
	@PostConstruct
	public void init(){
		System.out.println("用户查询初始化");
		initUserList();
		
	}
	
	public void initUserList(){
		users=userService.userQueryAll();
		
//		 List<User> list = new ArrayList<User>();
//	        for(int i = 0 ; i < 10 ; i++) {
//	        	User user=new User();
//	        	user.setUsercode("a"+i);
//	            list.add(user);
//	        }
//	        users=list;
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
	
	public void userFuzzyQuery(){
		User user=new User();
		user.setUsercode(Tools.SpaceDisappear(this.userCode));
		users=userService.userFuzzyQuery(user);
		System.out.println("模糊查询用户"+users.size());
	}
	
	public void onRowEdit(RowEditEvent event) {
		User user=(User)event.getObject();
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
