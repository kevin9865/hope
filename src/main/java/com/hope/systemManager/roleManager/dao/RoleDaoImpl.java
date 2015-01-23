package com.hope.systemManager.roleManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.roleManager.model.Role;

public class RoleDaoImpl implements RoleDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public void delete(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(role);
	}

	@Override
	public void update(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.update(role);
	}

	@Override
	public Role roleQuery(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Role roleTemp=(Role) session.load(Role.class, role.getRoleId());
		return roleTemp;
	}

	@Override
	public List<Role> roleQueryAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> list=session.createQuery("from Role r").list();
		return list;
	}

}
