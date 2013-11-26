package com.bruce.designer.front.controller;

import java.util.List;

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
import com.bruce.designer.util.MessageUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@NeedAuthorize
@RequestMapping(value = "settings")
public class MessageController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IMessageService messageService;

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	/**
	 * 我的消息
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value="inbox")
	public String inbox(Model model, HttpServletRequest request) {
		String jsp = "/settings/messageList";
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		int messageType = NumberUtils.toInt(request.getParameter("messageType"), 0);
		List<Message> messageList = null;
		if (messageType <= 0) {// 消息中心
			messageList = messageService.queryMessageSummary(userId);
			model.addAttribute("messageList", messageList);
			//加载fromId的详细资料
			if(messageList!=null&&messageList.size()>0){
				for(Message message: messageList){
					if(!MessageUtil.isBroadcastMessage(message.getMessageType())){
						int fromId = message.getFromId();
						User fromUser = userService.loadById(fromId);
						message.setFromUser(fromUser);
					}
				}
			}
			return "/settings/inbox";
		} else {// 进入消息列表页
			PagingData<Message> messagePagingData = messageService.pagingQuery(userId, messageType, 1, 16);
			if (messagePagingData != null && messagePagingData.getPageData() != null) {
				messageList = messagePagingData.getPageData();
				//加载fromId的详细资料
				if(messageList!=null&&messageList.size()>0){
					for(Message message: messageList){
						int fromId = message.getFromId();
						User fromUser = userService.loadById(fromId);
						message.setFromUser(fromUser);
					}
				}
				
				model.addAttribute("messagePagingData", messagePagingData);
				if (MessageUtil.isChatMessage(messageType)) {
					// 检查toUser是否存在
					int toId = messageType;
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
					jsp = "/settings/messageChatList";
				}
			}
			// 同时将消息标记为已读
			// int readNum = messageService.markRead(userId, messageType);
			model.addAttribute("messageList", messageList);
			return jsp;
		}
	}

	@RequestMapping(value = "/inbox/comments")
	public String inboxComments(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_COMMENT);
		model.addAttribute("messageList", messageList);
		return "inbox";
	}

	@RequestMapping(value = "/inbox/likes")
	public String inboxLikes(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_LIKE);
		model.addAttribute("messageList", messageList);
		return "inbox";
	}

	@RequestMapping(value = "/inbox/favorites")
	public String inboxFavorites(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_FAVORITIES);
		model.addAttribute("messageList", messageList);
		return "inbox";
	}

	@RequestMapping(value = "/inbox/flowers")
	public String flowers(Model model, HttpServletRequest request) {
		return "inbox";
	}

	@RequestMapping(value = "/markRead")
	public String markRead(Model model, HttpServletRequest request) {
		// int result = messageService.markRead(userId, );
		return "markRead";
	}

	@RequestMapping(value = "/markReadAll")
	public String markReadAll(Model model, int userId) {
		int result = messageService.markReadAll(userId);
		return "markRead";
	}

	@RequestMapping(value = "/outbox")
	public String outbox(Model model, HttpServletRequest request) {
		return "outbox";
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

	@ResponseBody
	@RequestMapping(value = "/broadcastMsg")
	public String broadcastMsg() {
		String message = "这是一条系统广播";
		messageService.broadcast2All(message);
		return message;
	}

}
