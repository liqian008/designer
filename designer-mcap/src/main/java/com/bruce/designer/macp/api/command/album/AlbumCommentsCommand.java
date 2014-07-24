/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Comment;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.util.UploadUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 获取评论command
 * @author liqian
 * 
 */
@Component
public class AlbumCommentsCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(AlbumCommentsCommand.class);
    
    @Autowired
    private IAlbumCommentService albumCommentService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumCommentService, "albumCommentService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int userId = context.getUserId();
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String albumIdStr = context.getStringParams().get("albumId");
    	int albumId = NumberUtils.toInt(albumIdStr, 0);
    	
    	int tailId = 0;
    	int limit = 20;
    	if(logger.isDebugEnabled()){
            logger.debug("MCS获取专辑["+albumId+"]评论列表, tailId： "+tailId+", limit："+limit);
        }
		
		List<Comment> commentList = albumCommentService.fallLoadComments(albumId, tailId, limit + 1);
		long commentTailId = 0;
		if (commentList != null) {
			if (commentList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				commentList.remove(limit);
				commentTailId = commentList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("MCS还有更多评论，commentTailId： "+commentTailId);
                }
			}
			
			for(Comment comment: commentList){
				String userHeadImg = UploadUtil.getAvatarUrl(comment.getFromId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
				comment.setUserHeadImg(userHeadImg);
			}
		}
		rt.put("commentList", commentList);
		rt.put("commentTailId", String.valueOf(commentTailId));
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

}
