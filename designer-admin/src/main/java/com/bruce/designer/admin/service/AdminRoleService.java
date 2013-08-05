package com.bruce.designer.admin.service;

import java.util.List;


import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.baseService.IBaseService;

public interface AdminRoleService extends IBaseService<AdminRole, Integer>{

	public List<AdminRole> getRolesByUserId(Integer userId);

	public List<AdminRole> getAvailableRoles();

	public int saveRoleResources(Integer roleId, List<Integer> menuIdList);
	
	public int deleteResourcesByRoleId(Integer roleId);
		
}
