package com.hope.systemManager.functionManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.functionManager.dao.SysFunctionDao;
import com.hope.systemManager.functionManager.model.SysFunction;

public class SysFunctionServiceImpl implements SysFunctionService {

	private SysFunctionDao sysFunctionDao;

	public SysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}

	public void setSysFunctionDao(SysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}

	@Transactional
	public List<SysFunction> sysFunctionQueryAll() {
		List<SysFunction> list = sysFunctionDao.sysFunctionQueryAll();
		return list;
	}

	@Transactional
	public void add(SysFunction sysFunction) {
		sysFunctionDao.add(sysFunction);
	}

	@Transactional
	public void delete(SysFunction sysFunction) {
		sysFunctionDao.delete(sysFunction);
	}

	@Transactional
	public void deleteBatch(List<SysFunction> list) {
		for (SysFunction sys : list) {
			sysFunctionDao.delete(sys);
		}
	}

	@Transactional
	public void update(SysFunction sysFunction) {
		sysFunctionDao.update(sysFunction);
	}

	@Transactional
	public List<SysFunction> sysFunctionQuery(List<String> list) {
		return sysFunctionDao.sysFunctionQuery(list);
	}

	@Transactional
	public int maxId() {
		return sysFunctionDao.maxId();
	}

}
