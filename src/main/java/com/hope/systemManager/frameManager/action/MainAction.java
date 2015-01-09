package com.hope.systemManager.frameManager.action;

import java.io.Serializable;
import java.util.List;

import org.hibernate.mapping.Table;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.service.SysFunctionService;

public class MainAction implements Serializable{
	public MainAction(){
			
	}
	
	public void initPanelMenu(){
		panelMenu =new DefaultMenuModel();
		
		List<SysFunction> list=sysFunctionService.sysFunctionQueryAll();
		
		DefaultSubMenu subMenu=null;
		DefaultSubMenu subMenu1=null;
		DefaultMenuItem menuItem1=null;
		DefaultMenuItem menuItem2=null;
		
		int addMenuBefore=0;
		int addmenuAfter=0;
		for(SysFunction sf:list){
			if(sf.getLevelGrade()==1){
				subMenu=new DefaultSubMenu();
				subMenu.setLabel(sf.getSysFunName());
				subMenu.setStyle("font-size:15px;");
				addmenuAfter++;
			}else if (sf.getLevelGrade()==2) {
				if(null==sf.getUrl()||sf.getUrl().equals("")){
					subMenu1=new DefaultSubMenu();
					subMenu1.setLabel(sf.getSysFunName());
					subMenu1.setStyle("font-size:15px;");
					subMenu.addElement(subMenu1);
				}else {
					menuItem1=new DefaultMenuItem();
					menuItem1.setValue(sf.getSysFunName());
					menuItem1.setUrl(sf.getUrl());
					subMenu.addElement(menuItem1);
				}
			}else if (sf.getLevelGrade()==3) {
				menuItem2=new DefaultMenuItem();
				menuItem2.setValue(sf.getSysFunName());
				menuItem2.setUrl(sf.getUrl());
				subMenu1.addElement(menuItem2);
			}
			if(addMenuBefore!=addmenuAfter){
				panelMenu.getElements().add(subMenu);
				addMenuBefore++;
			}
		}
		tabView=new TabView();
		Tab tab=new Tab();
		tab.setTitle("ceshi");
		tabView.getChildren().add(tab);
		
	}
	private SysFunctionService sysFunctionService;
	private MenuModel panelMenu;
	private TabView tabView;

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

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
		
		initPanelMenu();
	}

	public String logout(){
		System.out.println("系统退出");
		return "logout";
	}
}