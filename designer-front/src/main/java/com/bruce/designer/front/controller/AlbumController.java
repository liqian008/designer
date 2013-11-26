package com.bruce.designer.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.annotation.NeedAuthorize.AuthorizeType;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.TagAlbum;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UploadUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AlbumController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private ICounterService counterService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private ITagAlbumService tagAlbumService;
	@Autowired
	private IHotService hotService;

	public static final int HOME_LIMIT = 3;
	public static final int FULL_LIMIT = 4;
	public static final int SIDE_LIMIT = 3*2;

	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(Model model) {
		return index(model);
	}

	/**
	 * 首页请求
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		// PagingData<Album> albumPagingData =
		// albumService.pagingQuery(ConstService.ALBUM_OPEN_STATUS, 1, 16);
		// if (albumPagingData != null && albumPagingData.getPageData() != null)
		// {
		// // List<Album> albumList =
		// // albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
		// List<Album> albumList = albumPagingData.getPageData();
		// if (albumList != null && albumList.size() > 0) {
		// for (Album loopAlbum : albumList) {
		// int albumId = loopAlbum.getId();
		// loopAlbum.setBrowseCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_BROWSE
		// + albumId));
		// loopAlbum.setCommentCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_COMMENT
		// + albumId));
		// loopAlbum.setLikeCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_LIKE
		// + albumId));
		// loopAlbum.setFavoriteCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_FAVORITE
		// + albumId));
		// }
		// model.addAttribute("albumList", albumList);
		// }
		// model.addAttribute("albumPagingData", albumPagingData);
		// }
		int limit = FULL_LIMIT;
		List<Album> albumList = albumService.fallLoadAlbums(0, limit + 1, true);
		int tailId = 0;
		if (albumList != null) {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				tailId = albumList.get(limit - 1).getId();
			}
			initAlbumDataCount(albumList);

			model.addAttribute("albumList", albumList);
			model.addAttribute("tailId", tailId);
		}
		return "index";
	}

	/**
	 * 时间轴作品列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/albums", method = RequestMethod.GET)
	public String albums(Model model) {
		return "album/latestAlbums";
	}

	@NeedAuthorize
	@RequestMapping(value = "/followAlbums", method = RequestMethod.GET)
	public String followAlbums(Model model) {
		return "album/followAlbums";
	}

	/**
	 * 作品详情
	 * 
	 * @param model
	 * @param albumId
	 * @return
	 */
	@RequestMapping(value = "/album/{albumId}", method = RequestMethod.GET)
	public String albumInfo(Model model, @PathVariable int albumId) {
		return albumInfo(model, albumId, -1);
	}

	/**
	 * 作品详情
	 * 
	 * @param model
	 * @param albumId
	 * @param albumSlideId
	 * @return
	 */
	@RequestMapping(value = "/album/{albumId}/{albumSlideId}", method = RequestMethod.GET)
	public String albumInfo(Model model, @PathVariable int albumId, @PathVariable int albumSlideId) {
		Album albumInfo = albumService.loadById(albumId);
		if (albumInfo != null) {
			// 读取作品列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
			albumInfo.setSlideList(slideList);
			if (albumSlideId < 0) {
				albumSlideId = slideList.get(0).getId();
			}
			// 选择cover图片
			for (int slideIndex = 0; slideIndex < slideList.size(); slideIndex++) {
				AlbumSlide albumSlide = slideList.get(slideIndex);
				if (albumSlideId == albumSlide.getId()) {
					model.addAttribute("albumSlide", albumSlide);
					// 设置slide的index，用以判断是否有上一张、下一张
					model.addAttribute("slideIndex", slideIndex);
					// 增加浏览计数
					counterService.incrBrowser(albumInfo.getUserId(), albumId, 1);
					initAlbumDataCount(albumInfo);
					break;
				}
			}

			User queryUser = userService.loadById(albumInfo.getUserId());
			model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);

			model.addAttribute("albumInfo", albumInfo);
			return "album/albumInfo";
		}else{
			throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
		}
	}

	/**
	 * 
	 * @param model
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
			List<String> tagNameList = tagService.getTagNamesByAlbumId(albumId);
			
			model.addAttribute("album", album);
			return "settings/albumEdit";
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
	@RequestMapping(value = "/publishAlbum", method = RequestMethod.POST)
	public String publishAlbum(Model model, HttpServletRequest request, String title, long price, int coverId, int[] albumSlideNums, String tags) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		if (albumSlideNums != null && albumSlideNums.length > 0) {
			String link = request.getParameter("link");
			
			Album album = new Album();
			album.setUserId(userId);
			album.setTitle(title);
			album.setStatus(ConstService.ALBUM_OPEN_STATUS);
			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
			album.setPrice(price);
			album.setLink(link);
			
			// 提交作品专辑，建议使用外部主键生成器
			int result = albumService.save(album);
			if (result > 0) {
				int albumId = album.getId();
				//保存albumSlide
				for (int tempSlideId : albumSlideNums) {
					String remark = request.getParameter("remark" + tempSlideId);

					AlbumSlide slide = new AlbumSlide();
					slide.setAlbumId(albumId);
					slide.setSlideSmallImg(request.getParameter("smallImage" + tempSlideId));
					slide.setSlideMediumImg(request.getParameter("mediumImage" + tempSlideId));
					slide.setSlideLargeImg(request.getParameter("largeImage" + tempSlideId));
					slide.setRemark(remark);
					slide.setUserId(userId);
					slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
					albumSlideService.save(slide);
				}
				//关联作品与tag
				List<String> tagNameList = parseTagNameList(tags);
				tagService.tagAlbum(albumId, tagNameList);
			}
			//TODO 增加计数
		}
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已成功发布，现在将转入个人主页，请稍候…");
		return ResponseUtil.getForwardReirect();
	}
	
	/**
	 * 将用逗号分割的tagsName转为tagNameList
	 * @param tagsName
	 * @return
	 */
	private List<String> parseTagNameList(String tagsName){
		List<String> tagNameList = new ArrayList<String>();
		String[] tagNames = null;
		if(tagsName!=null){
			tagNames = tagsName.split(",");
			if(tagNames!=null&&tagNames.length>0){
				for(String tagName: tagNames){
					tagNameList.add(tagName);
				}
			}
		}
		return tagNameList;
	}

	/**
	 * 更新专辑（未确定是否开放该功能）
	 * 
	 * @param model
	 * @param request
	 * @param album
	 * @return
	 */
	@NeedAuthorize(authorizeType = AuthorizeType.DESIGNER)
	@RequestMapping(value = "/updateAlbum")
	public String updateAlbum(Model model, HttpServletRequest request, int albumId, String title, long price, int coverId, int[] albumNums, boolean tagsChange, String tags, String link) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		
		Album album = albumService.loadById(albumId);
		if(album!=null && user.getId().equals(album.getId())){//是否是自己发布的作品
			album = new Album();
			album.setId(albumId);
			album.setTitle(title);
			album.setCoverSmallImg(request.getParameter("smallImage" + coverId));
			album.setCoverMediumImg(request.getParameter("mediumImage" + coverId));
			album.setCoverLargeImg(request.getParameter("largeImage" + coverId));
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

	@RequestMapping(value = "/deleteAlbum")
	public String deleteAlbum(Model model, HttpServletRequest request, int ownerId, int albumId) {
		// 检查用户登录
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		if (ownerId == userId) {
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

	@RequestMapping(value = "moreAlbums.json")
	public ModelAndView moreAlbums(HttpServletRequest request, @RequestParam("albumsTailId") int tailId, int numberPerLine) {
		int limit = 4;
		int designerId = NumberUtils.toInt(request.getParameter("designerId"));
		List<Album> albumList = null;
		if (designerId > 0) {
			limit = HOME_LIMIT;
			albumList = albumService.fallLoadDesignerAlbums(designerId, tailId, limit + 1, true);
		} else {
			limit = FULL_LIMIT;
			albumList = albumService.fallLoadAlbums(tailId, limit + 1, true);
		}

		int nextTailId = 0;
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
			}
			initAlbumDataCount(albumList);
			String responseHtml = buildFallLoadHtml(albumList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}

	@NeedAuthorize
	@RequestMapping(value = "moreUserFollowAlbums.json")
	public ModelAndView moreUserFollowAlbums(HttpServletRequest request, int albumsTailId, int numberPerLine) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();

		int limit = FULL_LIMIT;
		List<Album> albumList = null;
		albumList = albumService.fallLoadUserFollowAlbums(userId, albumsTailId, limit + 1);
		int nextTailId = 0;

		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
			}
			initAlbumDataCount(albumList);
			String responseHtml = buildFallLoadHtml(albumList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}

	}

	@RequestMapping(value = "/moreTagAlbums.json")
	public ModelAndView moreTagAlbums(Model model, HttpServletRequest request, @RequestParam("tag") String tagName, @RequestParam("albumsTailId") int tailId,
			int numberPerLine) {
		int limit = 1;
		List<Album> albumList = null;
		albumList = albumService.fallLoadAlbumsByTagName(tagName, tailId, limit + 1);

		int nextTailId = 0;
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
			}
			initAlbumDataCount(albumList);
			String responseHtml = buildFallLoadHtml(albumList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	@RequestMapping(value = "/hot/albums.json")
	public ModelAndView moreHotAlbums4Json(Model model, HttpServletRequest request) {
		int limit = 4;
		List<Album> albumList = null;
		albumList = hotService.fallLoadHotAlbums(0, limit);

		int nextTailId = 0;
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			initAlbumDataCount(albumList);
			String responseHtml = buildFallLoadHtml(albumList, 3);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}

	/**
	 * 右侧栏加载的最新作品
	 * 
	 * @param request
	 * @param numberPerLine
	 * @return
	 */
	@RequestMapping(value = "sideLatestAlbums.json")
	public ModelAndView sideLatestAlbums(HttpServletRequest request) {
		int tailId = 0;
		int limit = 6;
		int designerId = NumberUtils.toInt(request.getParameter("designerId"));
		List<Album> albumList = null;
		if (designerId > 0) {
			albumList = albumService.fallLoadDesignerAlbums(designerId, tailId, limit, false);
		} else {
			albumList = albumService.fallLoadAlbums(tailId, limit, false);
		}
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = buildSidebarHtml(albumList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	/**
	 * 热门作品列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hot/albums", method = RequestMethod.GET)
	public String hotAlbums(Model model) {
		return "album/hotAlbums";
	}
	
	/**
	 * 右侧栏加载的最热作品
	 * 
	 * @param request
	 * @param numberPerLine
	 * @return
	 */
	@RequestMapping(value = "sideHotAlbums.json")
	public ModelAndView sideHotAlbums(HttpServletRequest request) {
		List<Album> albumList = hotService.fallLoadHotAlbums(0, SIDE_LIMIT);
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = buildSidebarHtml(albumList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}

	/**
	 * 
	 * @param albumList
	 * @return
	 */
	private String buildFallLoadHtml(List<Album> albumList, int numberPerLine) {
		int span = 3;
		if (numberPerLine <= 0) {
			numberPerLine = 4;
		}
		span = 12 / numberPerLine;

		// TODO freemarker template
		if (albumList != null && albumList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			// sb.append("<div class='shortcode-blogpost row-fluid'>");
			for (Album album : albumList) {
				i++;
				if (i % numberPerLine == 1) {
					sb.append("<div class='shortcode-blogpost row-fluid'>");
				}
				sb.append("<article class='blog-item span" + span + "'>");
				sb.append("<div class='blog-post-image-wrap'>");
				sb.append("<a class='blog-single-link' href='/designer-front/album/" + album.getId() + "'>");
				sb.append("<img src='" + album.getCoverMediumImg() + "'>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("<div class='content-wrap span9'>");
				sb.append("<a href='#'><h5>" + album.getTitle() + "</h5></a>");
				sb.append("<ul>");
				sb.append("<li><span>标 签:&nbsp;</span>");
				List<String> tagNameList = album.getTagList();
				if (tagNameList != null && tagNameList.size() > 0) {
					int m=0;
					for (String tagName : tagNameList) {
						m++;
						sb.append("<a href='/designer-front/tag/");
						sb.append(tagName);
						sb.append("'>");
						sb.append(tagName);
						sb.append("</a>");
						if(m<tagNameList.size()){
							sb.append(",&nbsp;");
						}
					}
				}
				sb.append("</li>");

				sb.append("<li><span>价 格:</span>" + album.getPrice() + "</li>");
				sb.append("<li><a href='/designer-front/album/" + album.getId() + "'>" + album.getBrowseCount() + "&nbsp;浏览&nbsp;</a>/&nbsp;");
				sb.append("<a href='/designer-front/album/" + album.getId() + "'>" + album.getCommentCount() + "&nbsp;评论&nbsp;</a>/&nbsp;");
				sb.append("<a href='/designer-front/album/" + album.getId() + "'>" + album.getFavoriteCount() + "&nbsp;收藏&nbsp;</a>");
				sb.append("</li> ");
				sb.append("</ul>");
				sb.append("</div>");
				sb.append("<div class='content-avatar'>");
				sb.append("<a href='/designer-front/" + album.getUserId() + "/home'>");
				sb.append("<img src='"+UploadUtil.getAvatarUrl(album.getUserId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"'/>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("</article>");
				if (i % numberPerLine == 0) {
					sb.append("</div>");
				}

			}
			// sb.append("</div>");
			return sb.toString();
		}
		return "";
	}

	/**
	 * 
	 * @param albumList
	 * @return
	 */
	private String buildSidebarHtml(List<Album> albumList) {
		// TODO freemarker template
		if (albumList != null && albumList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Album album : albumList) {
				sb.append("<div class='flickr_badge_image' id='flickr_badge_image" + album.getId() + "'><a href='/designer-front/album/" + album.getId()
						+ "'><img src='" + album.getCoverSmallImg() + "' title='" + album.getTitle() + "'" + album.getTitle()
						+ "'height='75' width='75'></a></div>");
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * 批量初始化专辑计数
	 * 
	 * @param albumList
	 */
	private void initAlbumDataCount(List<Album> albumList) {
		for (Album loopAlbum : albumList) {
			initAlbumDataCount(loopAlbum);
		}
	}

	/**
	 * 初始化专辑计数
	 * 
	 * @param album
	 */
	private void initAlbumDataCount(Album album) {
		if (album != null) {
			int albumId = album.getId();
			album.setBrowseCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_BROWSE + albumId));
			album.setCommentCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId));
			album.setLikeCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_LIKE + albumId));
			album.setFavoriteCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_FAVORITE + albumId));
		}
	}

//	/**
//	 * 初始化作品计数
//	 * 
//	 * @param album
//	 */
//	private void initAlbumSlideDataCount(AlbumSlide albumSlide) {
//		if (albumSlide != null) {
//			int albumSlideId = albumSlide.getId();
//			albumSlide.setBrowseCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUMSLIDE_BROWSE + albumSlideId));
//			albumSlide.setCommentCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUMSLIDE_COMMENT + albumSlideId));
//			albumSlide.setLikeCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUMSLIDE_LIKE + albumSlideId));
//			albumSlide.setFavoriteCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_FAVORITE + albumSlideId));
//		}
//	}

}
