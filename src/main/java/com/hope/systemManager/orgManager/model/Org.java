package com.hope.systemManager.orgManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//组织结构表
@Entity
// @Table(name = "ORG")
public class Org {
	// 组织结构行ID
	private String orgLineId;
	// 公司名称
	private String companyName;
	// 组织名称
	private String orgName;
	// 组织结构ID
	private String orgId;
	// 层ID
	private String levelId;
	// 层级
	private int levelGrade;
	// 状态（是否启用）
	private String active;

	@Id
	@Column(name = "ORG_LINE_ID")
	public String getOrgLineId() {
		return orgLineId;
	}

	public void setOrgLineId(String orgLineId) {
		this.orgLineId = orgLineId;
	}

	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "ORG_NAME")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "LEVEL_ID")
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	@Column(name = "LEVEL_GRADE")
	public int getLevelGrade() {
		return levelGrade;
	}

	public void setLevelGrade(int levelGrade) {
		this.levelGrade = levelGrade;
	}

	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
