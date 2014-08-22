/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.cache.album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bruce.designer.model.Album;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
import com.bruce.designer.constants.ConstRedis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Comments for AlbumCache.java
 * redis数据结构为String
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-9-24 下午09:34:49
 */
@Repository
public class AlbumCache implements InitializingBean {
    
    private static final Gson gson = new GsonBuilder().create();
    
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(AlbumCache.class);

    private static final String KEY_PREFIX = "album";
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    public Album getAlbum(int albumId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            String albumJson = shardedJedis.get(getKey(albumId));
            cacheShardedJedisPool.returnResource(shardedJedis);
            if (albumJson != null) {
                return gson.fromJson(albumJson, Album.class);
            }
        } catch (Throwable t) {
            logger.error("getAlbum: " + albumId, t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }
    
    /**
     * 批量获取用户
     * @param albumIdList
     * @return
     */
    public Map<Integer, Album> multiGetAlbum(List<Integer> albumIdList) {
        Map<Integer, Album> albumMap = new HashMap<Integer, Album>();
        if (albumIdList != null && albumIdList.size() > 0) {
            List<String> keyList = new ArrayList<String>();
            for (Integer id : albumIdList) {
                keyList.add(getKey(id));
            }
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            Map<String, String> resultMap = shardedJedis.mgetMap(keyList);
            cacheShardedJedisPool.returnResource(shardedJedis);
            for (Integer id : albumIdList) {
                String albumJsonStr = resultMap.get(getKey(id));
                Album album = null;
                try{
                    album = gson.fromJson(albumJsonStr, Album.class);
                }catch(Exception e){
                    logger.error("multiGetAlbum(List<Integer>)", e);
                }
                
//                if (byteArray != null) {
//                    AlbumPB albumPB;
//                    try {
//                        albumPB = AlbumPB.parseFrom(byteArray);
//                        album = AlbumPBUtils.convert2Album(albumPB);
//                    } catch (InvalidProtocolBufferException e) {
//                        logger.error("multiGetFeed(List<Integer>)", e);
//                    }
//                }
                albumMap.put(id, album);
            }
        } catch (Throwable t) {
            logger.error("multiGetAlbum: " + albumIdList, t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        }
        return albumMap;
    }

    public boolean setAlbum(Album album) {
        if (album != null) {
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.set(getKey(album.getId()), gson.toJson(album));
                cacheShardedJedisPool.returnResource(shardedJedis);
                return true;
            } catch (Throwable t) {
                logger.error("setAlbum: " + album.getId(), t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }
    
    public boolean setAlbumList(List<Album> albumList) {
        if(albumList!=null) {
            // TODO 优化
            for(Album album: albumList) {
                setAlbum(album);
            }
            return true;
        }
        return false;
    }

    public boolean deleteAlbum(int albumId) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            shardedJedis.del(getKey(albumId));
            cacheShardedJedisPool.returnResource(shardedJedis);
            return true;
        } catch (Throwable t) {
            logger.error("deleteAlbum: "+albumId, t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }

    private String getKey(int albumId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + KEY_PREFIX + "_" + albumId;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheShardedJedisPool, "cacheShardedJedisPool must not null!");
    }

}
