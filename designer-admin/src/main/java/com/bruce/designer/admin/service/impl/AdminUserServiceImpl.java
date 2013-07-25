package com.bruce.designer.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.admin.bean.security.AdminRoleMenuCriteria;
import com.bruce.designer.admin.bean.security.AdminUser;
import com.bruce.designer.admin.bean.security.AdminUserCriteria;
import com.bruce.designer.admin.bean.security.AdminUserRole;
import com.bruce.designer.admin.bean.security.AdminUserRoleCriteria;
import com.bruce.designer.admin.dao.security.AdminUserMapper;
import com.bruce.designer.admin.dao.security.AdminUserRoleMapper;
import com.bruce.designer.admin.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;

	@Override
	public int save(AdminUser adminUser) {
		return adminUserMapper.insert(adminUser);
	}

	@Override
	public int updateById(AdminUser adminUser) {
		return adminUserMapper.updateByPrimaryKeySelective(adminUser);
	}

	@Override
	public int deleteById(Integer id) {
		return adminUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminUser loadById(Integer id) {
		return adminUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminUser> queryAll() {
		return adminUserMapper.selectByExample(null);
	}

	@Override
	public AdminUser loadUserByUsername(String username) {
		AdminUserCriteria criteria = new AdminUserCriteria();
		criteria.createCriteria().andUsernameEqualTo(username);
		List<AdminUser> adminUserList =  adminUserMapper.selectByExample(criteria);
		return adminUserList!=null&&adminUserList.size()==1?adminUserList.get(0):null;
	}


	@Override
	public int saveUserRoles(Integer userId, List<Integer> roleIdList) {
		if(roleIdList!=null&&roleIdList.size()>0){
			for(int roleId: roleIdList){
				AdminUserRole adminUserRole = new AdminUserRole();
				adminUserRole.setUserId(userId);
				adminUserRole.setRoleId(roleId);
				adminUserRoleMapper.insert(adminUserRole);
			}
			return roleIdList.size();
		}
		return 0;
	}
	
	@Override
	public int deleteRolesByUserId(Integer userId) {
		AdminUserRoleCriteria criteria = new AdminUserRoleCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return adminUserRoleMapper.deleteByExample(criteria);
	}
	
}
