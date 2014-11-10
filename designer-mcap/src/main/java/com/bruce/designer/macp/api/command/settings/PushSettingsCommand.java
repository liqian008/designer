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

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.service.IUserPushTokenService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 绑定push token
 * @author liqian
 * 
 */
@Component
public class PushSettingsCommand extends AbstractApiCommand implements InitializingBean {

	private static final Log logger = LogFactory.getLog(PushSettingsCommand.class);

	@Autowired
	private IUserPushTokenService userPushTokenService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userPushTokenService, "userPushTokenService is required!");
	}

	@Override
	public ApiResult onExecute(ApiCommandContext context) {
		int hostId = context.getUserId();

		String mode = context.getStringParams().get("mode");//1为写操作，0为读操作
		if("1".equalsIgnoreCase(mode)){//写操作
			
			//按位设置的消息开关，从低位到高位, 0表示不下发对应类型push，1表示下发对应类型push。(bit-0: 评论; bit-1: 喜欢; bit-2: 收藏; bit-3: 私信; bit-4: 分享; bit-5: 被关注)
			String pushMaskStr = context.getStringParams().get("pushMask");//位运算
			if (logger.isDebugEnabled()) {
				logger.debug("用户[" + hostId + "]设置pushSettings[" + pushMaskStr + "]");
			}
			int pushMask = NumberUtils.toInt(pushMaskStr, 31);
		}else{//读操作
			if (logger.isDebugEnabled()) {
				logger.debug("用户[" + hostId + "]查询pushSettings");
			}
			UserPushToken userPushToken = userPushTokenService.load(hostId, (short)1);
		}
		//bind操作
		return ResponseBuilderUtil.buildErrorResult();
	}
}
