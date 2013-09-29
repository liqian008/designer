package com.bruce.designer.front.controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bruce.designer.bean.User;
import com.bruce.designer.bean.upload.UploadImageResult;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.exception.NotLoginException;
import com.bruce.designer.json.JsonResultObject;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.JsonResultUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private ICommentService commentService;
    
    @RequestMapping(value="usernameExists", method = RequestMethod.POST)
    @ResponseBody
    public JsonResultObject usernameExists(String username){
        boolean usernameExists = false;
        try {
            usernameExists = userService.userExists(username);
            if(usernameExists){
                return JsonResultUtil.generateSucceedResult(usernameExists);
            }else{
                return JsonResultUtil.generateExceptionResult(ErrorCode.USERNAME_EXISTS_ERROR, "用户名已存在!");
            }
        } catch(Exception e){
            return JsonResultUtil.generateExceptionResult(ErrorCode.SYSTEM_ERROR, "系统出错，请稍后再试!");
        }
    }
    
    @RequestMapping(value="uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResultObject upload(@RequestParam("image") MultipartFile image, HttpServletRequest request){
        User user = null;
        try {
            user = getSessionUser(request);
            int userId = user.getId();
            UploadImageResult imageResult = uploadService.uploadImage(image.getBytes(), userId, "upload.jpg");
            return JsonResultUtil.generateSucceedResult(imageResult);
        } catch (NotLoginException e) {//未登录异常
            return JsonResultUtil.generateExceptionResult(e);
        } catch(Exception e2){//系统异常
            return JsonResultUtil.generateExceptionResult(new DesignerException(ErrorCode.UPLOAD_IMAGE_ERROR, "上传图片出错，请稍后再试!"));
        }
    }
    
    @RequestMapping(value="comment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResultObject like(HttpServletRequest request, String comment, int albumId, int albumSlideId, int toId, int designerId){
        User user = null;
        try {
            user = getSessionUser(request);
            int commentResult = commentService.comment("", comment, albumId, albumSlideId, user.getId(), toId, designerId);
            return JsonResultUtil.generateSucceedResult(null);
        } catch (NotLoginException e) {//未登录异常
            return JsonResultUtil.generateExceptionResult(e);
        } catch(Exception e2){//系统异常
            return JsonResultUtil.generateExceptionResult(new DesignerException(ErrorCode.UPLOAD_IMAGE_ERROR, "评论出错，请稍后再试!"));
        }
    }
    
    
    @RequestMapping(value="uploadImage2")
    @ResponseBody
    public JsonResultObject uploadImage2(HttpServletRequest request){
        User user = new User();
        user.setUsername("user");
        return JsonResultUtil.generateSucceedResult(user);
    }
    
    /**
     * 从Session中获取用户对象
     * @param request
     * @return
     * @throws NotLoginException
     */
    private User getSessionUser(HttpServletRequest request) throws NotLoginException {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        if(user==null){
            throw new NotLoginException(ErrorCode.USER_NOT_LOGIN, "用户尚未登录，无法执行该操作");
        }
        return user;
    }
    
    
    
    
}
