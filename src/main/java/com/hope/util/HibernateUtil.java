package com.hope.util;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

public class HibernateUtil {
	
	public static SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
