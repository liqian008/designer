package com.bruce.designer.front.controller.mcs;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/api")
public class McsUserController {
    
    private static final Logger logger = LoggerFactory.getLogger(McsUserController.class);

	private static final int DEFAULT_PAGING_SIZE = 20;
	
	public static final int FOLLOW_PAGE_SIZE = NumberUtils.toInt(ConfigUtil.getString("follow_page_size"), DEFAULT_PAGING_SIZE);
	public static final int FAN_PAGE_SIZE = NumberUtils.toInt(ConfigUtil.getString("fan_page_size"), DEFAULT_PAGING_SIZE);
    
    @Autowired
    private IUserService userService; 
    @Autowired
    private IUserGraphService userGraphService;

    /**
     * 个人主页【个人资料】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/info.json")
    public ModelAndView userInfo(Model model,  HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        if(logger.isDebugEnabled()){
            logger.debug("加载用户["+queryUserId+"]的个人资料");
        }
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
        	//判断是否是设计师
        	int followsCount = (int) userGraphService.getFollowCount(queryUserId);
        	int fansCount = (int) userGraphService.getFanCount(queryUserId);
//        	queryUser.setFollowsCount(followsCount);
//        	queryUser.setFansCount(fansCount);
        	
        	//TODO 判断关注状态，以呈现不同状态的按钮
        	
        	Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("userinfo", queryUser);
			dataMap.put("followsCount", followsCount);
			dataMap.put("fansCount", fansCount);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
        }else{
            if(logger.isErrorEnabled()){
                logger.error("加载用户["+queryUserId+"]个人资料信息失败");
            }
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
    }
    
    /**
     * 个人主页【关注列表】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/follows.json") 
    public ModelAndView userFollows(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的关注列表");
        }
    	User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
            followMap.put(queryUserId, false);

            // TODO 获取关注列表
            int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
    		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), FOLLOW_PAGE_SIZE);
    		
            List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, pageNo, pageSize);
            
            if(followList!=null&&followList.size()>0){
            	for(int i=followList.size()-1; i>=0;i--){
            		UserFollow userFollow = followList.get(i);
            		//只取有效用户
            		if(userFollow!=null&&userFollow.getFollowUser()!=null){
            			 //根据关注人员的名单，抽取关注人员Id以查询关注状态
//            			if(user!=null&&user.getId()>0){
//            				int followId = userFollow.getFollowId();
//            				//默认状态为未关注
//            				followMap.put(followId, false);
//                        }
            		}else{
            			followList.remove(i);
            		}
            	}
            	
                //用户已登录且map中有数据，需要计算关注状态
//                if(user!=null&&followMap!=null&&followMap.size()>0){
//                    for(Entry<Integer, Boolean> entry: followMap.entrySet()){
//                        int keyId = entry.getKey();
//                        entry.setValue(userGraphService.isFollow(user.getId(), keyId));
//                    }
//                }
            }else{
                //无需处理
            }
            //构造返回数据
            Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("followList", followList);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
        }else{
            if(logger.isErrorEnabled()){
                logger.error("查询用户["+queryUserId+"]的关注列表失败");
            }
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
    }
    
    /**
     * 个人主页【粉丝列表】
     * @param model
     * @param userId
     * @return 
     */
    @RequestMapping(value = "/{userId}/fans.json")
    public ModelAndView userFans(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的粉丝列表");
        }
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            
            Map<Integer, Boolean> fanMap = new HashMap<Integer, Boolean>();
            fanMap.put(queryUserId, false);

            // TODO 获取粉丝列表
            int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
    		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), FAN_PAGE_SIZE);
    		
            List<UserFan> fanList = userGraphService.getFanListWithUser(queryUserId, pageNo, pageSize);
            
//            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(fanList!=null&&fanList.size()>0){
            	for(int i=fanList.size()-1; i>=0;i--){
            		UserFan userFan = fanList.get(i);
            		//只取有效用户
            		if(userFan!=null&&userFan.getFanUser()!=null){
            			//根据粉丝人员的名单，抽取粉丝人员Id以查询关注状态
//            			if(user!=null&&user.getId()>0){
//	            			int fanId = userFan.getFanId();
//	                        fanMap.put(fanId, false);
//            			}
            		}else{//移除无效用户
            			fanList.remove(i);
            		}
            	}
                
                
//                if(user!=null&&fanMap!=null&&fanMap.size()>0){
//                    for(Entry<Integer, Boolean> entry: fanMap.entrySet()){
//                        int keyId = entry.getKey();
//                        entry.setValue(userGraphService.isFollow(user.getId(), keyId));
//                    }
//                }
            }else{
              //无需处理
            }
            
            Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("fanList", fanList);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
        }else{
            if(logger.isErrorEnabled()){
                logger.error("查询用户["+queryUserId+"]的粉丝列表失败");
            }
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
    }
    
    @RequestMapping(value = "usernameExists.json")
	public ModelAndView usernameExists(String username) {
        if(logger.isDebugEnabled()){
            logger.debug("检查用户名["+username+"]是否注册");
        }
		boolean usernameExists = false;
		usernameExists = userService.usernameExists(username);
		if (usernameExists) {
		    if(logger.isDebugEnabled()){
	            logger.debug("用户名["+username+"]已注册");
	        }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_USERNAME_EXISTS));
		} else {
		    if(logger.isDebugEnabled()){
                logger.debug("用户名["+username+"]未注册，可以使用");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
    
    @RequestMapping(value = "nicknameExists.json")
	public ModelAndView nicknameExists(String nickname) {
		boolean nicknameExists = false;
		if(logger.isDebugEnabled()){
            logger.debug("检查用户昵称["+nickname+"]是否注册");
        }
		nicknameExists = userService.nicknameExists(nickname);
		if (nicknameExists) {
		    if(logger.isDebugEnabled()){
                logger.debug("用户昵称["+nickname+"]已注册");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_NICKNAME_EXISTS));
		} else {
		    if(logger.isDebugEnabled()){
                logger.debug("用户昵称["+nickname+"]未注册，可以使用");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
}
