/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
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
import com.bruce.designer.util.UploadUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 用户资料
 * @author liqian
 * 
 */
@Component
public class UserInfoCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(UserInfoCommand.class);
    
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
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String queryUserIdStr = context.getStringParams().get("userId");
    	int queryUserId = NumberUtils.toInt(queryUserIdStr, 0);
    	
    	if(logger.isDebugEnabled()){
            logger.debug("加载用户["+queryUserId+"]的个人资料");
        }
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
        	
        	//补全头像信息
        	String designerAvatarUrl = UploadUtil.getAvatarUrl(queryUserId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
        	queryUser.setHeadImg(designerAvatarUrl);
        	
        	//判断是否是设计师
        	int followsCount = (int) userGraphService.getFollowCount(queryUserId);
        	int fansCount = (int) userGraphService.getFanCount(queryUserId);
        	
        	rt.put("userinfo", queryUser);
        	rt.put("followsCount", followsCount);
        	rt.put("fansCount", fansCount);
        	
        	boolean hasFollowed = false;
        	if(hostId!=queryUserId){
        		//判断关注状态，以呈现不同状态的按钮
        		hasFollowed = userGraphService.isFollow(hostId, queryUserId);
        	}
        	rt.put("hasFollowed", hasFollowed);
        	
			return ResponseBuilderUtil.buildSuccessResult(rt);
        }
        return ResponseBuilderUtil.buildErrorResult();
    }

}
