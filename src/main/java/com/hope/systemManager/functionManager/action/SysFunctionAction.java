package com.hope.systemManager.functionManager.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.functionManager.service.SysFunctionOperationService;
import com.hope.systemManager.functionManager.service.SysFunctionService;

public class SysFunctionAction {
	
	@PostConstruct
	public void init() {
		initSysFunctionList();
	}
	public void initSysFunctionList(){
		sysFunctions=sysFunctionService.sysFunctionQueryAll();
	}
	
	private SysFunctionService sysFunctionService;
	private List<SysFunction> sysFunctions;
	private List<SysFunction> filteredSysFunctions;
	private List<SysFunction> selectedSysFunctions;
	private String sysFunIdSelect;

	public String getSysFunIdSelect() {
		return sysFunIdSelect;
	}
	public void setSysFunIdSelect(String sysFunIdSelect) {
		this.sysFunIdSelect = sysFunIdSelect;
	}
	public List<SysFunction> getSelectedSysFunctions() {
		return selectedSysFunctions;
	}
	public void setSelectedSysFunctions(List<SysFunction> selectedSysFunctions) {
		this.selectedSysFunctions = selectedSysFunctions;
	}
	public List<SysFunction> getSysFunctions() {
		return sysFunctions;
	}
	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}
	public List<SysFunction> getFilteredSysFunctions() {
		return filteredSysFunctions;
	}
	public void setFilteredSysFunctions(List<SysFunction> filteredSysFunctions) {
		this.filteredSysFunctions = filteredSysFunctions;
	}
	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}
	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}
	
	public void onRowEdit(RowEditEvent event) {
		try {
			SysFunction sysFunction = (SysFunction) event.getObject();
			sysFunctionService.update(sysFunction);

			initSysFunctionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowCancel(RowEditEvent event) {
		
	}
	
	public void onRowSelect(SelectEvent event) {
		try {
			sysFunIdSelect=((SysFunction)event.getObject()).getSysFunId();
			initSysFunctionOperationList(sysFunIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void addSysFunction(){
		try {
			SysFunction sysFunction=new SysFunction();
			sysFunction.setSysFunId(sysFunIdForm);
			sysFunction.setSysFunName(sysFunNameForm);
			sysFunction.setLevelId(levelIdForm);
			sysFunction.setLevelGrade(levelGradeForm);
			sysFunction.setUrl(urlForm);
			sysFunction.setActive(activeForm);
			sysFunctionService.add(sysFunction);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initSysFunctionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSysFunction(){
		try {
			sysFunctionService.deleteBatch(selectedSysFunctions);
			initSysFunctionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 系统功能表单
	 */
	private String sysFunIdForm;
	private String sysFunNameForm;
	private String levelIdForm;
	private int levelGradeForm;
	private String urlForm;
	private String activeForm;

	public String getSysFunIdForm() {
		return sysFunIdForm;
	}
	public void setSysFunIdForm(String sysFunIdForm) {
		this.sysFunIdForm = sysFunIdForm;
	}
	public String getSysFunNameForm() {
		return sysFunNameForm;
	}
	public void setSysFunNameForm(String sysFunNameForm) {
		this.sysFunNameForm = sysFunNameForm;
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
	public String getUrlForm() {
		return urlForm;
	}
	public void setUrlForm(String urlForm) {
		this.urlForm = urlForm;
	}
	public String getActiveForm() {
		return activeForm;
	}
	public void setActiveForm(String activeForm) {
		this.activeForm = activeForm;
	}
	
	
	/************************************************系统功能操作****************************************************************/
	public void initSysFunctionOperationList(String sysFunIdSelect){
		SysFunctionOperation sysFunctionOperation=new SysFunctionOperation();
		sysFunctionOperation.setSysFunId(sysFunIdSelect);
		sysFunctionOperations=sysFunctionOperationService.sysFunctionOperationQueryAll(sysFunctionOperation);
	}
	
	private SysFunctionOperationService sysFunctionOperationService;
	private List<SysFunctionOperation> sysFunctionOperations;
	private List<SysFunctionOperation> filteredSysFunctionOperations;
	private List<SysFunctionOperation> selectedSysFunctionOperations;

	public List<SysFunctionOperation> getFilteredSysFunctionOperations() {
		return filteredSysFunctionOperations;
	}
	public void setFilteredSysFunctionOperations(
			List<SysFunctionOperation> filteredSysFunctionOperations) {
		this.filteredSysFunctionOperations = filteredSysFunctionOperations;
	}
	public List<SysFunctionOperation> getSelectedSysFunctionOperations() {
		return selectedSysFunctionOperations;
	}
	public void setSelectedSysFunctionOperations(
			List<SysFunctionOperation> selectedSysFunctionOperations) {
		this.selectedSysFunctionOperations = selectedSysFunctionOperations;
	}
	public List<SysFunctionOperation> getSysFunctionOperations() {
		return sysFunctionOperations;
	}
	public void setSysFunctionOperations(
			List<SysFunctionOperation> sysFunctionOperations) {
		this.sysFunctionOperations = sysFunctionOperations;
	}
	public SysFunctionOperationService getSysFunctionOperationService() {
		return sysFunctionOperationService;
	}
	public void setSysFunctionOperationService(
			SysFunctionOperationService sysFunctionOperationService) {
		this.sysFunctionOperationService = sysFunctionOperationService;
	}
	
	public void onRowEditOpe(RowEditEvent event) {
		try {
			SysFunctionOperation sysFunctionOperation = (SysFunctionOperation) event.getObject();
			sysFunctionOperationService.update(sysFunctionOperation);

			initSysFunctionOperationList(sysFunIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowCancelOpe(RowEditEvent event) {
		
	}
	
	public void onRowSelectOpe(SelectEvent event) {
		
    }
	
	public void addSysFunctionOperation(){
	}
	
	public void deleteSysFunctionOperation(){
		try {
			sysFunctionOperationService.deleteBatch(selectedSysFunctionOperations);
			initSysFunctionOperationList(sysFunIdSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
