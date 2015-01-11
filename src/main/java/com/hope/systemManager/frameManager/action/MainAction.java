package com.hope.systemManager.frameManager.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;

import com.hope.systemManager.frameManager.form.TabViewForm;
import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.service.SysFunctionService;

@ViewScoped
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
		System.out.println("panelMenu="+panelMenu);
		
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
					menuItem1.setCommand("#{MainAction.addTab}");
					//menuItem1.setUrl(sf.getUrl());
					menuItem1.setUpdate("@form");
					menuItem2.setAjax(true);
					subMenu.addElement(menuItem1);
				}
			}else if (sf.getLevelGrade()==3) {
				menuItem2=new DefaultMenuItem();
				menuItem2.setValue(sf.getSysFunName());
				//menuItem2.setUrl(sf.getUrl());
				menuItem2.setAjax(true);
				menuItem2.setCommand("#{MainAction.addTab}");
				menuItem2.setUpdate("@form");
				subMenu1.addElement(menuItem2);
			}
			if(addMenuBefore!=addmenuAfter){
				panelMenu.getElements().add(subMenu);
				addMenuBefore++;
			}
		}
		
//		FacesContext fc = FacesContext.getCurrentInstance();
////        tabView = (TabView) fc.getApplication().createComponent(
////                "org.primefaces.component.TabView");
//		tabView=new TabView();
//		Tab tab=new Tab();
//		tab.setTitle("测试");
//		tab.setTitleStyleClass("ct-fontsize");
//		tab.setClosable(true);
//		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
//		try {
//			faceletContext.includeFacelet(tab, "/systemManager/userManager/user_query.xhtml");
//		} catch (FaceletException e) {
//			e.printStackTrace();
//		} catch (FacesException e) {
//			e.printStackTrace();
//		} catch (ELException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		tabView.getChildren().add(tab);
		tabs = new ArrayList<TabViewForm>();
		TabViewForm tabViewForm=new TabViewForm();
		tabViewForm.setTitle("ceshi");
		tabViewForm.setUrl("/systemManager/userManager/user_query.xhtml");
		tabs.add(tabViewForm);
	}
	
	private List<TabViewForm> tabs;
	
	public List<TabViewForm> getTabs() {
//		System.out.println("tabs="+tabs);
//		if(tabs==null){
//			tabs = new ArrayList<TabViewForm>();
//			TabViewForm tabViewForm=new TabViewForm();
//			tabViewForm.setTitle("ceshi");
//			tabViewForm.setUrl("/systemManager/userManager/user_query.xhtml");
//			tabs.add(tabViewForm);
//		}
		
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
//		System.out.println("panelmenu="+panelMenu);
//		if(panelMenu==null){
//			initPanelMenu();
//		}
		
		
	}
	
	public void addTab(){
		System.out.println("panelmenu="+panelMenu);
		System.out.println("tab增加"+tabView);
//		Tab tab=new Tab();
//		tab.setTitle("测试");
//		tab.setTitleStyleClass("ct-fontsize");
//		tab.setClosable(true);
//		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
//		try {
//			faceletContext.includeFacelet(tab, "/systemManager/userManager/user_query.xhtml");
//		} catch (FaceletException e) {
//			e.printStackTrace();
//		} catch (FacesException e) {
//			e.printStackTrace();
//		} catch (ELException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		tabView.getChildren().add(tab);
		TabViewForm tabViewForm=new TabViewForm();
		tabViewForm.setTitle("ceshi");
		tabViewForm.setUrl("/systemManager/userManager/user_query.xhtml");
		tabs.add(tabViewForm);
		
//		RequestContext context = RequestContext.getCurrentInstance();  
//		  context.update("tabview");
	}

	public void onTabChange(TabChangeEvent event) {
		
    }
	public void test(){
		
	}
	
	public String logout(){
		System.out.println("系统退出");
		httpSession.invalidate();
		return "logout";
	}
}
