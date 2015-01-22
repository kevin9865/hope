package com.hope.systemManager.functionManager.action;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.functionManager.service.SysFunctionOperationService;

public class SysFunctionOperationAction {
	@PostConstruct
	public void init() {
		initSysFunctionOperationList();
	}
	public void initSysFunctionOperationList(){
		sysFunctionOperations=sysFunctionOperationService.sysFunctionOperationQueryAll();
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
	
	public void onRowEdit(RowEditEvent event) {
		try {
			SysFunctionOperation sysFunctionOperation = (SysFunctionOperation) event.getObject();
			sysFunctionOperationService.update(sysFunctionOperation);

			initSysFunctionOperationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowCancel(RowEditEvent event) {
		
	}
	
	public void onRowSelect(SelectEvent event) {
		
    }
	
	public void addSysFunctionOperation(){
	}
	
	public void deleteSysFunctionOperation(){
		try {
			sysFunctionOperationService.deleteBatch(selectedSysFunctionOperations);
			initSysFunctionOperationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
