/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.settings;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.service.IUserPushTokenService;
import com.bruce.designer.service.IUserService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 绑定push token
 * 
 * @author liqian
 * 
 */
@Component
public class BindPushTokenCommand extends AbstractApiCommand implements InitializingBean {

	private static final Log logger = LogFactory.getLog(BindPushTokenCommand.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPushTokenService userPushTokenService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService is required!");
	}

	@Override
	public ApiResult onExecute(ApiCommandContext context) {
		int hostId = context.getUserId();

		String osTypeStr = context.getStringParams().get("osType");
		short osType = NumberUtils.toShort(osTypeStr, (short)0);
		String modeStr = context.getStringParams().get("mode");
		int mode = NumberUtils.toInt(modeStr, 0);
		
		
		String pushChannelIdStr = context.getStringParams().get("pushChannelId");
		String pushUserIdStr = context.getStringParams().get("pushUserId");
		if (logger.isDebugEnabled()) {
			logger.debug("用户[" + hostId + "]绑定mode[" +mode +"], pushChannelId[" + pushChannelIdStr + "], pushUserIdStr["+pushUserIdStr+"]");
		}
		
		long pushChannelId = NumberUtils.toLong(pushChannelIdStr, 0l);
		if(pushChannelId>0){
			if(mode==0){//解绑
				//unbind操作
				int result = userPushTokenService.disablePushToken(hostId, osType, pushChannelId, pushUserIdStr);
				if(result>0){
					return ResponseBuilderUtil.buildSuccessResult(result);
				}
			}else{//绑定
				//bind操作
				int result = userPushTokenService.enablePushToken(hostId, osType, pushChannelId, pushUserIdStr);
				if(result>0){
					return ResponseBuilderUtil.buildSuccessResult(result);
				}
			}
		}
		return ResponseBuilderUtil.buildErrorResult();
	}
}
