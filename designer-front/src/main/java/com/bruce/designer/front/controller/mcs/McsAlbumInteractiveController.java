package com.bruce.designer.front.controller.mcs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.User;
import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstDateFormat;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.DesignerHtmlUtils;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.service.*;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.UploadUtil;

/**
 * 专辑交互Controller
 * @author liqian
 *
 */
@Controller
@RequestMapping(value="/api")
public class McsAlbumInteractiveController {
	
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumCommentService albumCommentService;
	@Autowired
	private IAlbumLikeService albumLikeService;
	@Autowired
	private IAlbumFavoriteService albumFavoriteService;
	@Autowired
	private IAlbumCounterService albumCounterService;
	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(McsAlbumInteractiveController.class);
	
	/*评论分页数量*/
    public static final int COMMENT_LIMIT = NumberUtils.toInt(ConfigUtil.getString("comment_limit"), 20);
    
	/**
	 * 加载更多评论
	 * @param request
	 * @param albumId
	 * @param tailId
	 * @return
	 */
	@RequestMapping(value = "moreComments.json")
	public ModelAndView moreComments(HttpServletRequest request, int albumId, @RequestParam("commentsTailId") long tailId) {
		int limit = COMMENT_LIMIT;
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS获取专辑["+albumId+"]评论列表, tailId： "+tailId+", limit："+limit);
        }
		
		List<Comment> commentList = albumCommentService.fallLoadComments(albumId, tailId, limit + 1);

		long nextTailId = 0;
		if (commentList != null) {
			if (commentList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				commentList.remove(limit);
				nextTailId = commentList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("MCS还有更多评论，tailId： "+nextTailId);
                }
			}
		}
		
//		Integer userId = null;
//		User currentUser = getSessionUser(request);
//		if(currentUser!=null){
//			userId = currentUser.getId();
//		}
//		String responseHtml = DesignerHtmlUtils.buildFallLoadHtml(commentList, userId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("html", responseHtml);
		dataMap.put("commentList", commentList);
		dataMap.put("tailId", String.valueOf(nextTailId));
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}

	

	/**
	 * 评论
	 * @param request
	 * @param comment
	 * @param albumId
	 * @param toId
	 * @param designerId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "comment.json", method = RequestMethod.POST)
	public ModelAndView comment(HttpServletRequest request, String comment, int albumId, int toId, int designerId) {
		User user = getSessionUser(request);
		int fromId = user.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS评论设计师["+designerId+"]的专辑["+albumId+"], from 用户["+fromId+"] to 用户["+toId+"]");
        }
		
		Comment commentResult = albumCommentService.comment(null, comment, albumId, fromId, toId, designerId);
		
		if (commentResult != null) {// 成功响应
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(buildCommentHtml(commentResult)));
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		} else {
		    if(logger.isErrorEnabled()){
	            logger.error("MCS评论设计师["+designerId+"]的专辑["+albumId+"], from 用户["+fromId+"] to 用户["+toId+"]，操作失败");
	        }
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_ERROR));
		    return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
	}
	
	/**
	 * 赞
	 * @param request
	 * @param fromId
	 * @param albumId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "like.json", method = RequestMethod.POST)
	public ModelAndView like(HttpServletRequest request,int albumId) {
		User currentUser = getSessionUser(request);
		int userId = currentUser.getId();
		
		Album album = albumService.loadById(albumId);
		int designerId = album.getUserId();
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS用户["+userId+"]赞了设计师["+designerId+"]的专辑["+albumId+"]");
        }
		long result = albumCounterService.incrLike(album.getUserId(), albumId, userId);
		if(result>0){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("MCS用户["+userId+"]赞设计师["+designerId+"]的专辑["+albumId+"]，操作失败");
            }
			return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
	}
	
	/**
	 * 取消赞
	 * @param request
	 * @param fromId
	 * @param albumId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "unlike.json", method = RequestMethod.POST)
	public ModelAndView unlike(HttpServletRequest request, int albumId) {
		User currentUser = getSessionUser(request);
		int userId = currentUser.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS用户["+userId+"]取消赞设计师的专辑["+albumId+"]");
        }
		
		boolean result = albumLikeService.unlike(userId, albumId);
		if(result){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("MCS用户["+userId+"]取消赞设计师的专辑["+albumId+"]，操作失败");
            }
			return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
	}
	
	/**
	 * 收藏
	 * @param request
	 * @param fromId
	 * @param albumId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "favorite.json", method = RequestMethod.POST)
	public ModelAndView favorite(HttpServletRequest request, int albumId) {
		User currentUser = getSessionUser(request);
		int userId = currentUser.getId();
		Album album = albumService.loadById(albumId);
		int designerId = album.getUserId();
//		boolean result = albumLikeService.like(currentUser.getId(), albumId, album.getUserId());
//		boolean result = albumFavoriteService.favorite(currentUser.getId(), albumId, album.getUserId());
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS用户["+userId+"]收藏了设计师["+designerId+"]的专辑["+albumId+"]");
        }
		long result = albumCounterService.incrFavorite(designerId, albumId, userId);
		if(result>0){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("MCS用户["+userId+"]收藏设计师["+designerId+"]的专辑["+albumId+"]，操作失败");
            }
			return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
	}
	
	/**
	 * 取消收藏
	 * @param request
	 * @param fromId
	 * @param albumId
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "unfavorite.json", method = RequestMethod.POST)
	public ModelAndView unfavorite(HttpServletRequest request, int albumId) {
		User currentUser = getSessionUser(request);
		int userId = currentUser.getId();
		
		if(logger.isDebugEnabled()){
            logger.debug("MCS用户["+userId+"]取消收藏设计师的专辑["+albumId+"]");
        }
		boolean result = albumFavoriteService.unfavorite(currentUser.getId(), albumId);
		if(result){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
		    if(logger.isErrorEnabled()){
                logger.error("MCS用户["+userId+"]取消收藏设计师的专辑["+albumId+"]，操作失败");
            }
			return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
		}
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
