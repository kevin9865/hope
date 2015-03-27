package com.hope.systemManager.approveManager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.systemManager.approveManager.model.ApproveFlowHeader;
import com.hope.systemManager.approveManager.model.ApproveFlowItem;
import com.hope.systemManager.approveManager.service.RoutineApproveService;
import com.hope.systemManager.approveManager.util.SessionTools;
import com.hope.util.Tools;

public class RoutineApproveAction {
	
	public RoutineApproveService routineApproveService;

	public RoutineApproveService getRoutineApproveService() {
		return routineApproveService;
	}

	public void setRoutineApproveService(RoutineApproveService routineApproveService) {
		this.routineApproveService = routineApproveService;
	}

	/**
	 * 提交日常审批草稿
	 * @return
	 */
	public String submit() {
		try {
			Map<String, Object> headerMap = new HashMap<String, Object>();
			headerMap.put("title", title);
			String header = Tools.writeJson(headerMap);

			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("content", content);
			String item = Tools.writeJson(itemMap);

			// 审批内容抬头
			ApproveContentHeader contentHeader = new ApproveContentHeader();
			contentHeader.setContentTitle(title);
			contentHeader.setContentHeader(header);
			// 审批内容明细
			ApproveContentItem contentItem = new ApproveContentItem();
			contentItem.setContentItem(item);

			List<ApproveContentItem> items = new ArrayList<ApproveContentItem>();
			items.add(contentItem);

			contentHeader.setApproveContentItems(items);
			
			ApproveFlowHeader flowHeader=routineApproveService.query("M001");
			List<ApproveFlowItem> flowItems=flowHeader.getApproveFlowItems();
			List<ApproveContentPerson> contentPersons=new ArrayList<ApproveContentPerson>();
			for(ApproveFlowItem fItem:flowItems){
				ApproveContentPerson cp=new ApproveContentPerson();
				cp.setUsername(fItem.getUsername());
				cp.setName(fItem.getName());
				cp.setNodeIndex(fItem.getNodeIndex());
				cp.setNodeName(fItem.getNodeName());
				cp.setCheckScript(fItem.getCheckScript());
				cp.setConditions(fItem.getConditions());
				contentPersons.add(cp);
			}
			
			contentHeader.setApproveContentPersons(contentPersons);
			SessionTools.setContext("contentHeader", contentHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve";
	}

	/**
	 * 表单
	 */
	private String title;
	private String content;

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
