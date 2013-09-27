package com.bruce.designer.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.designer.bean.Message;
import com.bruce.designer.bean.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="settings")
public class MessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private IMessageService messageService;

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@RequestMapping(value = "/inbox/comments")
    public String inboxComments(Model model, HttpServletRequest request) {
	    User user = (User)request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_COMMENT);
        model.addAttribute("messageList", messageList);
        return "inbox";
    }
	
	@RequestMapping(value = "/inbox/likes")
    public String inboxLikes(Model model, HttpServletRequest request) {
	    User user = (User)request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        List<Message> messageList = messageService.queryMessagesByType(userId, ConstService.MESSAGE_TYPE_LIKE);
        model.addAttribute("messageList", messageList);
        return "inbox";
    }
	

    @RequestMapping(value = "/inbox/favorites")
    public String inboxFavorites(Model model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(ConstFront.CURRENT_USER);
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
	    //int result = messageService.markRead(userId, );
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
	
	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public String sendMsg(Model model, User user) {
	    return "sendMsg";
    }
	
	@ResponseBody
	@RequestMapping(value = "/broadcastMsg")
    public String broadcastMsg() {
	    String message = "这是一条系统广播";
	    messageService.broadcast2All(message);
        return message;
    }
	
}
