package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.service.*;
import com.bruce.designer.util.UploadUtil;

/**
 * 专辑交互Controller
 * @author liqian
 *
 */
@Controller
public class AlbumInteractiveController {
	
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

	private static final Logger logger = LoggerFactory.getLogger(AlbumInteractiveController.class);
	
	/**
	 * 加载更多评论
	 * @param request
	 * @param albumId
	 * @param tailId
	 * @return
	 */
	@RequestMapping(value = "moreComments.json")
	public ModelAndView moreComments(HttpServletRequest request, int albumId, @RequestParam("commentsTailId") long tailId) {
		int limit = 5;
		List<Comment> commentList = albumCommentService.fallLoadComments(albumId, tailId, limit + 1);

		long nextTailId = 0;
		if (commentList != null) {
			if (commentList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				commentList.remove(limit);
				nextTailId = commentList.get(limit - 1).getId();
			}
		}
		
		Integer userId = null;
		User currentUser = getSessionUser(request);
		if(currentUser!=null){
			userId = currentUser.getId();
		}
		String responseHtml = buildFallLoadHtml(commentList, userId);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", responseHtml);
		dataMap.put("tailId", String.valueOf(nextTailId));
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}

	private String buildFallLoadHtml(List<Comment> commentList, Integer userId) {
		// TODO freemarker template
		if (commentList != null && commentList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Comment comment : commentList) {
				sb.append("<li class='comment depth-1' id='li-comment-1'>" + "<div class='comment-container' id='comment-1'><div class='comment-avatar'>"
						+ "<div class='comment-author vcard'>" + "<img src='"+UploadUtil.getAvatarUrl(comment.getFromId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"'/>" + "</div></div>"
						+ "<div class='comment-body'><div class='comment-meta commentmetadata'>" + "<h6 class='comment-author'>"
						+ "<a href='#' rel='external nofollow' class='url'>" + comment.getNickname() + "</a> 发表于 "
						+ DateFormatUtils.format(comment.getCreateTime(), ConstDateFormat.YYYYMMDD_HHMM_FORMAT) + "</h6></div>"
						+ "<div class='comment-content'>");
				sb.append(comment.getComment());
				boolean displayReplyBtn = userId!=null&&!userId.equals(comment.getFromId());
				if(displayReplyBtn){
					sb.append("&nbsp;&nbsp;<a href=\"javascript:reply("+comment.getFromId()+",'"+comment.getNickname()+"')\">回复</a>");
				}
				sb.append("</div> </div></div></li>");
			}
			return sb.toString();
		}
		return "";
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
		Comment commentResult = albumCommentService.comment(null, comment, albumId, fromId, toId, designerId);
		
		if (commentResult != null) {// 成功响应
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(buildCommentHtml(commentResult)));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_ERROR));
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
		Album album = albumService.loadById(albumId);
		long result = albumCounterService.incrLike(album.getUserId(), albumId, currentUser.getId());
		if(result>0){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
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
		boolean result = albumLikeService.unlike(currentUser.getId(), albumId);
		if(result){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
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
		Album album = albumService.loadById(albumId);
//		boolean result = albumLikeService.like(currentUser.getId(), albumId, album.getUserId());
//		boolean result = albumFavoriteService.favorite(currentUser.getId(), albumId, album.getUserId());
		
		long result = albumCounterService.incrFavorite(album.getUserId(), albumId, currentUser.getId());
		if(result>0){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
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
		boolean result = albumFavoriteService.unfavorite(currentUser.getId(), albumId);
		if(result){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
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

	private static String buildCommentHtml(Comment commentResult){
		// TODO 需freemarker优化此方法
		StringBuilder sb = new StringBuilder("<li class='comment depth-1' id='li-comment-1'>" +
				"<div class='comment-container' id='comment-1'><div class='comment-avatar'>" +
				"<div class='comment-author vcard'>" +
				"<img src='"+UploadUtil.getAvatarUrl(commentResult.getFromId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"'/>" +
				"</div></div>" + 
				"<div class='comment-body'><div class='comment-meta commentmetadata'>" +
				"<h6 class='comment-author'>" +
				"<a href='#' rel='external nofollow' class='url'>" +
				commentResult.getNickname()+"</a> 发表于 "+DateFormatUtils.format(commentResult.getCreateTime(), ConstDateFormat.YYYYMMDD_HHMM_FORMAT)+"</h6></div>" +
				"<div class='comment-content'>"+commentResult.getComment()+"</div> </div></div></li>");
		return sb.toString();
	}
	
	
//	private static String buildCommentHtml(List<Comment> commentList, boolean hasMore){
//		if(commentList!=null&&commentList.size()>0){
//			StringBuilder sb = new StringBuilder();
//			for(Comment comment: commentList){
//				sb.append(buildCommentHtml(comment));
//			}
//			if(hasMore){
//				sb.append("");
//			}
//			return sb.toString();
//		}
//		return "";
//	}
	
//	private static String buildMoreHtml(Comment commentResult){
//		// TODO 需freemarker优化此方法
//		StringBuilder sb = new StringBuilder("<li class='comment depth-1' id='li-comment-1'>" +
//			"<div class='comment-container' id='comment-1'>"+
//			"<div class='comment-body'>" +
//			"<div class='comment-content'><a href='javascript:void(0)'>加载更多评论...</a></div> </div></div></li>");
//		return sb.toString();
//	}
}
