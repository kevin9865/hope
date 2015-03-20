package com.hope.systemManager.approveManager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "APPROVE_CONTENT_ITEM")
public class ApproveContentItem {
//	// 审批申请抬头ID
//	private ApproveContentHeader approveContentHeader;
	// 审批申请行ID
	private int contentItemId;
	// 审批内容行ID
	private String itemId;
	// 审批内容对应行项ID
	private int lineId;
	// 审批内容行JSON
	private String contentItem;
	
	private int contentHeaderId;
	
	@Column(name = "CONTENT_HEADER_ID")
	public int getContentHeaderId() {
		return contentHeaderId;
	}

	public void setContentHeaderId(int contentHeaderId) {
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
	
	@Id
	@Column(name = "CONTENT_ITEM_ID")
	public int getContentItemId() {
		return contentItemId;
	}

	public void setContentItemId(int contentItemId) {
		this.contentItemId = contentItemId;
	}
	@Column(name = "ITEM_ID")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	@Column(name = "LINE_ID")
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	@Column(name = "CONTENT_ITEM")
	public String getContentItem() {
		return contentItem;
	}

	public void setContentItem(String contentItem) {
		this.contentItem = contentItem;
	}

}
