package com.hope.orgManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.orgManager.model.Org;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Org org) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Org org) {
		// TODO Auto-generated method stub

	}

	@Override
	public Org orgQuery(Org org) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Org> orgQueryAll() {
		Session session=sessionFactory.getCurrentSession();
		List<Org> list=session.createQuery("from Org o").list();
		return list;
	}

}
