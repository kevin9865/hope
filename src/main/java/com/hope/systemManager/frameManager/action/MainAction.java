package com.hope.systemManager.frameManager.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.hope.systemManager.frameManager.form.TabViewForm;
import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.service.SysFunctionService;

public class MainAction implements Serializable {
	HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
	HttpSession httpSession = request.getSession();

	public MainAction() {
	}

	@PostConstruct
	public void init() {
		System.out.println("当前登录用户"
				+ LoginAction.getCurrentUser().getUsercode());
		initPanelMenu();
	}

	/**
	 * 初始化菜单栏
	 */
	public void initPanelMenu() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if (null == LoginAction.getCurrentUser().getOpeAuth())
				return;
			Map<String, String> opeAuthMap = mapper.readValue(LoginAction
					.getCurrentUser().getOpeAuth(), Map.class);

			List<String> keyList = new ArrayList<String>();
			Set keys = opeAuthMap.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String[] k = key.split("-");
				if (!keyList.contains(k[0])) {
					keyList.add(k[0]);
				}
			}

			panelMenu = new DefaultMenuModel();
			List<SysFunction> list = sysFunctionService
					.sysFunctionQuery(keyList);
			// List<SysFunction> list =
			// sysFunctionService.sysFunctionQueryAll();
			DefaultSubMenu subMenu = null;
			DefaultSubMenu subMenu1 = null;
			DefaultMenuItem menuItem1 = null;
			DefaultMenuItem menuItem2 = null;

			int addMenuBefore = 0;
			int addmenuAfter = 0;
			for (SysFunction sf : list) {
				if (sf.getLevelGrade() == 1) {
					subMenu = new DefaultSubMenu();
					subMenu.setLabel(sf.getSysFunName());
					subMenu.setStyleClass("ct-fontsize");
					addmenuAfter++;
				} else if (sf.getLevelGrade() == 2) {
					if (null == sf.getUrl() || sf.getUrl().equals("")) {
						subMenu1 = new DefaultSubMenu();
						subMenu1.setLabel(sf.getSysFunName());
						subMenu1.setStyleClass("ct-fontsize");
						subMenu.addElement(subMenu1);
					} else {
						menuItem1 = new DefaultMenuItem();
						menuItem1.setValue(sf.getSysFunName());
						menuItem1.setCommand("#{mainAction.addTab('"
								+ menuItem1.getValue() + "','" + sf.getUrl()
								+ "')}");
						menuItem1.setUpdate(":tabView");
						menuItem1.setStyleClass("ct-fontsize");
						menuItem1.setAjax(true);
						subMenu.addElement(menuItem1);
					}
				} else if (sf.getLevelGrade() == 3) {
					menuItem2 = new DefaultMenuItem();
					menuItem2.setValue(sf.getSysFunName());
					menuItem2.setAjax(true);
					menuItem2.setCommand("#{mainAction.addTab('"
							+ menuItem2.getValue() + "','" + sf.getUrl()
							+ "')}");
					menuItem2.setUpdate(":tabView");
					menuItem2.setStyleClass("ct-fontsize");
					subMenu1.addElement(menuItem2);
				}
				if (addMenuBefore != addmenuAfter) {
					panelMenu.getElements().add(subMenu);
					addMenuBefore++;
				}
			}

			tabs = new ArrayList<TabViewForm>();
			// TabViewForm tabViewForm=new TabViewForm();
			// tabViewForm.setTitle("ceshi");
			// tabViewForm.setUrl("/systemManager/userManager/user_query.xhtml");
			// tabs.add(tabViewForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<TabViewForm> tabs;
	private SysFunctionService sysFunctionService;
	private MenuModel panelMenu;
	private TabView tabView;
	private int tabIdTemp;

	public List<TabViewForm> getTabs() {
		return tabs;
	}

	public int getTabIdTemp() {
		return tabIdTemp;
	}

	public void setTabIdTemp(int tabIdTemp) {
		this.tabIdTemp = tabIdTemp;
	}

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

	/**
	 * tab增加
	 * 
	 * @param value
	 * @param url
	 */
	public void addTab(String value, String url) {
		tabIdTemp = tabs.size();
		for (TabViewForm tv : tabs) {
			if (tv.getTitle().equals(value)) {
				tabIdTemp = tv.getIndex();
				return;
			}
		}
		TabViewForm tabViewForm = new TabViewForm();
		tabViewForm.setTitle(value);
		tabViewForm.setUrl(url);
		tabViewForm.setIndex(tabIdTemp);
		tabs.add(tabViewForm);
	}

	/**
	 * tab变更事件
	 * 
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event) {

	}

	/**
	 * tab关闭事件
	 * 
	 * @param event
	 */
	public void onTabClose(TabCloseEvent event) {
		for (TabViewForm tv : tabs) {
			if (tv.getTitle().equals(event.getTab().getTitle())) {
				tabs.remove(tv);
			}
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logout() {
		System.out.println("系统退出");
		httpSession.invalidate();
		return "logout";
	}
}
