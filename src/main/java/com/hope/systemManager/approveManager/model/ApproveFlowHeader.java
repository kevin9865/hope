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
	private long flowHeaderId;
	private String flowName;
	private String orgId;
	private Date createtime;
	private String active;
	private String companyCode;
	private String processClassPath;
	private String entityClassPath;
	private String entityId;
	private String url;
	
	private List<ApproveFlowItem> approveFlowItems=new ArrayList<ApproveFlowItem>();
	
	
	@Column(name = "PROCESS_CLASSPATH")
	public String getProcessClassPath() {
		return processClassPath;
	}

	public void setProcessClassPath(String processClassPath) {
		this.processClassPath = processClassPath;
	}
	@Column(name = "ENTITY_CLASSPATH")
	public String getEntityClassPath() {
		return entityClassPath;
	}

	public void setEntityClassPath(String entityClassPath) {
		this.entityClassPath = entityClassPath;
	}
	@Column(name = "ENTITY_ID")
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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

	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Id
	@Column(name = "FLOW_HEADER_ID")
	public long getFlowHeaderId() {
		return flowHeaderId;
	}

	public void setFlowHeaderId(long flowHeaderId) {
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
