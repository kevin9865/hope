package com.hope.systemManager.approveManager.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.service.RoutineApprovePageService;
import com.hope.systemManager.approveManager.temp.ApproversTemp;
import com.hope.util.Tools;

public class RoutineApprovePageAction {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest httpRequest = (HttpServletRequest) context
			.getExternalContext().getRequest();
	
	@PostConstruct
	public void init(){
		try {
			String idTemp=httpRequest.getParameter("id");
			int id=0;
			if(idTemp!=null){
				id=Integer.valueOf(idTemp);
			}
			approveContentHeader = routineApprovePageService.query(id);
			title = approveContentHeader.getContentTitle();

			ApproveContentItem approveContentItem = approveContentHeader
					.getApproveContentItems().get(0);
			Map<String, Object> itemMap = Tools.readJson(approveContentItem
					.getContentItem());
			content = (String) itemMap.get("content");
			
			List<ApproveContentPerson> contentPersons=approveContentHeader.getApproveContentPersons();
			approvers=new ArrayList<ApproversTemp>();
			
			for(ApproveContentPerson acp:contentPersons){
				ApproversTemp approversTemp=new ApproversTemp();
				approversTemp.setSelect(true);
				approversTemp.setRoleName(acp.getNodeName());
				approversTemp.setName(acp.getName());
				approvers.add(approversTemp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private RoutineApprovePageService routineApprovePageService;
	private ApproveContentHeader approveContentHeader;

	public ApproveContentHeader getApproveContentHeader() {
		return approveContentHeader;
	}

	public void setApproveContentHeader(ApproveContentHeader approveContentHeader) {
		this.approveContentHeader = approveContentHeader;
	}

	public RoutineApprovePageService getRoutineApprovePageService() {
		return routineApprovePageService;
	}

	public void setRoutineApprovePageService(
			RoutineApprovePageService routineApprovePageService) {
		this.routineApprovePageService = routineApprovePageService;
	}
	
	
	/**
	 * 表单
	 */
	public String title;
	public String content;
	public List<ApproversTemp> approvers;

	public List<ApproversTemp> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<ApproversTemp> approvers) {
		this.approvers = approvers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
