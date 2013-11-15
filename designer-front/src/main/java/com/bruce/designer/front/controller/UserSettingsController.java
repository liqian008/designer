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

	@RequestMapping(params = "op=info", method = RequestMethod.GET)
	public String info(Model model) {
		return "settings/info";
	}

	@RequestMapping(params = "op=info", method = RequestMethod.POST)
	public String updateInfo(Model model, HttpServletRequest request) {
		short gender = NumberUtils.toShort(request.getParameter("gender"), (short) 1);
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		User updateUser = new User();
		// 获取性别
		updateUser.setGender(gender);
		updateUser.setId(user.getId());
		userService.updateById(updateUser);
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "个人资料更新成功，现在将转入后续页面，请稍候…");
		request.setAttribute(ConstFront.REDIRECT_URL, "./settings");
		return "forward:/redirect";
	}

	@RequestMapping(params = "op=avatar", method = RequestMethod.GET)
	public String avatar(Model model, User user) {
		return "settings/avatar";
	}

	/**
	 * 上传头像，生成头像规则为 $uploadPath/avatar/$userid/50.jpg，另有100.jpg与200.jpg
	 * 
	 * @param avatarFile
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "op=uploadAvatar", method = RequestMethod.POST)
	// @ResponseBody
	public String uploadAvatar(@RequestParam("avatarImage") MultipartFile avatarImage, HttpServletRequest request) throws IOException {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		UploadImageResult imageResult = uploadService.uploadAvatar(avatarImage.getBytes(), userId, null);
		if (imageResult != null) {
			request.setAttribute("originAvatarUrl", imageResult.getOriginalImage().getUrl());
			request.setAttribute("imgSrcWidth", imageResult.getOriginalImage().getWidth());
			request.setAttribute("imgSrcHeight", imageResult.getOriginalImage().getHeight());
		}

		// request.setAttribute("originAvatarUrl", imageResult.getUrl());
		// request.setAttribute("imgSrcWidth", imageResult.getWidth());
		// request.setAttribute("imgSrcHeight", imageResult.getHeight());

		// //获取头像保存路径
		// String avatarPath = genAvatarPath(userId);
		// //确定原始文件名
		// String avatarFilename = String.valueOf(userId)+"_original.jpg";
		// //构造&保存头像File
		// File originAvatar = new File(avatarPath, avatarFilename);
		// FileCopyUtils.copy(avatarImage.getBytes(), originAvatar);
		//
		// BufferedImage src = ImageIO.read(originAvatar); // 读入文件
		// int imgSrcWidth = src.getWidth(); // 得到源图宽
		// int imgSrcHeight = src.getHeight(); // 得到源图长
		//
		// String originAvatarUrl =
		// "http://localhost:8080/designer-front/staticFile/avatar/"+avatarFilename;

		// 返回临时头像的链接
		return "settings/avatar";
	}

	/**
	 * 获取用户头像的保存路径
	 * 
	 * @param userId
	 * @return
	 */
	private String genAvatarPath(int userId) {
		return PropertiesUtil.getString("avatar_upload_base_path");
	}

	@RequestMapping(params = "op=avatar", method = RequestMethod.POST)
	public String headPhotoGo(Model model, HttpServletRequest request, int x, int y, int w, int h) throws IOException {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		// 接口应该优化，返回头像数组
		UploadImageResult imageResult = uploadService.updateAvatar(userId, x, y, w, h);
		// 替换为新头像
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "头像更新成功，现在将转入后续页面，请稍候…");
		return "forward:/redirect";
	}

	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(params = "op=designerInfo", method = RequestMethod.GET)
	public String designerApply(Model model) {
		int userId = 0;
		User user = userService.loadById(userId);
		if (user != null) {
			model.addAttribute("", user);
		} else {

		}
		return "settings/designerApply";
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
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的申请资料已成功提交，请耐心等待回复。现在将转入首页，请稍候…");

		// 验证通过（修改用户状态、修改作品状态）
		int approvalResult = userService.designerApprove(user.getId());
		if (approvalResult > 0) {
			User designerUser = userService.loadById(userId);
			if (designerUser != null) {
				request.getSession().setAttribute(ConstFront.CURRENT_USER, designerUser);
			}
			messageService.sendMessage(ConstService.MESSAGE_DELIVER_ID_BROADCAST, userId, "恭喜您，您的设计师申请已经通过！",
					ConstService.MESSAGE_TYPE_SYSTEM);
		}

		return "forward:/redirect";
	}

	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(params = "op=publisher", method = RequestMethod.GET)
	public String publisher(Model model) {
		return "settings/publisher";
	}

	/**
	 * 进入修改密码页面
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "op=changePasswd", method = RequestMethod.GET)
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
	// @RequestMapping(value = "/changePasswd", method=RequestMethod.POST)
	// public String changePasswdGo(Model model, HttpServletRequest request,
	// String oldPassword, String password, String rePassword) {
	// User user = (User)
	// request.getSession().getAttribute(ConstFront.CURRENT_USER);
	// //TODO
	// //检查密码是否合法
	//
	// //重置密码
	// int result = userService.changePassword(user.getId(), rePassword);
	// request.setAttribute(ConstFront.REDIRECT_PROMPT,
	// "您的密码已修改成功，现在将转入首页，请稍候…");
	// return "forward:/settings";
	// }

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/changePasswd.json", method = RequestMethod.POST)
	public ModelAndView changePasswdGo(Model model, HttpServletRequest request, String oldPassword, String password, String rePassword) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		// TODO
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
	@RequestMapping(params = "op=flowers")
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
	@RequestMapping(params = "op=flowerings")
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
	@RequestMapping(params = "op=favorities")
	public String favorities(Model model, User user) {
		List<Album> albumList = albumService.queryAlbumByUserId(3);
		if (albumList != null && albumList.size() > 0) {
			model.addAttribute("albumList", albumList);
		}
		return "settings/myFavorities";
	}


}
