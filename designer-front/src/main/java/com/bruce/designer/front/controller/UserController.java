package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.data.UserboxInfoBean;
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
import com.bruce.designer.service.impl.UserGraphServiceImpl;
import com.bruce.designer.util.ConfigUtil;
import com.qq.connect.javabeans.weibo.FansIdolsBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	private static final int DEFAULT_PAGING_SIZE = 20;
	
	public static final int FOLLOW_PAGE_SIZE = NumberUtils.toInt(ConfigUtil.getString("follow_page_size"), DEFAULT_PAGING_SIZE);
	public static final int FAN_PAGE_SIZE = NumberUtils.toInt(ConfigUtil.getString("fan_page_size"), DEFAULT_PAGING_SIZE);
    
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
            // TODO 重构redis的key
//        	long hisFansCount = counterService.getCount(ConstRedis.COUNTER_KEY_FOLLOW + queryUser.getId());
//            long hisFollowesCount = counterService.getCount(ConstRedis.COUNTER_KEY_FAN + queryUser.getId());
        	//关注数
//        	long hisFollowsCount = userGraphService.getFollowCount(queryUserId);
//            model.addAttribute("followsCount", hisFollowsCount);
//            
//            //粉丝数
//            long hisFansCount = 0;
//            if(queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED){
//            	//设计师身份才查询粉丝数量
//            	hisFansCount = userGraphService.getFanCount(queryUserId);
//            }
//            model.addAttribute("fansCount", hisFansCount);
            
//            boolean hasFollowed = false;
//            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//            if(user!=null&&user.getId()>0){
//                int userId = user.getId();
//                hasFollowed = userGraphService.isFollow(userId, queryUserId);
//                model.addAttribute("hasFollowed", hasFollowed);
//            }
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
     * ajax获取用户资料（关注数、粉丝数，专辑数，关注状态）
     * @param username
     * @return
     */
    @RequestMapping(value = "userboxInfo.json")
   	public ModelAndView userboxInfo(HttpServletRequest request, int queryUserId) {
    	//TODO 用户发表的专辑数
    	int followsCount = (int) userGraphService.getFollowCount(queryUserId);
    	int fansCount = (int) userGraphService.getFanCount(queryUserId);
    	User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
    	boolean hasFollowed = false;
    	if(user!=null&&userGraphService.isFollow(user.getId(), queryUserId)){//已关注
        	hasFollowed = true;
        }
    	UserboxInfoBean userboxInfo = new UserboxInfoBean(hasFollowed, 0, fansCount, followsCount);
    	return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(userboxInfo));
   	}
    
    /**
     * 个人主页【关注列表】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/follows") 
    public String userFollows(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
    	
    	User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            
            Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
            followMap.put(queryUserId, false);

            // TODO 获取关注列表
            int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
    		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), FOLLOW_PAGE_SIZE);
    		
            List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, pageNo, pageSize);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            
            if(followList!=null&&followList.size()>0){
            	for(int i=followList.size()-1; i>=0;i--){
            		UserFollow userFollow = followList.get(i);
            		//只取有效用户
            		if(userFollow!=null&&userFollow.getFollowUser()!=null){
            			 //根据关注人员的名单，抽取关注人员Id以查询关注状态
            			if(user!=null&&user.getId()>0){
            				int followId = userFollow.getFollowId();
            				//默认状态为未关注
            				followMap.put(followId, false);
                        }
            		}else{
            			followList.remove(i);
            		}
            	}
            	
//                	for(UserFollow userFollow: followList){
//                    	if(userFollow!=null&&userFollow.getFollowUser()!=null){
//	                        int followId = userFollow.getFollowId();
//	                        followMap.put(followId, false);
//                    	}
//                    }
                    
                //用户已登录且map中有数据，需要计算关注状态
                if(user!=null&&followMap!=null&&followMap.size()>0){
                    for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                        int keyId = entry.getKey();
                        entry.setValue(userGraphService.isFollow(user.getId(), keyId));
                    }
                }
            }else{
                //无需处理
            }
            
            //分页数据
            int followCount = (int) userGraphService.getFollowCount(queryUserId);
            PagingData<UserFollow> followsPagingData = new PagingData<UserFollow>(followList, followCount, pageNo, pageSize);
            model.addAttribute("followsPagingData", followsPagingData);

            model.addAttribute("followList", followList);
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
    public String userFans(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);
            
            Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
            followMap.put(queryUserId, false);

            // TODO 获取粉丝列表
            int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
    		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), FAN_PAGE_SIZE);
    		
            List<UserFan> fanList = userGraphService.getFanListWithUser(queryUserId, pageNo, pageSize);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(fanList!=null&&fanList.size()>0){
            	for(int i=fanList.size()-1; i>=0;i--){
            		UserFan userFan = fanList.get(i);
            		//只取有效用户
            		if(userFan!=null&&userFan.getFanUser()!=null){
            			//根据粉丝人员的名单，抽取粉丝人员Id以查询关注状态
            			if(user!=null&&user.getId()>0){
	            			int fanId = userFan.getFanId();
	                        followMap.put(fanId, false);
            			}
            		}else{//移除无效用户
            			fanList.remove(i);
            		}
            	}
                
//                if(fanList!=null&&fanList.size()>0){
//                    for(UserFan userFan: fanList){
//                        int fanId = userFan.getFanId();
//                        followMap.put(fanId, false);
//                    }
//                }
                
                if(user!=null&&followMap!=null&&followMap.size()>0){
                    for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                        int keyId = entry.getKey();
                        entry.setValue(userGraphService.isFollow(user.getId(), keyId));
                    }
                }
            }else{
              //无需处理
            }
            
            //分页数据
            int fanCount = (int) userGraphService.getFanCount(queryUserId);
            PagingData<UserFan> fansPagingData = new PagingData<UserFan>(fanList, fanCount, pageNo, pageSize);
            model.addAttribute("fansPagingData", fansPagingData);
            
            model.addAttribute("followMap", followMap);
            model.addAttribute("fanList", fanList);
        }else{
        	throw new DesignerException(ErrorCode.USER_NOT_EXIST);
        }
        return "home/userFans";
    }
    
    /**
     * 
     * @param model
     * @param request
     * @param followedId 被关注人的id
     * @return
     */
    @NeedAuthorize
    @RequestMapping(value = "/follow.json")
    public ModelAndView follow(Model model,  HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        if(userId==uid){
        	//不能关注自己
        	return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.GRAPH_FOLLOW_SELF_DENIED));
        }else{
	        User followUser = userService.loadById(uid);
	        if(followUser!=null){
	        	if(followUser.getDesignerStatus()!=ConstService.DESIGNER_APPLY_APPROVED){
	            	//不能关注一般用户（只能关注设计师）
	            	return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.GRAPH_FOLLOW_COMMONUSER_DENIED));
	            }
		        boolean result = userGraphService.follow(userId, uid);
		        if(result){
		        	return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		        }
	        }
        }
        return ResponseBuilderUtil.SUBMIT_FAILED_VIEW; 
    }
    
    @NeedAuthorize
    @RequestMapping(value = "/unfollow.json")
    public ModelAndView unfollow(Model model, HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        boolean result = userGraphService.unfollow(userId, uid);
        if(result){
        	return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
        }else{
        	return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.GRAPH_UNFOLLOW_FAILED));
        }
    }
    
}
