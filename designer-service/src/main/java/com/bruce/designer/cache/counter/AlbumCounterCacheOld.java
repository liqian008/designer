package com.bruce.designer.cache.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * AlbumCounterCacheOld.java
 * redis数据结构为Sorted set
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class AlbumCounterCacheOld extends AbstractCounterCache {
	
	@Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;
	
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
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_FAVORITE;
	}
	
	protected String getLikeKey(int albumId) {
//		return null;
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_LIKE;
	}
	
	protected String getFavoriteKey(int albumId) {
		return "";
	}
	
	public long getLikeCount(int id) throws RedisKeyNotExistException{
		//likeCount额外处理
		return getCount(getLikeKey(), id);
	}
	
	public long getFavoriteCount(int id) throws RedisKeyNotExistException{
		//favorite额外处理
		return getCount(getFavoriteKey(), id);
	}

}
