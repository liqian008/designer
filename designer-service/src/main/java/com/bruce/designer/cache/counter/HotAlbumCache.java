package com.bruce.designer.cache.counter;

import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstScoreWeight;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class HotAlbumCache extends AbstractHotCache {

	private static final String KEY_PREFIX = "hotAlbum";

	protected String getKey() {
		return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + KEY_PREFIX;
	}

	@Override
	public long incrBrowseScore(int id) {
		return incrScore(id, ConstScoreWeight.SCORE_ALBUM_BROWSE);
	}

	@Override
	public long incrLikeScore(int id) {
		return incrScore(id, ConstScoreWeight.SCORE_ALBUM_LIKE);
	}

	@Override
	public long incrCommentScore(int id) {
		return incrScore(id, ConstScoreWeight.SCORE_ALBUM_COMMENT);
	}

	@Override
	public long incrFavoriteScore(int id) {
		return incrScore(id, ConstScoreWeight.SCORE_ALBUM_FAVORITE);
	}

	@Override
	public long reduceBrowseScore(int id) {
		return incrScore(id, 0 - ConstScoreWeight.SCORE_ALBUM_BROWSE);
	}

	@Override
	public long reduceLikeScore(int id) {
		return incrScore(id, 0 - ConstScoreWeight.SCORE_ALBUM_LIKE);
	}

	@Override
	public long reduceCommentScore(int id) {
		return incrScore(id, 0 - ConstScoreWeight.SCORE_ALBUM_COMMENT);
	}

	@Override
	public long reduceFavoriteScore(int id) {
		return incrScore(id, 0 - ConstScoreWeight.SCORE_ALBUM_FAVORITE);
	}

}
