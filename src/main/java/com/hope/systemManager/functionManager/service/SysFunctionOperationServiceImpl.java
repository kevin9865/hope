package com.hope.systemManager.functionManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.functionManager.dao.SysFunctionOperationDao;
import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public class SysFunctionOperationServiceImpl implements
		SysFunctionOperationService {

	private SysFunctionOperationDao sysFunctionOperationDao;

	public SysFunctionOperationDao getSysFunctionOperationDao() {
		return sysFunctionOperationDao;
	}

	public void setSysFunctionOperationDao(
			SysFunctionOperationDao sysFunctionOperationDao) {
		this.sysFunctionOperationDao = sysFunctionOperationDao;
	}

	@Transactional
	public void add(SysFunctionOperation sysFunctionOperation) {
		sysFunctionOperationDao.add(sysFunctionOperation);
	}

	@Transactional
	public void delete(SysFunctionOperation sysFunctionOperation) {
		sysFunctionOperationDao.delete(sysFunctionOperation);
	}

	@Transactional
	public void deleteBatch(List<SysFunctionOperation> list) {
		for (SysFunctionOperation sys : list) {
			sysFunctionOperationDao.delete(sys);
		}
	}

	@Transactional
	public void update(SysFunctionOperation sysFunctionOperation) {
		sysFunctionOperationDao.update(sysFunctionOperation);
	}

	@Transactional
	public List<SysFunctionOperation> sysFunctionOperationQueryAll() {
		List<SysFunctionOperation> list = sysFunctionOperationDao
				.sysFunctionOperationQueryAll();
		return list;
	}

	@Transactional
	public List<SysFunctionOperation> sysFunctionOperationQueryAll(
			SysFunctionOperation sysFunctionOperation) {
		List<SysFunctionOperation> list = sysFunctionOperationDao
				.sysFunctionOperationQueryAll(sysFunctionOperation);
		return list;
	}

}
