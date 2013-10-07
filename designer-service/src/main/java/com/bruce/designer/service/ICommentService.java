package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.Comment;

public interface ICommentService extends IBaseService<Comment, Long> {

	public List<Comment> queryCommentsByAlbumId(int albumId);
	
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
	public int comment(String title, String content, int albumId, int albumSlideId, int fromId, int toId, int designerId);
	
	
	/**
	 * 发表评论
	 * @param albumId
	 * @param albumSlideId
	 * @param fromId
	 * @param toId
	 * @param content
	 * @return
	 */
	public int comment(String title, String content, int albumId, int albumSlideId, int fromId, int toId);
}
