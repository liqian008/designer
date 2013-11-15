package com.bruce.designer.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.designer.model.User;
import com.bruce.designer.service.IHotService;

/**
 * Handles requests for the application home page.
 */
public class HotController {

	@Autowired
	private IHotService hotService;


	private static final Logger logger = LoggerFactory.getLogger(HotController.class);

	private static final int FULL_LIMIT = 8 * 4;


	@RequestMapping(value = "/hot/designers")
	public String hotDesigner(Model model, HttpServletRequest request) {
		List<User> designerList = hotService.fallLoadHotDesigners(0, FULL_LIMIT);
		model.addAttribute("designerList", designerList);
		return "designer/hotDesigners";
	}
	
	@RequestMapping(value = "/hot/albums")
	public String hotAlbums(Model model, HttpServletRequest request) {
		List<User> designerList = hotService.fallLoadHotDesigners(0, FULL_LIMIT);
		model.addAttribute("designerList", designerList);
		return "album/hotAlbums";
	}
}
