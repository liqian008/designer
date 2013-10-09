/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.cache.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.bruce.designer.model.User;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Comments for UserCache.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-9-24 下午09:34:49
 */
public class UserCache implements InitializingBean {
    
    private static final Gson gson = new GsonBuilder().create();
    
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserCache.class);

    private static final String KEY_PREFIX = "user";

    private DesignerShardedJedisPool shardedJedisPool;

    public User getUser(long userId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            String userJson = shardedJedis.get(getKey(userId));
            shardedJedisPool.returnResource(shardedJedis);
            if (userJson != null) {
                return gson.fromJson(userJson, User.class);
            }
        } catch (Throwable t) {
            logger.error("getUser", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    public Map<Integer, User> multiGetUser(List<Integer> userIdList) {
        return null;
    }

    public boolean setUser(User user) {
        if (user != null) {
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = shardedJedisPool.getResource();
                shardedJedis.set(getKey(user.getId()), gson.toJson(user));
                shardedJedisPool.returnResource(shardedJedis);
                return true;
            } catch (Throwable t) {
                logger.error("setTicket", t);
                if (shardedJedis != null) {
                    shardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }
    
    public boolean setUserList(List<User> userList) {
        if(userList!=null) {
            //TODO 优化
            for(User user: userList) {
                setUser(user);
            }
            return true;
        }
        return false;
    }

    public boolean deleteUser(long userId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.del(getKey(userId));
            shardedJedisPool.returnResource(shardedJedis);
            return true;
        } catch (Throwable t) {
            logger.error("setTicket", t);
            if (shardedJedis != null) {
                shardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    private String getKey(long userId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + userId;
    }

    public void setShardedJedisPool(DesignerShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(shardedJedisPool, "shardedJedisPool must not null!");
    }

}
