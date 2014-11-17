/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache.counter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.AlbumFavorite;

/**
 * Comments for AlbumFavoriteCache.java
 * redis数据结构为Sorted set
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:09
 */
@Repository
public class AlbumFavoriteCache {

    
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;
    
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(AlbumFavoriteCache.class);

    private static final String KEY_PREFIX = "albumFavorite";
    

    private String getKey(int albumId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + albumId;
    }
    
    /**
     * 获取指定albumId的收藏数
     * @param albumId
     * @return
     * @throws RedisKeyNotExistException
     */
    public long getFavoriteCount(int albumId) throws RedisKeyNotExistException{
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
            logger.error("getFavoriteCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }
    
    /**
     * 收藏
     * @param albumFavorite
     * @return
     * @throws RedisKeyNotExistException
     */
    public boolean favorite(int userId, int albumId) throws RedisKeyNotExistException {
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
            logger.error("favorite", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 取消收藏
     * @param uid
     * @param userId
     * @return
     */
    public boolean unfavorite(int userId, int albumId) {
        String key = getKey(albumId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(userId)) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("unfavorite", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }
    
    public boolean isFavorite(int userId, int albumId) throws RedisKeyNotExistException {
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
            logger.error("isFavorite", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }
    
    /**
     * 重建favorite缓存
     * @param albumId
     * @param favoriteList
     * @return
     */
    public boolean setFavoriteList(int albumId, List<AlbumFavorite> favoriteList) {
        if (favoriteList != null && favoriteList.size() > 0) {
            String key = getKey(albumId);
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
                boolean result = false;
                if(favoriteList!=null&&favoriteList.size()>0){
	                for (AlbumFavorite albumFavorite : favoriteList) {
						shardedJedis.zadd(key, albumFavorite.getCreateTime().getTime(), String.valueOf(albumFavorite.getUserId()));
					}
					result =  true;
				}else{
					result = false;
				}
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            } catch (JedisException t) {
                logger.error("setFavoriteList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

}
