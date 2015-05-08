package com.hope.systemManager.approveManager.service;

import javax.servlet.http.HttpServletRequest;

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
	/**
	 * 发送邮件
	 * @param addressee
	 * @param title
	 * @param content
	 */
	public void sendEmail(String addressee,String title,String content);
	/**
	 * 获取审批URL
	 * @param path
	 * @return
	 */
	public String getApproveUrl(String path);
	/**
	 * 
	 * @param httpRequest
	 */
	public void setHttpRequest(HttpServletRequest httpRequest);
}
