package com.bruce.designer.front.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.UserService;
import com.bruce.designer.util.PropertiesUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings(Model model) {
        int userId = 0;
        User user = userService.loadById(userId);
        if (user != null) {
            model.addAttribute("", user);
        } else {

        }
        return "settings";
    }

    @RequestMapping(value = "/settingsGo", method = RequestMethod.POST)
    public String settingsGo(Model model, User user) {
        userService.updateById(user);
        return "";
    }
    
    @RequestMapping(value = "/testAvatar")
    public String testAvatar(Model model, User user) {
        return "testAvatar";
    }
    
    /**
     * 上传头像，生成头像规则为 $uploadPath/avatar/$userid/50.jpg，另有100.jpg与200.jpg
     * @param avatarFile
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
//    @ResponseBody
    public String upload(@RequestParam("avatarImage") MultipartFile avatarImage, HttpServletRequest request) throws IOException{
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        //获取头像保存路径
        String avatarPath = genAvatarPath(userId);
        //确定原始文件名
        String avatarFilename = String.valueOf(userId)+"_original.jpg";
        //构造&保存头像File
        File originAvatar = new File(avatarPath, avatarFilename);
        FileCopyUtils.copy(avatarImage.getBytes(), originAvatar);
        
        BufferedImage src = ImageIO.read(originAvatar); // 读入文件
        int imgSrcWidth = src.getWidth(); // 得到源图宽
        int imgSrcHeight = src.getHeight(); // 得到源图长
        
        String originAvatarUrl = "http://localhost:8080/designer-front/staticFile/avatar/"+avatarFilename;
        
        request.setAttribute("originAvatarUrl", originAvatarUrl);
        
        //返回临时头像的链接
        return "testAvatar";
    }
    
    /**
     * 获取用户头像的保存路径
     * @param userId
     * @return
     */
    private String genAvatarPath(int userId) {
        return PropertiesUtil.getString("avatar_upload_base_path");
    }
    
    /**
     * resize头像批处理
     * @param userId
     * @return
     */
//    private File[] batchSize(File originAvatar) {
//        return null;
//    }

    @RequestMapping(value = "/updateAvatarGo", method = RequestMethod.POST)
    public String headPhotoGo(Model model,  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        
        int userId = user.getId();
        //获取头像保存路径
        String avatarPath = genAvatarPath(userId);
        //确定原始文件名
        String avatarFilename = String.valueOf(userId)+"_original.jpg";
        //构造原始文件
        File originAvatar = new File(avatarPath, avatarFilename);
        String destFilename = String.valueOf(userId)+".jpg";
        
        File destAvatar = new File(avatarPath, destFilename);
        if(originAvatar.exists()&&destAvatar.delete()){
        	originAvatar.renameTo(destAvatar);
        }
        
        //定位临时头像
        //resize并保存成3套临时头像图片（50x50/100x100/200x200）并返回各自url
        //File[] resizedAvatars = batchSize(originAvatar);
        
        //替换为新头像
        request.setAttribute(ConstFront.REDIRECT_PROMPT, "头像更新成功，现在将转入后续页面，请稍候…");
        return "forward:/redirect.art";
    }

    @RequestMapping(value = "/applyDesigner", method = RequestMethod.GET)
    public String applyDesigner(Model model) {
        int userId = 0;
        User user = userService.loadById(userId);
        if (user != null) {
            model.addAttribute("", user);
        } else {

        }
        return "applyDesigner";
    }

    @RequestMapping(value = "/applyDesignerGo", method = RequestMethod.POST)
    public String applyDesignerGo(Model model,  HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的申请资料已成功提交，现在将转入首页，请稍候…");
        return "forward:/redirect.art";
    }

    /**
     * 修改密码
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/changePasswd")
    public String changePasswd(Model model, User user) {
        return "changePasswd";
    }
    
    /**
     * 更新个人资料
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateProfile")
    public String updateProfile(Model model, User user) {
        return "updateProfile";
    }
    
    
    
    /**
     * 我的粉丝
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/myFlowers")
    public String myFlowers(Model model, User user) {
        return "myFlowers";
    }
    
    
    /**
     * 我的关注
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/myFlowerings")
    public String myFlowerings(Model model, User user) {
        return "myFlowerings";
    }
    
    /**
     * 我的收藏
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/myFavorities")
    public String myFavorities(Model model, User user) {
        return "myFavorities";
    }
}
