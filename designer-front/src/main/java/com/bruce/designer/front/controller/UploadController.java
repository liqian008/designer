package com.bruce.designer.front.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.User;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IUploadService uploadService;

	@NeedAuthorize
	@RequestMapping(value = "uploadImage.json", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
		if (image.getSize() > 1024 * 1024 * 2) {// 图片超大
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_OVERSIZE));
		}
		User user = getSessionUser(request);
		int userId = user.getId();
		try {
			UploadImageResult imageResult = uploadService.uploadImage(image.getBytes(), userId, "upload.jpg");
			if (imageResult != null) {
				return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(imageResult));
			}
		} catch (IOException e) {
			logger.error("upload(MultipartFile)", e);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_ERROR));
			
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR));
	}

	/**
	 * 从Session中获取用户对象
	 * 
	 * @param request
	 * @return
	 * @throws NotLoginException
	 */
	private User getSessionUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		return user;
	}

}
