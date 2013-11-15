package com.bruce.designer.util;

import com.bruce.designer.constants.ConstService;

public class MessageUtil {

	public static String getMessageTypeName(int messageType) {
		switch (messageType) {
			case ConstService.MESSAGE_TYPE_SYSTEM: {
				return "系统消息";
			}
			case ConstService.MESSAGE_TYPE_FLOWER: {
				return "关注消息";
			}
			case ConstService.MESSAGE_TYPE_COMMENT: {
				return "评论消息";
			}
			case ConstService.MESSAGE_TYPE_LIKE: {
				return "喜欢消息";
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
	
	public static boolean isChatMessage(int messageType){
		return messageType>=100; 
	}
}
