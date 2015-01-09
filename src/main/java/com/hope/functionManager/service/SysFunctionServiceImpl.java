package com.hope.functionManager.service;

import java.util.List;

import com.hope.functionManager.dao.SysFunctionDao;
import com.hope.functionManager.model.SysFunction;

public class SysFunctionServiceImpl implements SysFunctionService{
	
	private SysFunctionDao sysFunctionDao;
	
	public SysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}
	
	public void setSysFunctionDao(SysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}

	@Override
	public List<SysFunction> sysFunctionQueryAll() {
		List<SysFunction> list=sysFunctionDao.sysFunctionQueryAll();
		return list;
	}

}
