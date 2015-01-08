package com.hope.userManager.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.hope.userManager.model.User;

public class UserDaoImpl implements UserDao{

//	private HibernateTemplate hibernateTemplate; 
//	
//	public HibernateTemplate getHibernateTemplate() {
//		return hibernateTemplate;
//	}
//	@Resource
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
	}

	public void delete(User user) {
		
	}

	public void update(User user) {
		
	}
	
	public User userQuery(User user) {
		Session session=sessionFactory.getCurrentSession();
		List<User> list=session.createQuery("from User u where u.usercode='"+user.getUsername()+"'").list();
		if (list.isEmpty()) {
			return null;
		}else {
			User userTemp=list.get(0);
			return userTemp;
		}

//		Query query=session.createSQLQuery("select * from user").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		List list=query.list();
//		if (list.isEmpty()) {
//			System.out.println("为空");
//		}else {
//			Map map = (Map)list.get(0);
//		}
//		return null;
//		User user2=new User();
//		user2.setUsername("bibo");
//		user2.setPassword("123456");
//		if(user.getUsername().equals(user2.getUsername())){
//			return user2;
//		}else {
//			return null;
//		}
	}

}
