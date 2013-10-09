/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache.album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import redis.clients.jedis.exceptions.JedisException;

import com.bruce.designer.model.Album;
import com.bruce.designer.cache.DesignerShardedJedis;
import com.bruce.designer.cache.DesignerShardedJedisPool;
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.sparta.Designer.album.model.Album;
//import com.sparta.Designer.pb.Album.AlbumPB;
//import com.sparta.Designer.pb.utils.AlbumPBUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Comments for AlbumCache.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-25 上午11:06:03
 */
@Repository
public class AlbumCache implements InitializingBean {

    private static final Gson gson = new GsonBuilder().create();
    
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AlbumCache.class);

    private static final String KEY_PREFIX = "album_";

    private static final String DEFAULT_NAMESPACE = "ufabang";
    
    @Autowired
    private DesignerShardedJedisPool cacheShardedJedisPool;

    private String getKey(String namespace, long id) {
        return KEY_PREFIX + namespace + "_" + id;
    }

    public Album getAlbum(int id) {
        return getAlbum(DEFAULT_NAMESPACE, id);
    }

    public Album getAlbum(String namespace, long id) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();
            
            String albumJson = shardedJedis.get(getKey(namespace, id));
            cacheShardedJedisPool.returnResource(shardedJedis);
            if (albumJson != null) {
                try {
                    return gson.fromJson(albumJson, Album.class);
                } catch (Exception e) {
                    logger.error("getAlbum(long)", e);
                }
            }
            
        } catch (JedisException t) {
            logger.error("getUser", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return null;
    }

    public Map<Integer, Album> multiGetAlbum(List<Integer> idList) {
        return multiGetAlbum(DEFAULT_NAMESPACE, idList);
    }

    public Map<Integer, Album> multiGetAlbum(String namespace, List<Integer> idList) {
//        Map<Long, Album> albumMap = new HashMap<Long, Album>();
//        if (idList != null && idList.size() > 0) {
//            List<byte[]> keyList = new ArrayList<byte[]>();
//            for (Long id : idList) {
//                keyList.add(getKey(namespace, id).getBytes());
//            }
//
//            DesignerShardedJedis shardedJedis = null;
//            try {
//                shardedJedis = (DesignerShardedJedis) cacheShardedJedisPool.getResource();
//
//                Map<String, byte[]> resultMap = shardedJedis.mgetMap(keyList);
//                cacheShardedJedisPool.returnResource(shardedJedis);
//                for (Long id : idList) {
//                    byte[] byteArray = resultMap.get(getKey(namespace, id));
//                    Album album = null;
//                    if (byteArray != null) {
//                        AlbumPB albumPB;
//                        try {
//                            albumPB = AlbumPB.parseFrom(byteArray);
//                            album = AlbumPBUtils.convert2Album(albumPB);
//                        } catch (InvalidProtocolBufferException e) {
//                            logger.error("multiGetAlbum(List<Long>)", e);
//                        }
//                    }
//                    albumMap.put(id, album);
//                }
//            } catch (JedisException t) {
//                logger.error("setAlbum", t);
//                if (shardedJedis != null) {
//                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
//                }
//            }
//        }
//        return albumMap;
        return null;
    }

    public boolean setAlbumList(List<Album> albumList) {
        return setAlbumList(DEFAULT_NAMESPACE, albumList);
    }

    public boolean setAlbumList(String namespace, List<Album> albumList) {
        for (Album album : albumList) {
            setAlbum(namespace, album);
        }
        return true;
    }

    public boolean setAlbum(Album album) {
        return setAlbum(DEFAULT_NAMESPACE, album);
    }

    public boolean setAlbum(String namespace, Album album) {
        if (album != null) {
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();
                shardedJedis.set(getKey(namespace, album.getId()), gson.toJson(album));
                cacheShardedJedisPool.returnResource(shardedJedis);
                return true;
            } catch (JedisException t) {
                logger.error("setAlbum", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        return false;
    }

    public boolean deleteAlbum(int id) {
        return deleteAlbum(DEFAULT_NAMESPACE, id);
    }

    public boolean deleteAlbum(String namespace, int id) {
        DesignerShardedJedis shardedJedis = null;
        try {
            shardedJedis = cacheShardedJedisPool.getResource();

            shardedJedis.del(getKey(namespace, id));

            cacheShardedJedisPool.returnResource(shardedJedis);
            return true;
        } catch (JedisException t) {
            logger.error("setAlbum", t);
            if (shardedJedis != null) {
                cacheShardedJedisPool.returnBrokenResource(shardedJedis);
            }
        }
        return false;
    }
    
    public long deleteAlbumList(String namespace, List<Integer> idList) {
        if(idList!=null && idList.size()>0) {
            List<String> keyList = new ArrayList<String>();
            for(Integer id : idList) {
                keyList.add(getKey(namespace, id));
            }
            DesignerShardedJedis shardedJedis = null;
            try {
                shardedJedis = cacheShardedJedisPool.getResource();

                long result = shardedJedis.del(keyList);

                cacheShardedJedisPool.returnResource(shardedJedis);
                return result;
            } catch (JedisException t) {
                logger.error("setAlbum", t);
                if (shardedJedis != null) {
                    cacheShardedJedisPool.returnBrokenResource(shardedJedis);
                }
            }
        }
        
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheShardedJedisPool, "cacheShardedJedisPool must not null!");
    }

    public void setShardedJedisPool(DesignerShardedJedisPool cacheShardedJedisPool) {
        this.cacheShardedJedisPool = cacheShardedJedisPool;
    }

}
