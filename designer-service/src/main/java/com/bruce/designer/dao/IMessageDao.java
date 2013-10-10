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
    public List<Message> queryMessageSummary(int userId);
    
    /**
     * 查询用户的未读消息
     * @param userId
     * @param type
     * @return
     */
    public List<Message> queryMessagesByType(int userId, short type);
    
    /**
     * 分页查询用户的对话消息
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    public PagingData<Message> pagingQuery(int userId, int deliverId, int pageNo, int pageSize);
    
    /**
     * 发送单条消息
     */
    public int sendMessage(int fromId, int toId, int deliverId, String content, short messageType);
    	
    /**
     * 发送多条消息
     */
    public int sendMessage(int fromId, int[] toIds, int deliverId, String message, short messageType);
    
    /**
     * 将用户所有消息都标记为已读
     */
    public int markReadAll(int userId);
    
    /**
     * 批量标记为已读
     */
    public int markRead(int userId, short messageType);
    
    /**
     * 标记为已读
     */
    public int markRead(int userId, long messageId);
}
