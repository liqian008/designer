package com.bruce.designer.front.controller.mcs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;

@Controller
@RequestMapping(value="/api")
public class McsAlbumController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumCommentService commentService;
	@Autowired
	private IAlbumSlideService albumSlideService;
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
	
	private static final Logger logger = LoggerFactory.getLogger(McsAlbumController.class);

	@RequestMapping(value = "moreAlbums.json")
	public ModelAndView moreAlbums(HttpServletRequest request, @RequestParam(required=false, defaultValue="0") int designerId, @RequestParam(value="albumsTailId") int tailId) {
	    if(logger.isDebugEnabled()){
            logger.debug("MCS加载更多专辑，tailId: "+tailId);
        }
	    int limit = 4;
		List<Album> albumList = null;
		if (designerId > 0) {//设计师专辑类型
		    if(logger.isDebugEnabled()){
	            logger.debug("MCS查询设计师专辑列表");
	        }
			limit = HOME_LIMIT;
			albumList = albumService.fallLoadDesignerAlbums(designerId, tailId, limit + 1, true, true);
		} else {//首页全屏类型
		    if(logger.isDebugEnabled()){
                logger.debug("MCS查询首页专辑列表");
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
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("albumList", albumList);
			dataMap.put("albumTailId", String.valueOf(nextTailId));
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

	/**
	 * 作品详情
	 * 
	 * @param model
	 * @param albumId
	 * @param albumSlideId
	 * @return
	 */
	@RequestMapping(value = "/album/{albumId}.json", method = RequestMethod.GET)
	public ModelAndView albumInfo(HttpServletRequest request, @PathVariable int albumId) {
		if(logger.isDebugEnabled()){
            logger.debug("MCS浏览专辑["+albumId+ "]");
        }
		Album albumInfo = albumService.loadById(albumId);
		if (albumInfo != null) {
			// 读取作品列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
			albumInfo.setSlideList(slideList);
			
			//加载交互状态（赞、收藏）
			User currentUser = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
			if(currentUser!=null){
				if(logger.isDebugEnabled()){
	                logger.debug("MCS加载["+albumId+"]的交互数据");
	            }
				albumService.initAlbumInteractionStatus(albumInfo, currentUser.getId());
			}
			
			// 增加浏览计数
			int guestId = currentUser!=null?currentUser.getId():0;
			if(logger.isDebugEnabled()){
                logger.debug("MCS增加专辑["+albumId+"]浏览计数, 浏览人: " + guestId);
            }
			albumCounterService.incrBrowser(albumInfo.getUserId(), albumId, guestId);
			
			//加载作者信息
//			User queryUser = userService.loadById(albumInfo.getUserId());

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
			
//			model.addAttribute("albumInfo", albumInfo);
//			return "album/albumInfo";
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("albumInfo", albumInfo);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}else{
			if(logger.isErrorEnabled()){
				logger.error("加载作品集["+albumId+"]出错");
			}
			throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
		}
	}
	
	//日热门
    @RequestMapping(value = "/hot/dailyAlbums.json", method = RequestMethod.GET)
    public ModelAndView hotDailyAlbums() {
        return hotAlbums(IHotService.DAILY_FLAG, ConstFront.HOT_ALBUM_DAILY_LIMIT); 
    }
    
    //周热门
    @RequestMapping(value = "/hot/weeklyAlbums.json", method = RequestMethod.GET)
    public ModelAndView hotWeeklyAlbums() {
        return hotAlbums(IHotService.WEEKLY_FLAG, ConstFront.HOT_ALBUM_WEEKLY_LIMIT);
    }
    
    //月热门
    @RequestMapping(value = "/hot/monthlyAlbums.json", method = RequestMethod.GET)
    public ModelAndView hotMonthlyAlbums() {
        return hotAlbums(IHotService.MONTHLY_FLAG, ConstFront.HOT_ALBUM_MONTHLY_LIMIT);
    }
    
    private ModelAndView hotAlbums(int mode, int limit) {
        List<Album> hotAlbumList = hotService.fallLoadHotAlbums(mode, limit);
    	Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("hotAlbumList", hotAlbumList);
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
    }
	
}
