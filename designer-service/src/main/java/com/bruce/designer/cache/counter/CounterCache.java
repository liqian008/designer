/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.cache.counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.service.ICounterService;


/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
@Repository
@Deprecated
public class CounterCache implements InitializingBean {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CounterCache.class);
    
    private static final String KEY_PREFIX = "counter";

    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    public long getCount(String key) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            long value = NumberUtils.toLong(shardedJedis.get(getKey(key)));
            cacheShardedJedisPool.returnResource(shardedJedis);
            return value;
        } catch (Throwable t) {
            logger.error("getFavorCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    
    public Map<String, Long> mGetCounter(List<String> keyList) {
        Map<String, Long> counterMap = new HashMap<String, Long>();
        if(keyList!=null && keyList.size()>0){
            for(String key: keyList){
                counterMap.put(key, getCount(key));
            }
        }
        return counterMap;
    }
    

    public long increase(String key) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            long value = shardedJedis.incr(getKey(key));
            cacheShardedJedisPool.returnResource(shardedJedis);
            return value;
        } catch (Throwable t) {
            logger.error("getFavorCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    public long increaseNum(String key, int num) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            long value = shardedJedis.incrBy(getKey(key), num);
            cacheShardedJedisPool.returnResource(shardedJedis);
            return value;
        } catch (Throwable t) {
            logger.error("getFavorCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    public long reduce(String key) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            long value = shardedJedis.decr(getKey(key));
            cacheShardedJedisPool.returnResource(shardedJedis);
            return value;
        } catch (Throwable t) {
            logger.error("getFavorCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    public long reduceNum(String key, int num) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            long value = shardedJedis.decrBy(getKey(key), num);
            cacheShardedJedisPool.returnResource(shardedJedis);
            return value;
        } catch (Throwable t) {
            logger.error("getFavorCount", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return 0;
    }

    private String getKey(String key) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" +  key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.isTrue(StringUtils.isNotBlank(namespace), "namespace must not blank");
        Assert.notNull(cacheShardedJedisPool, "cacheShardedJedisPool must not null!");
    }

}
