package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.Message;
import com.bruce.designer.data.PagingData;

public interface IMessageDao extends IBaseDao<Message, Long>{
    
	
	/**
     * 查询用户的收信箱
     * @param userId
     * @return
     */
    public int queryUnreadMessageCount(int userId);
    
    /**
     * 查询用户的收信箱
     * @param userId
     * @return
     */
    public List<Message> queryMessageSummary(int userId);
    
    /**
     * 查询用户的未读消息
     * @param userId
     * @param type
     * @return
     */
    public List<Message> queryMessagesByType(int userId, int messageType);
    
    /**
     * 分页查询用户的对话消息
     * @param userId
     * @param messageType
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PagingData<Message> pagingQuery(int userId, int messageType, int pageNo, int pageSize);
    
    
    /**
     * 发送单条消息
     * @param fromId
     * @param toId
     * @param content
     * @param messageType
     * @return
     */
    public int sendMessage(long sourceId, int fromId, int toId, String content, int messageType);
    
    /**
     *  批量发送多条消息
     * @param fromId
     * @param toIdList
     * @param message
     * @param messageType
     * @return
     */
    public int sendMessage(long sourceId, int fromId, List<Integer> toIdList, String message, int messageType);
    
    /**
     * 发送聊天消息，需特别处理
     * @param fromId
     * @param toId
     * @param content
     * @return
     */
    public int sendChatMessage(int fromId, int toId, String content);
    
    
    /**
     * 将用户所有消息都标记为已读
     */
    public int markReadAll(int userId);
    
    /**
     * 批量标记为已读
     */
    public int markRead(int userId, int messageType);
    
    /**
     * 批量标记为已读
     */
    public int markRead(int userId, List<Long>messageIdList);
    
    

	public List<Message> fallLoadList(int userId, int messageType, Long tailId, int limit);



	
}
