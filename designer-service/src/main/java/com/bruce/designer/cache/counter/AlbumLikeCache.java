/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache.counter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.AlbumLike;

/**
 * Comments for AlbumLikeCache.java
 * redis数据结构为Sorted set
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:09
 */
@Repository
public class AlbumLikeCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AlbumLikeCache.class);

    private static final String KEY_PREFIX = "albumLike";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    private String getKey(int albumId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + albumId;
    }
    
    /**
     * 获取指定albumId的赞数
     * @param albumId
     * @return
     * @throws RedisKeyNotExistException
     */
    public long getLikeCount(int albumId) throws RedisKeyNotExistException{
    	String key = getKey(albumId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
            	cacheShardedJedisPool.returnResource(shardedJedis);
            	return shardedJedis.zcard(key);
            }
        } catch (JedisException t) {
            logger.error("getLikeCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }
    
    /**
     * 赞
     * @param albumLike
     * @return
     * @throws RedisKeyNotExistException
     */
    public boolean like(int userId, int albumId) throws RedisKeyNotExistException {
//        String key = getKey(albumLike.getAlbumId());
    	String key = getKey(albumId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) System.currentTimeMillis(),
                        String.valueOf(userId)) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("like", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
    * 取消赞
    * @param albumId
    * @param userId
    * @return
    */
    public boolean unlike(int userId, int albumId) {
        String key = getKey(albumId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(albumId)) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("unlike", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }
    
    
    public boolean isLike(int userId, int albumId) throws RedisKeyNotExistException {
        String key = getKey(albumId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(userId)) != null;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("isLiked", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 重建like缓存
     * @param albumId
     * @param likeList
     * @return
     */
    public boolean setLikeList(int albumId, List<AlbumLike> likeList) {
        if (likeList != null && likeList.size() > 0) {
            String key = getKey(albumId);
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
                boolean result = false;
                if(likeList!=null&&likeList.size()>0){
	                for (AlbumLike albumLike : likeList) {
						shardedJedis.zadd(key, albumLike.getCreateTime().getTime(), String.valueOf(albumLike.getUserId()));
					}
					result =  true;
				}else{
					result = false;
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
        return false;
    }

}
