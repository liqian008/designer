package com.bruce.designer.admin.service;

import java.util.List;

import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.designer.service.BaseService;

public interface AdminRoleService extends BaseService<AdminRole, Integer>{

	public List<AdminRole> getRolesByUserId(Integer userId);

	public List<AdminRole> getAvailableRoles();

	public int saveRoleMenus(Integer roleId, List<Integer> menuIdList);
	
	public int deleteMenusByRoleId(Integer roleId);
		
}
