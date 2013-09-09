package com.bruce.designer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Message;
import com.bruce.designer.bean.MessageCriteria;
import com.bruce.designer.bean.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.MessageMapper;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.UserService;

@Service
public class MessageServiceImpl implements IMessageService {

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
	
	@Override
	public int sendMessage(int fromId, int toId, String message, short messageType){
		Message msgBean = new Message();
		msgBean.setFromId(fromId);
		msgBean.setToId(toId);
		msgBean.setMessage(message);
		msgBean.setMessageType(messageType);
		Date currentTime = new Date(System.currentTimeMillis());
		msgBean.setCreateTime(currentTime);
		return save(msgBean);
	}

	@Override
	public int sendMessage(int fromId, int[] toIds, String message,
			short messageType) {
		if(toIds!=null&&toIds.length>0){
			for(int toId: toIds){
				sendMessage(fromId, toId, message, messageType);
			}
			return toIds.length;
		}
		return 0;
	}
	
	
	public List<Message> queryUnreadMessages(int userId) {
		MessageCriteria criteria = new MessageCriteria();
		criteria.createCriteria().andToIdEqualTo(userId).andStatusEqualTo(ConstService.MESSAGE_STATUS_UNREAD);
		return messageMapper.selectByExample(criteria);
	}

	/**
	 * 广播至所有用户
	 */
	@Override
	public int broadcast2All(Message message) {
		//此api需要重构只获取userId即可
		List<User> userList = userService.queryUsersByStatus(ConstService.USER_STATUS_OPEN);
		if(userList!=null&&userList.size()>0){
			for(User user: userList){
				message.setFromId(ConstService.SYSTEM_USER_ID);
				message.setToId(user.getId());
				save(message);
			}
			return userList.size();
		}
		return 0;
	}

	/**
	 * 广播至所有设计师
	 */
	@Override
	public int broadcase2Designers(Message message) {
		//此api需要重构只获取designerId即可
		List<User> desiangerList = userService.queryDesignersByStatus(ConstService.USER_STATUS_OPEN);
		if(desiangerList!=null&&desiangerList.size()>0){
			for(User user: desiangerList){
				message.setFromId(ConstService.SYSTEM_USER_ID);
				message.setToId(user.getId());
				save(message);
			}
			return desiangerList.size();
		}
		return 0;
	}

	
	

}
