package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface ICommentDao extends IFoundationDao<Comment, Long, CommentCriteria> {

	
	
	/**
     * 瀑布流数据加载dao
     * @param offsetId
     * @param limit
     * @return
     */
    public List<Comment> fallLoadComments(int albumId, Long tailId, int limit);
    
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
	
	/**
     * 分组查询评论数据，为重建索引提供数据
     */
    List<CountCacheBean> queryCommentStat();

}
