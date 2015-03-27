package com.hope.systemManager.roleManager.dao;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.orgManager.model.Org;
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
		//Role roleTemp=(Role) session.load(Role.class, role.getRoleId());
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<Role> list=session.createQuery("from Role r where r.roleId='"+role.getRoleId()+"' and r.companyCode='"+companyCode+"'").list();
		if(list.isEmpty()){
			return null;
		}else {
			Role roleTemp=list.get(0);
			//session.evict(roleTemp);
			return roleTemp;
		}
	}

	@Override
	public List<Role> roleQueryAll() {
		Session session = sessionFactory.getCurrentSession();
		String companyCode=LoginAction.getCurrentUser().getCompanyCode();
		List<Role> list=session.createQuery("from Role r where r.companyCode='"+companyCode+"'").list();
		return list;
	}

	@Override
	public int maxRoleId() {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id=(BigInteger)session.createSQLQuery("select nextval('role_seq')").uniqueResult();
		return id.intValue();
	}

}
