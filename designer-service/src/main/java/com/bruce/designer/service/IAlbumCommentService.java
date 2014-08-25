package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;
import com.bruce.foundation.service.IFoundationDao;

/**
 * 专辑交互service
 * @author liqian
 *
 */
public interface IAlbumCommentService extends IFoundationDao<Comment, Long, CommentCriteria> {

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

	/**
     * 分组查询评论数据，为重建索引提供数据
     */
    public List<CountCacheBean> queryCommentStat();
	
}
