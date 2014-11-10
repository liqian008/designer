/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 关注&取消关注 操作
 * @author liqian
 * 
 */
@Component
public class PostFollowCommand extends AbstractApiCommand implements
		InitializingBean {

	private static final Log logger = LogFactory.getLog(PostFollowCommand.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserGraphService userGraphService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService is required!");
	}

	@Override
	public ApiResult onExecute(ApiCommandContext context) {
		int hostId = context.getUserId();

		boolean followAction = true;
		String actionName = "关注";
		String mode = context.getStringParams().get("mode");//1为关注，0为取消关注
		if("0".equalsIgnoreCase(mode)){
			followAction=false;
			actionName = "取消关注";
		}
		
		String designerIdStr = context.getStringParams().get("designerId");
		int designerId = NumberUtils.toInt(designerIdStr, 0);

		if (logger.isDebugEnabled()) {
			logger.debug("用户[" + hostId + "]"+actionName+"设计师[" + designerId + "]");
		}
		if (hostId == designerId) {
			if (logger.isErrorEnabled()) {
				logger.error("用户[" + hostId + "]不能"+actionName+"自己");
			}
			// 不能关注自己
		} else {
			User followUser = userService.loadById(designerId);
			if (followUser != null) {
				if (followUser.getDesignerStatus() != ConstService.DESIGNER_APPLY_APPROVED) {
					if (logger.isErrorEnabled()) {
						logger.error("[" + designerId + "]非设计师身份，不能进行"+actionName+"操作");
					}
					// 不能关注一般用户（只能关注设计师）
				}
				boolean result =false;
				if(followAction){
					result = userGraphService.follow(hostId, designerId);
				}else{
					result = userGraphService.unfollow(hostId, designerId);
				}
				if (result) {
					//操作成功
				}
				if (logger.isDebugEnabled()) {
					logger.debug("用户[" + hostId + "]"+actionName+"设计师[" + designerId
							+ "]结果: " + result);
				}
			}
			return ResponseBuilderUtil.buildSuccessResult();
		}
		return ResponseBuilderUtil.buildErrorResult();
	}
}
