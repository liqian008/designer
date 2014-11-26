package com.bruce.designer.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IMessageDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.IndexSlideCriteria;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.MessageCriteria;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IPushService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.MessageUtil;

@Service
public class MessageServiceImpl implements IMessageService, InitializingBean {

	@Autowired
	private IMessageDao messageDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private IPushService pushService;

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
	 * 发送系统广播
	 */
	@Override
	public int sendSystemMessage(int toId, String content) {
		return messageDao.sendMessage(0, ConstService.MESSAGE_DELIVER_ID_BROADCAST, toId, content, ConstService.MESSAGE_TYPE_SYSTEM);
	}

	/**
	 * 批量发送系统广播
	 */
	public int sendSystemMessage(List<Integer> toIdList, String content) {
		if (toIdList != null && toIdList.size() > 0) {
			for (int toId : toIdList) {
				sendSystemMessage(toId, content);
			}
			return toIdList.size();
		}
		return 0;
	}

	/**
	 * 发送消息
	 */
	@Override
	public int sendMessage(long sourceId, int fromId, int toId, String content, int messageType) {
		if (!MessageUtil.isChatMessage(messageType) && fromId != toId) {// 除chat消息外，不可以给自己发消息
			int result = messageDao.sendMessage(sourceId, fromId, toId, content, messageType);
			if(result>0){
				//同时push消息，给客户端
				pushService.pushMessage(messageType, content, sourceId, fromId, toId);
			}
		}
		return 0;
	}

	/**
	 * 批量发送消息
	 */
	@Override
	public int sendMessage(long sourceId, int fromId, Set<Integer> toIdSet, String content, int messageType) {
		if (toIdSet != null && toIdSet.size() > 0) {
			for (int toId : toIdSet) {
				sendMessage(sourceId, fromId, toId, content, messageType);
			}
			return toIdSet.size();
		}
		return 0;
	}

	/**
	 * 发送聊天消息
	 */
	@Override
	public int sendChatMessage(int fromId, int toId, String content) {
		int result = messageDao.sendChatMessage(fromId, toId, content);
		if(result>0){
			//同时push消息，给客户端
			pushService.pushMessage(fromId, content, 0, fromId, toId);
		}
		return result;
	}

	@Override
	public int queryUnreadMessageCount(int userId) {
		return messageDao.queryUnreadMessageCount(userId);
	}

	/**
	 * 消息摘要
	 */
	@Override
	public List<Message> queryMessageSummary(int userId) {
		// Sql：select id, message, message_type, source_id, from_id, to_id,
		// dialog_id, status, create_time, update_time, sum(unread) unread from
		// (select * from tb_message ORDER BY id desc ) aliasMessage where
		// to_id= #{id,jdbcType=INTEGER} group by dialog_id
		return messageDao.queryMessageSummary(userId);
	}

	/**
	 * 根据消息类型查询
	 */
	@Override
	public List<Message> queryMessagesByType(int userId, int messageType) {
		return messageDao.queryMessagesByType(userId, messageType);
	}

	/**
	 * 分页展示消息列表
	 */
	@Override
	public PagingData<Message> pagingQuery(int userId, int messageType, int pageNo, int pageSize) {
		PagingData<Message> messagePagingData = messageDao.pagingQuery(userId, messageType, pageNo, pageSize);
		// List<Message> messageList = null;
		// if (messagePagingData != null && messagePagingData.getPageData() !=
		// null) {
		// messageList = messagePagingData.getPageData();
		// if(messageList!=null&&messageList.size()>0){
		// for(Message message: messageList){
		// int fromId = message.getFromId();
		// User fromUser = userService.loadById(fromId);
		// message.setFromUser(fromUser);
		// }
		// }
		// }
		return messagePagingData;
	}

	/**
	 * 瀑布流方式加载更多消息
	 */
	@Override
	public List<Message> fallLoadMessagesByType(int userId, int messageType, long messageTailId, int limit) {
		List<Message> messageList = messageDao.fallLoadList(userId, messageType, messageTailId, limit);
		return messageList;
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
	public int markRead(int userId, int messageType) {
		return messageDao.markRead(userId, messageType);
	}

	/**
	 * 标记为已读
	 */
	@Override
	public int markRead(int userId, List<Long> messageIdList) {
		return messageDao.markRead(userId, messageIdList);
	}

	/**
	 * 广播至所有用户
	 */
	@Override
	public int broadcast2All(String content) {
		// TODO 此api需要重构只获取userId即可
		List<User> userList = null;// userService.queryUsersByStatus(ConstService.USER_STATUS_OPEN);
		if (userList != null && userList.size() > 0) {
			// 需使用批处理
			long sourceId = 0;
			for (User user : userList) {
				sendMessage(sourceId, ConstService.MESSAGE_DELIVER_ID_BROADCAST, user.getId(), content, ConstService.MESSAGE_TYPE_SYSTEM);
			}
			return userList.size();
		}
		return 0;
	}

	/**
	 * 广播至所有设计师
	 */
	@Override
	public int broadcase2Designers(String content) {
		// TODO 此api需要重构只获取designerId即可
		List<User> desiangerList = null;// userService.queryDesignersByStatus(ConstService.USER_STATUS_OPEN);
		if (desiangerList != null && desiangerList.size() > 0) {
			// 需使用批处理
			long sourceId = 0;
			for (User user : desiangerList) {
				sendMessage(sourceId, ConstService.MESSAGE_DELIVER_ID_BROADCAST, user.getId(), content, ConstService.MESSAGE_TYPE_SYSTEM);
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
	
	
	
	
	
	

	@Override
	public int updateByCriteria(Message t, MessageCriteria criteria) {
		return messageDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(MessageCriteria criteria) {
		return messageDao.deleteByCriteria(criteria);
	}

	@Override
	public List<Message> queryAll(String orderByClause) {
		return messageDao.queryAll(orderByClause);
	}

	@Override
	public List<Message> queryByCriteria(MessageCriteria criteria) {
		return messageDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(MessageCriteria criteria) {
		return messageDao.countByCriteria(criteria);
	}
	
}
