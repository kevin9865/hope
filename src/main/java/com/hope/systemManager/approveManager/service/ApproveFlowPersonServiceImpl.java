package com.hope.systemManager.approveManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveFlowItemDao;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public class ApproveFlowPersonServiceImpl implements ApproveFlowPersonService {

	private ApproveFlowItemDao approveFlowItemDao;

	public ApproveFlowItemDao getApproveFlowItemDao() {
		return approveFlowItemDao;
	}

	public void setApproveFlowItemDao(ApproveFlowItemDao approveFlowItemDao) {
		this.approveFlowItemDao = approveFlowItemDao;
	}

	@Transactional
	public void add(ApproveFlowItem approveFlowItem) {
		approveFlowItemDao.add(approveFlowItem);
	}

	@Transactional
	public void delete(ApproveFlowItem approveFlowItem) {
		approveFlowItemDao.delete(approveFlowItem);
	}

	@Transactional
	public void deleteBatch(List<ApproveFlowItem> list) {
		for (ApproveFlowItem item : list) {
			approveFlowItemDao.delete(item);
		}
	}

	@Transactional
	public void update(ApproveFlowItem approveFlowItem) {
		approveFlowItemDao.update(approveFlowItem);
	}

	@Transactional
	public List<ApproveFlowItem> queryAll() {
		return null;
	}

	@Transactional
	public List<ApproveFlowItem> queryAll(ApproveFlowItem approveFlowItem) {
		return approveFlowItemDao.queryAll(approveFlowItem);
	}

	@Transactional
	public int maxId() {
		return approveFlowItemDao.maxId();
	}

}
