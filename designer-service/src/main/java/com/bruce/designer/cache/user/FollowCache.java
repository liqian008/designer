/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.model.UserFollow;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
public class FollowCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FollowCache.class);

    private static final String KEY_PREFIX = "follow_";

    private DesignerShardedJedisPool shardedJedisPool;

    /**
     * 添加关注至缓存
     * 
     * @param follow
     * @return
     */
    public boolean addFollow(UserFollow follow) throws RedisKeyNotExistException {
        String key = KEY_PREFIX + follow.getUserId();
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) follow.getCreateTime().getTime(),
                        String.valueOf(follow.getFollowId())) > 0;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("addUserFollow", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
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
    public boolean removeFollow(int uid, long follow) {
        String key = KEY_PREFIX + uid;
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(follow)) > 0;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("removeUserFollow", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
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
        String key = KEY_PREFIX + hostId;
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(clientId)) != null;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("isUserFollowed", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
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
        String key = KEY_PREFIX + uid2;
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zrank(key, String.valueOf(uid1)) != null;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("isFans", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
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
            String key = KEY_PREFIX + uid;
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = shardedJedisPool.getResource();
                shardedJedis.del(key);
                Map<Double, String> followMap = new HashMap<Double, String>();
                for (UserFollow follow : followList) {
                    followMap.put((double) follow.getCreateTime().getTime(), String.valueOf(follow.getFollowId()));
                }

                boolean result = shardedJedis.zadd(key, followMap) > 0;
                shardedJedisPool.returnResource(shardedJedis);

                return result;
            } catch (JedisException t) {
                logger.error("setUserFollowList", t);
                if (shardedJedis != null) {
                    shardedJedisPool.returnBrokenResource(shardedJedis);
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
        String key = KEY_PREFIX + uid;
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, 0, -1);

                shardedJedisPool.returnResource(shardedJedis);
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
                shardedJedisPool.returnBrokenResource(shardedJedis);
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
        String key = KEY_PREFIX + uid;
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {

                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, start, end);

                shardedJedisPool.returnResource(shardedJedis);
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
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    public void setShardedJedisPool(DesignerShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

}
