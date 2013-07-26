package com.bruce.designer.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bruce.designer.admin.bean.security.AdminResource;
import com.bruce.designer.service.BaseService;

public interface AdminResourceService extends BaseService<AdminResource, Integer>{

	public List<AdminResource> getAllNavResources();
	
	public List<AdminResource> getAvailableResources();
	
	public List<AdminResource> getChildResources(Integer parentId);
	
	public List<AdminResource> getResourcesByRoleId(Integer roleId);

	public void reloadResourcesForUser(HttpServletRequest request);
	
	
}
