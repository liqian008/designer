package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.util.HtmlUtils;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UploadUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DesignerController {

	@Autowired
	private IUserService userService; 
	@Autowired
	private IHotService hotService;

	private static final Logger logger = LoggerFactory.getLogger(DesignerController.class);

	private static final int FULL_LIMIT = 8;

	@RequestMapping(value = "/designers")
	public String latestDesigners(Model model, HttpServletRequest request) {
		List<User> designerList = userService.fallLoadDesignerList(0, FULL_LIMIT);
		model.addAttribute("designerList", designerList);
		return "designer/latestDesigners";
	}
	
	/**
	 * 热门设计师
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hot/designers")
	public String hotDesigner(Model model, HttpServletRequest request) {
//		System.err.println("DesignerController: "+hotService);
		List<User> designerList = hotService.fallLoadHotDesigners(0, FULL_LIMIT);
		model.addAttribute("designerList", designerList);
		return "designer/hotDesigners";
	}

	/**
	 * 新晋设计师
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/designers.json")
	public ModelAndView latestDesigners4Json(Model model, HttpServletRequest request) {
		int limit = 5;
		List<User> designerList = userService.fallLoadDesignerList(0, limit);
		if (designerList == null || designerList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = HtmlUtils.buildFallLoadHtml(designerList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	/**
	 * 右边栏热门设计师
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hot/designers.json")
	public ModelAndView hotDesigners4Json(Model model, HttpServletRequest request) {
		int limit = 5;
		List<User> designerList = hotService.fallLoadHotDesigners(0, limit);
		if (designerList == null || designerList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = HtmlUtils.buildFallLoadHtml(designerList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	

}
