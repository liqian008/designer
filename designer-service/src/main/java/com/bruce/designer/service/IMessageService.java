package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.Message;

public interface IMessageService extends IBaseService<Message, Long>{
    
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
    
//    public int sendFLowerMessage(int fromId, int toId, String message);
//    
//    public int sendCommentMessage(int fromId, int toId, int lzId, int sourceId, String message);
//    
//    public int sendLikeMessage(int fromId, int toId, int sourceId, String message);
//    
//    public int sendFavoriteMessage(int fromId, int toId, int sourceId, String message);
    
    
    /**
     * 发送单条消息
     */
    public int sendMessage(int fromId, int toId, int deliverId, String content, short messageType);
    	
    /**
     * 发送多条消息
     */
    public int sendMessage(int fromId, int[] toIds, int deliverId, String message, short messageType);
    
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
