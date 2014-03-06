/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.UserFan;

/**
 * Comments for FanCache.java
 * redis数据结构为Sorted set
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:09
 */
@Repository
public class FanCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FanCache.class);

    private static final String KEY_PREFIX = "fan";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    
    /**
     * 获取指定uid的关注数
     */
    public long getFanCount(int userId) throws RedisKeyNotExistException{
    	String key = getKey(userId);
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
            logger.error("getFanCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }
    /**
     * 添加粉丝至缓存
     * 
     * @param follow
     * @return
     */
    public boolean addFan(UserFan fans) throws RedisKeyNotExistException {
        String key = getKey(fans.getUserId());
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) fans.getCreateTime().getTime(),
                        String.valueOf(fans.getFanId())) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }

        } catch (JedisException t) {
            logger.error("addUserFan", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
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
    public boolean removeFan(int uid, long fans) {
        String key = getKey(uid);
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                return false;
            } else {
                boolean result = shardedJedis.zrem(key, String.valueOf(fans)) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("removeUserFan", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
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
    public boolean setFanList(int uid, List<UserFan> fansList) {
        if (fansList != null && fansList.size() > 0) {
            String key = getKey(uid);
            DesignerShardedJedis shardedJedis = null;
            try {
            	shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.del(key);
            	boolean result = false;
                if(fansList!=null&&fansList.size()>0){
	                for (UserFan fan : fansList) {
						shardedJedis.zadd(key, fan.getCreateTime().getTime(), String.valueOf(fan.getFanId()));
					}
					result =  true;
				}else{
					result = false;
				}
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            } catch (JedisException t) {
                logger.error("setUserFanList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
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
    public List<UserFan> getAllFanList(int uid) throws RedisKeyNotExistException {
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
                List<UserFan> fansList = new ArrayList<UserFan>();
                for (Tuple tuple : tupleSet) {
                    UserFan fans = new UserFan();
                    fans.setUserId(uid);
                    fans.setFanId(NumberUtils.toInt(tuple.getElement()));
                    fans.setCreateTime(new Date((long) tuple.getScore()));
                    fansList.add(fans);
                }
                cacheShardedJedisPool.returnResource(shardedJedis);
                return fansList;
            }
        } catch (JedisException t) {
            logger.error("getAllUserFanList", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
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
    public List<UserFan> getFanList(int uid, int start, int end) throws RedisKeyNotExistException {
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
                List<UserFan> fansList = new ArrayList<UserFan>();
                for (Tuple tuple : tupleSet) {
                    UserFan fans = new UserFan();
                    fans.setUserId(uid);
                    fans.setFanId(NumberUtils.toInt(tuple.getElement()));
                    fans.setCreateTime(new Date((long) tuple.getScore()));
                    fansList.add(fans);
                }
                cacheShardedJedisPool.returnResource(shardedJedis);
                return fansList;
            }
        } catch (JedisException t) {
            logger.error("getUserFanList", t);
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
