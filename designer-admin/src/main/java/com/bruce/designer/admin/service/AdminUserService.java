package com.bruce.designer.admin.service;

import java.util.List;

import com.bruce.designer.admin.bean.security.AdminUser;
import com.bruce.designer.service.BaseService;

public interface AdminUserService extends BaseService<AdminUser, Integer> {

	public AdminUser loadUserByUsername(String username);

	public int saveUserRoles(Integer userId, List<Integer> roleIdList);

}
