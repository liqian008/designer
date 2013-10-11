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
	 * 发送消息
	 */
	@Override
	public int sendMessage(int fromId, int toId, int dialogId, String content, short messageType){
		//保存消息实体
	    Message message = new Message();
		message.setMessage(content);
		message.setMessageType(messageType);
		message.setFromId(fromId);
		message.setToId(toId);
		message.setDialogId(dialogId);
		Date currentTime = new Date(System.currentTimeMillis());
		message.setCreateTime(currentTime);
		int result = save(message);
		return result;
		//保存user_message关系
	}
	
	/**
	 * 批量发送消息
	 */
	@Override
	public int sendMessage(int fromId, int[] toIds, int deliverId, String message, short messageType) {
		if(toIds!=null&&toIds.length>0){
			for(int toId: toIds){
				sendMessage(fromId, toId, deliverId, message, messageType);
			}
			return toIds.length;
		}
		return 0;
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
	public List<Message> queryMessagesByType(int userId, short messageType) {
		MessageCriteria criteria = new MessageCriteria();
		criteria.createCriteria().andToIdEqualTo(userId).andMessageTypeEqualTo(messageType);
		return messageMapper.selectByExample(criteria);
	}
	
	/**
	 * 分页展示消息列表
	 */
    @Override
    public PagingData<Message> pagingQuery(int userId, int dialogId, int pageNo, int pageSize){
        if(pageNo<0) pageNo = 1;
        int offset = (pageNo-1) * pageSize;
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andDialogIdEqualTo(dialogId);
        criteria.setOffset(offset);
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
    public int markRead(int userId, short messageType) {
        Message message = new Message();
        message.setUnread(ConstService.MESSAGE_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andMessageTypeEqualTo(messageType);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }
    
    /**
     * 标记为已读
     */
    @Override
    public int markRead(int userId, long messageId) {
        Message message = new Message();
        message.setUnread(ConstService.MESSAGE_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andIdEqualTo(messageId);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(messageMapper, "messageMapper can't be null");
//        Assert.notNull(userMessageMapper, "userMessageMapper can't be null");
        Assert.notNull(userService, "userService can't be null");
    }

}