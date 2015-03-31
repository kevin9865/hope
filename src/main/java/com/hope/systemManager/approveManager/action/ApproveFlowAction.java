package com.hope.systemManager.approveManager.action;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.net.AprEndpoint;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;
import com.hope.systemManager.approveManager.service.ApproveFlowPersonService;
import com.hope.systemManager.approveManager.service.ApproveFlowService;
import com.hope.systemManager.frameManager.action.LoginAction;

public class ApproveFlowAction {
	/************************************************审批流抬头 ******************************************************************/
	@PostConstruct
	public void init(){
		try {
			approveFlowHeaders=approveFlowService.approveFlowQueryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ApproveFlowService approveFlowService;
	private List<ApproveFlowHeader> approveFlowHeaders;
	private List<ApproveFlowHeader> filteredApproveFlowHeaders;
	private List<ApproveFlowHeader> selectedApproveFlowHeaders;
	private long flowHeaderIdSelect;

	public List<ApproveFlowHeader> getApproveFlowHeaders() {
		return approveFlowHeaders;
	}

	public void setApproveFlowHeaders(List<ApproveFlowHeader> approveFlowHeaders) {
		this.approveFlowHeaders = approveFlowHeaders;
	}

	public List<ApproveFlowHeader> getFilteredApproveFlowHeaders() {
		return filteredApproveFlowHeaders;
	}

	public void setFilteredApproveFlowHeaders(
			List<ApproveFlowHeader> filteredApproveFlowHeaders) {
		this.filteredApproveFlowHeaders = filteredApproveFlowHeaders;
	}

	public List<ApproveFlowHeader> getSelectedApproveFlowHeaders() {
		return selectedApproveFlowHeaders;
	}

	public void setSelectedApproveFlowHeaders(
			List<ApproveFlowHeader> selectedApproveFlowHeaders) {
		this.selectedApproveFlowHeaders = selectedApproveFlowHeaders;
	}

	public long getFlowHeaderIdSelect() {
		return flowHeaderIdSelect;
	}

	public void setFlowHeaderIdSelect(long flowHeaderIdSelect) {
		this.flowHeaderIdSelect = flowHeaderIdSelect;
	}

	public ApproveFlowService getApproveFlowService() {
		return approveFlowService;
	}

	public void setApproveFlowService(ApproveFlowService approveFlowService) {
		this.approveFlowService = approveFlowService;
	}
	
	/**
	 * 编辑功能Table
	 * 
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		try {
			ApproveFlowHeader approveFlowHeader=(ApproveFlowHeader) event.getObject();
			approveFlowService.update(approveFlowHeader);
			init();
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
	 * 选择功能Table
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			flowHeaderIdSelect = ((ApproveFlowHeader) event.getObject()).getFlowHeaderId();
			initApproveFlowPersonList(flowHeaderIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加功能
	 */
	public void add() {
		try {
			ApproveFlowHeader approveFlowHeader=new ApproveFlowHeader();
			approveFlowHeader.setFlowHeaderId(approveFlowService.maxId());
			approveFlowHeader.setOrgId(orgIdForm);
			approveFlowHeader.setCompanyCode(LoginAction.getCurrentUser().getCompanyCode());
			approveFlowHeader.setCreatetime(new Date());
			approveFlowHeader.setFlowName(flowNameForm);
			approveFlowHeader.setActive(activeForm);
			approveFlowService.add(approveFlowHeader);
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除功能
	 */
	public void delete() {
		try {
			approveFlowService.deleteBatch(selectedApproveFlowHeaders);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 审批流抬头表单
	 */
	private long flowHeaderIdForm;
	private String flowNameForm;
	private String orgIdForm;
	private String companyCodeForm;
	private String activeForm;

	public long getFlowHeaderIdForm() {
		return flowHeaderIdForm;
	}

	public void setFlowHeaderIdForm(long flowHeaderIdForm) {
		this.flowHeaderIdForm = flowHeaderIdForm;
	}

	public String getFlowNameForm() {
		return flowNameForm;
	}

	public void setFlowNameForm(String flowNameForm) {
		this.flowNameForm = flowNameForm;
	}

	public String getOrgIdForm() {
		return orgIdForm;
	}

	public void setOrgIdForm(String orgIdForm) {
		this.orgIdForm = orgIdForm;
	}

	public String getCompanyCodeForm() {
		return companyCodeForm;
	}

	public void setCompanyCodeForm(String companyCodeForm) {
		this.companyCodeForm = companyCodeForm;
	}

	public String getActiveForm() {
		return activeForm;
	}

	public void setActiveForm(String activeForm) {
		this.activeForm = activeForm;
	}
	
	/************************************************审批流行项******************************************************************/
	
	public void initApproveFlowPersonList(long flowHeaderIdSelect){
		ApproveFlowItem approveFlowItem=new ApproveFlowItem();
		approveFlowItem.setFlowHeaderId(flowHeaderIdSelect);
		approveFlowItems=approveFlowPersonService.queryAll(approveFlowItem);
	}
	
	private ApproveFlowPersonService approveFlowPersonService;
	private List<ApproveFlowItem> approveFlowItems;
	private List<ApproveFlowItem> filteredApproveFlowItems;
	private List<ApproveFlowItem> selectedApproveFlowItems;

	public ApproveFlowPersonService getApproveFlowPersonService() {
		return approveFlowPersonService;
	}

	public void setApproveFlowPersonService(
			ApproveFlowPersonService approveFlowPersonService) {
		this.approveFlowPersonService = approveFlowPersonService;
	}

	public List<ApproveFlowItem> getApproveFlowItems() {
		return approveFlowItems;
	}

	public void setApproveFlowItems(List<ApproveFlowItem> approveFlowItems) {
		this.approveFlowItems = approveFlowItems;
	}

	public List<ApproveFlowItem> getFilteredApproveFlowItems() {
		return filteredApproveFlowItems;
	}

	public void setFilteredApproveFlowItems(
			List<ApproveFlowItem> filteredApproveFlowItems) {
		this.filteredApproveFlowItems = filteredApproveFlowItems;
	}

	public List<ApproveFlowItem> getSelectedApproveFlowItems() {
		return selectedApproveFlowItems;
	}

	public void setSelectedApproveFlowItems(
			List<ApproveFlowItem> selectedApproveFlowItems) {
		this.selectedApproveFlowItems = selectedApproveFlowItems;
	}
	
	/**
	 * 编辑功能操作Table
	 * 
	 * @param event
	 */
	public void onRowEditOpe(RowEditEvent event) {
		try {
			ApproveFlowItem approveFlowItem=(ApproveFlowItem) event.getObject();
			approveFlowPersonService.update(approveFlowItem);
			initApproveFlowPersonList(flowHeaderIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭编辑功能操作Table
	 * 
	 * @param event
	 */
	public void onRowCancelOpe(RowEditEvent event) {

	}

	/**
	 * 增加功能操作
	 */
	public void addFlowPerson() {
		try {
			ApproveFlowItem approveFlowItem=new ApproveFlowItem();
			approveFlowItem.setFlowHeaderId(flowHeaderIdSelect);
			approveFlowItem.setFlowItemId(approveFlowService.maxId());
			approveFlowItem.setNodeName(nodeNameForm);
			approveFlowItem.setNodeIndex(nodeIndexForm);
			approveFlowItem.setConditions(conditionsForm);
			approveFlowItem.setCheckScript(checkScriptForm);
			approveFlowItem.setUsername(usernameForm);
			
			approveFlowPersonService.add(approveFlowItem);
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg3').hide()");
			initApproveFlowPersonList(flowHeaderIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除功能操作
	 */
	public void deleteApproveFlowPerson() {
		try {
			approveFlowPersonService.deleteBatch(selectedApproveFlowItems);
			initApproveFlowPersonList(flowHeaderIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化operationForm表单数据
	 */
	public void initFlowPersonForm() {
		nodeIndexForm=0;
		nodeNameForm="";
		conditionsForm="";
		checkScriptForm="";
		usernameForm="";
	}
	
	/**
	 * 审批流行项表单
	 */
	private int nodeIndexForm;
	private String nodeNameForm;
	private String conditionsForm;
	private String checkScriptForm;
	private String usernameForm;

	public int getNodeIndexForm() {
		return nodeIndexForm;
	}

	public void setNodeIndexForm(int nodeIndexForm) {
		this.nodeIndexForm = nodeIndexForm;
	}

	public String getNodeNameForm() {
		return nodeNameForm;
	}

	public void setNodeNameForm(String nodeNameForm) {
		this.nodeNameForm = nodeNameForm;
	}

	public String getConditionsForm() {
		return conditionsForm;
	}

	public void setConditionsForm(String conditionsForm) {
		this.conditionsForm = conditionsForm;
	}

	public String getCheckScriptForm() {
		return checkScriptForm;
	}

	public void setCheckScriptForm(String checkScriptForm) {
		this.checkScriptForm = checkScriptForm;
	}

	public String getUsernameForm() {
		return usernameForm;
	}

	public void setUsernameForm(String usernameForm) {
		this.usernameForm = usernameForm;
	}
	
}
