/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.MessageUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 用户的关注列表
 * @author liqian
 * 
 */
@Component
public class MessageListCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(MessageListCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService messageService;

	private static final int MESSAGE_PAGE_SIZE = 5;
    
    @Override 
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	if(logger.isDebugEnabled()){
            logger.debug("用户["+hostId+"]查看消息列表");
        }
    	
    	String messageTypeStr = context.getStringParams().get("messageType");
    	String pageNoStr = context.getStringParams().get("pageNo");
    	
    	int messageType = NumberUtils.toInt(messageTypeStr, ConstService.MESSAGE_TYPE_SYSTEM);
    	int pageNo = NumberUtils.toInt(pageNoStr, ConstService.MESSAGE_TYPE_SYSTEM);
		
    	PagingData<Message> messagePagingData = messageService.pagingQuery(hostId, messageType, pageNo, MESSAGE_PAGE_SIZE);
		List<Message> messageList = messagePagingData.getPagingData();
		if(messageList!=null&&messageList.size()>0){
			
			List<Integer> fromIdList = new ArrayList<Integer>();
			for(Message message: messageList){
				//非系统广播，先构造fromId列表
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					int fromId = message.getFromId();
					fromIdList.add(fromId);
				}
			}
			//获取用户map
			Map<Integer, User> fromUserMap = userService.getUserMap(fromIdList);
			
			for(Message message: messageList){
				//非系统广播，加载fromId对应的用户信息
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					
					int fromId = message.getFromId();
					message.setFromUser(fromUserMap.get(fromId));
					
				}
				
				MessageBoxCommand.fillMessageContent(message);
			}
			//读取消息后标记为已读
			messageService.markRead(hostId, messageType);
		}
		rt.put("messageList", messageList);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }
    

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	
}
