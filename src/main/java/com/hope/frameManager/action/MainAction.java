package com.hope.frameManager.action;

import java.io.Serializable;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

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
	
	public MenuModel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(MenuModel panelMenu) {
		this.panelMenu = panelMenu;
	}

	public String logout(){
		System.out.println("系统退出");
		return "logout";
	}
}
