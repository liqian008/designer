package com.bruce.designer.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bruce.designer.admin.bean.security.AdminMenu;
import com.bruce.designer.admin.bean.security.AdminMenuCriteria;
import com.bruce.designer.admin.bean.security.AdminRoleMenuCriteria;
import com.bruce.designer.admin.dao.security.AdminMenuMapper;
import com.bruce.designer.admin.dao.security.AdminRoleMenuMapper;
import com.bruce.designer.admin.security.WebSecurityMetadataSource;
import com.bruce.designer.admin.services.AdminMenuService;
import com.bruce.designer.admin.utils.ConstantsUtil;

@Service
public class AdminMenuServiceImpl implements AdminMenuService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminMenuServiceImpl.class);
	
	
	@Autowired
	private WebSecurityMetadataSource securityMetadataSource;
	@Autowired
	private AdminMenuMapper adminMenuMapper;
	@Autowired
	private AdminRoleMenuMapper adminRoleMenuMapper;

	@Override
	public int save(AdminMenu adminMenu) {
		return adminMenuMapper.insert(adminMenu);
	}

	@Override
	public int updateById(AdminMenu adminMenu) {
		return adminMenuMapper.updateByPrimaryKey(adminMenu);
	}

	@Override
	public int deleteById(Integer id) {
		return adminMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminMenu loadById(Integer id) {
		return adminMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminMenu> queryAll() {
		return adminMenuMapper.selectByExample(null);
	}
	
	@Override
	public List<AdminMenu> getChildMenus(Integer parentMenuId) {
		AdminMenuCriteria criteria = new AdminMenuCriteria();
		criteria.createCriteria().andParentIdEqualTo(parentMenuId);
		return adminMenuMapper.selectByExample(criteria);
	}
	

	@Override
	public List<AdminMenu> getAllNavMenus() {
//		AdminMenuCriteria criteria = new AdminMenuCriteria();
//		criteria.createCriteria();
		return adminMenuMapper.selectByExample(null);
	}
	
	
	
	public void reloadMenusForUser(HttpServletRequest request) {
		// 用户有权限的展示菜单
		List<AdminMenu> allMenus = null;
		List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<Integer> roleIdList = new ArrayList<Integer>();
		if (authList != null) {
			SimpleGrantedAuthority superAuthority = new SimpleGrantedAuthority(
					ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER");
			if (authList.contains(superAuthority)) {
				// 超级用户取得所有菜单
				allMenus = getAllNavMenus();
			} else {
				for (GrantedAuthority authority : authList) {
					String authorityName = authority.toString();
					if (!(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER").equals(authorityName)) {
						String roleIdStr = authorityName.substring(
								ConstantsUtil.SECURITY_AUTHORITY_PREFIX
										.length(), authorityName.length());
						Integer roleId = new Integer(roleIdStr);
						roleIdList.add(roleId);
					}
				}
				// 一般用户取得有显示权的菜单
				//allMenus = getAllNavMenusByRoleIds(roleIdList);
			}
		}

		List<AdminMenu> menus = this.loadAdminMenus(allMenus);
		request.getSession().setAttribute("menus", menus);
		reloadCachedAuthories();
	}

	/**
	 * 权限变更后，需要即时刷新以生效
	 */
	private void reloadCachedAuthories() {
		if (true) {
			securityMetadataSource.initResource();
		}
	}
	
	/**
	 * 按层级整理菜单
	 * @param allMenus
	 * @return
	 */
	public List<AdminMenu> loadAdminMenus(List<AdminMenu> allMenus) {
		List<AdminMenu> menus = new ArrayList<AdminMenu>();
		for (AdminMenu menu : allMenus) {
			// 先遍历出第1级菜单
			if (menu.getParentId() == 0) {
//				AdminMenu currentMenu = new AdminMenu(menu.getId(),
//						menu.getMenuName(), menu.getMenuCode(),
//						menu.getMenuUrl());
				AdminMenu currentMenu = new AdminMenu();
				currentMenu.setId(menu.getId());
				currentMenu.setMenuName(menu.getMenuName());
				currentMenu.setMenuCode(menu.getMenuCode());
				currentMenu.setMenuUrl(menu.getMenuUrl());
				
				menus.add(currentMenu);
				this.loadChildMenus(currentMenu, allMenus);
			}
		}
		return menus;
	}

	private void loadChildMenus(AdminMenu currentMenu, List<AdminMenu> allMenus) {
		for (AdminMenu menu : allMenus) {
			// 如果是当前菜单的子菜单
			if (menu.getParentId() == currentMenu.getId()){
//				AdminMenu childMenu = new AdminMenu(menu.getId(),
//						menu.getMenuName(), menu.getMenuCode(),
//						menu.getMenuUrl());
				AdminMenu childMenu = new AdminMenu();
				childMenu.setId(menu.getId());
				childMenu.setMenuName(menu.getMenuName());
				childMenu.setMenuCode(menu.getMenuCode());
				childMenu.setMenuUrl(menu.getMenuUrl());
				currentMenu.addChild(childMenu); 
				// 递归
				this.loadChildMenus(childMenu, allMenus);
			}
		}
	}

	@Override
	public List<AdminMenu> getMenusByRoleId(Integer roleId) {
		AdminRoleMenuCriteria criteria = new AdminRoleMenuCriteria();
		criteria.createCriteria().andRoleIdEqualTo(roleId);
		adminRoleMenuMapper.selectByExample(criteria);
		return null;
	}
	
}
