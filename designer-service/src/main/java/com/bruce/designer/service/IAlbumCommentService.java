package com.bruce.designer.service;

import java.util.List;
import com.bruce.designer.model.Comment;

/**
 * 专辑交互service
 * @author liqian
 *
 */
public interface IAlbumCommentService extends IBaseService<Comment, Long> {

//	public List<Comment> queryCommentsByAlbumSlideId(int albumSlideId);
//	public List<Comment> queryCommentsByAlbumId(int albumId);

	public List<Comment> fallLoadComments(int albumId, long commentsTailId, int limit);

	

	/**
	 * 评论
	 * @param title
	 * @param content
	 * @param albumId
	 * @param albumSlideId
	 * @param fromId
	 * @param toId
	 * @param designerId
	 * @return
	 */
	public Comment comment(String title, String content, int albumId, int fromId, int toId, int designerId);

	/**
	 * 发表评论
	 * 
	 * @param albumId
	 * @param albumSlideId
	 * @param fromId
	 * @param toId
	 * @param content
	 * @return
	 */
	public Comment comment(String title, String content, int albumId, int fromId, int toId);
	
	
//	/**
//	 * 赞
//	 * @param fromId
//	 * @param designerId
//	 * @param albumId
//	 * @return
//	 */
//	public int like(int fromId, int designerId, int albumId);
//	
//	/**
//	 * 取消赞
//	 * @param fromId
//	 * @param designerId
//	 * @param albumId
//	 * @return
//	 */
//	public int unlike(int fromId, int designerId, int albumId);
//	
//	/**
//	 * 收藏
//	 * @param fromId
//	 * @param designerId
//	 * @param albumId
//	 * @return
//	 */
//	public int favorite(int fromId, int designerId, int albumId);
//	
//	/**
//	 * 取消收藏
//	 * @param fromId
//	 * @param designerId
//	 * @param albumId
//	 * @return
//	 */
//	public int unfavorite(int fromId, int designerId, int albumId);
}
