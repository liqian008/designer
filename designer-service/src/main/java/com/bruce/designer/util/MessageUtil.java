package com.bruce.designer.util;

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
	
	public static boolean isBroadcastMessage(int messageType){
		return messageType == ConstService.MESSAGE_TYPE_SYSTEM; 
	}
}
