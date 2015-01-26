package com.hope.systemManager.userManager.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.hope.systemManager.functionManager.model.SysFunction;
import com.hope.systemManager.functionManager.model.SysFunctionOperation;
import com.hope.systemManager.functionManager.service.SysFunctionService;
import com.hope.systemManager.roleManager.model.Role;
import com.hope.systemManager.roleManager.modeltemp.SysFuncionTree;
import com.hope.systemManager.roleManager.service.RoleService;
import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.util.Tools;

public class UserAction implements Serializable {

	@PostConstruct
	public void init() {
		initUserList();
	}

	/**
	 * 初始化
	 */
	public void initUserList() {
		users = userService.userQueryAll();
		roles = roleService.roleQueryAll();
	}

	private UserService userService;
	private List<User> users;
	private List<User> filteredUsers;
	private List<User> selectedUsers;
	private List<Role> roles;
	private RoleService roleService;
	private SysFunctionService sysFunctionService;

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUsers() {
		return users;
	}

	// public void userFuzzyQuery() {
	// User user = new User();
	// user.setUsercode(Tools.SpaceDisappear(this.usercode));
	// users = userService.userFuzzyQuery(user);
	// }

	/**
	 * 编辑table行事件
	 * 
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		try {
			User user = (User) event.getObject();
			userService.update(user);
			initUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭编辑table
	 * 
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {

	}

	/**
	 * 选择功能Table
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			User user = (User) event.getObject();
			usercodeAuthForm = user.getUsercode();
			reloadTreeNode();

			ObjectMapper mapper = new ObjectMapper();
			if (null != user.getOpeAuth() && !user.getOpeAuth().equals("")) {
				Map<String, String> opeAuthMap = mapper.readValue(
						user.getOpeAuth(), Map.class);
				recursionTreeNode(treeNode, opeAuthMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加用户
	 */
	public void addUser() {
		try {
			User user = new User();
			user.setUserId(userIdForm);
			user.setUsercode(usercodeForm);
			user.setUsername(usernameForm);
			user.setPassword(passwordForm);
			user.setEmail(emailForm);
			user.setPhone(phoneForm);
			user.setRoleId(roleIdForm);
			user.setOrgId(orgIdForm);
			user.setActive(activeForm);
			userService.add(user);

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户权限
	 * 
	 * @param nodes
	 */
	public void updateUserAuth(TreeNode[] nodes) {
		try {
			User user = new User();
			user.setUsercode(usercodeAuthForm);

			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> opeAuthMap = new HashMap<String, String>();

			for (TreeNode node : nodes) {
				SysFuncionTree sTree = (SysFuncionTree) node.getData();
				String sysFunId = sTree.getSysFunId();
				String temp3 = sysFunId.substring(6);
				if (Integer.valueOf(temp3) > 0) {
					String temp2 = sysFunId.substring(0, 6) + "000";
					opeAuthMap.put(temp2 + "-" + "null", "null");
					String temp1 = sysFunId.substring(0, 3) + "000000";
					opeAuthMap.put(temp1 + "-" + "null", "null");
				}
				opeAuthMap.put(
						sTree.getSysFunId() + "-" + sTree.getSysFunOpeId(),
						sTree.getSysFunName() + "-" + sTree.getSysFunOpeName());
			}
			String opeAuth = mapper.writeValueAsString(opeAuthMap);
			user.setOpeAuth(opeAuth);
			userService.updateUserAuth(user);

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg2').hide()");
			initUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归treeNode，勾选用户权限
	 * 
	 * @param node
	 * @param opeAuth
	 */
	public void recursionTreeNode(TreeNode node, Map<String, String> opeAuth) {
		if (node.getChildCount() == 0) {
			return;
		} else {
			for (TreeNode tn : node.getChildren()) {
				SysFuncionTree sysFuncionTree = (SysFuncionTree) tn.getData();

				String opeIdTemp = opeAuth.get(sysFuncionTree.getSysFunId()
						+ "-" + sysFuncionTree.getSysFunOpeId());
				if (null != opeIdTemp && !opeIdTemp.equals("null")) {
					tn.setSelected(true);
				}
				recursionTreeNode(tn, opeAuth);
			}
		}
	}

	/**
	 * 重新加载功能列表
	 */
	public void reloadTreeNode() {
		try {
			treeNode = new DefaultTreeNode("Root Node", null);
			TreeNode firstLevel = null;
			TreeNode secondLevel = null;
			TreeNode thirdLevel = null;
			TreeNode fourthLevel = null;

			List<SysFunction> list = sysFunctionService.sysFunctionQueryAll();
			for (SysFunction sf : list) {
				if (sf.getLevelGrade() == 1) {
					SysFuncionTree sTree = new SysFuncionTree();
					sTree.setSysFunId(sf.getSysFunId());
					sTree.setSysFunName(sf.getSysFunName());
					sTree.setSysFunOpeId("null");
					sTree.setSysFunOpeName(sf.getSysFunName());

					firstLevel = new DefaultTreeNode(sTree, this.treeNode);
				} else if (sf.getLevelGrade() == 2) {
					SysFuncionTree sTree = new SysFuncionTree();
					sTree.setSysFunId(sf.getSysFunId());
					sTree.setSysFunName(sf.getSysFunName());
					sTree.setSysFunOpeId("null");
					sTree.setSysFunOpeName(sf.getSysFunName());

					secondLevel = new DefaultTreeNode(sTree, firstLevel);
				} else if (sf.getLevelGrade() == 3) {
					SysFuncionTree sTree = new SysFuncionTree();
					sTree.setSysFunId(sf.getSysFunId());
					sTree.setSysFunName(sf.getSysFunName());
					sTree.setSysFunOpeId("null");
					sTree.setSysFunOpeName(sf.getSysFunName());

					thirdLevel = new DefaultTreeNode(sTree, secondLevel);

					if (!sf.getSysFunctionOperations().isEmpty()) {
						for (SysFunctionOperation sfo : sf
								.getSysFunctionOperations()) {
							SysFuncionTree sTreeChild = new SysFuncionTree();
							sTreeChild.setSysFunId(sf.getSysFunId());
							sTreeChild.setSysFunName(sf.getSysFunName());
							sTreeChild.setSysFunOpeId(sfo.getSysFunOpeId());
							sTreeChild.setSysFunOpeName(sfo.getOperation());

							fourthLevel = new DefaultTreeNode(sTreeChild,
									thirdLevel);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TreeNode treeNode;
	private TreeNode[] selectedNodes;

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public TreeNode getTreeNode() {
		return treeNode;
	}

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	/**
	 * addUserDialog表单
	 */
	private String userIdForm;
	private String usercodeForm;
	private String usernameForm;
	private String passwordForm;
	private String emailForm;
	private String phoneForm;
	private String roleIdForm;
	private String orgIdForm;
	private String activeForm;

	public String getUserIdForm() {
		return userIdForm;
	}

	public void setUserIdForm(String userIdForm) {
		this.userIdForm = userIdForm;
	}

	public String getUsercodeForm() {
		return usercodeForm;
	}

	public void setUsercodeForm(String usercodeForm) {
		this.usercodeForm = usercodeForm;
	}

	public String getUsernameForm() {
		return usernameForm;
	}

	public void setUsernameForm(String usernameForm) {
		this.usernameForm = usernameForm;
	}

	public String getPasswordForm() {
		return passwordForm;
	}

	public void setPasswordForm(String passwordForm) {
		this.passwordForm = passwordForm;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getPhoneForm() {
		return phoneForm;
	}

	public void setPhoneForm(String phoneForm) {
		this.phoneForm = phoneForm;
	}

	public String getRoleIdForm() {
		return roleIdForm;
	}

	public void setRoleIdForm(String roleIdForm) {
		this.roleIdForm = roleIdForm;
	}

	public String getOrgIdForm() {
		return orgIdForm;
	}

	public void setOrgIdForm(String orgIdForm) {
		this.orgIdForm = orgIdForm;
	}

	public String getActiveForm() {
		return activeForm;
	}

	public void setActiveForm(String activeForm) {
		this.activeForm = activeForm;
	}

	/**
	 * userOpeAuthDialog表单
	 */
	private String usercodeAuthForm;

	public String getUsercodeAuthForm() {
		return usercodeAuthForm;
	}

	public void setUsercodeAuthForm(String usercodeAuthForm) {
		this.usercodeAuthForm = usercodeAuthForm;
	}

}
