package com.hope.systemManager.frameManager.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;

import com.hope.systemManager.frameManager.form.TabViewForm;
import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.service.SysFunctionService;

public class MainAction implements Serializable{
	HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
	HttpSession httpSession = request.getSession();
	
	public MainAction(){
	}
	
	@PostConstruct
	public void init(){
		initPanelMenu();
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
					menuItem1.setCommand("#{MainAction.addTab('"+menuItem1.getValue()+"','"+sf.getUrl()+"')}");
					//menuItem1.setUrl(sf.getUrl());
					menuItem1.setUpdate(":form:tabView");
					menuItem1.setStyle("font-size:15px;");
					menuItem2.setAjax(true);
					subMenu.addElement(menuItem1);
				}
			}else if (sf.getLevelGrade()==3) {
				menuItem2=new DefaultMenuItem();
				menuItem2.setValue(sf.getSysFunName());
				//menuItem2.setUrl(sf.getUrl());
				menuItem2.setAjax(true);
				menuItem2.setCommand("#{MainAction.addTab('"+menuItem2.getValue()+"','"+sf.getUrl()+"')}");
				menuItem2.setUpdate(":tabView");
				menuItem2.setStyle("font-size:15px;");
				subMenu1.addElement(menuItem2);
			}
			if(addMenuBefore!=addmenuAfter){
				panelMenu.getElements().add(subMenu);
				addMenuBefore++;
			}
		}

		tabs = new ArrayList<TabViewForm>();
//		TabViewForm tabViewForm=new TabViewForm();
//		tabViewForm.setTitle("ceshi");
//		tabViewForm.setUrl("/systemManager/userManager/user_query.xhtml");
//		tabs.add(tabViewForm);
	}
	
	private List<TabViewForm> tabs;
	
	public List<TabViewForm> getTabs() {
		return tabs;
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
	}
	
	public void addTab(String value,String url){
		for(TabViewForm tv:tabs){
        	if(tv.getTitle().equals(value)){
        		return;
        	}
        }
		TabViewForm tabViewForm=new TabViewForm();
		tabViewForm.setTitle(value);
		tabViewForm.setUrl(url);
		tabs.add(tabViewForm);
	}

	public void onTabChange(TabChangeEvent event) {
//		FacesMessage msg = new FacesMessage("Tab Change", "Change tab: " + event.getTab().getTitle());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onTabClose(TabCloseEvent event){
//		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        for(TabViewForm tv:tabs){
        	if(tv.getTitle().equals(event.getTab().getTitle())){
        		tabs.remove(tv);
        	}
        }
	}
	
	public String logout(){
		System.out.println("系统退出");
		httpSession.invalidate();
		return "logout";
	}
}
