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

import com.bruce.designer.model.UserFollow;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class FollowCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FollowCache.class);

    private static final String KEY_PREFIX = "follow";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    /**
     * 添加关注至缓存
     * 
     * @param follow
     * @return
     */
    public boolean addFollow(UserFollow follow) throws RedisKeyNotExistException {
        String key = getKey(follow.getUserId());
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) follow.getCreateTime().getTime(),
                        String.valueOf(follow.getFollowId())) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("addUserFollow", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 取消关注至缓存
     * 
     * @param follow
     * @return
     */
    public boolean removeFollow(int userId, int unfollowId) {
        String key = getKey(userId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(unfollowId)) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("removeUserFollow", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 是否关注
     * 
     * @param follow
     * @return
     */
    public boolean isFollowed(int hostId, int clientId) throws RedisKeyNotExistException {
        String key = getKey(hostId);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(clientId)) != null;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("isUserFollowed", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 是否粉丝
     * 
     * @param follow
     * @return
     */
    public boolean isFans(long uid1, long uid2) throws RedisKeyNotExistException {
        String key = getKey(uid2);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(uid1)) != null;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("isFans", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 设置关注列表
     * 
     * @param uid
     * @param followList
     * @return
     */
    public boolean setFollowList(long uid, List<UserFollow> followList) {
        if (followList != null && followList.size() > 0) {
            String key = getKey(uid);
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
                Map<Double, String> followMap = new HashMap<Double, String>();
                for (UserFollow follow : followList) {
                    followMap.put((double) follow.getCreateTime().getTime(), String.valueOf(follow.getFollowId()));
                }

                boolean result = shardedJedis.zadd(key, followMap) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);

                return result;
            } catch (JedisException t) {
                logger.error("setUserFollowList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

    /**
     * 获取关注列表
     * 
     * @param uid
     * @return
     */
    public List<UserFollow> getAllFollowList(int uid) throws RedisKeyNotExistException {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrevrangeWithScores(key, 0, -1);

                cacheShardedJedisPool.returnResource(shardedJedis);
                List<UserFollow> followList = new ArrayList<UserFollow>();
                for (Tuple tuple : tupleSet) {
                    UserFollow follow = new UserFollow();
                    follow.setUserId(uid);
                    follow.setCreateTime(new Date((long) tuple.getScore()));
                    follow.setFollowId(NumberUtils.toInt(tuple.getElement()));
                    followList.add(follow);
                }
                return followList;
            }
        } catch (JedisException t) {
            logger.error("getAllUserFollowList", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    /**
     * 分页获取关注列表
     * 
     * @param uid
     * @return
     */
    public List<UserFollow> getFollowList(int uid, int start, int end)
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
                Set<Tuple> tupleSet = shardedJedis.zrevrangeWithScores(key, start, end);

                cacheShardedJedisPool.returnResource(shardedJedis);
                List<UserFollow> followList = new ArrayList<UserFollow>();
                for (Tuple tuple : tupleSet) {
                    UserFollow follow = new UserFollow();
                    follow.setUserId(uid);
                    follow.setCreateTime(new Date((long) tuple.getScore()));
                    follow.setFollowId(NumberUtils.toInt(tuple.getElement()));
                    followList.add(follow);
                }
                return followList;
            }
        } catch (JedisException t) {
            logger.error("getUserFollowList", t);
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
