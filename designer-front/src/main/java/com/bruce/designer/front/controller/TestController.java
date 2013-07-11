package com.bruce.designer.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.AlbumSlideService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AlbumSlideService albumSlideService;
    

    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    
    
    @RequestMapping(value = "/carousel", method = RequestMethod.GET)
    public String carousel( Model model) {
        return "carousel";
    }
    
}
