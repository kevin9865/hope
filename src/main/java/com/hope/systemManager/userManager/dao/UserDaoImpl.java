package com.hope.systemManager.userManager.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.userManager.model.User;

public class UserDaoImpl implements UserDao {

	// private HibernateTemplate hibernateTemplate;
	//
	// public HibernateTemplate getHibernateTemplate() {
	// return hibernateTemplate;
	// }
	// @Resource
	// public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	// this.hibernateTemplate = hibernateTemplate;
	// }

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public void delete(User user) {

	}

	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	public User userQuery(User user) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = session.createQuery("from User u where u.username='"+user.getUsername()+"'").list();
		if(list.isEmpty()){
			return null;
		}else{
			User userTemp=list.get(0);
			session.evict(userTemp);//释放session
			return userTemp;
		}
	}

	@Override
	public List<User> userQueryAll() {
		Session session = sessionFactory.getCurrentSession();
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<User> list = session.createQuery("from User u where u.companyCode='"+companyCode+"'").list();
		return list;
	}

	@Override
	public List<User> userFuzzyQuery(User user) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = session.createQuery(
				"from User u where u.username like '%" + user.getUsername()
						+ "%'").list();
		return list;
	}

	@Override
	public String maxUserId() {
		Session session = sessionFactory.getCurrentSession();
		String id=(String) session.createQuery("select max(u.userId) from User u").uniqueResult();
		return id;
	}

}
