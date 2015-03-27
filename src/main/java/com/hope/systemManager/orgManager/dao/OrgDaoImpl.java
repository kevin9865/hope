package com.hope.systemManager.orgManager.dao;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.orgManager.model.Org;

public class OrgDaoImpl implements OrgDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Org org) {
		Session session = sessionFactory.getCurrentSession();
		session.save(org);
	}

	@Override
	public void delete(Org org) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(org);
	}

	@Override
	public void update(Org org) {
		Session session = sessionFactory.getCurrentSession();
		session.update(org);
	}

	@Override
	public Org orgQuery(Org org) {
		Session session = sessionFactory.getCurrentSession();
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<Org> list=session.createQuery("from Org o where o.orgId='"+org.getOrgId()+"' and o.companyCode='"+companyCode+"'").list();
		if(list.isEmpty()){
			return null;
		}else {
			return list.get(0);
		}
	}

	@Override
	public List<Org> orgQueryAll() {
		Session session=sessionFactory.getCurrentSession();
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<Org> list=session.createQuery("from Org o where o.companyCode='"+companyCode+"'").list();
		return list;
	}
	@Override
	public int maxOrgLineId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger)session.createSQLQuery("select nextval('org_seq')").uniqueResult();
		return id.intValue();
	}

}
