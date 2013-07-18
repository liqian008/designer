package com.bruce.designer.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.designer.admin.bean.security.AdminRoleCriteria;
import com.bruce.designer.admin.bean.security.AdminRoleMenu;
import com.bruce.designer.admin.bean.security.AdminRoleMenuCriteria;
import com.bruce.designer.admin.bean.security.AdminUserRole;
import com.bruce.designer.admin.bean.security.AdminUserRoleCriteria;
import com.bruce.designer.admin.dao.security.AdminRoleMapper;
import com.bruce.designer.admin.dao.security.AdminRoleMenuMapper;
import com.bruce.designer.admin.dao.security.AdminUserRoleMapper;
import com.bruce.designer.admin.service.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminRoleServiceImpl.class);
	
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;
	@Autowired
	private AdminRoleMenuMapper adminRoleMenuMapper;

	@Override
	public int save(AdminRole adminRole) {
		return adminRoleMapper.insert(adminRole);
	}

	@Override
	public int updateById(AdminRole adminRole) {
		return adminRoleMapper.updateByPrimaryKey(adminRole);
	}

	@Override
	public int deleteById(Integer id) {
		return adminRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminRole loadById(Integer id) {
		return adminRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminRole> queryAll() {
		return adminRoleMapper.selectByExample(null);
	}
	
	
	@Override
	public List<AdminRole> getAvailableRoles() {
		AdminRoleCriteria criteria = new AdminRoleCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return adminRoleMapper.selectByExample(criteria);
	}
	


	@Override
	public List<AdminRole> getRolesByUserId(Integer userId) {
		AdminUserRoleCriteria criteria = new AdminUserRoleCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		List<AdminUserRole> userRolesList =  adminUserRoleMapper.selectByExample(criteria);
		if(userRolesList!=null&&userRolesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> roleIdList = new ArrayList<Integer>();
			for(AdminUserRole userRole: userRolesList){
				roleIdList.add(userRole.getRoleId());
			}
			AdminRoleCriteria roleCriteria = new AdminRoleCriteria();
			roleCriteria.createCriteria().andIdIn(roleIdList);
			return adminRoleMapper.selectByExample(roleCriteria);
		}
		return null;
	}

	@Override
	public int saveRoleMenus(Integer roleId, List<Integer> menuIdList) {
		if(menuIdList!=null&&menuIdList.size()>0){
			for(int menuId: menuIdList){
				AdminRoleMenu adminRoleMenu = new AdminRoleMenu();
				adminRoleMenu.setRoleId(roleId);
				adminRoleMenu.setMenuId(menuId);
				adminRoleMenuMapper.insert(adminRoleMenu);
			}
			return menuIdList.size();
		}
		return 0;
	}

	
}
