package com.hope.systemManager.orgManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//组织结构表
@Entity
//@Table(name = "ORG")
public class Org {
	// 组织结构ID
	private String orgId;
	// 层ID
	private String levelId;
	// 层级
	private int levelGrade;
	// 0层
	private String orgLevelZero;
	// 1层
	private String orgLevelOne;
	// 2层
	private String orgLevelTwo;
	// 3层
	private String orgLevelThree;
	// 4层
	private String orgLevelFour;
	// 5层
	private String orgLevelFive;
	// 6层
	private String orgLevelSix;
	// 7层
	private String orgLevelSeven;
	// 8层
	private String orgLevelEight;
	// 状态（是否启用）
	private String active;

	@Id
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

	@Column(name = "ORG_LEVEL_ZERO")
	public String getOrgLevelZero() {
		return orgLevelZero;
	}

	public void setOrgLevelZero(String orgLevelZero) {
		this.orgLevelZero = orgLevelZero;
	}

	@Column(name = "ORG_LEVEL_ONE")
	public String getOrgLevelOne() {
		return orgLevelOne;
	}

	public void setOrgLevelOne(String orgLevelOne) {
		this.orgLevelOne = orgLevelOne;
	}

	@Column(name = "ORG_LEVEL_TWO")
	public String getOrgLevelTwo() {
		return orgLevelTwo;
	}

	public void setOrgLevelTwo(String orgLevelTwo) {
		this.orgLevelTwo = orgLevelTwo;
	}

	@Column(name = "ORG_LEVEL_THREE")
	public String getOrgLevelThree() {
		return orgLevelThree;
	}

	public void setOrgLevelThree(String orgLevelThree) {
		this.orgLevelThree = orgLevelThree;
	}

	@Column(name = "ORG_LEVEL_FOUR")
	public String getOrgLevelFour() {
		return orgLevelFour;
	}

	public void setOrgLevelFour(String orgLevelFour) {
		this.orgLevelFour = orgLevelFour;
	}

	@Column(name = "ORG_LEVEL_FIVE")
	public String getOrgLevelFive() {
		return orgLevelFive;
	}

	public void setOrgLevelFive(String orgLevelFive) {
		this.orgLevelFive = orgLevelFive;
	}

	@Column(name = "ORG_LEVEL_SIX")
	public String getOrgLevelSix() {
		return orgLevelSix;
	}

	public void setOrgLevelSix(String orgLevelSix) {
		this.orgLevelSix = orgLevelSix;
	}

	@Column(name = "ORG_LEVEL_SEVEN")
	public String getOrgLevelSeven() {
		return orgLevelSeven;
	}

	public void setOrgLevelSeven(String orgLevelSeven) {
		this.orgLevelSeven = orgLevelSeven;
	}

	@Column(name = "ORG_LEVEL_EIGHT")
	public String getOrgLevelEight() {
		return orgLevelEight;
	}

	public void setOrgLevelEight(String orgLevelEight) {
		this.orgLevelEight = orgLevelEight;
	}

	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
