package com.bruce.designer.front.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.annotation.NeedAuthorize.AuthorizeType;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;

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
	@Autowired
	private ITagService tagService;
	@Autowired
	private ITagAlbumService tagAlbumService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserSettingsController.class);

	public static final int MY_ALBUMS_LIMIT = NumberUtils.toInt(ConfigUtil.getString("my_album_limit"), 10);

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
    @RequestMapping(value = "/thirdparty.json", method = RequestMethod.POST)
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

	@RequestMapping(value= "/designerInfo", method = RequestMethod.GET)
	public String designerInfo(Model model,HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		int userId = user.getId();
//		if (user != null) {
//			model.addAttribute("", user);
//		}
//		throw new DesignerException(ErrorCode.USER_NOT_EXIST);
		return "settings/designerInfo";
	}
	
	
	@RequestMapping(value = "/designerApply", method = RequestMethod.POST)
	public String designerApply(Model model, HttpServletRequest request, String title, long price, int coverId, int[] albumSlideNums, String tags) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		if (albumSlideNums != null && albumSlideNums.length > 0) {
			String link = request.getParameter("link");
			String remark = request.getParameter("remark");
			
			Album album = new Album();
			album.setUserId(userId);
			album.setTitle(title);
			album.setStatus(ConstService.ALBUM_OPEN_STATUS);
			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
			album.setPrice(price);
			album.setLink(link);
			album.setRemark(remark);

			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
				int albumId = album.getId();
				for (int tempSlideId : albumSlideNums) {
					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(album.getId());

					slide.setSlideSmallImg(request.getParameter("smallImage" + tempSlideId));
					slide.setSlideMediumImg(request.getParameter("largeImage" + tempSlideId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + tempSlideId));

					slide.setRemark(remark);
					slide.setUserId(userId);
					if(coverId==tempSlideId){
						slide.setIsCover(ConstService.ALBUM_SLIDE_IS_COVER);
					}else{
						slide.setIsCover(ConstService.ALBUM_SLIDE_ISNOT_COVER);
					}
					slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
					albumSlideService.save(slide);
				}
				//关联作品与tag
				List<String> tagNameList = parseTagNameList(tags);
				tagService.tagAlbum(albumId, tagNameList);
			}
			
			String idNum = StringUtils.defaultString(request.getParameter("idNum"), "");
			String realname = StringUtils.defaultString(request.getParameter("realname"), "");
			String mobile = StringUtils.defaultString(request.getParameter("mobile"), "");
			String company = StringUtils.defaultString(request.getParameter("company"), "");
			String taobaoHomepage = StringUtils.defaultString(request.getParameter("taobaoHomepage"), "");
			int applyResult = userService.apply4Designer(userId, realname, idNum, mobile, company, taobaoHomepage);
			
			request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的申请资料已成功提交，请耐心等待审批。现在将转入首页，请稍候…");

			// 验证通过（修改用户状态、修改作品状态）
			int approvalResult = userService.designerApprove(user.getId());
			if (approvalResult > 0) {
				User designerUser = userService.loadById(userId);
				if (designerUser != null) {
					request.getSession().setAttribute(ConstFront.CURRENT_USER, designerUser);
				}
			}
		}
		
		return ResponseUtil.getForwardReirect();
	}

	
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/albums", method = RequestMethod.GET)
	public String albums(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), MY_ALBUMS_LIMIT);
		
