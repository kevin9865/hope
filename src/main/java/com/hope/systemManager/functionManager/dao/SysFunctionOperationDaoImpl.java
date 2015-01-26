package com.hope.systemManager.functionManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;

public class SysFunctionOperationDaoImpl implements SysFunctionOperationDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(SysFunctionOperation sysFunctionOperation) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sysFunctionOperation);
	}

	@Override
	public void delete(SysFunctionOperation sysFunctionOperation) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(sysFunctionOperation);
	}

	@Override
	public void update(SysFunctionOperation sysFunctionOperation) {
		Session session = sessionFactory.getCurrentSession();
		session.update(sysFunctionOperation);
	}

	@Override
	public SysFunctionOperation sysFunctionOperationQuery(
			SysFunctionOperation sysFunctionOperation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFunctionOperation> sysFunctionOperationQueryAll() {
		Session session = sessionFactory.getCurrentSession();
		List<SysFunctionOperation> list = session.createQuery(
				"from SYS_FUNCTION_OPERATION sfo").list();
		return list;
	}

	@Override
	public List<SysFunctionOperation> sysFunctionOperationQueryAll(
			SysFunctionOperation sysFunctionOperation) {
		Session session = sessionFactory.getCurrentSession();
		List<SysFunctionOperation> list = session.createQuery(
				"from SYS_FUNCTION_OPERATION sfo where sfo.sysFunId='"
						+ sysFunctionOperation.getSysFunId() + "'").list();
		return list;
	}

}
