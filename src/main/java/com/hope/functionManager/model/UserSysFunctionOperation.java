package com.hope.functionManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//用户功能操作表
@Entity
//@Table(name = "USER_SYS_FUNCTION_OPERATION")
public class UserSysFunctionOperation {
	// 操作ID
	private String userOpeId;
	// 角色ID
	private String roleId;
	// 用户ID
	private String userId;
	// 功能ID
	private String sysFunId;
	// 操作权限
	private String opeAuth;

	@Id
	@Column(name = "USER_OPE_ID")
	public String getUserOpeId() {
		return userOpeId;
	}

	public void setUserOpeId(String userOpeId) {
		this.userOpeId = userOpeId;
	}

	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "SYS_FUN_ID")
	public String getSysFunId() {
		return sysFunId;
	}

	public void setSysFunId(String sysFunId) {
		this.sysFunId = sysFunId;
	}

	@Column(name = "OPE_AUTH")
	public String getOpeAuth() {
		return opeAuth;
	}

	public void setOpeAuth(String opeAuth) {
		this.opeAuth = opeAuth;
	}
}
