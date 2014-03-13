package com.bruce.designer.front.controller;

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
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.DesignerHtmlUtils;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IAlbumRecommendService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.IIndexSlideService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.SharedInfo;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.OAuthUtil;

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
	private IAlbumCommentService commentService;
	@Autowired
	private IAlbumSlideService albumSlideService;
//	@Autowired
//	private ICounterService counterService;
	@Autowired
	private IAlbumCounterService albumCounterService;
	@Autowired
	private IHotService hotService;

	/*非全屏情况下item的数量*/
	public static final int HOME_LIMIT = NumberUtils.toInt(ConfigUtil.getString("main_home_album_limit"), 2);
	/*全屏情况下item的数量*/
	public static final int FULL_LIMIT = NumberUtils.toInt(ConfigUtil.getString("main_latest_album_limit"), 4);
	/*侧栏item的数量*/
	public static final int SIDE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("slide_latest_album_limit"), 3*2);
	/*首页专辑推荐的排序数量*/
	public static final int INDEX_SLIDE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("index_slide_limit"), 4);
	
	

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
//		if(logger.isDebugEnabled()){
//			logger.debug("首页");
//		}
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
//		if(logger.isDebugEnabled()){
//			logger.debug("时间轴作品集");
//		}
		return "album/latestAlbums";
	}

	@NeedAuthorize
	@RequestMapping(value = "/followAlbums", method = RequestMethod.GET)
	public String followAlbums(Model model) {
//		if(logger.isDebugEnabled()){
//			logger.debug("我的关注作品集");
//		}
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
	public String albumInfo(HttpServletRequest request, Model model, @PathVariable int albumId) {
		return albumInfo(request, model, albumId, -1);
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
	public String albumInfo(HttpServletRequest request, Model model, @PathVariable int albumId, @PathVariable int albumSlideId) {
		Album albumInfo = albumService.loadById(albumId);
		if (albumInfo != null) {
			// 读取作品列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
			albumInfo.setSlideList(slideList);
			if (albumSlideId < 0) {//需选择指定coverId的作为封面图
				//默认使用第一张
				albumSlideId = slideList.get(0).getId();
				//有指定coverId的情况下，使用coverId对应的作为封面图
				for (AlbumSlide albumSlide: slideList) {
					if (albumSlide.getIsCover()!=null) {
						albumSlideId = albumSlide.getId();
						break;
					}
				}
			}
			// 选择cover图片
			for (int slideIndex = 0; slideIndex < slideList.size(); slideIndex++) {
				AlbumSlide albumSlide = slideList.get(slideIndex);
				if (albumSlideId == albumSlide.getId()) {
					model.addAttribute("albumSlide", albumSlide);
					// 设置slide的index，用以判断是否有上一张、下一张
					model.addAttribute("slideIndex", slideIndex);
					break;
				}
			}
			
			//加载交互状态（赞、收藏）
			User currentUser = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
			if(currentUser!=null){
				albumService.initAlbumInteractionStatus(albumInfo, currentUser.getId());
				
				//提交发布第三， for test
//				boolean isShareOut = UserSettingsController.ALBUM_SHAREOUT_FLAG;
//				if(albumInfo!=null && albumInfo.getId()> 0&& isShareOut){
//					List<SharedInfo> sharedInfoList = OAuthUtil.buildSharedInfoList(albumInfo, currentUser.getAccessTokenMap());
//				}
			}
			
			// 增加浏览计数
			int guestId = currentUser!=null?currentUser.getId():0;
			if(logger.isDebugEnabled()){
                logger.debug("增加专辑["+albumId+"]浏览计数, 浏览人: " + guestId);
            }
			albumCounterService.incrBrowser(albumInfo.getUserId(), albumId, guestId);
			
			//加载作者信息
			User queryUser = userService.loadById(albumInfo.getUserId());
			model.addAttribute(ConstFront.REQUEST_USER_ATTRIBUTE, queryUser);

			//加载专辑的计数信息
			if(logger.isDebugEnabled()){
                logger.debug("加载专辑["+albumId+"]浏览计数");
            }
			albumService.initAlbumWithCount(albumInfo);
			
			//加载专辑的标签
			if(logger.isDebugEnabled()){
                logger.debug("加载专辑["+albumId+"]的标签");
            }
			albumService.initAlbumWithTags(albumInfo);
			
			model.addAttribute("albumInfo", albumInfo);
			return "album/albumInfo";
		}else{
			if(logger.isErrorEnabled()){
				logger.error("加载作品集["+albumId+"]出错");
			}
			throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
		}
	}


	@RequestMapping(value = "moreAlbums.json")
	public ModelAndView moreAlbums(HttpServletRequest request, @RequestParam("albumsTailId") int tailId, int numberPerLine) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多专辑，tailId: "+tailId +", 每页展示条目："+numberPerLine);
        }
	    int limit = 4;
		int designerId = NumberUtils.toInt(request.getParameter("designerId"));
		List<Album> albumList = null;
		if (designerId > 0) {//设计师专辑类型
		    if(logger.isDebugEnabled()){
	            logger.debug("查询设计师专辑列表");
	        }
			limit = HOME_LIMIT;
			albumList = albumService.fallLoadDesignerAlbums(designerId, tailId, limit + 1, true, true);
		} else {//首页全屏类型
		    if(logger.isDebugEnabled()){
                logger.debug("查询首页专辑列表");
            }
			limit = FULL_LIMIT;
			albumList = albumService.fallLoadAlbums(tailId, limit + 1,  true,  true);
		}

		int nextTailId = 0;
		if (albumList == null || albumList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
	                logger.debug("还有更多专辑，tailId： "+nextTailId);
	            }
			}
			String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(albumList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}

	@NeedAuthorize
	@RequestMapping(value = "moreUserFollowAlbums.json")
	public ModelAndView moreUserFollowAlbums(HttpServletRequest request, int albumsTailId, int numberPerLine) {
	    User currentUser = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = currentUser.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载我的关注专辑，userId："+userId+"，albumsTailId: "+albumsTailId +", 每页展示条目："+numberPerLine);
        }

		int limit = FULL_LIMIT;
		//获取关注列表
		List<Album> albumList = albumService.fallLoadUserFollowAlbums(userId, albumsTailId, limit + 1);
		int nextTailId = 0;

		if (albumList == null || albumList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("还有更多专辑，tailId： "+nextTailId);
                }
			}
			String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(albumList, numberPerLine);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}

	@RequestMapping(value = "/moreTagAlbums.json")
	public ModelAndView moreTagAlbums(Model model, HttpServletRequest request, @RequestParam("tag") String tagName, @RequestParam("albumsTailId") int tailId,
			int numberPerLine) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载Tag专辑，tagName："+tagName+"，tailId: "+tailId +", 每页展示条目："+numberPerLine);
        }
	    
	    int limit = 1;
		List<Album> albumList = null;
		albumList = albumService.fallLoadAlbumsByTagName(tagName, tailId, limit + 1);

		int nextTailId = 0;
		if (albumList == null || albumList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				nextTailId = albumList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("还有更多专辑，tailId： "+nextTailId);
                }
			}
			String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(albumList, numberPerLine); 
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	
	//日热门
    @RequestMapping(value = "/hot/dailyAlbums", method = RequestMethod.GET)
    public String hotDailyAlbums(Model model) {
        return hotAlbums(model, IHotService.DAILY_FLAG, ConstFront.HOT_ALBUM_DAILY_LIMIT); 
    }
    
    //周热门
    @RequestMapping(value = "/hot/weeklyAlbums", method = RequestMethod.GET)
    public String hotWeeklyAlbums(Model model) {
        return hotAlbums(model, IHotService.WEEKLY_FLAG, ConstFront.HOT_ALBUM_WEEKLY_LIMIT);
    }
    
    //月热门
    @RequestMapping(value = "/hot/monthlyAlbums", method = RequestMethod.GET)
    public String hotMonthlyAlbums(Model model) {
        return hotAlbums(model, IHotService.MONTHLY_FLAG, ConstFront.HOT_ALBUM_MONTHLY_LIMIT);
    }
    
    private String hotAlbums(Model model, int mode, int limit) {
        List<Album> hotAlbumList = hotService.fallLoadHotAlbums(mode, limit);
        model.addAttribute("hotAlbumList", hotAlbumList);
        model.addAttribute("mode", mode);
        return "album/hotAlbums";
    }
    
