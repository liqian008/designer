package com.bruce.designer.util;

import com.bruce.designer.constants.ConstService;

public class MessageUtil {
    
    public static String getMessageTypeName(short messageType){
        switch(messageType){
            case ConstService.MESSAGE_TYPE_BROADCAST:{ 
                return "系统消息";
            }
            case ConstService.MESSAGE_TYPE_FLOWER:{
                return "关注";
            }
            case ConstService.MESSAGE_TYPE_COMMENT:{
                return "评论";
            }
            case ConstService.MESSAGE_TYPE_LIKE:{
                return "喜欢";
            }
            case ConstService.MESSAGE_TYPE_FAVORITIES:{
                return "收藏";
            }
            case ConstService.MESSAGE_TYPE_AT:{
                return "点名";
            }
            case ConstService.MESSAGE_TYPE_CHAT:{
                return "聊天";
            }
            
        }
        return null;
    }
}
