package com.hope.systemManager.approveManager.util;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.service.ApproveFlowService;
import com.hope.systemManager.approveManager.service.ApproveProcess;

public class ApproveUtil {
	private SessionFactory sessionFactory;
	private ApproveFlowService approveFlowService;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ApproveFlowService getApproveFlowService() {
		return approveFlowService;
	}

	public void setApproveFlowService(ApproveFlowService approveFlowService) {
		this.approveFlowService = approveFlowService;
	}

	/**
	 * 动态加载审批流程
	 * 
	 * @param header
	 * @return
	 */
	public ApproveProcess getProcess(ApproveContentHeader header) {
		ApproveProcess approveProcess = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			ApproveFlowHeader flowHeader = approveFlowService
					.flowNameQuery(header.getApproveName());

			if (flowHeader == null) {
				return null;
			}

			String processClassPath = flowHeader.getProcessClassPath();
			String entityClassPath = flowHeader.getEntityClassPath();
			String entityId = flowHeader.getEntityId();
			if (processClassPath == null)
				return null;
			Class clas = Class.forName(processClassPath);
			Constructor constructor = clas.getConstructor(Class
					.forName(entityClassPath));
			List list = session.createQuery(
					"from " + Class.forName(entityClassPath).getSimpleName()
							+ " t where t." + entityId + "='"
							+ header.getHeaderId() + "'").list();

			approveProcess = (ApproveProcess) constructor.newInstance(list
					.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return approveProcess;
	}

	/**
	 * 动态获取审批url
	 * 
	 * @param flowName
	 * @return
	 */
	public String getUrl(String flowName) {
		ApproveFlowHeader flowHeader = approveFlowService
				.flowNameQuery(flowName);
		return flowHeader.getUrl();
	}
}
