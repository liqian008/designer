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
public class HotAlbumCache extends AbstractHotCache {

	private static final String KEY_PREFIX = "hot_album";

	protected String getKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX;
	}

}
