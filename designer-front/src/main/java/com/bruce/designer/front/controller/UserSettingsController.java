package com.bruce.designer.front.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
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
import com.bruce.designer.front.util.DesignerHtmlUtils;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.mail.MailService;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumFavoriteService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedInfo;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.OAuthUtil;
import com.google.code.kaptcha.Constants;

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
	private IOAuthService oauthService;
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumFavoriteService albumFavoriteService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private ITagAlbumService tagAlbumService;
	@Autowired
	private MailService mailService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserSettingsController.class);

	public static final int MY_ALBUMS_LIMIT = NumberUtils.toInt(ConfigUtil.getString("my_album_limit"), 10);
	/*我的收藏item的数量*/
	public static final int MY_FAVORITE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("myfavorite_album_limit"), 2);
	/*发布第三方的状态*/
	public static final boolean ALBUM_SHAREOUT_FLAG = BooleanUtils.toBoolean(ConfigUtil.getString("album_shareout_flag"), "true", "false");

	@RequestMapping(method = RequestMethod.GET) 
	public String settings(Model model) {
		return info(model);
	}

	@RequestMapping(value="/info", method = RequestMethod.GET)
	public String info(Model model) {
		return "settings/info";
	}

	@RequestMapping(value="/thirdparty", method = RequestMethod.GET)
	public String thirdparty(Model model) {
		return "settings/thirdparty";
	}
	
//	@NeedAuthorize
//    @RequestMapping(value = "/thirdparty.json", method = RequestMethod.POST)
//	public ModelAndView thirdparty(HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		boolean share2Wb = true;
//		boolean share2QQ = true;
//		boolean success = false;
//		if (success) {
//            return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
//        } else {
//            return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
//        }
//    }
	

	@RequestMapping(value= "/avatar", method = RequestMethod.GET)
	public String avatar(Model model, User user) {
		return "settings/avatar";
	}

	
	@NeedAuthorize
    @RequestMapping(value = "uploadAvatar.json", method = RequestMethod.POST)
	public ModelAndView uploadAvatar(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]上传头像");
        }
		boolean success = true; 
        try {
        	uploadService.uploadAvatar(file.getBytes(), user.getId());
        } catch (IOException e) {
        	success = false;
        	if(logger.isErrorEnabled()){
                logger.error("用户["+user.getId()+"]上传头像失败", e);
            }
//            logger.error("uploadAvatar(MultipartFile)", e);
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
		return "settings/designerInfo";
	}
	
	
	@RequestMapping(value = "/designerApply", method = RequestMethod.POST)
	public String designerApply(Model model, HttpServletRequest request, String title, long price, int coverId, int[] albumSlideNums, String tags,
			@RequestParam(required = false, defaultValue = "") String link) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]申请设计师");
        }
		
		if (albumSlideNums != null && albumSlideNums.length > 0) {
			if(!"".equals(link)&&!link.startsWith("http://")){
				link = "http://"+link;
			}
			
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
			Date currentTime = new Date();
			album.setCreateTime(currentTime);
			album.setUpdateTime(currentTime);
			
			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
			    int albumId = album.getId();
			    
			    if(logger.isDebugEnabled()){
		            logger.debug("用户["+user.getId()+"]申请设计师的作品["+albumId+"]");
		        }
			    
				for (int tempSlideId : albumSlideNums) {
					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(album.getId());

					slide.setSlideSmallImg(request.getParameter("smallImage" + tempSlideId));
					slide.setSlideMediumImg(request.getParameter("largeImage" + tempSlideId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + tempSlideId));

					slide.setRemark(remark);
					slide.setUserId(userId);
					slide.setCreateTime(currentTime);
					slide.setUpdateTime(currentTime);
					
					if(coverId==tempSlideId){
						slide.setIsCover(ConstService.ALBUM_SLIDE_IS_COVER);
					}else{
						slide.setIsCover(ConstService.ALBUM_SLIDE_ISNOT_COVER);
					}
					slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
					albumSlideService.save(slide);
				}
				if(logger.isDebugEnabled()){
                    logger.debug("用户["+user.getId()+"]申请设计师的作品["+albumId+"]标签:"+tags);
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
			if(applyResult>0){
    			if(logger.isDebugEnabled()){
                    logger.debug("用户["+user.getId()+"]申请设计师操作成功");
                }
    			request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的申请资料已成功提交，请耐心等待审批。现在将转入首页，请稍候…");
    			//系统异步发送申请邮件给工作人员
    			mailService.sendDesignerApplyMail(album.getId());
    			
    			// 验证通过（修改用户状态、修改作品状态）
    			int approvalResult = userService.designerApprove(user.getId());
    			if (approvalResult > 0) {
    				User designerUser = userService.loadById(userId);
    				if (designerUser != null) {
    					request.getSession().setAttribute(ConstFront.CURRENT_USER, designerUser);
    				}
    			}
			}else{
			    if(logger.isErrorEnabled()){
                    logger.error("变更用户["+user.getId()+"]申请状态失败");
                }
			}
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("用户["+user.getId()+"]申请作品数有误");
            }
		}
		
		return ResponseUtil.getForwardReirect();
	}

	
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value= "/albums", method = RequestMethod.GET)
	public String albums(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]查看个人作品列表");
        }
		
		int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), MY_ALBUMS_LIMIT);
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]查看个人作品列表, pageNo: "+pageNo+", pageSize:"+pageSize);
        }
		
