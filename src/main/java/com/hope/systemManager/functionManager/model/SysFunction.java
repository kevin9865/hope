package com.hope.systemManager.functionManager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	//id
	private long sysFid;
	//公司代码
	private String companyCode;
	
	private List<SysFunctionOperation> sysFunctionOperations=new ArrayList<SysFunctionOperation>();
	
	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Id
	@Column(name = "SYS_FID")
	public long getSysFid() {
		return sysFid;
	}

	public void setSysFid(long sysFid) {
		this.sysFid = sysFid;
	}

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

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "SYS_FID")
	@OrderBy(value="sysFunOpeId")
	public List<SysFunctionOperation> getSysFunctionOperations() {
		return sysFunctionOperations;
	}

	public void setSysFunctionOperations(
			List<SysFunctionOperation> sysFunctionOperations) {
		this.sysFunctionOperations = sysFunctionOperations;
	}
	
	
	
}
