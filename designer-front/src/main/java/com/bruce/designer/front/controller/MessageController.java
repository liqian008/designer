package com.bruce.designer.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.designer.bean.User;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private IMessageService messageService;

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@RequestMapping(value = "/inbox")
	public String inbox(Model model) {
		return "inbox";
	}
	
	@RequestMapping(value = "/markRead")
    public String markRead(Model model) {
        return "markRead";
    }
	
	@RequestMapping(value = "/outbox")
    public String outbox(Model model) {
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
