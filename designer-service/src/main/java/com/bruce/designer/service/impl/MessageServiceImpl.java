package com.bruce.designer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Message;
import com.bruce.designer.bean.MessageCriteria;
import com.bruce.designer.bean.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.MessageMapper;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.UserService;

@Service
public class MessageServiceImpl implements IMessageService, InitializingBean {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private UserService userService; 

	public int save(Message t) {
		return messageMapper.insert(t);
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
	public int sendMessage(int fromId, int toId, String content, short messageType){
		//保存消息实体
	    Message message = new Message();
		message.setMessage(content);
		message.setMessageType(messageType);
		message.setFromId(fromId);
		message.setToId(toId);
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
	public int sendMessage(int fromId, int[] toIds, String message, short messageType) {
		if(toIds!=null&&toIds.length>0){
			for(int toId: toIds){
				sendMessage(fromId, toId, message, messageType);
			}
			return toIds.length;
		}
		return 0;
	}
	
	/**
     * 未读消息列表
     */
    @Override
    public List<Message> queryInboxMessages(int userId) {
        //select *, count(message_type) from (select * from tb_message ORDER BY id desc ) aliasTb where to_id=3 group by message_type ;
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId);
        return messageMapper.selectByExample(criteria);
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
     * 将用户所有消息都标记为已读
     */
	@Override
    public int markReadAll(int userId) {
        Message message = new Message();
        message.setStatus(ConstService.MESSAGE_STATUS_READ);
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
        message.setStatus(ConstService.MESSAGE_STATUS_READ);
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
        message.setStatus(ConstService.MESSAGE_STATUS_READ);
        //查询条件
        MessageCriteria criteria = new MessageCriteria();
        criteria.createCriteria().andToIdEqualTo(userId).andIdEqualTo(messageId);
        int result = messageMapper.updateByExampleSelective(message, criteria);
        return result;
    }

	/**
	 * 广播至所有用户
	 */
	@Override
	public int broadcast2All(String message) {
		//此api需要重构只获取userId即可
		List<User> userList = userService.queryUsersByStatus(ConstService.USER_STATUS_OPEN);
		if(userList!=null&&userList.size()>0){
		    //需使用批处理
			for(User user: userList){
			    sendMessage(ConstService.MESSAGE_SYSTEM_SOURCE_ID, user.getId(), message, ConstService.MESSAGE_TYPE_BROADCAST);
			}
			return userList.size();
		}
		return 0;
	}

	/**
	 * 广播至所有设计师
	 */
	@Override
	public int broadcase2Designers(String message) {
		//此api需要重构只获取designerId即可
		List<User> desiangerList = userService.queryDesignersByStatus(ConstService.USER_STATUS_OPEN);
		if(desiangerList!=null&&desiangerList.size()>0){
		    //需使用批处理
			for(User user: desiangerList){
			    sendMessage(ConstService.MESSAGE_SYSTEM_SOURCE_ID, user.getId(), message, ConstService.MESSAGE_TYPE_BROADCAST);
			}
			return desiangerList.size();
		}
		return 0;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(messageMapper, "messageMapper can't be null");
//        Assert.notNull(userMessageMapper, "userMessageMapper can't be null");
        Assert.notNull(userService, "userService can't be null");
    }

}
