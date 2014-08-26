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
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * 用户的专辑数cache
 * redis数据结构为Sorted set
 * @createTime 2013-9-11 下午06:46:02
 */
@Repository
public class UserAlbumCounterCache{
	
    private static final Logger logger = LoggerFactory.getLogger(UserAlbumCounterCache.class);
    
	@Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;
	
	/* 用户专辑数的key */
	public static final String COUNTER_KEY_USER_ALBUM_AMOUNT = "userAlbum";
	
	public long getAlbumCount(int userId) throws RedisKeyNotExistException {
	    return getCount(getUserAlbumKey(), userId);
    }
	
	/**
	 * 增加专辑
	 * @param albumId
	 * @return
	 * @throws RedisKeyNotExistException
	 */
	public long incrAlbum(int userId) throws RedisKeyNotExistException {
	    return incrByKey(getUserAlbumKey(), userId);
    }

	/**
	 * 减少专辑数
	 * @param albumId
	 * @return
	 * @throws RedisKeyNotExistException
	 */
	public long reduceAlbum(int userId) throws RedisKeyNotExistException {
	    return incrByKey(getUserAlbumKey(), userId, -1);
    }

	
	
	
	private long incrByKey(String key, int id) throws RedisKeyNotExistException {
		return incrByKey(key, id, 1);
    }
	
	
	private long incrByKey(String key, int id, int score) throws RedisKeyNotExistException {
        long result = 0;
	    DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            boolean exists = shardedJedis.exists(key);
            if (exists == false) {
                cacheShardedJedisPool.returnResource(shardedJedis);
                throw new RedisKeyNotExistException();
            } else {
                result = new Double(shardedJedis.zincrby(key, score, String.valueOf(id))).longValue();
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
	 * 重建用户的专辑数量缓存
	 * @param dataList
	 * @return
	 */
	public boolean setUserAlbumDataList(List<CountCacheBean> dataList) {
	    return setDataList(getUserAlbumKey(), dataList);
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
                logger.error("setUserAlbumList", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return result;
    }
    
	private String getUserAlbumKey() {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_USER_ALBUM_AMOUNT;
    }

}
