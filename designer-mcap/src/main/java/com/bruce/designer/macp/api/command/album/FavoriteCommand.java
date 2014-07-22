/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.album;

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
 * 收藏&取消收藏 操作
 * @author liqian
 * 
 */
@Component
public class FavoriteCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(FavoriteCommand.class);
    
    @Autowired
    private IAlbumCounterService albumCounterService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumCounterService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	
    	String albumIdStr = context.getStringParams().get("albumId");
    	int albumId = NumberUtils.toInt(albumIdStr, 0);
    	String designerIdStr = context.getStringParams().get("designerId");
    	int designerId = NumberUtils.toInt(designerIdStr, 0);
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS用户["+hostId+"]收藏了设计师["+designerId+"]的专辑["+albumId+"]");
        }
		long result = albumCounterService.incrFavorite(designerId, albumId, hostId);
		if(result>0){
			return ResponseBuilderUtil.buildSuccessResult();
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("MCS用户["+hostId+"]收藏设计师["+designerId+"]的专辑["+albumId+"]，操作失败");
            }
		    return ResponseBuilderUtil.buildErrorResult();
		}
    }

}
