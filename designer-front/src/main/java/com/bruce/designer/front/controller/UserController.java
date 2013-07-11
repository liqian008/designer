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
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CommentService commentService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model) {
	    int userId = 0;
        User user = userService.loadById(userId);
        if(user!=null){
            model.addAttribute("", user);
        }else{
            
        }
        return "settings";
	}
	
	@RequestMapping(value = "/settingsGo", method = RequestMethod.POST)
    public String settingsGo(Model model, User user) {
        userService.updateById(user);
	    return "";
    }
	
	@RequestMapping(value = "/headPhotoGo", method = RequestMethod.POST)
    public String headPhotoGo(Model model) {
	    User user = new User();
	    userService.updateById(user);
        return "";
    }
	
	@RequestMapping(value = "/applyDesigner", method = RequestMethod.GET)
    public String applyDesigner(Model model) {
	    int userId = 0;
	    User user = userService.loadById(userId);
	    if(user!=null){
	        model.addAttribute("", user);
	    }else{
	        
	    }
        return "applyDesigner";
    }
    
	@RequestMapping(value = "/applyDesignerGo", method = RequestMethod.POST)
    public String applyDesignerG(Model model, User user) {
        
	    userService.updateById(user);
	    return "";
    }
}
