package com.hope.systemManager.functionManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.util.Tools;

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
		Session session = sessionFactory.getCurrentSession();
		session.save(sysFunction);

	}

	@Override
	public void delete(SysFunction sysFunction) {
		Session session = sessionFactory.getCurrentSession();
		SysFunction sys = (SysFunction) session.load(SysFunction.class,
				sysFunction.getSysFunId());
		session.delete(sys);
	}

	@Override
	public void update(SysFunction sysFunction) {
		Session session = sessionFactory.getCurrentSession();
		session.update(sysFunction);
	}

	@Override
	public SysFunction sysFunctionQuery(SysFunction sysFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFunction> sysFunctionQueryAll() {
		Session session = sessionFactory.getCurrentSession();
		List<SysFunction> list = session.createQuery(
				"from SYS_FUNCTION sf order by sf.sysFunId").list();

		return list;
	}

	@Override
	public List<SysFunction> sysFunctionQuery(List<String> list) {
		Session session = sessionFactory.getCurrentSession();
		List<SysFunction> listTemp = session.createQuery(
				"from SYS_FUNCTION sf where"
						+ Tools.listConvertSqlIn("sf.sysFunId", "in", list)
						+ "order by sf.sysFunId").list();
		return listTemp;
	}

}
