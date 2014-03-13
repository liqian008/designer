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
import com.bruce.designer.model.UserFollow;

/**
 * Comments for FollowCache.java
 * redis数据结构为Sorted set
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
     * 获取指定uid的关注数
     */
    public long getFollowCount(int userId) throws RedisKeyNotExistException{
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
            logger.error("getFollowCount: "+userId, t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    /**
     * 添加关注至缓存
     * 
     * @param follow
     * @return
     */
    public boolean addFollow(UserFollow userFollow) throws RedisKeyNotExistException {
        String key = getKey(userFollow.getUserId());
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                boolean result = shardedJedis.zadd(key, (double) userFollow.getCreateTime().getTime(),
                        String.valueOf(userFollow.getFollowId())) > 0;
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            }
        } catch (JedisException t) {
            logger.error("addFollow: "+userFollow, t);
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
            logger.error("removeFollow: "+userId+", "+unfollowId, t);
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
//            logger.error("isUserFollowed", t);
            logger.error("isFollowed: "+hostId+", "+clientId, t);
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
            logger.error("isFans: "+uid1+", "+uid2, t);
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
                boolean result = false;
                if(followList!=null&&followList.size()>0){
	                for (UserFollow follow : followList) {
	                	shardedJedis.zadd(key, follow.getCreateTime().getTime(), String.valueOf(follow.getFollowId()));
	                }
	                result = true;
                }else{
                	result = false;
                }
                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            } catch (JedisException t) {
                logger.error("setFollowList: "+uid+", "+followList, t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

    /**
     * 获取指定用户的关注列表
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
            logger.error("getAllFollowList: "+uid, t);
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
            logger.error("getFollowList: "+uid + ", "+ start +", "+ end, t);
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
