package com.hope.systemManager.approveManager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "APPROVE_FLOW_ITEM")
public class ApproveFlowItem {
//	private ApproveFlowHeader approveFlowHeader;
	private long flowItemId;
	private String nodeName;
	private int nodeIndex;
	private String conditions;
	private String checkScript;
	private String username;
	private String name;
	private long flowHeaderId;
	
	
	@Column(name = "FLOW_HEADER_ID")
	public long getFlowHeaderId() {
		return flowHeaderId;
	}

	public void setFlowHeaderId(long flowHeaderId) {
		this.flowHeaderId = flowHeaderId;
	}

	@Column(name = "USER_NAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	//@JoinColumn(name="FLOW_HEADER_ID")
//	public ApproveFlowHeader getApproveFlowHeader() {
//		return approveFlowHeader;
//	}
//
//	public void setApproveFlowHeader(ApproveFlowHeader approveFlowHeader) {
//		this.approveFlowHeader = approveFlowHeader;
//	}
	
	@Id
	@Column(name = "FLOW_ITEM_ID")
	public long getFlowItemId() {
		return flowItemId;
	}

	public void setFlowItemId(long flowItemId) {
		this.flowItemId = flowItemId;
	}
	@Column(name = "NODE_NAME")
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	@Column(name = "NODE_INDEX")
	public int getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}
	@Column(name = "CONDITIONS")
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	@Column(name = "CHECK_SCRIPT")
	public String getCheckScript() {
		return checkScript;
	}

	public void setCheckScript(String checkScript) {
		this.checkScript = checkScript;
	}

}
