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

import com.bruce.designer.model.Comment;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 评论 操作
 * @author liqian
 * 
 */
@Component
public class CommentCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(CommentCommand.class);
    
    @Autowired
    private IAlbumCommentService albumCommentService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumCommentService, "albumCommentService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	
    	String albumIdStr = context.getStringParams().get("albumId");
    	int albumId = NumberUtils.toInt(albumIdStr, 0);
    	
    	String designerIdStr = context.getStringParams().get("designerId");
    	int designerId = NumberUtils.toInt(designerIdStr, 0);
    	
    	String toIdStr = context.getStringParams().get("toId");
    	int toId = NumberUtils.toInt(toIdStr, 0);
		
    	String comment =  context.getStringParams().get("comment");

		if(logger.isDebugEnabled()){
            logger.debug("MCS评论设计师["+designerId+"]的专辑["+albumId+"], from 用户["+hostId+"] to 用户["+toId+"]");
        }
		
		Comment commentResult = albumCommentService.comment(null, comment, albumId, hostId, toId, designerId);
		
		if (commentResult != null) {// 成功响应
			return ResponseBuilderUtil.buildSuccessResult();
		} else {
		    if(logger.isErrorEnabled()){
	            logger.error("MCS评论设计师["+designerId+"]的专辑["+albumId+"], from 用户["+hostId+"] to 用户["+toId+"]，操作失败");
	        }
		    return ResponseBuilderUtil.buildErrorResult();
		}
    }

}
