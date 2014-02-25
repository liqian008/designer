/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.counter.AlbumCounterCache;
import com.bruce.designer.cache.counter.CounterCache;
import com.bruce.designer.cache.counter.HotAlbumCache;
import com.bruce.designer.cache.counter.HotDesignerCache;
import com.bruce.designer.dao.IAlbumCounterDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.AlbumCounter;
import com.bruce.designer.service.ICounterService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
//@Service
public class CounterServiceImplBackup implements ICounterService, InitializingBean { 

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CounterServiceImplBackup.class);

	@Autowired
	private AlbumCounterCache albumCounterCache;
	@Autowired
	private IAlbumCounterDao albumCounterDao;
	@Autowired
	private CounterCache counterCache;
	@Autowired
	private HotAlbumCache hotAlbumCache;
	@Autowired
	private HotDesignerCache hotDesignerCache;
	
	
	

//	@Override
//	public long getCount(String key) {
//		return counterCache.getCount(key);
//	}
//
//	@Override
//	public Map<String, Long> mGetCounter(List<String> keyList) {
//		return counterCache.mGetCounter(keyList);
//	}
//
//	@Override
//	public long increase(String key) {
//		return counterCache.increase(key);
//	}
//
//	@Override
//	public long increaseNum(String key, int num) {
//		return counterCache.increaseNum(key, num);
//	}
//
//	@Override
//	public long reduce(String key) {
//		return counterCache.reduce(key);
//	}
//
//	@Override
//	public long reduceNum(String key, int num) {
//		return counterCache.reduceNum(key, num);
//	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(counterCache, "counterCache must not null!");
	}

	@Override
	public long incrBrowser(int designerId, int albumId) {
		//修改专辑浏览计数
		long albumBrowseCount = albumCounterCache.incrBrowse(albumId, 1);
		//TODO 修改设计师的浏览计数
		
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_BROWSE);
//		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_BROWSE);
		hotAlbumCache.incrBrowseScore(albumId);
		hotDesignerCache.incrBrowseScore(designerId);
		return albumBrowseCount;
	}

	@Override
	public long incrComment(int designerId, int albumId) {
		//修改专辑评论计数
		long albumCommentCount = albumCounterCache.incrComment(albumId, 1);
		//TODO 修改设计师的计数
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_COMMENT);
//		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_COMMENT);
		hotAlbumCache.incrCommentScore(albumId);
		hotDesignerCache.incrCommentScore(designerId);
		
		return albumCommentCount;
	}

	@Override
	public long incrLike(int designerId, int albumId) {
		//修改专辑like计数
		long albumLikeCount = albumCounterCache.incrLike(albumId, 1);
		//TODO 修改设计师的计数
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_LIKE);
//		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_LIKE);
		hotAlbumCache.incrLikeScore(albumId);
		hotDesignerCache.incrLikeScore(designerId);
		return albumLikeCount;
	}
	
	@Override
	public long incrFavorite(int designerId, int albumId) {
		//修改专辑收藏计数
		long albumLikeCount = albumCounterCache.incrFavorite(albumId, 1);
		//TODO 修改设计师的计数
		
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_FAVORITE);
//		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_FAVORITE);
		hotAlbumCache.incrFavoriteScore(albumId);
		hotDesignerCache.incrFavoriteScore(designerId);
		return albumLikeCount;
	}
	
	@Override
	public long reduceBrowser(int designerId, int albumId) {
		// 修改专辑浏览计数
		long albumBrowseCount = albumCounterCache.incrBrowse(albumId, 0-1);
		//TODO 修改设计师的计数
		
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, 0-ConstScoreWeight.SCORE_ALBUM_BROWSE);
//		hotDesignerCache.incrScore(designerId, 0-ConstScoreWeight.SCORE_USER_BROWSE);
		hotAlbumCache.reduceBrowseScore(albumId);
		hotDesignerCache.reduceBrowseScore(designerId);
		return albumBrowseCount;
	}

	@Override
	public long reduceComment(int designerId, int albumId) {
		// 修改专辑评论计数
		long albumCommentCount = albumCounterCache.incrComment(albumId, 0-1);
		//TODO 修改设计师的计数
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, 0-ConstScoreWeight.SCORE_ALBUM_COMMENT);
//		hotDesignerCache.incrScore(designerId, 0-ConstScoreWeight.SCORE_USER_COMMENT);
		hotAlbumCache.reduceCommentScore(albumId);
		hotDesignerCache.reduceCommentScore(designerId);
		return albumCommentCount;
	}

	@Override
	public long reduceLike(int designerId, int albumId) {
		// 修改专辑like计数
		long albumLikeCount = albumCounterCache.incrLike(albumId, 0-1);
		//TODO 修改设计师的计数
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, 0-ConstScoreWeight.SCORE_ALBUM_LIKE);
//		hotDesignerCache.incrScore(designerId, 0-ConstScoreWeight.SCORE_USER_LIKE);
		hotAlbumCache.reduceLikeScore(albumId);
		hotDesignerCache.reduceLikeScore(designerId);
		return albumLikeCount;
	}
	
	@Override
	public long reduceFavorite(int designerId, int albumId) {
		// 修改专辑收藏计数
		long albumLikeCount = albumCounterCache.incrFavorite(albumId, 0-1);
		//TODO 修改设计师的计数
		
		//修改热门排行计分
//		hotAlbumCache.incrScore(albumId, 0-ConstScoreWeight.SCORE_ALBUM_FAVORITE);
//		hotDesignerCache.incrScore(designerId, 0-ConstScoreWeight.SCORE_USER_FAVORITE);
		hotAlbumCache.reduceFavoriteScore(albumId);
		hotDesignerCache.reduceFavoriteScore(designerId);
		return albumLikeCount;
	}
	
	
