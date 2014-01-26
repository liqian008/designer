package com.bruce.designer.front.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.model.Message;
import com.bruce.designer.model.User;
import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.impl.UserGraphServiceImpl;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.MessageUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@NeedAuthorize
@RequestMapping(value = "settings")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IMessageService messageService;
	
	public static final int MESSAGE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("message_limit"), 20);

	/**
	 * 我的消息
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	
//	@RequestMapping(value="inbox")
//	public String inbox(Model model, HttpServletRequest request) {
//		String jsp = "/settings/msgboxList";
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		int userId = user.getId();
//
//		int messageType = NumberUtils.toInt(request.getParameter("messageType"), 0);
//		List<Message> messageList = null;
//		if (messageType <= 0) {// 消息中心
//			messageList = messageService.queryMessageSummary(userId);
//			model.addAttribute("messageList", messageList);
//			//加载fromId的详细资料
//			if(messageList!=null&&messageList.size()>0){
//				for(Message message: messageList){
//					if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
//						int fromId = message.getFromId();
//						User fromUser = userService.loadById(fromId);
//						message.setFromUser(fromUser);
//					}
//				}
//			}
//			return "/settings/inbox";
//		} else {// 进入消息列表页
//
//			int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
//			int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 10);
//			
//			PagingData<Message> messagePagingData = messageService.pagingQuery(userId, messageType, pageNo, pageSize);
//			if (messagePagingData != null && messagePagingData.getPagingData() != null) {
//				messageList = messagePagingData.getPagingData();
//				//加载fromId的详细资料
//				if(messageList!=null&&messageList.size()>0){
//					for(Message message: messageList){
//						int fromId = message.getFromId();
//						User fromUser = userService.loadById(fromId);
//						message.setFromUser(fromUser);
//					}
//				}
//				
//				model.addAttribute("messagePagingData", messagePagingData);
//				if (MessageUtil.isChatMessage(messageType)) {
//					// 检查toUser是否存在
//					int toId = messageType;
//					if (toId == userId) {//不能给自己发消息
//						throw new DesignerException(ErrorCode.MESSAGE_TO_SELF);
//					} else {
//						User toUser = userService.loadById(toId);
//						if (toUser == null || toUser.getId() == null) {
//							throw new DesignerException(ErrorCode.USER_NOT_EXIST);
//						} else {
//							model.addAttribute(ConstFront.MESSAGE_TARGET_USER_ATTRIBUTE, toUser);
//						}
//					}
//					jsp = "/settings/msgboxChatList";
//				}
//			}
//			// 同时将消息标记为已读
//			// int readNum = messageService.markRead(userId, messageType);
//			model.addAttribute("messageList", messageList);
//			return jsp;
//		}
//	}
	
	/**
	 * 消息中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="msgbox")
	public String msgbox(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		// 消息中心
//		int messageType = NumberUtils.toInt(request.getParameter("messageType"), 0);
		List<Message> messageList = messageService.queryMessageSummary(userId);
		model.addAttribute("messageList", messageList);
		//加载fromId的详细资料
		if(messageList!=null&&messageList.size()>0){
			List<Integer> fromIdList = new ArrayList<Integer>();
			for(Message message: messageList){
				//非系统广播，先构造fromId列表
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					int fromId = message.getFromId();
					fromIdList.add(fromId);
				}
			}
			//获取用户map
			Map<Integer, User> fromUserMap = userService.getUserMap(fromIdList);
			
			for(Message message: messageList){
				//非系统广播，加载fromId对应的用户信息
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					int fromId = message.getFromId();
					message.setFromUser(fromUserMap.get(fromId));
				}
			}
		}
		return "/settings/msgbox/msgbox";
	}

	@RequestMapping(value = "/msgbox/sys")
	public String sys(Model model, HttpServletRequest request) {
		PagingData<Message> messagePagingData = getPagingDataByMessageType(request, ConstService.MESSAGE_TYPE_SYSTEM);
		model.addAttribute("messagePagingData", messagePagingData);
		return "/settings/msgbox/sys";
	}
	
	@RequestMapping(value = "/msgbox/comments")
	public String comments(Model model, HttpServletRequest request) {
		PagingData<Message> messagePagingData = getPagingDataByMessageType(request, ConstService.MESSAGE_TYPE_COMMENT);
		model.addAttribute("messagePagingData", messagePagingData);
		return "/settings/msgbox/comments";
	}

	@RequestMapping(value = "/msgbox/likes")
	public String likes(Model model, HttpServletRequest request) {
		PagingData<Message> messagePagingData = getPagingDataByMessageType(request, ConstService.MESSAGE_TYPE_LIKE);
		model.addAttribute("messagePagingData", messagePagingData);
		return "/settings/msgbox/likes";
	}
	
	@RequestMapping(value = "/msgbox/chat")
	public String chats(Model model, HttpServletRequest request, int toId) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		// 检查toUser是否存在
		if (toId == userId) {//不能给自己发消息
			throw new DesignerException(ErrorCode.MESSAGE_TO_SELF);
		} else {
			User toUser = userService.loadById(toId);
			if (toUser == null || toUser.getId() == null) {
				throw new DesignerException(ErrorCode.USER_NOT_EXIST);
			} else {
				model.addAttribute(ConstFront.MESSAGE_TARGET_USER_ATTRIBUTE, toUser);
			}
		}
		
		int messageType = toId;
		PagingData<Message> messagePagingData = getPagingDataByMessageType(request, messageType);
		model.addAttribute("messagePagingData", messagePagingData);
		return "/settings/msgbox/chat";
	}
	
	@RequestMapping(value = "/msgbox/favorites")
	public String favorites(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_FAVORITIES);
		model.addAttribute("messageList", messageList);
		return "/settings/msgbox/favorites";
	}

	@RequestMapping(value = "/msgs/flowers")
	public String flowers(Model model, HttpServletRequest request) {
		return "/settings/msgbox/flowers";
	}

	/**
	 * 查询未读消息数，供首页headerBar上展示
	 * @param model
	 * @param request
	 * @param toId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/unreadMessageCount.json")
	public ModelAndView unreadMessageCount(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		if(user!=null){
			int userId = user.getId();
			int result = messageService.queryUnreadMessageCount(userId);
			if (result > 0) {
				return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(result));
			}
		}
		return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
	}

	@NeedAuthorize
	@RequestMapping(value = "/sendMsg.json", method = RequestMethod.POST)
	public ModelAndView sendMsg(Model model, HttpServletRequest request, int toId, String content) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		if (!MessageUtil.isChatMessage(toId)) {// 只能再聊天场景下回复消息
			throw new DesignerException(ErrorCode.MESSAGE_UNSUPPORT_TYPE);
		} else if (toId == userId) {// 不能给自己发消息
			throw new DesignerException(ErrorCode.MESSAGE_TO_SELF);
		}
		int result = messageService.sendChatMessage(userId, toId, content);
		if (result > 0) {
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		} else {
			return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
	}
	
	/**
	 * 分页获取消息数据
	 * @param request
	 * @param messageType
	 * @return
	 */
	private PagingData<Message> getPagingDataByMessageType(HttpServletRequest request, int messageType) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), MESSAGE_LIMIT);
		PagingData<Message> messagePagingData = messageService.pagingQuery(userId, messageType, pageNo, pageSize);
		List<Message> messageList = messagePagingData.getPagingData();
		if(messageList!=null&&messageList.size()>0){
			
			List<Integer> fromIdList = new ArrayList<Integer>();
			for(Message message: messageList){
				//非系统广播，先构造fromId列表
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					int fromId = message.getFromId();
					fromIdList.add(fromId);
				}
			}
			//获取用户map
			Map<Integer, User> fromUserMap = userService.getUserMap(fromIdList);
			
			for(Message message: messageList){
				//非系统广播，加载fromId对应的用户信息
				if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
					int fromId = message.getFromId();
					message.setFromUser(fromUserMap.get(fromId));
				}
			}
			
			//加载message的fromUser用户信息
			// TODO for循环优化，改为map方式，避免重复取用户数据
			
			
//			for(Message message: messageList){
//				int fromId = message.getFromId();
//				User fromUser = userService.loadById(fromId);
//				message.setFromUser(fromUser);
//			}
			
			//读取消息后标记为已读
			messageService.markRead(userId, messageType);
		}
		return messagePagingData;
	}
	
//	@RequestMapping(value = "/outbox")
//	public String outbox(Model model, HttpServletRequest request) {
//		return "outbox";
//	}
	
//	@RequestMapping(value = "/markRead")
//	public String markRead(Model model, HttpServletRequest request) {
//		// int result = messageService.markRead(userId, );
//		return "markRead";
//	}
//
//	@RequestMapping(value = "/markReadAll")
//	public String markReadAll(Model model, int userId) {
//		int result = messageService.markReadAll(userId);
//		return "markRead";
//	}

}