//		List<Album> albumList = albumService.queryAlbumByUserId(userId);
		PagingData<Album> albumPagingData = albumService.pagingQuery(userId, ConstService.ALBUM_OPEN_STATUS, pageNo, pageSize);
		if(albumPagingData!=null){
//			List<Album> albumList = albumPagingData.getPagingData();
//			model.addAttribute("albumList", albumList);
			model.addAttribute("albumPagingData", albumPagingData);
		}
		return "settings/album/albums";
	}
	
	/**
	 * 新建作品辑
	 * @param model
	 * @return
	 */
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/newAlbum", method = RequestMethod.GET)
	public String newAlbum(Model model) {
		return "settings/album/albumNew";
	}
	/**
	 * 编辑作品辑
	 * @param model
	 * @param request
	 * @param albumId
	 * @return
	 */
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value = "/editAlbum")
	public String editAlbum(Model model, HttpServletRequest request, int albumId) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		if (albumId > 0) {
			Album album = albumService.loadById(albumId);
			if (album == null) {//加载失败
				throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
			}else if(userId != album.getUserId()){//作者不匹配，无法编辑
				throw new DesignerException(ErrorCode.ALBUM_AUTHOR_NOT_MATCH);
			}
			// 读取作品列表
			List<AlbumSlide> albumSlideList = albumSlideService.querySlidesByAlbumId(albumId);
			model.addAttribute("albumSlideList", albumSlideList);
//			List<String> tagNameList = tagService.getTagNamesByAlbumId(albumId);
			//加载计数
			albumService.initAlbumWithCount(album);
			//加载标签
			albumService.initAlbumWithTags(album);
			
			model.addAttribute("album", album);
			return "settings/album/albumEdit";
		}
		throw new DesignerException(ErrorCode.ALBUM_ERROR);
	}
	/**
	 * 
	 * @param model
	 * @param request
	 * @param title
	 * @param coverId
	 * @param albumNums
	 * @return
	 */
	@NeedAuthorize(authorizeType=AuthorizeType.DESIGNER)
	@RequestMapping(value = "/postAlbum", method = RequestMethod.POST)
	public String postAlbum(Model model, HttpServletRequest request, String title, long price, int coverId, int[] albumSlideNums, String tags) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		if (albumSlideNums != null && albumSlideNums.length > 0) {
			String link = request.getParameter("link");
			String remark = request.getParameter("remark");
			
			Album album = new Album();
			album.setUserId(userId);
			album.setTitle(title);
			album.setStatus(ConstService.ALBUM_OPEN_STATUS);
			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
			album.setPrice(price);
			album.setLink(link);
			album.setRemark(remark);
			
			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
				int albumId = album.getId();
				//保存albumSlide
				for (int tempSlideId : albumSlideNums) {
//					String remark = request.getParameter("remark" + tempSlideId);

					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(albumId);
					slide.setSlideSmallImg(request.getParameter("smallImage" + tempSlideId));
					slide.setSlideMediumImg(request.getParameter("mediumImage" + tempSlideId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + tempSlideId));
					slide.setRemark(remark);
					slide.setUserId(userId);
					if(coverId==tempSlideId){
						slide.setIsCover(ConstService.ALBUM_SLIDE_IS_COVER);
					}else{
						slide.setIsCover(ConstService.ALBUM_SLIDE_ISNOT_COVER);
					}
					slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
					albumSlideService.save(slide);
				}
				//关联作品与tag
				List<String> tagNameList = parseTagNameList(tags);
				tagService.tagAlbum(albumId, tagNameList);
			}
			// TODO 增加计数
		}
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已成功发布，现在将转入个人主页，请稍候…");
		return ResponseUtil.getForwardReirect();
	}
	
	/**
	 * 更新作品专辑（未确定是否开放该功能）
	 * 
	 * @param model
	 * @param request
	 * @param album
	 * @return
	 */
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value = "/updateAlbum")
	public String updateAlbum(Model model, HttpServletRequest request, int albumId, String title, long price, boolean coverChange, int coverId, boolean tagsChange, String tags, String link) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		Album album = albumService.loadById(albumId);
		if(album!=null && user.getId().equals(album.getUserId())){//是否是自己发布的作品
			album = new Album();
			album.setId(albumId);
			album.setTitle(title);
			if(coverChange){//coverId发生变化，封面需重新设置
				album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
				album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
				album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
				albumSlideService.setCover(userId, albumId, coverId);
			}
			album.setPrice(price);
			album.setLink(link);
			
			int result = albumService.updateById(album);
			if(result>0){
				if(tagsChange){//tag发生变化，需重新关联作品与tag
					List<String> tagNameList = parseTagNameList(tags);
					tagService.tagAlbum(albumId, tagNameList);
				}
				request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已成功修改，现在将转入首页，请稍候…");
				return ResponseUtil.getForwardReirect();
			}
		}else{
			throw new DesignerException(ErrorCode.ALBUM_AUTHOR_NOT_MATCH);
		}
		throw new DesignerException(ErrorCode.ALBUM_ERROR);
	}

	@NeedAuthorize(authorizeType=AuthorizeType.DESIGNER)
	@RequestMapping(value = "/deleteAlbum")
	public String deleteAlbum(Model model, HttpServletRequest request, int ownerId, int albumId) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		if (ownerId == userId) {
			//删除作品
			int result = albumService.deleteUserAlbum(userId, albumId);
			if(result>0){
				request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已删除，现在将转入首页，请稍候…");
				return ResponseUtil.getForwardReirect();
			}
		} else {
			// 操作有误，不能删除别人的作品
			throw new DesignerException(ErrorCode.ALBUM_AUTHOR_NOT_MATCH);
		}
		throw new DesignerException(ErrorCode.ALBUM_ERROR);
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
	
	/**
	 * 将用空格分割的tagsName转为tagNameList
	 * @param tagsName
	 * @return
	 */
	private List<String> parseTagNameList(String tagsName){
		List<String> tagNameList = new ArrayList<String>();
//		String[] tagNames = null;
//		if(tagsName!=null){
//			tagNames = tagsName.split(" ");
//			if(tagNames!=null&&tagNames.length>0){
//				for(String tagName: tagNames){
//					tagNameList.add(tagName.trim());
//				}
//			}
//		}
		StringTokenizer st = new StringTokenizer(tagsName, " ");
		while(st.hasMoreElements()){
			String tagName = st.nextToken();
			tagNameList.add(tagName);
		}
		return tagNameList;
	}

}
