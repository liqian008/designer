package com.bruce.designer.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFans;
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
            List<Album> albumList = albumService.queryAlbumByUserId(queryUserId);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
            
            if(albumList!=null&&albumList.size()>0){
//                for(Album loopAlbum: albumList){
//                    int albumId = loopAlbum.getId();
////                  List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
////                  loopAlbum.setCommentList(commentList);
//                }
                model.addAttribute("albumList", albumList);
            }
        }
        return "userHome";
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
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
        }
        return "userInfo";
    }
    
    /**
     * 个人主页【关注列表】
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/follow")
    public String userFollow(Model model, HttpServletRequest request, @PathVariable("userId") int queryUserId) {
        
        User queryUser = userService.loadById(queryUserId);
        if(queryUser!=null){
            model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);

            //TODO 获取关注列表
            List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, 1, 20);
            model.addAttribute("followList", followList);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
        }
        return "userFollow";
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
            
            //TODO 获取粉丝列表
            List<UserFans> fansList = userGraphService.getFansListWithUser(queryUserId, 1, 20);
            model.addAttribute("fansList", fansList);
            
            User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
            if(user!=null&&user.getId()>0){
                int userId = user.getId();
                boolean hasFollowed = userGraphService.isFollow(userId, queryUserId);
                model.addAttribute("hasFollowed", hasFollowed);
            }
        }
        return "userFans";
    }
    
    @RequestMapping(value = "/follow")
    public String follow(Model model,  HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        boolean result = userGraphService.follow(userId, uid);
        return "redirect:/index.art";
    }
    
    @RequestMapping(value = "/unfollow")
    public String unflower(Model model, HttpServletRequest request, int uid) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        boolean result = userGraphService.unfollow(userId, uid);
        return "redirect:/index.art";
    }
    
}
