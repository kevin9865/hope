package com.hope.systemManager.functionManager.dao;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.frameManager.action.LoginAction;
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
				sysFunction.getSysFid());
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
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<SysFunction> list = session.createQuery(
				"from SYS_FUNCTION sf where sf.companyCode='"+companyCode+"' order by sf.sysFunId").list();

		return list;
	}

	@Override
	public List<SysFunction> sysFunctionQuery(List<String> list) {
		Session session = sessionFactory.getCurrentSession();
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<SysFunction> listTemp = session.createQuery(
				"from SYS_FUNCTION sf where"
						+ Tools.listConvertSqlIn("sf.sysFunId", "in", list)
						+ " and sf.companyCode='"+companyCode+"' order by sf.sysFunId").list();
		return listTemp;
	}

	@Override
	public int maxId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger)session.createSQLQuery("select nextval('sys_fun_seq')").uniqueResult();
		return id.intValue();
	}

}
