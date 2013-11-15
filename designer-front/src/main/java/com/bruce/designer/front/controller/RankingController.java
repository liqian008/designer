package com.bruce.designer.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RankingController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ICounterService counterService;

	@RequestMapping(value = "/hot/day")
	public String day(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
		
		return "rankin/day";
	}
	
//	@RequestMapping(value = "/hot/week")
//	public String week(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
//
//	}
//	
//	@RequestMapping(value = "/hot/month")
//	public String month(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
//
//	}
//	
//	@RequestMapping(value = "/hot/year")
//	public String year(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
//
//	}

}
