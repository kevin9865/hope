package com.hope.systemManager.approveManager.service;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveFlowHeaderDao;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;

public class RoutineApproveServiceImpl implements RoutineApproveService{

	private ApproveFlowHeaderDao approveFlowHeaderDao;
	
	public ApproveFlowHeaderDao getApproveFlowHeaderDao() {
		return approveFlowHeaderDao;
	}

	public void setApproveFlowHeaderDao(ApproveFlowHeaderDao approveFlowHeaderDao) {
		this.approveFlowHeaderDao = approveFlowHeaderDao;
	}

	@Transactional
	public ApproveFlowHeader query(String orgId) {
		return approveFlowHeaderDao.query(orgId);
	}

}
