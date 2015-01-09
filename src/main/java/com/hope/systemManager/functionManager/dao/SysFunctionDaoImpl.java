package com.hope.systemManager.functionManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.functionManager.model.SysFunction;

public class SysFunctionDaoImpl implements SysFunctionDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(SysFunction sysFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SysFunction sysFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SysFunction sysFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysFunction sysFunctionQuery(SysFunction sysFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFunction> sysFunctionQueryAll() {
		Session session=sessionFactory.getCurrentSession();
		List<SysFunction> list=session.createQuery("from SYS_FUNCTION sf").list();
		
		return list;
	}

}