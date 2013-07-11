package com.bruce.designer.front.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.bean.Comment;
import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.CommentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@RequestMapping(value = "/postComment", method = RequestMethod.POST)
	public String postComment(Model model, HttpServletRequest request, int albumId, int albumPostId, String comment) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		if(user!=null){
			Comment commentBean = new Comment();
			commentBean.setComment(comment);
			commentBean.setAlbumId(albumId);
			commentBean.setAlbumContentId(albumPostId);
			commentBean.setUserId(user.getId());
			commentBean.setNickname(user.getNickname());
			Date currentTime = new Date();
			commentBean.setCreateTime(currentTime);
			commentBean.setUpdateTime(currentTime);
			int result = commentService.save(commentBean);
		}
		return "redirect:/";
	}
	
}
