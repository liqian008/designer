/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UserUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * @author liqian
 * 
 */
@Component
public class GuestLoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(GuestLoginCommand.class);
    @Autowired
    private PassportService passportService;
    @Autowired
    private IUserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int userId = UserUtil.GUEST_ID;
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		UserPassport userPassport = new UserPassport();
        userPassport.setUserId(userId);
        userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
        String ticket = passportService.createTicket(userPassport);
        userPassport.setTicket(ticket);
        
        paramMap.put("userPassport", userPassport);
        
        return ResponseBuilderUtil.buildSuccessResult(paramMap);
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