//	@RequestMapping(value = "/hot/albums.json")
//	public ModelAndView moreHotAlbums4Json(Model model, HttpServletRequest request, int period) {
//		int limit = 4;
//		List<Album> albumList = null;
//		albumList = hotService.fallLoadHotAlbums(period, 0, limit);
//
//		int nextTailId = 0;
//		if (albumList == null || albumList.size() == 0) {
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
//		} else {
//			String responseHtml = HtmlUtils.buildFallLoadHtml(albumList, 3);
//			Map<String, String> dataMap = new HashMap<String, String>();
//			dataMap.put("html", responseHtml);
//			dataMap.put("tailId", String.valueOf(nextTailId));
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
//		}
//	}

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
			albumList = albumService.fallLoadDesignerAlbums(designerId, tailId, limit, false, false);
		} else {
			albumList = albumService.fallLoadAlbums(tailId, limit,  false, false);
		}
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = DesignerHtmlUtils.buildSidebarHtml(albumList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	/**
	 * 右侧栏加载的最热作品
	 * 
	 * @param request
	 * @param numberPerLine
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "sideHotAlbums.json")
	public ModelAndView sideHotAlbums(HttpServletRequest request) {
		List<Album> albumList = null;
		//hotService.fallLoadHotAlbums(0, SIDE_LIMIT);
		if (albumList == null || albumList.size() == 0) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			String responseHtml = DesignerHtmlUtils.buildSidebarHtml(albumList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
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
