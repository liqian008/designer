package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
    
    @Autowired
    private ICounterService counterService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserGraphService userGraphService;
    @Autowired
    private IAlbumService albumService;

    /**
     * 个人主页【作品集】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/home")
    public String userHome(Model model,  HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
        }else{
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
        return "home/userHome";
    }
    
    /**
     * 个人主页【个人资料】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/info")
    public String userInfo(Model model,  HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
        	model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            
        	long hisFansCount = counterService.getCount(ConstRedis.COUNTER_KEY_FOLLOW + queryUser.getId());
            long hisFollowesCount = counterService.getCount(ConstRedis.COUNTER_KEY_FAN + queryUser.getId());
            model.addAttribute("fansNumber", hisFansCount);
            model.addAttribute("followsNumber", hisFollowesCount);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
            return "home/userInfo";
        }else{
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
    }
    
    @RequestMapping(value = "usernameExists.json")
	public ModelAndView usernameExists(String username) {
		boolean usernameExists = false;
		usernameExists = userService.usernameExists(username);
		if (usernameExists) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_USERNAME_EXISTS));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
    
    @RequestMapping(value = "nicknameExists.json")
	public ModelAndView nicknameExists(String nickname) {
		boolean nicknameExists = false;
		nicknameExists = userService.nicknameExists(nickname);
		if (nicknameExists) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_NICKNAME_EXISTS));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
    
    /**
     * 个人主页【关注列表】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/follows") 
    public String userFollow(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            
            Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
            followMap.put(queryUserId, false);

            //TODO 获取关注列表
            List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, 1, 20);
            model.addAttribute("followList", followList);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                //根据关注人员的名单，抽取关注人员Id以查询关注状态
                if(followList!=null&&followList.size()>0){
                    for(UserFollow userFollow: followList){
                        int followId = userFollow.getFollowId();
                        followMap.put(followId, false);
                    }
                }
                
                if(followMap!=null&&followMap.size()>0){
                    for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                        int keyId = entry.getKey();
                        entry.setValue(userGraphService.isFollow(userId, keyId));
                    }
                }
            }else{
                //无需处理
            }
            model.addAttribute("followMap", followMap);
        }else{
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
        return "home/userFollows";
    }
    
    /**
     * 个人主页【粉丝列表】
     * @param model
     * @param userId
     * @return 
     */
    @RequestMapping(value = "/{userId}/fans")
    public String userFan(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            
            Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
            followMap.put(queryUserId, false);
            
            //TODO 获取粉丝列表
            List<UserFan> fanList = userGraphService.getFanListWithUser(queryUserId, 1, 20);
            model.addAttribute("fanList", fanList);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                //根据粉丝人员的名单，抽取粉丝人员Id以查询关注状态
                if(fanList!=null&&fanList.size()>0){
                    for(UserFan userFan: fanList){
                        int fanId = userFan.getFanId();
                        followMap.put(fanId, false);
                    }
                }
                
                if(followMap!=null&&followMap.size()>0){
                    for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                        int keyId = entry.getKey();
                        entry.setValue(userGraphService.isFollow(userId, keyId));
                    }
                }
            }else{
              //无需处理
            }
            model.addAttribute("followMap", followMap);
        }else{
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
        return "home/userFans";
    }
    
    
    @NeedAuthorize
    @RequestMapping(value = "/follows")
    public ModelAndView follow(Model model,  HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        boolean result = userGraphService.follow(userId, uid);
        if(result){
        	return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
        }else{
        	return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
        }
    }
    
    @NeedAuthorize
    @RequestMapping(value = "/unfollow")
    public ModelAndView unflower(Model model, HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        boolean result = userGraphService.unfollow(userId, uid);
        if(result){
        	return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
        }else{
        	return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
        }
    }
    
}
