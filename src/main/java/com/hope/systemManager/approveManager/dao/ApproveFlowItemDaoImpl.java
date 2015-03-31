package com.hope.systemManager.approveManager.dao;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public class ApproveFlowItemDaoImpl implements ApproveFlowItemDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(ApproveFlowItem approveFlowItem) {
		Session session = sessionFactory.getCurrentSession();
		session.save(approveFlowItem);
	}

	@Override
	public void delete(ApproveFlowItem approveFlowItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(approveFlowItem);
	}

	@Override
	public void update(ApproveFlowItem approveFlowItem) {
		Session session = sessionFactory.getCurrentSession();
		session.update(approveFlowItem);
	}

	@Override
	public List<ApproveFlowItem> queryAll(ApproveFlowItem approveFlowItem) {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveFlowItem> list = session.createQuery("from APPROVE_FLOW_ITEM a where a.flowHeaderId='"+approveFlowItem.getFlowHeaderId()+"' order by a.nodeIndex").list();
		return list;
	}

	@Override
	public int maxId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger)session.createSQLQuery("select nextval('approve_flow_seq')").uniqueResult();
		return id.intValue();
	}

}
