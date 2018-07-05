package com.goosen.commons.model.po;

import javax.persistence.Table;

import com.goosen.commons.model.BasePO;

/**
 * 角色菜单实体
 * @author Goosen
 * 2018年7月5日 -下午11:02:41
 */
@Table(name = "sys_role_menu")
public class RoleMenu extends BasePO{
	
	private static final long serialVersionUID = -7491215402569546437L;
	
    private String roleId;
    private String menuId;
    
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
