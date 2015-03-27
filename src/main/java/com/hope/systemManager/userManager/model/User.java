package com.hope.systemManager.userManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//用户表
@Entity
public class User {
	// 用户ID
	private String userId;
	// 用户名
	private String username;
	// 姓名
	private String name;
	// 密码
	private String password;
	// 邮箱
	private String email;
	// 电话
	private String phone;
	// 角色ID
	private String roleId;
	// 组织结构ID
	private String orgId;
	// 状态（是否启用）
	private String active;
	// 权限操作
	private String opeAuth;
	// 角色名称
	private String roleName;
	// 组织结构名称
	private String orgName;
	//公司名称
	private String companyName;
	//公司代码
	private String companyCode;
	
	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "ORG_NAME")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Id
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	@Column(name = "OPE_AUTH")
	public String getOpeAuth() {
		return opeAuth;
	}

	public void setOpeAuth(String opeAuth) {
		this.opeAuth = opeAuth;
	}
	
}
