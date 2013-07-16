package com.bruce.designer.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.bean.User;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CommentService commentService;

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@RequestMapping(value = "/inbox")
	public String inbox(Model model) {
		return "inbox";
	}
	
	@RequestMapping(value = "/mark2Read")
    public String mark2Read(Model model) {
        return "mark2Read";
    }
	
	@RequestMapping(value = "/outbox")
    public String outbox(Model model) {
        return "outbox";
    }
	
	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public String settingsGo(Model model, User user) {
	    return "sendMsg";
    }
	
}