//		List<Album> albumList = albumService.queryAlbumByUserId(userId);
		PagingData<Album> albumPagingData = albumService.pagingQuery(userId, ConstService.ALBUM_OPEN_STATUS, pageNo, pageSize);
		if(albumPagingData!=null){
//			List<Album> albumList = albumPagingData.getPagingData();
			model.addAttribute("albumPagingData", albumPagingData);
		}
		return "settings/album/albums";
	}
	
	/**
	 * 我的收藏
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value= "/favorites", method = RequestMethod.GET)
	public String favorites(Model model, HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//		int userId = user.getId();
//		
//		int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
//		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), MY_ALBUMS_LIMIT);
//		
//		PagingData<Album> albumPagingData = albumService.pagingQuery(userId, ConstService.ALBUM_OPEN_STATUS, pageNo, pageSize);
//		if(albumPagingData!=null){
//			model.addAttribute("albumPagingData", albumPagingData);
//		}
		return "settings/album/myFavorites";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "moreFavoritesAlbums.json")
	public ModelAndView moreFavoritesAlbums(HttpServletRequest request, int favoriteTailId, int numberPerLine) {
		User currentUser = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = currentUser.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载用户[userId]的收藏专辑，tailId: "+favoriteTailId +", 每页展示条目："+numberPerLine);
        }

		int limit = MY_FAVORITE_LIMIT;
		//获取收藏列表
		List<AlbumFavorite> favoriteList = albumFavoriteService.fallLoadUserFavoriteAlbums(userId, favoriteTailId, limit + 1);
		int nextTailId = 0;

		if (favoriteList == null || favoriteList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (favoriteList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				favoriteList.remove(limit);
				nextTailId = favoriteList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("还有更多专辑，tailId： "+nextTailId);
                }
			}
			String responseHtml = DesignerHtmlUtils.buildFallLoadFavoriteFHtml(favoriteList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap)); 
		}
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
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]编辑作品["+albumId+"]");
        }
		
		if (albumId > 0) {
			Album album = albumService.loadById(albumId);
			if (album == null) {//加载失败
			    if(logger.isErrorEnabled()){
                    logger.error("专辑["+albumId+"]数据异常");
                }
				throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
			}else if(userId != album.getUserId()){//作者不匹配，无法编辑
			    if(logger.isErrorEnabled()){
		            logger.error("用户["+user.getId()+"]与专辑作者["+album.getUserId()+"]不匹配");
		        }
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
	public String postAlbum(Model model, HttpServletRequest request, String title, long price, int coverId, int[] albumSlideNums, String tags,
			@RequestParam(required = false, defaultValue = "") String link, @RequestParam(defaultValue = "") String verifyCode) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]发布作品");
        }
		
		/* 检查验证码 */
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			throw new DesignerException(ErrorCode.SYSTEM_VERIFYCODE_ERROR);
		}
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		Album album = null;
		
		if (albumSlideNums != null && albumSlideNums.length > 0) {
			if(!StringUtils.isBlank(link)&&!link.toLowerCase().startsWith("http://")){
				link = "http://"+link;
			}
			
			String remark = request.getParameter("remark");
			
			album = new Album();
			album.setUserId(userId);
			album.setTitle(title);
			album.setStatus(ConstService.ALBUM_OPEN_STATUS);
			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
			album.setPrice(price);
			album.setLink(link);
			album.setRemark(remark);
			Date currentTime = new Date();
			album.setCreateTime(currentTime);
			album.setUpdateTime(currentTime);
			
			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
				int albumId = album.getId();
				//保存albumSlide
				for (int tempSlideId : albumSlideNums) {
					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(albumId);
					slide.setSlideSmallImg(request.getParameter("smallImage" + tempSlideId));
					slide.setSlideMediumImg(request.getParameter("mediumImage" + tempSlideId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + tempSlideId));
					slide.setRemark(remark);
					slide.setUserId(userId);
					slide.setCreateTime(currentTime);
					slide.setUpdateTime(currentTime);
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
				
				//是否使用第三方分享
				boolean isShareOut = ALBUM_SHAREOUT_FLAG;
				if (logger.isDebugEnabled()) {
					logger.debug("全局设置作品集" + albumId + "分享到第三方账户状态:" + isShareOut);
				}
				if (album != null && album.getId() > 0 && isShareOut) {
					List<SharedInfo> sharedInfoList = OAuthUtil.buildSharedInfoList(album, user.getAccessTokenMap());
					oauthService.shareout(sharedInfoList);
				}

				request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已成功发布，现在将转入首页，请稍候…");
				return ResponseUtil.getForwardReirect();
			}else{
			    if(logger.isErrorEnabled()){
	                logger.error("用户["+user.getId()+"]发布作品出错");
	            }
			    throw new DesignerException(ErrorCode.ALBUM_CREATE_FAILED);
			}
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("用户["+user.getId()+"]发布作品数有误");
            }
		    throw new DesignerException(ErrorCode.ALBUM_CREATE_FAILED);
		}
		
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
	public String updateAlbum(Model model, HttpServletRequest request, int albumId, String title, long price, boolean coverChange, int coverId, boolean tagsChange, String tags,
			@RequestParam(required = false, defaultValue = "") String link, @RequestParam(defaultValue = "") String verifyCode) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]更新作品["+albumId+"]");
        }
		
		/* 检查验证码 */
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			throw new DesignerException(ErrorCode.SYSTEM_VERIFYCODE_ERROR);
		}
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		Album album = albumService.loadById(albumId);
		if(album!=null && user.getId().equals(album.getUserId())){//是否是自己发布的作品
			if(!StringUtils.isBlank(link)&&!link.toLowerCase().startsWith("http://")){
				link = "http://"+link;
			}
			
			album = new Album();
			album.setId(albumId);
			album.setTitle(title);
			album.setPrice(price);
			album.setLink(link);
			Date currentTime = new Date();
			album.setUpdateTime(currentTime);
			if(coverChange){//coverId发生变化，封面需重新设置
				album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
				album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
				album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
				albumSlideService.setCover(userId, albumId, coverId);
			}
			
			int result = albumService.updateById(album);
			if(result>0){
				if(tagsChange){//tag发生变化，需重新关联作品与tag
					List<String> tagNameList = parseTagNameList(tags);
					tagService.tagAlbum(albumId, tagNameList);
				}
				
				model.addAttribute(ConstFront.REDIRECT_URL, ConstFront.CONTEXT_PATH +"/settings/albums");
				request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品辑已成功修改，现在将转入作品辑管理页，请稍候…");
				return ResponseUtil.getForwardReirect();
			}else{
			    if(logger.isErrorEnabled()){
	                logger.error("用户["+user.getId()+"]更新作品["+albumId+"]失败");
	            }
			}
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("用户["+user.getId()+"]更新非自己作品["+albumId+"]失败");
            }
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
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]删除作品["+albumId+"]");
        }
		
		if (ownerId == userId) {
			//删除作品
			int result = albumService.deleteUserAlbum(userId, albumId);
			if(result>0){
				request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已删除，现在将转入首页，请稍候…");
				return ResponseUtil.getForwardReirect();
			}
		} else {
		    if(logger.isErrorEnabled()){
	            logger.error("用户["+user.getId()+"]删除非自己作品["+albumId+"]失败");
	        }
			// 操作有误，不能删除别人的作品
			throw new DesignerException(ErrorCode.ALBUM_AUTHOR_NOT_MATCH);
		}
		if(logger.isErrorEnabled()){
            logger.error("用户["+user.getId()+"]删除作品["+albumId+"]失败");
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
	    if(logger.isDebugEnabled()){
            logger.debug("用户["+user.getId()+"]修改密码");
        }
		if(userService.authUser(user.getUsername(), oldPassword)!=null){
			// 重置密码
			int result = userService.changePassword(user.getId(), rePassword);
			if(result>0){
			    if(logger.isDebugEnabled()){
	                logger.debug("用户["+user.getId()+"]修改密码成功");
	            }
				return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
			}
		}else{
		    if(logger.isErrorEnabled()){
	            logger.error("用户["+user.getId()+"]修改密码失败");
	        }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_PASSWORD_NOT_MATCH));
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_CHANGE_PASSWORD_FAILED));
	}

//	/**
//	 * 我的粉丝
//	 * 
//	 * @param model
//	 * @param user
//	 * @return
//	 */
//	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
//	@RequestMapping(value= "/flowers")
//	public String flowers(Model model, User user) {
//		return "settings/myFlowers";
//	}
//
//	/**
//	 * 我的关注
//	 * 
//	 * @param model
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value= "/flowerings")
//	public String flowerings(Model model, User user) {
//		return "settings/myFlowerings";
//	}

	
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
