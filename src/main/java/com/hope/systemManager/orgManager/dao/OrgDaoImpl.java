package com.hope.systemManager.orgManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		List<Org> list=session.createQuery("from Org o where o.orgId='"+org.getOrgId()+"'").list();
		if(list.isEmpty()){
			return null;
		}else {
			return list.get(0);
		}
	}

	@Override
	public List<Org> orgQueryAll() {
		Session session=sessionFactory.getCurrentSession();
		List<Org> list=session.createQuery("from Org o").list();
		return list;
	}
	@Override
	public String maxOrgLineId() {
		Session session = sessionFactory.getCurrentSession();
		String id=(String) session.createQuery("select max(o.orgLineId) from Org o").uniqueResult();
		return id;
	}

}
