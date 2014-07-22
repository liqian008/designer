/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.service.IAlbumCounterService;
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
public class FollowCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(FollowCommand.class);
    
    @Autowired
    private IAlbumCounterService albumCounterService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumCounterService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	
    	String designerIdStr = context.getStringParams().get("designerId");
    	int designerId = NumberUtils.toInt(designerIdStr, 0);
		
    	return ResponseBuilderUtil.buildSuccessResult();
    }

}
