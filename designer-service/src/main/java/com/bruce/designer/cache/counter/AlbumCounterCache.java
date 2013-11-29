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
public class AlbumCounterCache extends AbstractCounterCache {

	/* 整个作品辑浏览数的key */
	public static final String COUNTER_KEY_ALBUM_BROWSE = "albumBrowse";
	/* 整个作品辑评论数的key */
	public static final String COUNTER_KEY_ALBUM_COMMENT = "albumComment";
	/* 整个作品辑喜欢数的key */
	public static final String COUNTER_KEY_ALBUM_LIKE = "albumLike";
	/* 整个作品辑收藏数的key */
	public static final String COUNTER_KEY_ALBUM_FAVORITE = "albumFavorite";

	@Override
	protected String getBrowseKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_BROWSE;
	}

	@Override
	protected String getCommentKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_COMMENT;
	}

	@Override
	protected String getLikeKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_LIKE;
	}

	@Override
	protected String getFavoriteKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_LIKE;
	}

}
