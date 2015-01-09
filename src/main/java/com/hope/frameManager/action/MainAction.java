package com.hope.frameManager.action;

import java.io.Serializable;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.hope.functionManager.service.SysFunctionService;

public class MainAction implements Serializable{
	public MainAction(){
		initPanelMenu();	
	}
	
	public void initPanelMenu(){
		panelMenu =new DefaultMenuModel();
		
		for (int i = 0; i < 7; i++) {
			DefaultSubMenu subMenu=new DefaultSubMenu();
			subMenu.setLabel("测试1");
			subMenu.setStyle("font-size:15px;");
			DefaultMenuItem menuItem=new DefaultMenuItem();
			menuItem.setValue("name");
			subMenu.addElement(menuItem);
			
			panelMenu.getElements().add(subMenu);
		}
	}
	
	private MenuModel panelMenu;
	private SysFunctionService sysFunctionService;
	
	public MenuModel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(MenuModel panelMenu) {
		this.panelMenu = panelMenu;
	}
	
	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public String logout(){
		System.out.println("系统退出");
		return "logout";
	}
}
