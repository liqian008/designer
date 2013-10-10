package com.bruce.designer.cache.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.model.UserFavorite;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * Comments for UserFavoriteCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class FavoriteCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FavoriteCache.class);

    private static final String KEY_PREFIX = "favorite";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    /**
     * 添加收藏至缓存
     * 
     * @param favorite
     * @return
     */
    public boolean addFavorite(UserFavorite favorite) throws RedisKeyNotExistException {
        String key = getKey(favorite.getUserId());
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) favorite.getCreateTime().getTime(),
                        String.valueOf(favorite.getFavoriteAlbumId())) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("addUserFavorite", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 取消收藏至缓存
     * 
     * @param favorite
     * @return
     */
    public boolean removeFavorite(int uid, int albumId) {
        String key = getKey(uid);
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
            logger.error("removeUserFavorite", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 是否收藏
     * 
     * @param favorite
     * @return
     */
    public boolean isFavorite(int userId, int albumId) throws RedisKeyNotExistException {
        String key = getKey(userId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(albumId)) != null;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("isUserFavoriteed", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }


    /**
     * 设置收藏列表
     * 
     * @param uid
     * @param favoriteList
     * @return
     */
    public boolean setFavoriteList(long uid, List<UserFavorite> favoriteList) {
        if (favoriteList != null && favoriteList.size() > 0) {
            String key = getKey(uid);
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
                Map<Double, String> favoriteMap = new HashMap<Double, String>();
                for (UserFavorite favorite : favoriteList) {
                    favoriteMap.put((double) favorite.getCreateTime().getTime(), String.valueOf(favorite.getFavoriteAlbumId()));
                }

                boolean result = shardedJedis.zadd(key, favoriteMap) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);

                return result;
            } catch (JedisException t) {
                logger.error("setUserFavoriteList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

    /**
     * 获取收藏列表
     * 
     * @param uid
     * @return
     */
    public List<UserFavorite> getAllFavoriteList(int userId) throws RedisKeyNotExistException {
        String key = getKey(userId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, 0, -1);

                cacheShardedJedisPool.returnResource(shardedJedis);
                List<UserFavorite> favoriteList = new ArrayList<UserFavorite>();
                for (Tuple tuple : tupleSet) {
                    UserFavorite favorite = new UserFavorite();
                    favorite.setUserId(userId);
                    favorite.setCreateTime(new Date((long) tuple.getScore()));
                    favorite.setFavoriteAlbumId(NumberUtils.toInt(tuple.getElement()));
                    favoriteList.add(favorite);
                }
                return favoriteList;
            }
        } catch (JedisException t) {
            logger.error("getAllUserFavoriteList", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    /**
     * 分页获取收藏列表
     * 
     * @param uid
     * @return
     */
    public List<UserFavorite> getFavoriteList(int uid, int start, int end)
            throws RedisKeyNotExistException {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {

                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, start, end);

                cacheShardedJedisPool.returnResource(shardedJedis);
                List<UserFavorite> favoriteList = new ArrayList<UserFavorite>();
                for (Tuple tuple : tupleSet) {
                    UserFavorite favorite = new UserFavorite();
                    favorite.setUserId(uid);
                    favorite.setCreateTime(new Date((long) tuple.getScore()));
                    favorite.setFavoriteAlbumId(NumberUtils.toInt(tuple.getElement()));
                    favoriteList.add(favorite);
                }
                return favoriteList;
            }
        } catch (JedisException t) {
            logger.error("getUserFavoriteList", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }
    
    private String getKey(long uid) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + uid;
    }


}
