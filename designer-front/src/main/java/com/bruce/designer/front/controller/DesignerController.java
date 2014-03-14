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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.DesignerHtmlUtils;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;

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

	/*新晋设计师查询limit*/
	public static final int MAIN_LASTEST_DESIGNER_LIMIT = NumberUtils.toInt(ConfigUtil.getString("main_latest_designer_limit"), 20);
	/*新晋设计师查询limit*/
    public static final int SIDEBAR_LASTEST_DESIGNER_LIMIT = NumberUtils.toInt(ConfigUtil.getString("slide_latest_designer_limit"), 5);
    

	@RequestMapping(value = "/designers")
	public String latestDesigners(Model model, HttpServletRequest request) {
	    if(logger.isDebugEnabled()){
            logger.debug("获取新晋设计师");
        }
		List<User> designerList = userService.fallLoadDesignerList(0, MAIN_LASTEST_DESIGNER_LIMIT);
		model.addAttribute("designerList", designerList);
		return "designer/latestDesigners";
	}
	
	/**
	 * 热门设计师
	 * @param model
	 * @param request
	 * @return
	 */
	private String hotDesigners(Model model, int mode, int limit) {
	    if(logger.isDebugEnabled()){
            logger.debug("获取热门设计师，mode:"+mode+", limit: "+limit);
        }
	    List<User> designerList = hotService.fallLoadHotDesigners(mode, limit);
		model.addAttribute("designerList", designerList);
		model.addAttribute("mode", mode);
		return "designer/hotDesigners";
	}
	
	
	//日热门
    @RequestMapping(value = "/hot/dailyDesigners", method = RequestMethod.GET)
    public String hotDailyDesigners(Model model) {
        return hotDesigners(model, IHotService.DAILY_FLAG, ConstFront.HOT_DESIGNER_DAILY_LIMIT); 
    }
    
    //周热门
    @RequestMapping(value = "/hot/weeklyDesigners", method = RequestMethod.GET)
    public String hotWeeklyDesigners(Model model) {
        return hotDesigners(model, IHotService.WEEKLY_FLAG, ConstFront.HOT_DESIGNER_WEEKLY_LIMIT);
    }
    
    //月热门
    @RequestMapping(value = "/hot/monthlyDesigners", method = RequestMethod.GET)
    public String hotMonthlyDesigners(Model model) {
        return hotDesigners(model, IHotService.MONTHLY_FLAG, ConstFront.HOT_DESIGNER_MONTHLY_LIMIT);
    }
    
	
	/**
	 * 新晋设计师
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/designers.json")
	public ModelAndView latestDesigners4Json(Model model, HttpServletRequest request) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax获取新晋设计师");
        }
		int limit = SIDEBAR_LASTEST_DESIGNER_LIMIT;
		List<User> designerList = userService.fallLoadDesignerList(0, limit);
		if (designerList == null || designerList.size() == 0) {
		    if(logger.isDebugEnabled()){
	            logger.debug("ajax获取新晋设计师数量: 0");
	        }
		    return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(designerList);
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
	@Deprecated
	@RequestMapping(value = "/hot/designers.json")
	public ModelAndView hotDesigners4Json(Model model, HttpServletRequest request) {
		int limit = 5;
		List<User> designerList = hotService.fallLoadHotDesigners(0, limit);
		if (designerList == null || designerList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(designerList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	

}
