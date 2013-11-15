package com.bruce.designer.front.controller.ajax;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstDateFormat;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.User;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AjaxController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ICommentService commentService;

	@RequestMapping(value = "usernameAvailable.json")
	public ModelAndView usernameAvailable(String username) {
		boolean usernameExists = false;
		usernameExists = userService.userExists(username);
		if (usernameExists) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_USERNAME_EXISTS));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());

			// return ResponseBuilderUtil.buildSuccessResponse();
		}
	}

	// @NeedAuthorize
	// @RequestMapping(value = "uploadImage.json", method = RequestMethod.POST)
	// public ModelAndView upload(@RequestParam("image") MultipartFile image,
	// HttpServletRequest request) {
	// if(image.getSize()>1024*1024){//图片超大
	// throw new DesignerException(ErrorCode.UPLOAD_IMAGE_OVERSIZE);
	// }
	// User user = getSessionUser(request);
	// int userId = user.getId();
	// try {
	// UploadImageResult imageResult =
	// uploadService.uploadImage(image.getBytes(), userId, "upload.jpg");
	// if(imageResult!=null){
	// return ResponseBuilderUtil.buildSuccessResponse(imageResult);
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return
	// ResponseBuilderUtil.buildErrorResponse(ErrorCode.UPLOAD_IMAGE_ERROR);
	// }
	//
	

//	@RequestMapping(value = "moreComments.json", method = RequestMethod.POST)
//	public ModelAndView moreComments(HttpServletRequest request, int albumSlideId, int commentOffsetId) {
//		
//		commentService.queryCommentsByAlbumSlideId(albumSlideId);
//		
//		
////		User user = getSessionUser(request);
////		Comment commentResult = commentService.comment("", comment, albumId, albumSlideId, user.getId(), toId, designerId);
////		if (commentResult != null) {// 成功响应
////			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(buildCommentHtml(commentResult)));
////		} else {
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_ERROR));
////		}
//	}
	
	
	
}
