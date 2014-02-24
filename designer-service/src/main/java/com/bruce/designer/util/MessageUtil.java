package com.bruce.designer.util;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Message;

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
			case ConstService.MESSAGE_TYPE_FLOWER: {
				return "flowers";
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
				return "chat?toId="+messageType;
			}
		}
	}
	
	public static String getMessageDisplay(Message message) {
		if(message!=null&&message.getMessageType()!=null){
			switch (message.getMessageType()) {
				case ConstService.MESSAGE_TYPE_SYSTEM: {
					return message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_FLOWER: {
					return "";
				}
				case ConstService.MESSAGE_TYPE_COMMENT: {
					return "<a href='/designer-front/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " + 
						message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_LIKE: {
					return "<a href='/designer-front/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"赞了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_FAVORITIES: {
					return "<a href='/designer-front/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"收藏了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_AT: {
					return "";
				}
				default: {
					return "<a href='/designer-front/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " +
						message.getMessage();
				}
			}
		}
		return "";
	}
	
	
	public static boolean isChatMessage(int messageType){
		return messageType>=10000; 
	}
	
	public static boolean isBroadcastMessage(int messageType){
		return messageType == ConstService.MESSAGE_TYPE_SYSTEM; 
	}
}
