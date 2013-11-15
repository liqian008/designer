package com.bruce.designer.cache.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.exception.RedisKeyNotExistException;

/**
 * Comments for UserFollowCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-11 下午06:46:02
 */
public abstract class AbstractHotCache {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AbstractHotCache.class);

	@Autowired
	private DesignerShardedJedisPool cacheShardedJedisPool;

	public boolean incrScore(int id, int score){
		String key = getKey();
		DesignerShardedJedis shardedJedis = null;
		try {
			shardedJedis = cacheShardedJedisPool.getResource();
			boolean result = shardedJedis.zincrby(key, score, String.valueOf(id)) > 0;
			cacheShardedJedisPool.returnResource(shardedJedis);
			return result;
		} catch (JedisException t) {
			logger.error("addScore", t);
			if (shardedJedis != null) {
				cacheShardedJedisPool.returnBrokenResource(shardedJedis);
			}
		}
		return false;
	}

	/**
	 * 分页获取关注列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> getHotList(int start, int end) throws RedisKeyNotExistException {
		String key = getKey();
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
				List<Integer> albumIdList = new ArrayList<Integer>();
				for (Tuple tuple : tupleSet) {
					albumIdList.add(NumberUtils.toInt(tuple.getElement()));
				}
				return albumIdList;
			}
		} catch (JedisException t) {
			logger.error("getUserFollowList", t);
			if (shardedJedis != null) {
				cacheShardedJedisPool.returnBrokenResource(shardedJedis);
			}
		}
		return null;
	}

	abstract protected String getKey();

}
