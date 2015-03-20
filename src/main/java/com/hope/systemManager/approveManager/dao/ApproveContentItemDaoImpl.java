package com.hope.systemManager.approveManager.dao;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.approveManager.model.ApproveContentItem;

public class ApproveContentItemDaoImpl implements ApproveContentItemDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(ApproveContentItem approveContentItem) {
		Session session = sessionFactory.getCurrentSession();
		session.save(approveContentItem);
	}

	@Override
	public void delete(ApproveContentItem approveContentItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ApproveContentItem approveContentItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int maxId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger) session.createSQLQuery("select nextval('approve_seq')").uniqueResult();
		return id.intValue();
	}

}
