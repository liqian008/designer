package com.bruce.designer.front.controller.ajax;

import java.io.IOException;

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
import com.bruce.designer.exception.oauth.DesignerException;
import com.bruce.designer.exception.oauth.JsonResultObject;
import com.bruce.designer.exception.oauth.JsonResultUtil;
import com.bruce.designer.exception.oauth.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.exception.NotLoginException;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private IUploadService uploadService;
    
    @RequestMapping(value="uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResultObject upload(@RequestParam("image") MultipartFile avatarImage, HttpServletRequest request) throws IOException{
        User user;
        try {
            user = getSessionUser(request);
            int userId = user.getId();
            UploadImageResult imageResult = uploadService.uploadImage(avatarImage.getBytes(), userId, "upload.jpg");
            return JsonResultUtil.generateSucceedResult(imageResult);
        } catch (NotLoginException e) {
            return JsonResultUtil.generateExceptionResult(e);
        } catch(Exception e2){
            return JsonResultUtil.generateExceptionResult(new DesignerException(ErrorCode.UPLOAD_IMAGE_ERROR, "上传图片出错，请稍后再试!"));
        }
    }
    
    private User getSessionUser(HttpServletRequest request) throws NotLoginException {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        if(user==null){
            throw new NotLoginException(ErrorCode.USER_NOT_LOGIN, "用户尚未登录，无法执行该操作");
        }
        return user;
    }
}
