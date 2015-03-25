package com.hope.systemManager.approveManager.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;

public class ApproveContentHeaderDaoImpl implements ApproveContentHeaderDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(ApproveContentHeader approveContentHeader) {
		Session session = sessionFactory.getCurrentSession();
		session.save(approveContentHeader);
	}

	@Override
	public void delete(ApproveContentHeader approveContentHeader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ApproveContentHeader approveContentHeader) {
		Session session = sessionFactory.getCurrentSession();
		session.update(approveContentHeader);
	}

	@Override
	public ApproveContentHeader query(ApproveContentHeader approveContentHeader) {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a where a.contentHeaderId='"+approveContentHeader.getContentHeaderId()+"'").list();
		if(list.isEmpty()){
			return null;
		}else {
			ApproveContentHeader cHeader=list.get(0);
			return cHeader;
		}
	}

	@Override
	public List<ApproveContentHeader> queryAll() {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a").list();
		return list;
	}

	@Override
	public int maxId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger)session.createSQLQuery("select nextval('approve_seq')").uniqueResult();
		return id.intValue();
	}

	@Override
	public List<ApproveContentHeader> currentApproverQuery(String currentApprover) {
		Session session = sessionFactory.getCurrentSession();
		//List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a inner join fetch a.approveContentPersons as p where p.username='"+currentApprover+"' and p.status='Y' order by a.contentHeaderId desc").list();
		List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a where a.currentApprover='"+currentApprover+"' order by a.contentHeaderId desc").list();
		return list;
	}
	
	@Override
	public List<ApproveContentHeader> approverQuery(String currentApprover) {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a inner join fetch a.approveContentPersons as p where p.username='"+currentApprover+"' and p.status='N' order by a.contentHeaderId desc").list();
		return list;
	}

	@Override
	public List<ApproveContentHeader> submitterQuery(String submitter) {
		Session session = sessionFactory.getCurrentSession();
		List<ApproveContentHeader> list = session.createQuery("from APPROVE_CONTENT_HEADER a where a.submitter='"+submitter+"' order by a.contentHeaderId desc").list();
		return list;
	}

}
