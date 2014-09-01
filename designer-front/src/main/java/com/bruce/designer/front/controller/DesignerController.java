package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.bruce.designer.service.IUserGraphService;
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
	private IUserGraphService userGraphService; 
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

		if(designerList!=null&&designerList.size()>0){
			Map<Integer, Boolean> followMap = loadFollowMap(request, designerList);
			model.addAttribute("followMap", followMap);
		}
		return "designer/latestDesigners";
	}
	
	/**
	 * 热门设计师
	 * @param model
	 * @param request
	 * @return
	 */
	private String hotDesigners(Model model, HttpServletRequest request, short mode, int limit) {
	    if(logger.isDebugEnabled()){
            logger.debug("获取热门设计师，mode:"+mode+", limit: "+limit);
        }
	    List<User> designerList = hotService.fallLoadHotDesigners(mode, limit);
		model.addAttribute("designerList", designerList);
		model.addAttribute("mode", mode);
		
		if(designerList!=null&&designerList.size()>0){
			Map<Integer, Boolean> followMap = loadFollowMap(request, designerList);
			model.addAttribute("followMap", followMap);
		}
		return "designer/hotDesigners";
	}

	/**
	 * 加载关注状态
	 * @param request
	 * @param designerList
	 * @return
	 */
	private Map<Integer, Boolean> loadFollowMap(HttpServletRequest request, List<User> designerList) {
		Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
		if(designerList!=null&&designerList.size()>0){
			for(User designer: designerList){
				//默认状态下，均设置为未关注
				followMap.put(designer.getId(), false);
			}
			
			User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
			if(user!=null&&followMap!=null&&followMap.size()>0){
				//已登录情况下，查询关注状态
                for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                    int keyId = entry.getKey();
                    entry.setValue(userGraphService.isFollow(user.getId(), keyId));
                }
            }
		}
		return followMap;
	}
	
	
	//日热门
    @RequestMapping(value = "/hot/dailyDesigners", method = RequestMethod.GET)
    public String hotDailyDesigners(Model model, HttpServletRequest request) {
        return hotDesigners(model, request, IHotService.DAILY_FLAG, ConstFront.HOT_DESIGNER_DAILY_LIMIT); 
    }
    
    //周热门
    @RequestMapping(value = "/hot/weeklyDesigners", method = RequestMethod.GET)
    public String hotWeeklyDesigners(Model model ,HttpServletRequest request) {
        return hotDesigners(model, request, IHotService.WEEKLY_FLAG, ConstFront.HOT_DESIGNER_WEEKLY_LIMIT);
    }
    
    //月热门
    @RequestMapping(value = "/hot/monthlyDesigners", method = RequestMethod.GET)
    public String hotMonthlyDesigners(Model model, HttpServletRequest request) {
        return hotDesigners(model, request, IHotService.MONTHLY_FLAG, ConstFront.HOT_DESIGNER_MONTHLY_LIMIT);
    }
    
    //年热门
    @RequestMapping(value = "/hot/yearlyDesigners", method = RequestMethod.GET)
    public String hotYearlyDesigners(Model model, HttpServletRequest request) {
        return hotDesigners(model, request, IHotService.YEARLY_FLAG, ConstFront.HOT_DESIGNER_YEARLY_LIMIT);
    }
    
	
	/**
	 * 新晋设计师
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sideLatestDesigners.json")
	public ModelAndView sideLatestDesigners(Model model, HttpServletRequest request) {
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
		List<User> designerList = hotService.fallLoadHotDesigners((short)0, limit);
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
