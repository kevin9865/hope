package com.hope.systemManager.orgManager.action;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.hope.systemManager.orgManager.model.Org;
import com.hope.systemManager.orgManager.service.OrgService;

public class OrgAction {
	@PostConstruct
	public void init() {
		initOrgList();
	}

	/**
	 * 初始化功能Table
	 */
	public void initOrgList() {
		orgs = orgService.orgQueryAll();
	}

	private OrgService orgService;
	private List<Org> orgs;
	private List<Org> filteredOrgs;
	private List<Org> selectedOrgs;
	
	public List<Org> getSelectedOrgs() {
		return selectedOrgs;
	}

	public void setSelectedOrgs(List<Org> selectedOrgs) {
		this.selectedOrgs = selectedOrgs;
	}

	public OrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public List<Org> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}

	public List<Org> getFilteredOrgs() {
		return filteredOrgs;
	}

	public void setFilteredOrgs(List<Org> filteredOrgs) {
		this.filteredOrgs = filteredOrgs;
	}
	
	/**
	 * 编辑功能Table
	 * 
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		try {
			Org org = (Org) event.getObject();
			orgService.update(org);

			initOrgList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭编辑功能Table
	 * 
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {

	}
	/**
	 * 初始化orgId
	 */
	public void initAddOrgDialog(){
		try {
			orgLineIdForm=orgService.maxOrgLineId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 增加组织结构
	 */
	public void addOrg(){
		try {
			Org org=new Org();
			org.setOrgLineId(orgLineIdForm);
			org.setCompanyName(companyNameForm);
			org.setOrgName(orgNameForm);
			org.setLevelId(levelIdForm);
			org.setLevelGrade(levelGradeForm);
			org.setOrgId(orgIdForm);
			org.setActive(activeForm);
			orgService.add(org);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initOrgList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 批量删除组织机构
	 */
	public void deleteOrg(){
		try {
			orgService.deleteBatch(selectedOrgs);
			initOrgList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * orgDialog表单
	 */
	private String orgLineIdForm;
	private String companyNameForm;
	private String orgNameForm;
	private String levelIdForm;
	private int levelGradeForm;
	private String orgIdForm;
	private String activeForm;

	public String getOrgLineIdForm() {
		return orgLineIdForm;
	}

	public void setOrgLineIdForm(String orgLineIdForm) {
		this.orgLineIdForm = orgLineIdForm;
	}

	public String getCompanyNameForm() {
		return companyNameForm;
	}

	public void setCompanyNameForm(String companyNameForm) {
		this.companyNameForm = companyNameForm;
	}

	public String getOrgNameForm() {
		return orgNameForm;
	}

	public void setOrgNameForm(String orgNameForm) {
		this.orgNameForm = orgNameForm;
	}

	public String getLevelIdForm() {
		return levelIdForm;
	}

	public void setLevelIdForm(String levelIdForm) {
		this.levelIdForm = levelIdForm;
	}

	public int getLevelGradeForm() {
		return levelGradeForm;
	}

	public void setLevelGradeForm(int levelGradeForm) {
		this.levelGradeForm = levelGradeForm;
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
