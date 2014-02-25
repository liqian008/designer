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
public interface ICounterService {

//	/**
//	 * 获取计数
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public long getCount(String key);
//
//	/**
//	 * 批量获取计数
//	 * 
//	 * @param keyList
//	 * @return
//	 */
//	public Map<String, Long> mGetCounter(List<String> keyList);
//
//	/**
//	 * 计数加一
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public long increase(String key);
//
//	/**
//	 * 计数加num
//	 * 
//	 * @param key
//	 * @param num
//	 * @return
//	 */
//	public long increaseNum(String key, int num);
//
//	/**
//	 * 计数减一
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public long reduce(String key);
//
//	/**
//	 * 计数减num
//	 * 
//	 * @param namespace
//	 * @param key
//	 * @param num
//	 * @return
//	 */
//	public long reduceNum(String key, int num);


//	public long incrBrowser(int designerId, int albumId, int number);
//
//
//	public long incrComment(int designerId, int albumId, int number);
//
//	public long incrLike(int designerId, int albumId, int number);
	
	
	public long incrBrowser(int designerId, int albumId);
	
	public long incrComment(int designerId, int albumId);
	
	public long incrLike(int designerId, int albumId);
	
	public long incrFavorite(int designerId, int albumId);
	
	
	public long reduceBrowser(int designerId, int albumId);
	
	public long reduceComment(int designerId, int albumId);
	
	public long reduceLike(int designerId, int albumId);
	
	public long reduceFavorite(int designerId, int albumId);
	
//	public long incrFan(int userId, int fanId);
//	
//	public long reduceFan(int userId, int fanId);
	
	//删除作品时扣减热门计数
	public boolean removeAlbum(int designer, int albumId);

	//计算专辑的访问数据
	public long getBrowseCount(int albumId);
	
	public long getCommentCount(int albumId);
	
	public long getLikeCount(int albumId);
	
	public long getFavoriteCount(int albumId);
	
	//计算设计师的访问数据
	public long getTotalBrowseCount(int designerId);
	
	public long getTotalCommentCount(int designerId);
	
	public long getTotalLikeCount(int designerId);
	
	public long getTotalFavoriteCount(int designerId);
	
	public long getTotalFansCount(int designerId);
	
	public long getTotalFollowsCount(int designerId);
	
	
	/**
	 * 收藏计数
	 * 
	 * @param designerId
	 * @param albumId
	 */
	// public long favorite(int designerId, int albumId);
}
