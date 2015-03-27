package com.hope.systemManager.orgManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.orgManager.dao.OrgDao;
import com.hope.systemManager.orgManager.model.Org;

public class OrgServiceImpl implements OrgService{
	
	private OrgDao orgDao;
	
	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	@Transactional
	public void add(Org org) {
		orgDao.add(org);
	}

	@Transactional
	public void delete(Org org) {
		orgDao.delete(org);
	}

	@Transactional
	public void update(Org org) {
		orgDao.update(org);
	}

	@Transactional
	public Org orgQuery(Org org) {
		Org orgTemp=orgDao.orgQuery(org);
		return orgTemp;
	}

	@Transactional
	public List<Org> orgQueryAll() {
		List<Org> list=orgDao.orgQueryAll();
		return list;
	}

	@Transactional
	public void deleteBatch(List<Org> list) {
		for (Org org : list) {
			orgDao.delete(org);
		}
	}

	@Transactional
	public int maxOrgLineId() {
		return orgDao.maxOrgLineId();
	}
	
}
