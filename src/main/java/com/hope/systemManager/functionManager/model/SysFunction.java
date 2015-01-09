package com.hope.systemManager.functionManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//系统功能表
@Entity(name = "SYS_FUNCTION")
public class SysFunction {
	// 功能ID
	private String sysFunId;
	// 功能名
	private String sysFunName;
	// 层ID
	private String levelId;
	// 层级
	private int levelGrade;
	// 连接地址
	private String url;
	// 状态（是否启用）
	private String active;
	// 备注
	private String remark;

	@Id
	@Column(name = "SYS_FUN_ID")
	public String getSysFunId() {
		return sysFunId;
	}

	public void setSysFunId(String sysFunId) {
		this.sysFunId = sysFunId;
	}

	@Column(name = "SYS_FUN_NAME")
	public String getSysFunName() {
		return sysFunName;
	}

	public void setSysFunName(String sysFunName) {
		this.sysFunName = sysFunName;
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

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
