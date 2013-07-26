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

import com.bruce.designer.admin.bean.security.AdminResource;
import com.bruce.designer.admin.bean.security.AdminResourceCriteria;
import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.designer.admin.bean.security.AdminRoleCriteria;
import com.bruce.designer.admin.bean.security.AdminRoleResource;
import com.bruce.designer.admin.bean.security.AdminRoleResourceCriteria;
import com.bruce.designer.admin.bean.security.AdminUserRole;
import com.bruce.designer.admin.bean.security.AdminUserRoleCriteria;
import com.bruce.designer.admin.dao.security.AdminResourceMapper;
import com.bruce.designer.admin.dao.security.AdminRoleResourceMapper;
import com.bruce.designer.admin.security.WebSecurityMetadataSource;
import com.bruce.designer.admin.service.AdminResourceService;
import com.bruce.designer.admin.utils.AdminStatusEnum;
import com.bruce.designer.admin.utils.ConstantsUtil;

@Service
public class AdminResourceServiceImpl implements AdminResourceService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminResourceServiceImpl.class);
	
	
	@Autowired
	private WebSecurityMetadataSource securityMetadataSource;
	@Autowired
	private AdminResourceMapper adminResourceMapper;
	@Autowired
	private AdminRoleResourceMapper adminRoleResourceMapper;

	@Override
	public int save(AdminResource adminResource) {
		return adminResourceMapper.insert(adminResource);
	}

	@Override
	public int updateById(AdminResource adminResource) {
		return adminResourceMapper.updateByPrimaryKeySelective(adminResource);
	}

	@Override
	public int deleteById(Integer id) {
		return adminResourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminResource loadById(Integer id) {
		return adminResourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminResource> queryAll() {
		return adminResourceMapper.selectByExample(null);
	}
	
	@Override
	public List<AdminResource> getChildResources(Integer parentResourceId) {
		AdminResourceCriteria criteria = new AdminResourceCriteria();
		criteria.createCriteria().andParentIdEqualTo(parentResourceId);
		return adminResourceMapper.selectByExample(criteria);
	}
	
	
	@Override
	public List<AdminResource> getAllNavResources() {
	    AdminResourceCriteria criteria = new AdminResourceCriteria();
        criteria.createCriteria().andNavMenuEqualTo(AdminStatusEnum.OPEN.getStatus())
        .andStatusEqualTo(AdminStatusEnum.OPEN.getStatus());
		return adminResourceMapper.selectByExample(criteria);
	}
	
	@Override
    public List<AdminResource> getAvailableResources() {
	    AdminResourceCriteria criteria = new AdminResourceCriteria();
        criteria.createCriteria().andStatusEqualTo(AdminStatusEnum.OPEN.getStatus());
        return adminResourceMapper.selectByExample(criteria);
    }
	
	@Override
	public void reloadResourcesForUser(HttpServletRequest request) {
		// 用户有权限的展示菜单
		List<AdminResource> allResources = null;
		List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<Integer> roleIdList = new ArrayList<Integer>();
		if (authList != null) {
			SimpleGrantedAuthority superAuthority = new SimpleGrantedAuthority(
					ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER");
			if (authList.contains(superAuthority)) {
				// 超级用户取得所有资源菜单
				allResources = getAllNavResources();
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
				// 一般用户取得有显示权的资源菜单
				allResources = getAllNavResourcesByRoleIds(roleIdList);
			}
		}

		List<AdminResource> resources = loadAdminResources(allResources);
		request.getSession().setAttribute("resources", resources);
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
	 * @param allResources
	 * @return
	 */
	public List<AdminResource> loadAdminResources(List<AdminResource> allResources) {
		List<AdminResource> resources = new ArrayList<AdminResource>();
		for (AdminResource resource : allResources) {
			// 先遍历出第1级菜单
			if (resource.getParentId() == 0) {
//				AdminResource currentResource = new AdminResource(resource.getId(),
//						resource.getResourceName(), resource.getResourceCode(),
//						resource.getResourceUrl());
				AdminResource currentResource = new AdminResource();
				currentResource.setId(resource.getId());
				currentResource.setResourceName(resource.getResourceName());
				currentResource.setCode(resource.getCode());
				currentResource.setUrl(resource.getUrl());
				
				resources.add(currentResource);
				this.loadChildResources(currentResource, allResources);
			}
		}
		return resources;
	}

	private void loadChildResources(AdminResource currentResource, List<AdminResource> allResources) {
		for (AdminResource resource : allResources) {
			// 如果是当前菜单的子菜单
			if (resource.getParentId() == currentResource.getId()){
//				AdminResource childResource = new AdminResource(resource.getId(),
//						resource.getResourceName(), resource.getResourceCode(),
//						resource.getResourceUrl());
				AdminResource childResource = new AdminResource();
				childResource.setId(resource.getId());
				childResource.setResourceName(resource.getResourceName());
				childResource.setCode(resource.getCode());
				childResource.setUrl(resource.getUrl());
				currentResource.addChild(childResource); 
				// 递归
				this.loadChildResources(childResource, allResources);
			}
		}
	}

	@Override
	public List<AdminResource> getResourcesByRoleId(Integer roleId) {
		AdminRoleResourceCriteria criteria = new AdminRoleResourceCriteria();
		criteria.createCriteria().andRoleIdEqualTo(roleId);
		List<AdminRoleResource> roleResourcesList =  adminRoleResourceMapper.selectByExample(criteria);
		if(roleResourcesList!=null&&roleResourcesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> resourceIdList = new ArrayList<Integer>();
			for(AdminRoleResource roleResource: roleResourcesList){
				resourceIdList.add(roleResource.getResourceId());
			}
			AdminResourceCriteria resourceCriteria = new AdminResourceCriteria();
			resourceCriteria.createCriteria().andIdIn(resourceIdList);
			return adminResourceMapper.selectByExample(resourceCriteria);
		}
		return null;
	}
	
	
	public List<AdminResource> getAllNavResourcesByRoleIds(List<Integer> roleIdList) {
		AdminRoleResourceCriteria criteria = new AdminRoleResourceCriteria();
		criteria.createCriteria().andRoleIdIn(roleIdList);
		List<AdminRoleResource> roleResourcesList =  adminRoleResourceMapper.selectByExample(criteria);
		if(roleResourcesList!=null&&roleResourcesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> resourceIdList = new ArrayList<Integer>();
			for(AdminRoleResource roleResource: roleResourcesList){
				resourceIdList.add(roleResource.getResourceId());
			}
			AdminResourceCriteria resourceCriteria = new AdminResourceCriteria();
			resourceCriteria.createCriteria().andIdIn(resourceIdList);
			return adminResourceMapper.selectByExample(resourceCriteria);
		}
		return null;
	}
	
}
