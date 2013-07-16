package com.bruce.designer.admin.service;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bruce.designer.admin.security.WebSecurityMetadataSource;


@Service
public class MenuService {

	private static Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	private SecurityService securityService;
	@Autowired
	private WebSecurityMetadataSource securityMetadataSource;

	public void reloadMenusForUser(HttpServletRequest request) {
		//用户有权限的展示菜单
		List<MenuEntity> allMenus = null;
		List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<Integer> roleIdList = new ArrayList<Integer>();
		if(authList!=null){
			SimpleGrantedAuthority superAuthority = new SimpleGrantedAuthority(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER");
			if(authList.contains(superAuthority)){
				//超级用户取得所有可显示的菜单
				allMenus = securityService.getAllNavMenus();
			}else{
				for(GrantedAuthority authority: authList){
					String authorityName = authority.toString();
					if(!(ConstantsUtil.SECURITY_AUTHORITY_PREFIX+"SUPER").equals(authorityName)){
						String roleIdStr = authorityName.substring(ConstantsUtil.SECURITY_AUTHORITY_PREFIX.length(), authorityName.length());
						Integer roleId = new Integer(roleIdStr);
						roleIdList.add(roleId);
					}
				}
				// 一般用户取得有显示权的菜单
				allMenus = securityService.getAllNavMenusByRoleIds(roleIdList);
			}
		}
		
		List<MenuInfo> menus = this.loadMenuInfos(allMenus);
		request.getSession().setAttribute("menus", menus);
		reloadCachedAuthories();
	}
	
	//权限动态变更后，需要重新刷内容
	public void reloadCachedAuthories() {
		if(true){
			securityMetadataSource.initResource();
		}
	}

	public List<MenuInfo> loadMenuInfos(List<MenuEntity> allMenus) {
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		for (MenuEntity menu : allMenus) {
			// 先遍历出第1级菜单
			if (menu.getParentId() == 0) {
				MenuInfo currentMenu = new MenuInfo(menu.getId(),
						menu.getMenuName(), menu.getMenuCode(),
						menu.getMenuUrl());
				menus.add(currentMenu);
				this.loadChildMenus(currentMenu, allMenus);
			}
		}
		return menus;
	}

	private void loadChildMenus(MenuInfo currentMenu, List<MenuEntity> allMenus) {
		for (MenuEntity menu : allMenus) {
			// 如果是当前菜单的子菜单
			if (menu.getParentId() == currentMenu.getMenuId()) {
				MenuInfo childMenu = new MenuInfo(menu.getId(),
						menu.getMenuName(), menu.getMenuCode(),
						menu.getMenuUrl());
				currentMenu.addChild(childMenu);
				// 递归
				this.loadChildMenus(childMenu, allMenus);
			}
		}
	}

	public List<TreeData> getTreeData() {
		List<TreeData> treeDatas = new ArrayList<TreeData>();

		// 取得所有菜单
		List<MenuEntity> allMenus = this.securityService.getAllMenus();

		List<MenuInfo> menus = this.loadMenuInfos(allMenus);

		for (MenuInfo menu : menus) {
			//
			TreeData treeData = new TreeData();
			NodeData nodeData = new NodeData();
			nodeData.setTitle(menu.getMenuName());
			nodeData.getAttr().put("id", menu.getMenuId());

			treeData.getData().add(nodeData);
			this.loadTreeChild(treeData, menu.getChildMenus());
			treeDatas.add(treeData);
		}
		return treeDatas;
	}

	private void loadTreeChild(TreeData treeData, List<MenuInfo> menus) {

		for (MenuInfo menu : menus) {
			TreeData child = new TreeData();
			NodeData childNode = new NodeData();
			childNode.setTitle(menu.getMenuName());
			childNode.getAttr().put("id", menu.getMenuId());
			child.getData().add(childNode);

			// 递归
			this.loadTreeChild(child, menu.getChildMenus());
			treeData.getChildren().add(child);
		}
	}
}
