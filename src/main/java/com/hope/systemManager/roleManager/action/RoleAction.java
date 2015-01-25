package com.hope.systemManager.roleManager.action;

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

public class RoleAction implements Serializable{
	
	@PostConstruct
	public void init(){
		initRoleList();
	}
	
	public void initRoleList(){
		roles=roleService.roleQueryAll();
	}
	
	private RoleService roleService;
	private List<Role> roles;
	private List<Role> filteredRoles;
	private List<Role> selectedRoles;
	private String roleIdSelect;
	private SysFunctionService sysFunctionService;

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public String getRoleIdSelect() {
		return roleIdSelect;
	}

	public void setRoleIdSelect(String roleIdSelect) {
		this.roleIdSelect = roleIdSelect;
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

	public List<Role> getFilteredRoles() {
		return filteredRoles;
	}

	public void setFilteredRoles(List<Role> filteredRoles) {
		this.filteredRoles = filteredRoles;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Role role = (Role) event.getObject();
		try {
			roleService.update(role);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initRoleList();
	}

	public void onRowCancel(RowEditEvent event) {
		
	}
	
	/**
	 * 选择功能Table
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			Role role=(Role) event.getObject();
			roleIdForm=role.getRoleId();
			roleNameForm=role.getRoleName();
			roleDescForm=role.getRoleDesc();
			
			reloadTreeNode();
			
			ObjectMapper mapper = new ObjectMapper();
			if(role.getOpeAuth()!=null){
				Map<String,String> opeAuthMap=mapper.readValue(role.getOpeAuth(), Map.class);
				recursionTreeNode(treeNode,opeAuthMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recursionTreeNode(TreeNode node,Map<String,String> opeAuth){
		if(node.getChildCount()==0){
			return;
		}else {
			for(TreeNode tn : node.getChildren()) {
				SysFuncionTree sysFuncionTree=(SysFuncionTree) tn.getData();
				
				String opeIdTemp=opeAuth.get(sysFuncionTree.getSysFunId()+"-"+sysFuncionTree.getSysFunOpeId());
				if(null!=opeIdTemp&&!opeIdTemp.equals("null")){
					tn.setSelected(true);
				}
				recursionTreeNode(tn,opeAuth);
			}
		}
	}
	
	public void addRole(TreeNode[] nodes) {
		try {
			Role role = new Role();
			role.setRoleId(roleIdForm);
			role.setRoleName(roleNameForm);
			role.setRoleDesc(roleDescForm);

			ObjectMapper mapper = new ObjectMapper();
			Map<String,String> opeAuthMap = new HashMap<String,String>();
			
			for(TreeNode node : nodes) {
				SysFuncionTree sTree=(SysFuncionTree) node.getData();
				String sysFunId=sTree.getSysFunId();
				String temp3=sysFunId.substring(6);
				if(Integer.valueOf(temp3)>0){
					String temp2=sysFunId.substring(0, 6)+"000";
					opeAuthMap.put(temp2+"-"+"null", "null");
					String temp1=sysFunId.substring(0, 3)+"000000";
					opeAuthMap.put(temp1+"-"+"null", "null");
				}
				opeAuthMap.put(sTree.getSysFunId()+"-"+sTree.getSysFunOpeId(), sTree.getSysFunName()+"-"+sTree.getSysFunOpeName());
            }
			String opeAuth=mapper.writeValueAsString(opeAuthMap);
			role.setOpeAuth(opeAuth);
			roleService.add(role);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dlg1').hide()");
			initRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRole() {
		try {
			roleService.deleteBatch(selectedRoles);
			initRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reloadTreeNode(){
		try {
			this.treeNode = new DefaultTreeNode("Root Node", null);
		    TreeNode firstLevel =null;
		    TreeNode secondLevel=null;
		    TreeNode thirdLevel=null;
		    TreeNode fourthLevel=null;
		    
		    List<SysFunction> list = sysFunctionService.sysFunctionQueryAll();
		    for (SysFunction sf : list) {
		    	if (sf.getLevelGrade() == 1) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		firstLevel = new DefaultTreeNode(sTree, this.treeNode);
		    	} else if (sf.getLevelGrade() == 2) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		secondLevel = new DefaultTreeNode(sTree, firstLevel);
		    	}  else if (sf.getLevelGrade() == 3) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		thirdLevel = new DefaultTreeNode(sTree, secondLevel);
		    		
		    		if(!sf.getSysFunctionOperations().isEmpty()){
		    			for(SysFunctionOperation sfo:sf.getSysFunctionOperations()){
		    				SysFuncionTree sTreeChild=new SysFuncionTree();
		    				sTreeChild.setSysFunId(sf.getSysFunId());
		    				sTreeChild.setSysFunName(sf.getSysFunName());
		    				sTreeChild.setSysFunOpeId(sfo.getSysFunOpeId());
		    				sTreeChild.setSysFunOpeName(sfo.getOperation());
				    		
				    		fourthLevel = new DefaultTreeNode(sTreeChild, thirdLevel);
		    			}
		    		}
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void initTreeNode(){
	    try {
	    	roleIdForm=roleService.maxRoleId();
	    	
	    	this.treeNode = new DefaultTreeNode("Root Node", null);
		    TreeNode firstLevel =null;
		    TreeNode secondLevel=null;
		    TreeNode thirdLevel=null;
		    TreeNode fourthLevel=null;
		    
		    List<SysFunction> list = sysFunctionService.sysFunctionQueryAll();
		    for (SysFunction sf : list) {
		    	if (sf.getLevelGrade() == 1) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		firstLevel = new DefaultTreeNode(sTree, this.treeNode);
		    	} else if (sf.getLevelGrade() == 2) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		secondLevel = new DefaultTreeNode(sTree, firstLevel);
		    	}  else if (sf.getLevelGrade() == 3) {
		    		SysFuncionTree sTree=new SysFuncionTree();
		    		sTree.setSysFunId(sf.getSysFunId());
		    		sTree.setSysFunName(sf.getSysFunName());
		    		sTree.setSysFunOpeId("null");
		    		sTree.setSysFunOpeName(sf.getSysFunName());
		    		
		    		thirdLevel = new DefaultTreeNode(sTree, secondLevel);
		    		
		    		if(!sf.getSysFunctionOperations().isEmpty()){
		    			for(SysFunctionOperation sfo:sf.getSysFunctionOperations()){
		    				SysFuncionTree sTreeChild=new SysFuncionTree();
		    				sTreeChild.setSysFunId(sf.getSysFunId());
		    				sTreeChild.setSysFunName(sf.getSysFunName());
		    				sTreeChild.setSysFunOpeId(sfo.getSysFunOpeId());
		    				sTreeChild.setSysFunOpeName(sfo.getOperation());
				    		
				    		fourthLevel = new DefaultTreeNode(sTreeChild, thirdLevel);
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
	
	private String roleIdForm;
	private String roleNameForm;
	private String roleDescForm;

	public String getRoleIdForm() {
		return roleIdForm;
	}

	public void setRoleIdForm(String roleIdForm) {
		this.roleIdForm = roleIdForm;
	}

	public String getRoleNameForm() {
		return roleNameForm;
	}

	public void setRoleNameForm(String roleNameForm) {
		this.roleNameForm = roleNameForm;
	}

	public String getRoleDescForm() {
		return roleDescForm;
	}

	public void setRoleDescForm(String roleDescForm) {
		this.roleDescForm = roleDescForm;
	}
	
	public void displaySelectedMultiple(TreeNode[] nodes) {

        for(TreeNode node : nodes) {
            System.out.println(node.getData().toString());
            
        }
    }

}
