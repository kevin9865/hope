package com.hope.systemManager.approveManager.dao;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.approveManager.model.ApproveContentPerson;

public class ApproveContentPersonDaoImpl implements ApproveContentPersonDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(ApproveContentPerson approveContentPerson) {
		Session session = sessionFactory.getCurrentSession();
		session.save(approveContentPerson);
	}

	@Override
	public void delete(ApproveContentPerson approveContentPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ApproveContentPerson approveContentPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int maxId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger) session.createSQLQuery("select nextval('approve_seq')").uniqueResult();
		return id.intValue();
	}
	
}
