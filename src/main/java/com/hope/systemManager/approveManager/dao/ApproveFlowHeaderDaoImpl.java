package com.hope.systemManager.approveManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;

public class ApproveFlowHeaderDaoImpl implements ApproveFlowHeaderDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(ApproveFlowHeader approveFlowHeader) {
		Session session = sessionFactory.getCurrentSession();
		session.save(approveFlowHeader);
	}

	@Override
	public void delete(ApproveFlowHeader approveFlowHeader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ApproveFlowHeader approveFlowHeader) {
		Session session = sessionFactory.getCurrentSession();
		session.update(approveFlowHeader);
	}

	@Override
	public ApproveFlowHeader query(ApproveFlowHeader approveFlowHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApproveFlowHeader> queryAll() {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveFlowHeader> list = session.createQuery("from APPROVE_FLOW_HEADER a").list();
		return list;
	}

	@Override
	public ApproveFlowHeader query(String orgId) {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveFlowHeader> list = session.createQuery("from APPROVE_FLOW_HEADER a where a.orgId='"+orgId+"'").list();
		if(list.isEmpty()){
			return null;
		}else{
			ApproveFlowHeader flowHeader=list.get(0);
			session.evict(flowHeader);//释放session
			return flowHeader;
		}
	}

	@Override
	public int maxId() {
		return 0;
	}

}
