package com.bruce.designer.cache.counter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * AlbumCounterCache.java
 * redis数据结构为Sorted set
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class AlbumCounterCache{
	
    private static final Logger logger = Logger.getLogger(AlbumCounterCache.class);
    
	@Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;
	
	/* 整个作品辑浏览数的key */
	public static final String COUNTER_KEY_ALBUM_BROWSE = "albumBrowse";
	/* 整个作品辑评论数的key */
	public static final String COUNTER_KEY_ALBUM_COMMENT = "albumComment";
	
	
	public long getBrowseCount(int albumId) throws RedisKeyNotExistException {
	    return getCount(getBrowseKey(), albumId);
    }
	
	public long getCommentCount(int albumId) throws RedisKeyNotExistException {
	    return getCount(getCommentKey(), albumId);
    }
	
	
	/**
	 * 增加浏览
	 * @param albumId
	 * @return
	 * @throws RedisKeyNotExistException
	 */
	public long incrBrowse(int albumId) throws RedisKeyNotExistException {
	    return incrByKey(getBrowseKey(), albumId);
    }
	
	/**
	 * 增加评论
	 * @param albumId
	 * @return
	 * @throws RedisKeyNotExistException
	 */
	public long incrComment(int albumId) throws RedisKeyNotExistException {
        return incrByKey(getCommentKey(), albumId);
    }
	
	private long incrByKey(String key, int id) throws RedisKeyNotExistException {
        long result = 0;
	    DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                result = new Double(shardedJedis.zincrby(key, 1, String.valueOf(id))).longValue();
                cacheShardedJedisPool.returnResource(shardedJedis);
            }
            return result;
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }
	
    private long getCount(String key, int id) throws RedisKeyNotExistException {
		long result = 0;
	    DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Double countNum = shardedJedis.zscore(key, String.valueOf(id));
                cacheShardedJedisPool.returnResource(shardedJedis);
                if(countNum!=null){
	            	result = countNum.longValue();
	            }
            }
            return result;
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return result;
    }
	
	/**
	 * 重建浏览缓存
	 * @param dataList
	 * @return
	 */
	public boolean setBrowseDataList(List<CountCacheBean> dataList) {
	    return setDataList(getBrowseKey(), dataList);
	}
	
	/**
     * 重建评论缓存
     * @param dataList
     * @return
     */
	public boolean setCommentDataList(List<CountCacheBean> dataList) {
        return setDataList(getCommentKey(), dataList);
    }

	private boolean setDataList(String key, List<CountCacheBean> dataList) {
        boolean result = false;
        if (dataList != null && dataList.size() > 0) {
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
                if(dataList!=null&&dataList.size()>0){
                    for (CountCacheBean cacheBean : dataList) {
                        shardedJedis.zadd(key, cacheBean.getScore(), String.valueOf(cacheBean.getMember()));
                    }
                    result =  true;
                }
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            } catch (JedisException t) {
                logger.error("setLikeList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return result;
    }
    
	private String getBrowseKey() {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_BROWSE;
    }

    private String getCommentKey() {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_ALBUM_COMMENT;
    }
}
