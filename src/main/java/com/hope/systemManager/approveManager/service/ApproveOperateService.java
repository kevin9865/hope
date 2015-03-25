package com.hope.systemManager.approveManager.service;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveOperateService {
	/**
	 * 提交审批
	 */
	public void submit();

	/**
	 * 审批通过
	 * 
	 * @param approveContentHeader
	 * @param username
	 * @param remark
	 */
	public void agree(ApproveContentHeader approveContentHeader,
			String username, String remark);

	/**
	 * 审批驳回
	 * 
	 * @param approveContentHeader
	 * @param username
	 * @param remark
	 */
	public void refuse(ApproveContentHeader approveContentHeader,
			String username, String remark);

	/**
	 * 审批信息赋值
	 * 
	 * @param approveContentHeader
	 */
	public void setApproveContentHeader(
			ApproveContentHeader approveContentHeader);

	/**
	 * 设置当前审批人索引位置
	 * 
	 * @param index
	 */
	public void setIndex(int index);

	/**
	 * 设置审批url
	 * 
	 * @param url
	 */
	public void setUrl(String url);
}
