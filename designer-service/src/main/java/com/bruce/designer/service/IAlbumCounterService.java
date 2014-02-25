/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service;

/**
 * 计数服务
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:07:34
 */
public interface IAlbumCounterService {
	
	public long incrBrowser(int designerId, int albumId);

	public long reduceBrowser(int designerId, int albumId);

	public long incrComment(int designerId, int albumId);

	public long reduceComment(int designerId, int albumId);

	// 计算专辑的访问数据
	public long getBrowseCount(int albumId);

	public long getCommentCount(int albumId);

	// 删除作品时扣减热门计数
	public boolean removeAlbum(int designer, int albumId);

	// 计算设计师的访问数据
	public long getTotalBrowseCount(int designerId);

	public long getTotalCommentCount(int designerId);

	public long getTotalLikeCount(int designerId);

	public long getTotalFavoriteCount(int designerId);

	public long getTotalFansCount(int designerId);

	public long getTotalFollowsCount(int designerId);

}
