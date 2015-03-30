package com.hope.systemManager.functionManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//系统功能操作表
@Entity(name = "SYS_FUNCTION_OPERATION")
public class SysFunctionOperation {
	// 功能操作ID
	private int sysFunOpeId;
	// 功能ID
	private long sysFid;
	// 操作名
	private String operation;

	@Id
	@Column(name = "SYS_FUN_OPE_ID")
	public int getSysFunOpeId() {
		return sysFunOpeId;
	}

	public void setSysFunOpeId(int sysFunOpeId) {
		this.sysFunOpeId = sysFunOpeId;
	}

	@Column(name = "SYS_FID")
	public long getSysFid() {
		return sysFid;
	}

	public void setSysFid(long sysFid) {
		this.sysFid = sysFid;
	}

	@Column(name = "OPERATION")
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
