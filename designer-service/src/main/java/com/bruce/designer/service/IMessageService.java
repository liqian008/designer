package com.bruce.designer.service;

import java.util.List;

import com.bruce.baseSkeleton.service.IBaseService;

import com.bruce.designer.bean.Message;

public interface IMessageService extends IBaseService<Message, Long>{
    
	/**
	 * 查询用户的未读消息
	 * @param userId
	 * @return
	 */
    public List<Message> queryUnreadMessages(int userId);
    
    
    /**
     * 发送单条消息
     */
    public int sendMessage(int fromId, int toId, String message, short messageType);
    
    /**
     * 发送多条消息
     */
    public int sendMessage(int fromId, int[] toIds, String message, short messageType);
    
    /**
     * 广播至所有用户
     * @return
     */
    public int broadcast2All(String message);
    
    /**
     * 广播至所有设计师
     * @return
     */
    public int broadcase2Designers(String message);
}
