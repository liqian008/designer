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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
@Repository
public class UserCache implements InitializingBean {
    
    private static final Gson gson = new GsonBuilder().create();
    
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserCache.class);

    private static final String KEY_PREFIX = "user";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    public User getUser(int userId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            String userJson = shardedJedis.get(getKey(userId));
            cacheShardedJedisPool.returnResource(shardedJedis);
            if (userJson != null) {
                return gson.fromJson(userJson, User.class);
            }
        } catch (Throwable t) {
            logger.error("getUser", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }
    
    /**
     * 批量获取用户
     * @param userIdList
     * @return
     */
    public Map<Integer, User> multiGetUser(List<Integer> userIdList) {
        Map<Integer, User> userMap = new HashMap<Integer, User>();
        if (userIdList != null && userIdList.size() > 0) {
            List<String> keyList = new ArrayList<String>();
            for (Integer id : userIdList) {
                keyList.add(getKey(id));
            }
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            Map<String, String> resultMap = shardedJedis.mgetMap(keyList);
            cacheShardedJedisPool.returnResource(shardedJedis);
            for (Integer id : userIdList) {
                String userJsonStr = resultMap.get(getKey(id));
                User user = null;
                try{
                    user = gson.fromJson(userJsonStr, User.class);
                }catch(Exception e){
                    logger.error("multiGetUser(List<Integer>)", e);
                }
                
//                if (byteArray != null) {
//                    UserPB userPB;
//                    try {
//                        userPB = UserPB.parseFrom(byteArray);
//                        user = UserPBUtils.convert2User(userPB);
//                    } catch (InvalidProtocolBufferException e) {
//                        logger.error("multiGetFeed(List<Integer>)", e);
//                    }
//                }
                userMap.put(id, user);
            }
        } catch (Throwable t) {
            logger.error("getUser", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        }
        return userMap;
    }

    public boolean setUser(User user) {
        if (user != null) {
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.set(getKey(user.getId()), gson.toJson(user));
                cacheShardedJedisPool.returnResource(shardedJedis);
                return true;
            } catch (Throwable t) {
//                logger.error("setTicket", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }
    
    public boolean setUserList(List<User> userList) {
        if(userList!=null) {
            // TODO 优化
            for(User user: userList) {
                setUser(user);
            }
            return true;
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            shardedJedis.del(getKey(userId));
            cacheShardedJedisPool.returnResource(shardedJedis);
            return true;
        } catch (Throwable t) {
//            logger.error("setTicket", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    private String getKey(int userId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + userId;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheShardedJedisPool, "cacheShardedJedisPool must not null!");
    }

}
