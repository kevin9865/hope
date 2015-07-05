package com.hope.systemManager.approveManager.action;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.hope.systemManager.approveManager.model.ApproveContentItem;
import com.hope.systemManager.approveManager.model.ApproveContentPerson;
import com.hope.util.Tools;

public class RoutineApprovePageAction extends ApprovePage{

	/**
	 * 还原审批内容步骤 按照基础数据、分析数据、提交操作进行分块显示 提交人只能看到基础数据 审批人可以看到全部数据，但审批人审批完成后无法看到提交操作
	 */
	@PostConstruct
	public void init() {
		try {
			boolean flag=initBefore();

			// 判断是否显示数据
			if (flag == false) {
				title = null;
				content = null;
				approvers = null;
			} else {
				// 获取抬头数据和行项目数据
				title = approveContentHeader.getContentTitle();

				ApproveContentItem approveContentItem = approveContentHeader
						.getApproveContentItems().get(0);
				Map<String, Object> itemMap = Tools.readJson(approveContentItem
						.getContentItem());
				content = (String) itemMap.get("content");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 表单
	 */
	public String title;
	public String content;

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
