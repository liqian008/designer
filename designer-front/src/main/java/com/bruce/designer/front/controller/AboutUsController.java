package com.bruce.designer.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AboutUsController {

	private static final Logger logger = LoggerFactory.getLogger(AboutUsController.class);
	
	@RequestMapping(value = "/aboutUs")
	public String aboutUs(Model model) {
		if(logger.isDebugEnabled()){
			logger.debug("关于我们");
		}
		return "aboutUs";
	}
	
}
