package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Message;
import com.bruce.designer.data.PagingData;

public interface IMessageService extends IBaseService<Message, Long>{
    
	/**
	 * 查询用户的未读消息数
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
    public List<Message> queryMessagesByType(int userId, int type);
    
    /**
     * 
     * @param userId
     * @param messageType
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PagingData<Message> pagingQuery(int userId, int messageType, int pageNo, int pageSize);
    
    /**
     * 分页查询用户的对话消息
     * @param status
     * @param offset
     * @param limit
     * @return
     */
//    public PagingData<Message> pagingQuery(int userId, int pageNo, int pageSize);
    
//    public int sendFLowerMessage(int fromId, int toId, String message);
//    
//    public int sendCommentMessage(int fromId, int toId, int lzId, int sourceId, String message);
//    
//    public int sendLikeMessage(int fromId, int toId, int sourceId, String message);
//    
//    public int sendFavoriteMessage(int fromId, int toId, int sourceId, String message);
    
    /**
     * 系统广播
     * @param toId
     * @param content
     * @return
     */
    public int sendSystemMessage(int toId, String content);
    
    /**
     * 批量发送系统广播
     * @param toIdList
     * @param content
     * @return
     */
    public int sendSystemMessage(List<Integer> toIdList, String content);
    
    /**
     * 发送单条消息
     */
    public int sendMessage(long sourceId, int fromId, int toId, String content, int messageType);
    	
    /**
     * 发送多条消息
     */
    public int sendMessage(long sourceId, int fromId, List<Integer> toIdList, String content, int messageType);
    
    /**
     * 发送多条消息
     */
    public int sendChatMessage(int fromId, int toId, String content);
    
    /**
     * 广播至所有用户
     * @return
     */
    public int broadcast2All(String content);
    
    /**
     * 广播至所有设计师
     * @return
     */
    public int broadcase2Designers(String content);

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
	public int markRead(int userId, List<Long> messageIdList);


}
