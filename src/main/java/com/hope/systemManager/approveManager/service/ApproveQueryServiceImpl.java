package com.hope.systemManager.approveManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveContentHeaderDao;
import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public class ApproveQueryServiceImpl implements ApproveQueryService{

	private ApproveContentHeaderDao approveContentHeaderDao;

	public ApproveContentHeaderDao getApproveContentHeaderDao() {
		return approveContentHeaderDao;
	}

	public void setApproveContentHeaderDao(
			ApproveContentHeaderDao approveContentHeaderDao) {
		this.approveContentHeaderDao = approveContentHeaderDao;
	}

	@Transactional
	public List<ApproveContentHeader> query(String submitter) {
		List<ApproveContentHeader> list=approveContentHeaderDao.submitterQuery(submitter);
		return list;
	}

}
