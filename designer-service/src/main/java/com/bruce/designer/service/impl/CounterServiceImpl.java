/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.cache.counter.CounterCache;
import com.bruce.designer.cache.counter.HotAlbumCache;
import com.bruce.designer.cache.counter.HotDesignerCache;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstScoreWeight;
import com.bruce.designer.service.ICounterService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
@Service
public class CounterServiceImpl implements ICounterService, InitializingBean { 

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CounterServiceImpl.class);

	@Autowired
	private CounterCache counterCache;
	@Autowired
	private HotAlbumCache hotAlbumCache;
	@Autowired
	private HotDesignerCache hotDesignerCache;

	@Override
	public long getCount(String key) {
		return counterCache.getCount(key);
	}

	@Override
	public Map<String, Long> mGetCounter(List<String> keyList) {
		return counterCache.mGetCounter(keyList);
	}

	@Override
	public long increase(String key) {
		return counterCache.increase(key);
	}

	@Override
	public long increaseNum(String key, int num) {
		return counterCache.increaseNum(key, num);
	}

	@Override
	public long reduce(String key) {
		return counterCache.reduce(key);
	}

	@Override
	public long reduceNum(String key, int num) {
		return counterCache.reduceNum(key, num);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(counterCache, "counterCache must not null!");
	}

	@Override
	public long incrBrowser(int designerId, int albumId, int albumSlideId, int number) {
		// 专辑浏览计数
		increaseNum(ConstRedis.COUNTER_KEY_ALBUM_BROWSE + albumId, number);
		//单品浏览计数
		long albumSlideLikeCount = increaseNum(ConstRedis.COUNTER_KEY_ALBUMSLIDE_BROWSE + albumSlideId, number);
		//热门排行计分
		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_BROWSE);
		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_BROWSE);
		return albumSlideLikeCount;
	}

	@Override
	public long incrComment(int designerId, int albumId, int albumSlideId, int number) {
		// 专辑评论计数
		increaseNum(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId, number);
		//单品评论计数
		long albumSlideCommentCount = increaseNum(ConstRedis.COUNTER_KEY_ALBUMSLIDE_COMMENT + albumSlideId, number);
		//热门排行计分
		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_COMMENT);
		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_COMMENT);
		
		return albumSlideCommentCount;
	}

	@Override
	public long incrLike(int designerId, int albumId, int albumSlideId, int number) {
		// 专辑like计数
		increaseNum(ConstRedis.COUNTER_KEY_ALBUM_LIKE + albumId, number);
		//单品like计数
		long albumSlideLikeCount = increaseNum(ConstRedis.COUNTER_KEY_ALBUMSLIDE_LIKE + albumSlideId, number);
		//热门排行计分
		hotAlbumCache.incrScore(albumId, ConstScoreWeight.SCORE_ALBUM_LIKE);
		hotDesignerCache.incrScore(designerId, ConstScoreWeight.SCORE_USER_LIKE);
		return albumSlideLikeCount;
	}
	
	@Override
	public long incrBrowser(int designerId, int albumId, int albumSlideId) {
		return incrBrowser(designerId,  albumId, albumSlideId, 1);
	}

	@Override
	public long incrComment(int designerId, int albumId, int albumSlideId) {
		return incrComment(designerId,  albumId, albumSlideId, 1);
	}

	@Override
	public long incrLike(int designerId, int albumId, int albumSlideId) {
		return incrLike(designerId,  albumId, albumSlideId, 1);
	}
	
}
