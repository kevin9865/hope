package com.hope.systemManager.functionManager.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.functionManager.service.SysFunctionOperationService;
import com.hope.systemManager.functionManager.service.SysFunctionService;

public class SysFunctionAction {

	/************************************************ 系统功能 ******************************************************************/
	@PostConstruct
	public void init() {
		initSysFunctionList();
	}

	/**
	 * 初始化功能Table
	 */
	public void initSysFunctionList() {
		sysFunctions = sysFunctionService.sysFunctionQueryAll();
	}

	private SysFunctionService sysFunctionService;
	private List<SysFunction> sysFunctions;
	private List<SysFunction> filteredSysFunctions;
	private List<SysFunction> selectedSysFunctions;
	private long sysFidSelect;

	public long getSysFidSelect() {
		return sysFidSelect;
	}

	public void setSysFidSelect(long sysFidSelect) {
		this.sysFidSelect = sysFidSelect;
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

	/**
	 * 编辑功能Table
	 * 
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		try {
			SysFunction sysFunction = (SysFunction) event.getObject();
			sysFunctionService.update(sysFunction);

			initSysFunctionList();
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
			sysFidSelect = ((SysFunction) event.getObject()).getSysFid();
			initSysFunctionOperationList(sysFidSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加功能
	 */
	public void addSysFunction() {
		try {
			SysFunction sysFunction = new SysFunction();
			sysFunction.setSysFunId(sysFunIdForm);
			sysFunction.setSysFunName(sysFunNameForm);
			sysFunction.setLevelId(levelIdForm);
			sysFunction.setLevelGrade(levelGradeForm);
			sysFunction.setUrl(urlForm);
			sysFunction.setActive(activeForm);
			sysFunction.setSysFid(sysFunctionService.maxId());
			sysFunction.setCompanyCode(LoginAction.getCurrentUser().getCompanyCode());
			sysFunctionService.add(sysFunction);

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initSysFunctionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除功能
	 */
	public void deleteSysFunction() {
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

	/************************************************ 系统功能操作 ****************************************************************/
	/**
	 * 初始化功能操作Table
	 * 
	 * @param sysFunIdSelect
	 */
	public void initSysFunctionOperationList(long sysFidSelect) {
		SysFunctionOperation sysFunctionOperation = new SysFunctionOperation();
		sysFunctionOperation.setSysFid(sysFidSelect);
		sysFunctionOperations = sysFunctionOperationService
				.sysFunctionOperationQueryAll(sysFunctionOperation);

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

	/**
	 * 编辑功能操作Table
	 * 
	 * @param event
	 */
	public void onRowEditOpe(RowEditEvent event) {
		try {
			SysFunctionOperation sysFunctionOperation = (SysFunctionOperation) event
					.getObject();
			sysFunctionOperationService.update(sysFunctionOperation);

			initSysFunctionOperationList(sysFidSelect);
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
	public void addSysFunctionOperation() {
		try {
			SysFunctionOperation operation = new SysFunctionOperation();
			operation.setSysFid(sysFidOpeForm);
			operation.setSysFunOpeId(sysFunOpeIdOpeForm);
			operation.setOperation(operationForm);
			sysFunctionOperationService.add(operation);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg3').hide()");
			initSysFunctionOperationList(sysFidSelect);
			operationFormClear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除功能操作
	 */
	public void deleteSysFunctionOperation() {
		try {
			sysFunctionOperationService
					.deleteBatch(selectedSysFunctionOperations);
			initSysFunctionOperationList(sysFidSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空operationForm表单数据
	 */
	public void operationFormClear() {
		sysFidOpeForm = 0;
		sysFunOpeIdOpeForm = 0;
		operationForm = "";
	}

	/**
	 * 初始化operationForm表单数据
	 */
	public void initOperationForm() {
		sysFidOpeForm = sysFidSelect;
		sysFunOpeIdOpeForm = sysFunctionOperationService.maxId();
		operationForm = "";
	}

	/**
	 * 系统功能操作表单
	 */
	private long sysFidOpeForm;
	private int sysFunOpeIdOpeForm;
	private String operationForm;

	public long getSysFidOpeForm() {
		return sysFidOpeForm;
	}

	public void setSysFidOpeForm(long sysFidOpeForm) {
		this.sysFidOpeForm = sysFidOpeForm;
	}

	public int getSysFunOpeIdOpeForm() {
		return sysFunOpeIdOpeForm;
	}

	public void setSysFunOpeIdOpeForm(int sysFunOpeIdOpeForm) {
		this.sysFunOpeIdOpeForm = sysFunOpeIdOpeForm;
	}

	public String getOperationForm() {
		return operationForm;
	}

	public void setOperationForm(String operationForm) {
		this.operationForm = operationForm;
	}

}
