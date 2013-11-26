package com.bruce.designer.service;

import java.util.List;
import com.bruce.designer.model.Comment;

public interface ICommentService extends IBaseService<Comment, Long> {

//	public List<Comment> queryCommentsByAlbumSlideId(int albumSlideId);
//	public List<Comment> queryCommentsByAlbumId(int albumId);

	public List<Comment> fallLoadComments(int albumId, long commentsTailId, int limit);


	public int like(int designerId, int albumId);

	/**
	 * 
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
}
