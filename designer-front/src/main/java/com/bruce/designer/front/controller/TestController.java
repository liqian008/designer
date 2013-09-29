package com.bruce.designer.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IAlbumSlideService albumSlideService;
    

    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    
    
    @RequestMapping(value = "/carousel", method = RequestMethod.GET)
    public String carousel( Model model) {
        return "carousel";
    }
    
}
