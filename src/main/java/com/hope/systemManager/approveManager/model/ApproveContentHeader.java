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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity(name = "APPROVE_CONTENT_HEADER")
public class ApproveContentHeader {
	// 审批申请ID
	private int contentHeaderId;
	// 审批内容ID
	private String headerId;
	// 审批内容标题
	private String contentTitle;
	// 审批内容JSON
	private String contentHeader;
	// 提交人
	private String submitter;
	// 提交时间
	private Date submittime;
	// 当前状态
	private String status;
	// 审批内容行项
	private List<ApproveContentItem> approveContentItems = new ArrayList<ApproveContentItem>();
	// 审批人员
	private List<ApproveContentPerson> approveContentPersons = new ArrayList<ApproveContentPerson>();

	@Id
	@Column(name = "CONTENT_HEADER_ID")
	public int getContentHeaderId() {
		return contentHeaderId;
	}

	public void setContentHeaderId(int contentHeaderId) {
		this.contentHeaderId = contentHeaderId;
	}
	@Column(name = "HEADER_ID")
	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}
	@Column(name = "CONTENT_TITLE")
	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	
	@Column(name = "CONTENT_HEADER")
	public String getContentHeader() {
		return contentHeader;
	}

	public void setContentHeader(String contentHeader) {
		this.contentHeader = contentHeader;
	}
	@Column(name = "SUBMITTER")
	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	@Column(name = "SUBMITTIME")
	public Date getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "CONTENT_HEADER_ID")
	@OrderBy(value="lineId")
	public List<ApproveContentItem> getApproveContentItems() {
		return approveContentItems;
	}

	public void setApproveContentItems(
			List<ApproveContentItem> approveContentItems) {
		this.approveContentItems = approveContentItems;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "CONTENT_HEADER_ID")
	@OrderBy(value="nodeIndex")
	public List<ApproveContentPerson> getApproveContentPersons() {
		return approveContentPersons;
	}

	public void setApproveContentPersons(
			List<ApproveContentPerson> approveContentPersons) {
		this.approveContentPersons = approveContentPersons;
	}
}
