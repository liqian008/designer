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

import com.bruce.designer.cache.counter.AlbumCounterCache;
import com.bruce.designer.cache.counter.CounterCache;
import com.bruce.designer.cache.counter.HotAlbumCache;
import com.bruce.designer.cache.counter.HotDesignerCache;
import com.bruce.designer.dao.IAlbumCounterDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.AlbumCounter;
import com.bruce.designer.service.IAlbumCounterService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
//@Service
public class AlbmCounterServiceImpl implements IAlbumCounterService, InitializingBean { 

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AlbmCounterServiceImpl.class);

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
