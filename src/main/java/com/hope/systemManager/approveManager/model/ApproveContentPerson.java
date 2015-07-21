package com.hope.systemManager.approveManager.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "APPROVE_CONTENT_PERSON")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApproveContentPerson {
//	// 审批申请抬头ID
//	private ApproveContentHeader approveContentHeader;
	// 审批申请人行ID
	private int contentPersonId;
	// 审批人username
	private String username;
	// 审批人姓名
	private String name;
	// 审批节点描述
	private String nodeName;
	// 审批节点id
	private int nodeIndex;
	// 审批开始时间
	private Date createTime;
	// 审批结束时间
	private Date endTime;
	// 审批结果
	private String approveResult;
	// 备注
	private String remark;
	// 状态
	private String status;
	
	private long contentHeaderId;
	
	private String checkScript;
	private String conditions;
	private boolean select;
	
	@Transient
	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	@Transient
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	
	@Transient
	public String getCheckScript() {
		return checkScript;
	}

	public void setCheckScript(String checkScript) {
		this.checkScript = checkScript;
	}

	@Column(name = "CONTENT_HEADER_ID")
	public long getContentHeaderId() {
		return contentHeaderId;
	}

	public void setContentHeaderId(long contentHeaderId) {
		this.contentHeaderId = contentHeaderId;
	}
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	//@JoinColumn(name = "CONTENT_HEADER_ID")
//	public ApproveContentHeader getApproveContentHeader() {
//		return approveContentHeader;
//	}
//
//	public void setApproveContentHeader(
//			ApproveContentHeader approveContentHeader) {
//		this.approveContentHeader = approveContentHeader;
//	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Id
	@Column(name = "CONTENT_PERSON_ID")
	public int getContentPersonId() {
		return contentPersonId;
	}

	public void setContentPersonId(int contentPersonId) {
		this.contentPersonId = contentPersonId;
	}
	@Column(name = "USERNAME")
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
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "ENDTIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "APPROVE_RESULT")
	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
