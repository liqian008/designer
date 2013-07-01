package com.bruce.designer.front.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.bean.TbAlbum;
import com.bruce.designer.constant.Constants;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FrontController {

	@Autowired
	private UserService userService;
	@Autowired
	private AlbumService albumService;

	private static final Logger logger = LoggerFactory
			.getLogger(FrontController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

//		logger.info("userService:" + userService.queryAll());

		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model) {
		
		List<TbAlbum> albumList = albumService.queryAlbumByStatus(Constants.ALBUM_OPEN_STATUS);
		
		model.addAttribute("albumList", albumList);
		return "index";
	}
}
