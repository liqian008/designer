package com.bruce.designer.cache.counter;

import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstRedis;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
@Deprecated
public class UserCounterCache extends AbstractCounterCache {
	
	/* 整个用户浏览数的key */
	public static final String COUNTER_KEY_USER_BROWSE = "userBrowse";
	/* 整个用户评论数的key */
	public static final String COUNTER_KEY_USER_COMMENT = "userComment";
	/* 整个用户喜欢数的key */
	public static final String COUNTER_KEY_USER_LIKE = "userLike";
	/* 整个用户收藏数的key */
	public static final String COUNTER_KEY_USER_FAVORITE = "userFavorite";

	@Override
	protected String getBrowseKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_USER_BROWSE;
	}

	@Override
	protected String getCommentKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_USER_COMMENT;
	}

	@Override
	protected String getLikeKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_USER_LIKE;
	}

	@Override
	protected String getFavoriteKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_USER_FAVORITE;
	}

}
