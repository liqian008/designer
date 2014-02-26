package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IMessageDao;
import com.bruce.designer.dao.mapper.MessageMapper;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.MessageCriteria;
import com.bruce.designer.service.IUserService;

@Repository
public class MessageDaoImpl implements IMessageDao, InitializingBean {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private IUserService userService; 

	public int save(Message t) {
		return messageMapper.insertSelective(t);
	}

	public List<Message> queryAll() {
		return messageMapper.selectByExample(null);
	}

	public int updateById(Message t) {
		return messageMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Long id) {
	    return messageMapper.deleteByPrimaryKey(id);
	}

	public Message loadById(Long id) {
		return messageMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 消息摘要
     */
    @Override
    public int queryUnreadMessageCount(int userId){
    	MessageCriteria criteria = new MessageCriteria();
		criteria.createCriteria().andToIdEqualTo(userId).andUnreadEqualTo(ConstService.MESSAGE_UNREAD);
		return messageMapper.countByExample(criteria);
    }
	
	/**
	 * 发送消息
	 * @param fromId
	 * @param content
	 * @param messageType
	 * @param toId
	 * @return
	 */
	@Override
	public int sendMessage(long sourceId, int fromId, int toId, String content, int messageType) { 
		return sendMessage(sourceId, fromId, toId, content, messageType, ConstService.MESSAGE_UNREAD);
	}
	
	private int sendMessage(long sourceId, int fromId, int toId, String content, int messageType, short unreadStatus) {
		//保存消息实体
		Message message = new Message();
		message.setSourceId(sourceId);
		message.setFromId(fromId);
		message.setToId(toId);
		message.setMessage(content);
		message.setMessageType(messageType);
		message.setUnread(unreadStatus);
		Date currentTime = new Date(System.currentTimeMillis());
		message.setCreateTime(currentTime);
		int result = save(message);
		return result;
	}
	
	/**
	 * 批量发送消息
	 */
	@Override
	public int sendMessage(long sourceId, int fromId, List<Integer> toIdList, String content, int messageType) {
		if(toIdList!=null&&toIdList.size()>0){
			for(int toId: toIdList){
				sendMessage(sourceId, fromId, toId, content, messageType);
			}
			return toIdList.size();
		}
		return 0;
	}

	
	/**
	 * 发送聊天消息（对话类型的消息列表需保存两份，对方收件箱&自己的发件箱）
	 * @param fromId
	 * @param toId
	 * @param content
	 * @return
	 */
	@Override
	public int sendChatMessage(int fromId, int toId, String content) {
		int result = 0;
		//写入对方的收件箱
		int messageType = fromId;
		int sourceId = 0;
		sendMessage(sourceId, fromId, toId, content, messageType, ConstService.MESSAGE_UNREAD);
		//写入自己的发件箱（置为已读状态，不会增加未读消息数）
		messageType = toId;
		result = sendMessage(sourceId, fromId, fromId, content, messageType, ConstService.MESSAGE_READ);
		return result;
	}
	
	/**
     * 消息摘要
     */
    @Override
    public List<Message> queryMessageSummary(int userId){
        //Sql：select id, message, message_type, source_id, from_id, to_id, dialog_id, status, create_time, update_time, sum(unread) unread from (select * from tb_message ORDER BY id desc ) aliasMessage where to_id= #{id,jdbcType=INTEGER} group by dialog_id 
    	return messageMapper.queryMessageSummary(userId);
    }
    
	/**
	 * 根据消息类型查询
	 */
	@Override
	public List<Message> queryMessagesByType(int userId, int messageType) {
		MessageCriteria criteria = new MessageCriteria();
		criteria.createCriteria().andToIdEqualTo(userId).andMessageTypeEqualTo(messageType);
		return messageMapper.selectByExample(criteria);
	}
	
	/**
	 * 分页展示消息列表
	 */
    @Override
    public PagingData<Message> pagingQuery(int userId, int messageType, int pageNo, int pageSize){
        if(pageNo<0) pageNo = 1;
        int start = (pageNo-1) * pageSize;
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andMessageTypeEqualTo(messageType);
        criteria.setStart(start);
        criteria.setLimit(pageSize);
        criteria.setOrderByClause("id desc");
        List<Message> messageList = messageMapper.selectByExample(criteria); 
        int totalCount = messageMapper.countByExample(criteria);//总条数
        PagingData<Message> pagingData = new PagingData<Message>(messageList, totalCount, pageNo, pageSize);
        return pagingData;
    }
	
	
	/**
     * 将用户所有消息都标记为已读
     */
	@Override
    public int markReadAll(int userId) {
        Message message = new Message();
        message.setUnread(ConstService.MESSAGE_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }
	
	/**
     * 批量标记为已读
     */
    @Override
    public int markRead(int userId, int messageType) {
        Message message = new Message();
        message.setUnread(ConstService.MESSAGE_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andMessageTypeEqualTo(messageType);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }
    
    /**
     * 批量标记为已读
     */
    @Override
    public int markRead(int userId, List<Long> messageIdList) {
        Message message = new Message();
        message.setUnread(ConstService.MESSAGE_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andIdIn(messageIdList);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }
    

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(messageMapper, "messageMapper can't be null");
        Assert.notNull(userService, "userService can't be null");
    }

	@Override
	public List<Message> fallLoadList(Long tailId, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
