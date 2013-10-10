package com.bruce.designer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.MessageCriteria;
import com.bruce.designer.model.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IMessageDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;

@Service
public class MessageServiceImpl implements IMessageService, InitializingBean {

	@Autowired
	private IMessageDao messageDao;
	@Autowired
	private IUserService userService; 

	public int save(Message t) {
		return messageDao.save(t);
	}

	public List<Message> queryAll() {
		return messageDao.queryAll();
	}

	public int updateById(Message t) {
		return messageDao.updateById(t);
	}

	public int deleteById(Long id) {
	    return messageDao.deleteById(id);
	}

	public Message loadById(Long id) {
		return messageDao.loadById(id);
	}
	
	/**
	 * 发送消息
	 */
	@Override
	public int sendMessage(int fromId, int toId, int dialogId, String content, short messageType){
		return messageDao.sendMessage(fromId, toId, dialogId, content, messageType);
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
      return messageDao.queryMessageSummary(userId);
    }
    
	/**
	 * 根据消息类型查询
	 */
	@Override
	public List<Message> queryMessagesByType(int userId, short messageType) {
		return messageDao.queryMessagesByType(userId, messageType);
	}
	
	/**
	 * 分页展示消息列表
	 */
    @Override
    public PagingData<Message> pagingQuery(int userId, int dialogId, int pageNo, int pageSize){
//        if(pageNo<0) pageNo = 1;
//        int offset = (pageNo-1) * pageSize;
//        MessageCriteria criteria = new MessageCriteria();
//        criteria.createCriteria().andToIdEqualTo(userId).andDialogIdEqualTo(dialogId);
//        criteria.setOffset(offset);
//        criteria.setLimit(pageSize);
//        criteria.setOrderByClause("id desc");
//        List<Message> messageList = messageDao.selectByExample(criteria); 
//        int totalCount = messageDao.countByExample(criteria);//总条数
//        PagingData<Message> pagingData = new PagingData<Message>(messageList, totalCount, pageNo, pageSize);
//        return pagingData;
        return null;
    }
	
	
	/**
     * 将用户所有消息都标记为已读
     */
	@Override
    public int markReadAll(int userId) {
        return messageDao.markReadAll(userId);
	}
	/**
     * 批量标记为已读
     */
    @Override
    public int markRead(int userId, short messageType) {
        return messageDao.markRead(userId, messageType);
    }
    
    /**
     * 标记为已读
     */
    @Override
    public int markRead(int userId, long messageId) {
        return messageDao.markRead(userId, messageId);
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
			    sendMessage(ConstService.MESSAGE_DELIVER_ID_BROADCAST, user.getId(), ConstService.MESSAGE_DELIVER_ID_BROADCAST, message, ConstService.MESSAGE_TYPE_SYSTEM);
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
			    sendMessage(ConstService.MESSAGE_DELIVER_ID_BROADCAST, user.getId(), ConstService.MESSAGE_DELIVER_ID_BROADCAST,  message, ConstService.MESSAGE_TYPE_SYSTEM);
			}
			return desiangerList.size();
		}
		return 0;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(messageDao, "messageDao can't be null");
        Assert.notNull(userService, "userService can't be null");
    }

}
