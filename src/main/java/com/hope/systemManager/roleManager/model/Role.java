package com.hope.systemManager.roleManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//角色表
@Entity
//@Table(name = "ROLE")
public class Role {
	// 角色ID
	private String roleId;
	// 角色名
	private String roleName;
	// 角色描述
	private String roleDesc;
	// 权限操作
	private String opeAuth;
	//公司代码
	private String companyCode;
	
	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Id
	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_DESC")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	@Column(name = "OPE_AUTH")
	public String getOpeAuth() {
		return opeAuth;
	}

	public void setOpeAuth(String opeAuth) {
		this.opeAuth = opeAuth;
	}
	
}
