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

import com.bruce.designer.bean.UserFans;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * Comments for UserFansCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:09
 */
public class FansCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FansCache.class);

    private static final String KEY_PREFIX = "fans_";

    private DesignerShardedJedisPool shardedJedisPool;

    /**
     * 添加粉丝至缓存
     * 
     * @param follow
     * @return
     */
    public boolean addFans(UserFans fans) throws RedisKeyNotExistException {
        String key = getKey(fans.getUserId());
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) fans.getCreateTime().getTime(),
                        String.valueOf(fans.getFansId())) > 0;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("addUserFans", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 从缓存粉丝移除粉丝
     * 
     * @param follow
     * @return
     */
    public boolean removeFans(int uid, long fans) {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(fans)) > 0;
                shardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("removeUserFans", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    /**
     * 设置粉丝列表
     * 
     * @param uid
     * @param followList
     * @return
     */
    public boolean setFansList(int uid, List<UserFans> fansList) {
        if (fansList != null && fansList.size() > 0) {
            String key = getKey(uid);
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = shardedJedisPool.getResource();
                shardedJedis.del(key);
                Map<Double, String> fansMap = new HashMap<Double, String>();
                for (UserFans fans : fansList) {
                    fansMap.put((double) fans.getCreateTime().getTime(), String.valueOf(fans.getFansId()));
                }
                boolean result = shardedJedis.zadd(key, fansMap) > 0;
                shardedJedisPool.returnResource(shardedJedis);
                return result;

            } catch (JedisException t) {
                logger.error("setUserFansList", t);
                if (shardedJedis != null) {
                    shardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

    /**
     * 获取粉丝列表
     * 
     * @param uid
     * @return
     */
    public List<UserFans> getAllFansList(int uid) throws RedisKeyNotExistException {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, 0, -1);
                List<UserFans> fansList = new ArrayList<UserFans>();
                for (Tuple tuple : tupleSet) {
                    UserFans fans = new UserFans();
                    fans.setUserId(uid);
                    fans.setFansId(NumberUtils.toInt(tuple.getElement()));
                    fans.setCreateTime(new Date((long) tuple.getScore()));
                    fansList.add(fans);
                }
                shardedJedisPool.returnResource(shardedJedis);
                return fansList;
            }
        } catch (JedisException t) {
            logger.error("getAllUserFansList", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }

        return null;

    }

    /**
     * 分页获取粉丝列表
     * 
     * @param uid
     * @return
     */
    public List<UserFans> getFansList(int uid, int start, int end) throws RedisKeyNotExistException {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                shardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                Set<Tuple> tupleSet = shardedJedis.zrangeWithScores(key, start, end);
                List<UserFans> fansList = new ArrayList<UserFans>();
                for (Tuple tuple : tupleSet) {
                    UserFans fans = new UserFans();
                    fans.setUserId(uid);
                    fans.setFansId(NumberUtils.toInt(tuple.getElement()));
                    fans.setCreateTime(new Date((long) tuple.getScore()));
                    fansList.add(fans);
                }
                shardedJedisPool.returnResource(shardedJedis);
                return fansList;
            }
        } catch (JedisException t) {
            logger.error("getUserFansList", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    private String getKey(long uid) {
        return KEY_PREFIX + uid;
    }

    public void setShardedJedisPool(DesignerShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

}
