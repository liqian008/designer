package com.bruce.designer.cache.counter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
public abstract class AbstractCounterCache {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AbstractCounterCache.class);

	@Autowired
	private DesignerShardedJedisPool cacheShardedJedisPool;
	
	
	abstract protected String getBrowseKey();
	
	abstract protected String getCommentKey();
	
	abstract protected String getLikeKey();
	
	abstract protected String getFavoriteKey();
	
	/**
	 * 获取浏览数
	 * @param id
	 * @return
	 */
	public long getBrowseCount(int id){
		return getCount(getBrowseKey(), id);
	}
	
	public long getCommentCount(int id){
		return getCount(getCommentKey(), id);
	}
	
	public long getLikeCount(int id){
		return getCount(getLikeKey(), id);
	}
	
	public long getFavoriteCount(int id){
		return getCount(getFavoriteKey(), id);
	}
	
 	/**
	 * 增加浏览数
	 * @param id
	 * @return
	 */
	public long incrBrowse(int id, int score){
		return incrByKey(getBrowseKey(), id, score);
	}
	
	/**
	 * 增加评论数
	 * @param id
	 * @return
	 */
	public long incrComment(int id, int score){
		return incrByKey(getCommentKey(), id, score);
	}
	
	/**
	 * 增加赞数
	 * @param id
	 * @return
	 */
	public long incrLike(int id, int score){
		return incrByKey(getLikeKey(), id, score);
	}
	
	/**
	 * 增加收藏数
	 * @param id
	 * @return
	 */
	public long incrFavorite(int id, int score){
		return incrByKey(getFavoriteKey(), id, score);
	}
	
	/**
	 * 清空浏览数
	 * @param id
	 * @return
	 */
	public boolean clearBrowse(int id){
		return removeByKey(getBrowseKey(), id);
	}
	
	public boolean clearComment(int id){
		return removeByKey(getCommentKey(), id);
	}
	
	public boolean clearLike(int id){
		return removeByKey(getLikeKey(), id);
	}
	
	public boolean clearFavorite(int id){
		return removeByKey(getFavoriteKey(), id);
	}
	
	public long getCount(String key, int id) {
		long value = 0;
		DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            Double countNum = shardedJedis.zscore(key, String.valueOf(id));
            if(countNum!=null){
            	value = countNum.longValue();
            }else{
            	incrByKey(key, id, 0);
            }
            cacheShardedJedisPool.returnResource(shardedJedis);
        } catch (Throwable t) {
            logger.error("getCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return value;
    }
	
	private long incrByKey(String key, int id, int score){
		DesignerShardedJedis shardedJedis = null;
		try {
			shardedJedis = cacheShardedJedisPool.getResource();
			long result = new Double(shardedJedis.zincrby(key, score, String.valueOf(id))).longValue();
			cacheShardedJedisPool.returnResource(shardedJedis);
			return result;
		} catch (JedisException t) {
			logger.error("incrByKey", t);
			if (shardedJedis != null) {
				cacheShardedJedisPool.returnBrokenResource(shardedJedis);
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param key
	 * @param id
	 * @return
	 */
	private boolean removeByKey(String key, int id){
		DesignerShardedJedis shardedJedis = null;
		try {
			shardedJedis = cacheShardedJedisPool.getResource();
			long resultNum = shardedJedis.zrem(key, String.valueOf(id));
			cacheShardedJedisPool.returnResource(shardedJedis);
			if(resultNum==1){
				return true;
			}
		} catch (JedisException t) {
			logger.error("removeByKey", t);
			if (shardedJedis != null) {
				cacheShardedJedisPool.returnBrokenResource(shardedJedis);
			}
		}
		return false;
	}
}