//	@Override
//	public long incrFan(int userId, int fanId) {
//		// 专辑浏览计数
//		long userFanCount = 0;//albumCounterCache.incrBrowse(albumId, 1);
//		// TODO 修改热门排行计分
//		hotDesignerCache.incrScore(userId, ConstScoreWeight.SCORE_USER_FAN);
//		return userFanCount;
//	}
//	
//	@Override
//	public long reduceFan(int userId, int fanId) {
//		// 专辑浏览计数
//		long userFanCount = 0;//albumCounterCache.incrBrowse(albumId, 1);
//		// TODO 修改热门排行计分
//		hotDesignerCache.incrScore(userId, 0-ConstScoreWeight.SCORE_USER_FAN);
//		return userFanCount;
//	}
	
//	@Override
//	public long incrBrowser(int designerId, int albumId) {
//		return incrBrowser(designerId,  albumId, 1);
//	}
//
//	@Override
//	public long incrComment(int designerId, int albumId) {
//		return incrComment(designerId,  albumId, 1);
//	}
//
//	@Override
//	public long incrLike(int designerId, int albumId) {
//		return incrLike(designerId,  albumId, 1);
//	}
	
	@Override
	public boolean removeAlbum(int designer, int albumId) {
		// TODO 删除浏览、评论、like数值
		albumCounterCache.clearBrowse(albumId);
		albumCounterCache.clearComment(albumId);
		albumCounterCache.clearLike(albumId);
		albumCounterCache.clearFavorite(albumId);
		
		//TODO 删除设计师的浏览、评论、like数值
		
		//删除热门作品数值
		return hotAlbumCache.remove(albumId);
	}

	@Override
	public long getBrowseCount(int albumId) {
		int result = 0;
		try {
			return albumCounterCache.getBrowseCount(albumId);
		} catch (RedisKeyNotExistException e) {
			//加载DB，重建缓存
			List<AlbumCounter> dataList = albumCounterDao.queryAll();
			if(dataList!=null&&dataList.size()>0){
				List<CountCacheBean> countList = new ArrayList<CountCacheBean>();
				for (AlbumCounter data : dataList) {
					countList.add(new CountCacheBean(String.valueOf(data.getAlbumId()), data.getBrowseCount()));
                	if(albumId ==data.getAlbumId()){
                		result = data.getBrowseCount();
                	}
                }
				albumCounterCache.setBrowseDataList(countList);
			}
		}
		return result;
	}

	@Override
	public long getCommentCount(int albumId) {
		int result = 0;
		try {
			return albumCounterCache.getCommentCount(albumId);
		} catch (RedisKeyNotExistException e) {
			//加载DB，重建缓存
			List<AlbumCounter> dataList = albumCounterDao.queryAll();
			if(dataList!=null&&dataList.size()>0){
				List<CountCacheBean> countList = new ArrayList<CountCacheBean>();
				for (AlbumCounter data : dataList) {
					countList.add(new CountCacheBean(String.valueOf(data.getAlbumId()), data.getCommentCount()));
                	if(albumId ==data.getAlbumId()){
                		result = data.getCommentCount();
                	}
                }
				albumCounterCache.setCommentDataList(countList); 
			}
		}
		return result;
	}

	@Override
	public long getLikeCount(int albumId) {
		int result = 0;
		try {
			return albumCounterCache.getLikeCount(albumId);
		} catch (RedisKeyNotExistException e) {
			//加载DB，重建缓存
			List<AlbumCounter> dataList = albumCounterDao.queryAll();
			if(dataList!=null&&dataList.size()>0){
				List<CountCacheBean> countList = new ArrayList<CountCacheBean>();
				for (AlbumCounter data : dataList) {
					countList.add(new CountCacheBean(String.valueOf(data.getAlbumId()), data.getLikeCount()));
                	if(albumId ==data.getAlbumId()){
                		result = data.getLikeCount();
                	}
                }
				albumCounterCache.setLikeDataList(countList);
			}
		}
		return result;
	}

	@Override
	public long getFavoriteCount(int albumId) {
		int result = 0;
		try {
			return albumCounterCache.getFavoriteCount(albumId);
		} catch (RedisKeyNotExistException e) {
			//加载DB，重建缓存
			List<AlbumCounter> dataList = albumCounterDao.queryAll();
			if(dataList!=null&&dataList.size()>0){
				List<CountCacheBean> countList = new ArrayList<CountCacheBean>();
				for (AlbumCounter data : dataList) {
					countList.add(new CountCacheBean(String.valueOf(data.getAlbumId()), data.getFavoriteCount()));
                	if(albumId ==data.getAlbumId()){
                		result = data.getFavoriteCount();
                	}
                }
				albumCounterCache.setFavoriteDataList(countList);
			}
		}
		return result;
	}

	@Override
	public long getTotalBrowseCount(int designerId) {
		return 0;
	}

	@Override
	public long getTotalCommentCount(int designerId) {
		return 0;
	}

	@Override
	public long getTotalLikeCount(int designerId) {
		return 0;
	}

	@Override
	public long getTotalFavoriteCount(int designerId) {
		return 0;
	}

	@Override
	public long getTotalFansCount(int designerId) {
		return 0;
	}

	@Override
	public long getTotalFollowsCount(int designerId) {
		return 0;
	}
	
}
