package com.bruce.designer.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bruce.designer.admin.bean.security.AdminMenu;
import com.bruce.designer.service.BaseService;

public interface AdminMenuService extends BaseService<AdminMenu, Integer>{

	public List<AdminMenu> getAllNavMenus();
	
	public List<AdminMenu> getChildMenus(Integer parentId);
	
	public List<AdminMenu> getMenusByRoleId(Integer roleId);

	public void reloadMenusForUser(HttpServletRequest request);
	
	
}
