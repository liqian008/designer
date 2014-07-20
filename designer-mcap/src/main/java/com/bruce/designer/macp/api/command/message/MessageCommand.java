/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bruce.designer.model.User;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 用户的关注列表
 * @author liqian
 * 
 */
public class MessageCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(MessageCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService messageService;
    
    @Override 
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	User user = userService.loadById(1);
    	
    	rt.put("user", user);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	
}
