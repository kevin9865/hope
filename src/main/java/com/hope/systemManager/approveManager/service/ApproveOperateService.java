package com.hope.systemManager.approveManager.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;

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
	 * 获取审批URL
	 * 
	 * @param path
	 * @return
	 */
	public String getApproveUrl(String path);

	/**
	 * 
	 * @param httpRequest
	 */
	public void setHttpRequest(HttpServletRequest httpRequest);

	/**
	 * 加签
	 * 
	 * @param approveContentHeader
	 * @param username
	 * @param flag
	 * @param currentApprover
	 * @param remark
	 */
	public String countersigned(ApproveContentHeader approveContentHeader,
			String[] username, String flag, String currentApprover,
			String remark);

	/**
	 * 审批流操作
	 * 
	 * @param approveProcess
	 */
	public void setApproveProcess(ApproveProcess approveProcess);

	/**
	 * 初始化审批提交
	 * 
	 * @param httpRequest
	 * @param approveContentHeader
	 */
	public void initSubmit(HttpServletRequest httpRequest,
			ApproveContentHeader approveContentHeader);
}
