package com.bruce.designer.front.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.User;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.annotation.NeedAuthorize.AuthorizeType;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.PropertiesUtil;

/**
 * Handles requests for the application home page.
 */
@NeedAuthorize
@Controller
@RequestMapping(value = "/settings")
public class UserSettingsController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private IMessageService messageService;

	private static final Logger logger = LoggerFactory.getLogger(UserSettingsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String settings(Model model) {
		return info(model);
	}

	@RequestMapping(value="/info", method = RequestMethod.GET)
	public String info(Model model) {
		return "settings/info";
	}

//	@RequestMapping(value= "/info", method = RequestMethod.POST)
//	public String updateInfo(Model model, HttpServletRequest request) {
//		short gender = NumberUtils.toShort(request.getParameter("gender"), (short) 1);
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		User updateUser = new User();
//		// 获取性别
//		updateUser.setGender(gender);
//		updateUser.setId(user.getId());
//		userService.updateById(updateUser);
//		request.setAttribute(ConstFront.REDIRECT_PROMPT, "个人资料更新成功，现在将转入后续页面，请稍候…");
//		request.setAttribute(ConstFront.REDIRECT_URL, "./settings");
//		return "forward:/redirect";
//	}
	
	@RequestMapping(value="/thirdparty", method = RequestMethod.GET)
	public String thirdparty(Model model) {
		return "settings/thirdparty";
	}
	
	@NeedAuthorize
    @RequestMapping(value = "thirdparty.json", method = RequestMethod.POST)
	public ModelAndView thirdparty(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		boolean share2Wb = true;
		boolean share2QQ = true;
		boolean success = false;
		if (success) {
            return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
        } else {
            return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
        }
    }
	

	@RequestMapping(value= "/avatar", method = RequestMethod.GET)
	public String avatar(Model model, User user) {
		return "settings/avatar";
	}

	
	@NeedAuthorize
    @RequestMapping(value = "uploadAvatar.json", method = RequestMethod.POST)
	public ModelAndView uploadAvatar(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		boolean success = true; 
        try {
//        	UploadImageResult uploadResult = 
        	uploadService.uploadAvatar(file.getBytes(), user.getId());
        } catch (IOException e) {
        	success = false;
            logger.error("uploadAvatar(MultipartFile)", e);
        }
        if (success) {
            return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
        } else {
            return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
        }
    }
	

//	@RequestMapping(value= "/avatar", method = RequestMethod.POST)
//	public String headPhotoGo(Model model, HttpServletRequest request, int x, int y, int w, int h) throws IOException {
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		int userId = user.getId();
//		// 接口应该优化，返回头像数组
//		UploadImageResult imageResult = uploadService.updateAvatar(userId, x, y, w, h);
//		// 替换为新头像
//		request.setAttribute(ConstFront.REDIRECT_PROMPT, "头像更新成功，现在将转入后续页面，请稍候…");
//		return "forward:/redirect";
//	}

	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/designerInfo", method = RequestMethod.GET)
	public String designerInfo(Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		int userId = user.getId();
//		if (user != null) {
//			model.addAttribute("", user);
//		}
//		throw new DesignerException(ErrorCode.USER_NOT_EXIST);
		return "settings/designerInfo";
	}
	
	
	@RequestMapping(value = "/designerApply", method = RequestMethod.POST)
	public String designerApply(Model model, HttpServletRequest request, String title, int coverId, int[] albumNums) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		if (albumNums != null && albumNums.length > 0) {
			Album album = new Album();
			album.setUserId(userId);
			album.setTitle(title);
			album.setStatus(ConstService.ALBUM_OPEN_STATUS);

			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("largeImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));

			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
				for (int loopId : albumNums) {
					String remark = request.getParameter("remark" + loopId);

					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(album.getId());

					slide.setSlideSmallImg(request.getParameter("smallImage" + loopId));
					slide.setSlideMediumImg(request.getParameter("largeImage" + loopId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + loopId));

					slide.setRemark(remark);
					slide.setUserId(userId);
					slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
					albumSlideService.save(slide);
				}
			}
		}

		int applyResult = userService.apply4Designer(user.getId());
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的申请资料已成功提交，请耐心等待审批。现在将转入首页，请稍候…");

		// 验证通过（修改用户状态、修改作品状态）
		int approvalResult = userService.designerApprove(user.getId());
		if (approvalResult > 0) {
			User designerUser = userService.loadById(userId);
			if (designerUser != null) {
				request.getSession().setAttribute(ConstFront.CURRENT_USER, designerUser);
			}
			messageService.sendMessage(0, ConstService.MESSAGE_DELIVER_ID_BROADCAST, userId, "恭喜您，您的设计师申请已经通过！",
					ConstService.MESSAGE_TYPE_SYSTEM);
		}

		return "forward:/redirect";
	}

	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/newAlbum", method = RequestMethod.GET)
	public String newAlbum(Model model) {
		return "settings/albumNew";
	}

	/**
	 * 进入修改密码页面
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value= "/changePasswd", method = RequestMethod.GET)
	public String changePasswd(Model model) {
		return "settings/changePasswd";
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/changePasswd.json", method = RequestMethod.POST)
	public ModelAndView changePasswd(Model model, HttpServletRequest request, String oldPassword, String password, String rePassword) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		// TODO 密码加密
		if(userService.authUser(user.getUsername(), oldPassword)!=null){
			// 重置密码
			int result = userService.changePassword(user.getId(), rePassword);
			if(result>0){
				return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
			}
		}else{
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_PASSWORD_NOT_MATCH));
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_CHANGE_PASSWORD_FAILED));
	}

	/**
	 * 我的粉丝
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/flowers")
	public String flowers(Model model, User user) {
		return "settings/myFlowers";
	}

	/**
	 * 我的关注
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value= "/flowerings")
	public String flowerings(Model model, User user) {
		return "settings/myFlowerings";
	}

	/**
	 * 我的收藏
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value= "/favorities")
	public String favorities(Model model, User user) {
		List<Album> albumList = albumService.queryAlbumByUserId(3);
		if (albumList != null && albumList.size() > 0) {
			model.addAttribute("albumList", albumList);
		}
		return "settings/myFavorities";
	}


}
