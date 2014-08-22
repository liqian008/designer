package com.bruce.designer.util;

import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Message;

public class MessageUtil {

	public static String getMessageTypeName(int messageType) {
		switch (messageType) {
			case ConstService.MESSAGE_TYPE_SYSTEM: {
				return "系统消息";
			}
			case ConstService.MESSAGE_TYPE_FOLLOW: {
				return "关注消息";
			}
			case ConstService.MESSAGE_TYPE_COMMENT: {
				return "评论消息";
			}
			case ConstService.MESSAGE_TYPE_LIKE: {
				return "赞消息";
			}
			case ConstService.MESSAGE_TYPE_FAVORITIES: {
				return "收藏消息";
			}
			case ConstService.MESSAGE_TYPE_AT: {
				return "点名消息";
			}
			default: {
				return "私信消息";
			}
		}
	}
	
	/**
     * 构造消息内容
     * @param message
     */
    public static void fillMessageContent(Message message){
    	if(message!=null&&message.getMessageType()!=null){
    		int messageType = message.getMessageType();
    		String messageContent = null;
			if(messageType ==ConstService.MESSAGE_TYPE_LIKE){
	    		messageContent  = ("赞了您的专辑作品"+wrapSourceDesc(message.getSourceDesc()));
		    }else if(messageType ==ConstService.MESSAGE_TYPE_FAVORITIES){
		    	messageContent = ("收藏了您的专辑作品"+wrapSourceDesc(message.getSourceDesc()));
		    }else if(messageType ==ConstService.MESSAGE_TYPE_COMMENT){
		    	messageContent = message.getMessage() + wrapSourceDesc(message.getSourceDesc());
		    }else if(messageType ==ConstService.MESSAGE_TYPE_FOLLOW){
	    		messageContent = ("关注了您");
			}else if(messageType ==ConstService.MESSAGE_TYPE_AT){
			    messageContent = ("@了您");
			}
    		if(messageContent!=null && message.getFromUser()!=null){
    			message.setMessage(messageContent);
    		}
    	}
    }
	
	private static String wrapSourceDesc(String sourceDesc){
		String wrapDesc = "";
		if(!StringUtils.isBlank(sourceDesc)){
			wrapDesc = " - ["+sourceDesc+"]";
		}
		return wrapDesc;
	}
	
	
	public static String getMessageTypeFlag(int messageType) {
		switch (messageType) {
			case ConstService.MESSAGE_TYPE_SYSTEM: {
				return "sys";
			}
			case ConstService.MESSAGE_TYPE_FOLLOW: {
				return "follows";
			}
			case ConstService.MESSAGE_TYPE_COMMENT: {
				return "comments";
			}
			case ConstService.MESSAGE_TYPE_LIKE: {
				return "likes";
			}
			case ConstService.MESSAGE_TYPE_FAVORITIES: {
				return "favorites";
			}
			case ConstService.MESSAGE_TYPE_AT: {
				return "ats";
			}
			default: {
				return "chat/"+messageType;
			}
		}
	}
	
	
	public static boolean isChatMessage(int messageType){
		return messageType>=10000; 
	}
	
	/**
	 * 判断是否是系统广播
	 * @param messageType
	 * @return
	 */
	public static boolean isBroadcastMessage(int messageType) {
		return messageType == ConstService.MESSAGE_TYPE_SYSTEM; 
	}

	/**
	 * 判断是否是交互的消息（交互类消息有源，通常是Album）
	 * @param messageType
	 * @return
	 */
	public static boolean isInteractiveMessage(int messageType) {
		return messageType == ConstService.MESSAGE_TYPE_COMMENT || messageType == ConstService.MESSAGE_TYPE_LIKE || messageType == ConstService.MESSAGE_TYPE_FAVORITIES;
	}
}
