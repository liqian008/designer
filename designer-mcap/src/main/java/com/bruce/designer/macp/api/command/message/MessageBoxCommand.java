/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.MessageUtil;
import com.bruce.designer.util.UploadUtil;
import com.bruce.designer.util.UserUtil;
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
public class MessageBoxCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(MessageBoxCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService messageService;
    
    @Override 
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	if(logger.isDebugEnabled()){
            logger.debug("用户["+hostId+"]查看消息中心");
        }
		
		// 消息中心
		List<Message> messageBoxList = messageService.queryMessageSummary(hostId);
		//加载fromId的详细资料
		if(messageBoxList!=null&&messageBoxList.size()>0){
			List<Integer> fromIdList = new ArrayList<Integer>();
			for(Message message: messageBoxList){
				if(UserUtil.isGuest(hostId)){//登录者为游客身份
					message.setCreateTime(new Date());
				}else{//登录用户身份
					//非系统广播，先构造fromId列表
					if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
						int fromId = message.getFromId();
						fromIdList.add(fromId);
						if(MessageUtil.isChatMessage(message.getMessageType())){
							int chatId = message.getMessageType();
							fromIdList.add(chatId);
						}
					}
				}
			}
			//获取用户map
			Map<Integer, User> fromUserMap = userService.getUserMap(fromIdList);
			
			for(Message message: messageBoxList){
				
				//非系统广播，加载fromId对应的用户信息
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					
					int fromId = message.getFromId();
					User fromUser = fromUserMap.get(fromId);
					if(fromUser!=null){
						message.setFromUser(fromUser);
						String fromAvatarUrl = UploadUtil.getAvatarUrl(fromId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
						fromUser.setHeadImg(fromAvatarUrl);
					}
					if(MessageUtil.isChatMessage(message.getMessageType())){
						int chatId = message.getMessageType();
						User chatUser = fromUserMap.get(chatId);
						if(chatUser!=null){
							message.setChatUser(chatUser);
							String chatAvatarUrl = UploadUtil.getAvatarUrl(chatId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
							chatUser.setHeadImg(chatAvatarUrl);
						}
					}
				}
				MessageUtil.fillMessageContent(message);
			}
		}
		rt.put("messageBoxList", messageBoxList);
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
