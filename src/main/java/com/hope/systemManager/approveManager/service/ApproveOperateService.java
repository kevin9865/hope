package com.hope.systemManager.approveManager.service;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public interface ApproveOperateService {
	public void submit();
	public void agree(ApproveContentHeader approveContentHeader,String name);
	public void refuse(ApproveContentHeader approveContentHeader,String name);
	public void setUrl(String url);
	public void setApproveContentHeader(ApproveContentHeader approveContentHeader);
	public void setIndex(int index);
}
