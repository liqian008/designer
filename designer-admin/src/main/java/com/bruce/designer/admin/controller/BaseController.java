package com.bruce.designer.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bruce.designer.admin.security.WebUserDetails;

public abstract class BaseController {
	
	protected WebUserDetails getUserInfo(){
		WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
}
