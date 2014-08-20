/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.message;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.util.MessageUtil;
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
public class PostChatCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(PostChatCommand.class);
    
    @Autowired 
    private IMessageService messageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(messageService, "messageService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	
    	String toIdStr = context.getStringParams().get("toId");
    	int toId = NumberUtils.toInt(toIdStr, 0);
		
    	String content =  context.getStringParams().get("content");

		if(logger.isDebugEnabled()){
            logger.debug("MCS私信操作, from 用户["+hostId+"] to 用户["+toId+"]");
        }
		
		if (!MessageUtil.isChatMessage(toId)) {
			if(logger.isErrorEnabled()){
                logger.error("用户["+hostId+"]私信对象["+toId+"]有误");
            }
			return ResponseBuilderUtil.buildErrorResult(ErrorCode.MESSAGE_UNSUPPORT_TYPE);
		} else if (toId == hostId) {// 不能给自己发消息
		    if(logger.isErrorEnabled()){
                logger.error("用户["+hostId+"]不能给自己发私信消息");
            }
		    return ResponseBuilderUtil.buildErrorResult(ErrorCode.MESSAGE_TO_SELF);
		}else{
			int result = messageService.sendChatMessage(hostId, toId, content);
			if(result>0){
				if(logger.isErrorEnabled()){
		            logger.error("MCS私信操作, from 用户["+hostId+"] to 用户["+toId+"]，操作失败");
		        }
				return ResponseBuilderUtil.buildSuccessResult();
			}
		}
		return ResponseBuilderUtil.buildErrorResult();
    }

}
