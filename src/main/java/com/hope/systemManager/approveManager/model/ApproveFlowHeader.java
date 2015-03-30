package com.hope.systemManager.approveManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "APPROVE_FLOW_HEADER")
public class ApproveFlowHeader {
	private int flowHeaderId;
	private String flowName;
	private String orgId;
	private Date createtime;
	private String active;
	private String companyCode;
	
	private List<ApproveFlowItem> approveFlowItems=new ArrayList<ApproveFlowItem>();
	
	@Column(name = "ACTIVE")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Id
	@Column(name = "FLOW_HEADER_ID")
	public int getFlowHeaderId() {
		return flowHeaderId;
	}

	public void setFlowHeaderId(int flowHeaderId) {
		this.flowHeaderId = flowHeaderId;
	}
	@Column(name = "FLOW_NAME")
	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="FLOW_HEADER_ID")
	@OrderBy(value="nodeIndex")
	public List<ApproveFlowItem> getApproveFlowItems() {
		return approveFlowItems;
	}

	public void setApproveFlowItems(List<ApproveFlowItem> approveFlowItems) {
		this.approveFlowItems = approveFlowItems;
	}

}
