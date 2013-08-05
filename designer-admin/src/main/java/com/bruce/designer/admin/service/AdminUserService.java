package com.bruce.designer.admin.service;

import java.util.List;

import com.bruce.designer.admin.bean.security.AdminUser;
import com.bruce.baseService.IBaseService;

public interface AdminUserService extends IBaseService<AdminUser, Integer> {

	public AdminUser loadUserByUsername(String username);

	public int saveUserRoles(Integer userId, List<Integer> roleIdList);
	
	public int deleteRolesByUserId(Integer userId);

}
