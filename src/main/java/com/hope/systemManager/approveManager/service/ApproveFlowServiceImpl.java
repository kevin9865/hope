package com.hope.systemManager.approveManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveFlowHeaderDao;
import com.hope.systemManager.approveManager.dao.ApproveFlowItemDao;
import com.hope.systemManager.approveManager.dao.ApproveFlowItemDaoImpl;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public class ApproveFlowServiceImpl implements ApproveFlowService {

	private ApproveFlowHeaderDao approveFlowHeaderDao;
	private ApproveFlowItemDao approveFlowItemDao;

	public ApproveFlowHeaderDao getApproveFlowHeaderDao() {
		return approveFlowHeaderDao;
	}

	public void setApproveFlowHeaderDao(
			ApproveFlowHeaderDao approveFlowHeaderDao) {
		this.approveFlowHeaderDao = approveFlowHeaderDao;
	}

	public ApproveFlowItemDao getApproveFlowItemDao() {
		return approveFlowItemDao;
	}

	public void setApproveFlowItemDao(ApproveFlowItemDao approveFlowItemDao) {
		this.approveFlowItemDao = approveFlowItemDao;
	}

	@Transactional
	public void add(ApproveFlowHeader approveFlowHeader) {
		approveFlowHeaderDao.add(approveFlowHeader);
	}

	@Transactional
	public void delete(ApproveFlowHeader approveFlowHeader) {
		approveFlowHeaderDao.delete(approveFlowHeader);
	}

	@Transactional
	public void deleteBatch(List<ApproveFlowHeader> list) {
		for(ApproveFlowHeader header:list){
			for(ApproveFlowItem item:header.getApproveFlowItems()){
				approveFlowItemDao.delete(item);
			}
			approveFlowHeaderDao.delete(header);
		}
	}

	@Transactional
	public void update(ApproveFlowHeader approveFlowHeader) {
		approveFlowHeaderDao.update(approveFlowHeader);
	}

	@Transactional
	public List<ApproveFlowHeader> approveFlowQueryAll() {
		return approveFlowHeaderDao.queryAll();
	}

	@Transactional
	public int maxId() {
		return approveFlowHeaderDao.maxId();
	}

	@Transactional
	public ApproveFlowHeader flowNameQuery(String flowName) {
		return approveFlowHeaderDao.flowNameQuery(flowName);
	}

}
