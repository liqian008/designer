/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UserUtil;
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
public class MyInfoCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(MyInfoCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserGraphService userGraphService;
    @Autowired
    private IAlbumCounterService albumCounterService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	
    	int hostId = context.getUserId();
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	if(logger.isDebugEnabled()){
            logger.debug("加载自己["+hostId+"]的个人资料");
        }
        User hostUser = userService.loadById(hostId, true);//同时加载accessToken信息
        if(hostUser!=null){
        	
        	//补全头像信息
        	String userAvatarUrl = UserUtil.getAvatarUrl(hostUser, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
        	hostUser.setHeadImg(userAvatarUrl);
        	
        	int albumsCount = 0;
        	int fansCount = 0;
        	//判断是否是设计师（设计师才有粉丝&专辑数量）
        	if(hostUser!=null&&Short.valueOf(ConstService.DESIGNER_APPLY_APPROVED).equals(hostUser.getDesignerStatus())){
        		albumsCount = (int) albumCounterService.getUserAlbumCount(hostId);
        		fansCount = (int) userGraphService.getFanCount(hostId);
        	}
        	
        	int followsCount = (int) userGraphService.getFollowCount(hostId);
        	
        	rt.put("userinfo", hostUser);
        	rt.put("albumsCount", albumsCount);
        	rt.put("followsCount", followsCount);
        	rt.put("fansCount", fansCount);
        	
			return ResponseBuilderUtil.buildSuccessResult(rt);
        }
        return ResponseBuilderUtil.buildErrorResult();
    }

}
