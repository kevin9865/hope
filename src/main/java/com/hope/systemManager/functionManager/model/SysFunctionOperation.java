package com.hope.systemManager.functionManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//系统功能操作表
@Entity(name = "SYS_FUNCTION_OPERATION")
public class SysFunctionOperation {
	// 功能操作ID
	private String sysFunOpeId;
	// 功能ID
	private String sysFunId;
	// 操作名
	private String operation;

	@Id
	@Column(name = "SYS_FUN_OPE_ID")
	public String getSysFunOpeId() {
		return sysFunOpeId;
	}

	public void setSysFunOpeId(String sysFunOpeId) {
		this.sysFunOpeId = sysFunOpeId;
	}

	@Column(name = "SYS_FUN_ID")
	public String getSysFunId() {
		return sysFunId;
	}

	public void setSysFunId(String sysFunId) {
		this.sysFunId = sysFunId;
	}

	@Column(name = "OPERATION")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
