package com.hope.systemManager.approveManager.service;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveContentHeaderDao;
import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public class ApprovePageServiceImpl implements ApprovePageService{

	private ApproveContentHeaderDao approveContentHeaderDao;

	public ApproveContentHeaderDao getApproveContentHeaderDao() {
		return approveContentHeaderDao;
	}

	public void setApproveContentHeaderDao(
			ApproveContentHeaderDao approveContentHeaderDao) {
		this.approveContentHeaderDao = approveContentHeaderDao;
	}
	
	@Transactional
	public ApproveContentHeader query(int contentHeaderId) {
		ApproveContentHeader approveContentHeader=new ApproveContentHeader();
		approveContentHeader.setContentHeaderId(contentHeaderId);
		return approveContentHeaderDao.query(approveContentHeader);
	}

}
