/**
 * $Id: FeedRedisUtils.java 113192 2012-11-02 07:53:12Z yongshuai.yan@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.renren.x2.cache.client.redis.RedisClusterPoolClient;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-10-29 上午11:21:18
 */
public class FeedCacheUtils {

    private static final String FEED_PREFIX = "feed_";

    private static final String UGC_PREFIX = "ugc_";

    private static final String SCHOOL_PREFIX = "school_";

    private static final String USER_PREFIX = "user_";

    public static Map<String, String> getFieldValueMap(Object o) {
        Map<String, String> map = new HashMap<String, String>();
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if ("__ids".equals(f.getName())) {
                continue;
            }
            String value = null;
            try {
                value = CommonJsonUtils.toJson(f.get(o));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null) map.put(f.getName(), value);
        }
        map.remove(null);
        return map;
    }

    public static void fromField(Map<String, String> entries, Object obj) {
        Iterator<Entry<String, String>> itr = entries.entrySet().iterator();
        Class<?> clazz = obj.getClass();
        while (itr.hasNext()) {
            Entry<String, String> entry = itr.next();
            Field f = null;
            try {
                f = clazz.getField(entry.getKey().toString());
            } catch (SecurityException e) {
                //donothing
            } catch (NoSuchFieldException e) {
                //donothing
            }
            if (f != null) {
                String json = entry.getValue().toString();
                Object o = null;
                if ((f.getType() == Ugc.class && "content".equals(f.getName()))
                        || (f.getType() == Ugc.class)) {
                    o = FeedJsonUtils.convertJson2UGC(json);
                } else if (f.getType() == List.class && "content".equals(f.getName())) {
                    o = FeedJsonUtils.convertJson2UgcContentList(json);
                } else {
                    o = CommonJsonUtils.fromJson(json, f.getType());
                }
                try {
                    f.set(obj, o);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<String, Double> convertList2ScoreMap(List<Long> feedIds) {
        Map<String, Double> map = new HashMap<String, Double>();
        for (long feedId : feedIds) {
            map.put(String.valueOf(feedId), (double) feedId);
        }
        return map;
    }

    public static Map<String, Double> convertMap2ScoreMap(Map<Long, Long> ids) {
        Map<String, Double> map = new HashMap<String, Double>();
        Iterator<Entry<Long, Long>> itr = ids.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Long, Long> entry = itr.next();
            map.put(String.valueOf(entry.getKey()), (double) entry.getValue());
        }
        return map;
    }

    public static long getIndex(RedisClusterPoolClient client, String key, String value) {
        Long index = client.zrevrank(key, value);
        if (index != null) {
            return index;
        } else {
            return -1;
        }
    }

    public static int[] getRange(long index, int size, int offset, boolean forward) {
        long leftIndex = 0L;
        long rightIndex = 0L;
        //leftIndex <= rightIndex
        if (forward == true) {
            leftIndex = index + 1;
            rightIndex = leftIndex + size - 1;
        } else {
            leftIndex = index - 1 - size;
            rightIndex = leftIndex + size - 1;
        }
        if (offset > 0) {
            leftIndex -= offset;
            rightIndex -= offset;
        }
        if (offset < 0) {
            leftIndex += -offset;
            rightIndex += -offset;
        }
        if (leftIndex < 0) {
            leftIndex = 0;
        }
        if (rightIndex < 0) {
            rightIndex = 0;
        }
        if(size <= 0){
            rightIndex = -1;
        }
        return new int[] { (int) leftIndex, (int) rightIndex };
    }

    public static <T> T fromCacheValue(String value, Class<T> clazz) {
        return CommonJsonUtils.fromJson(value, clazz);
    }

    public static String toCacheValue(Object o) {
        return CommonJsonUtils.toJson(o);
    }

    public static String getFeedEntryKey(long feedId) {
        return FEED_PREFIX + feedId;
    }

    public static String getUgcEntryKey(long ugcId) {
        return UGC_PREFIX + ugcId;
    }

    public static String getSchoolFeedIndexKey(int schoolId) {
        return SCHOOL_PREFIX + schoolId + "_feed_index";
    }

    public static String getUserFeedIndexKey(int userId) {
        return USER_PREFIX + userId + "_feed_index";
    }

    public static String getUserAlbumFeedIndexKey(int userId) {
        return USER_PREFIX + "album_" + userId + "_feed_index";
    }

    public static String getUserFollowerIndexKey(int userId) {
        return USER_PREFIX + "follow_" + userId + "_feed_index";
    }

    public static String getFeedCommentIndexKey(long feedId) {
        return FEED_PREFIX + feedId + "_comment_index";
    }

    public static String getFeedMLikeIndexKey(long feedId) {
        return FEED_PREFIX + feedId + "_mlike_index";
    }

    public static String getFeedFLikeIndexKey(long feedId) {
        return FEED_PREFIX + feedId + "_flike_index";
    }

    public static String getUserLikeFeedIndexKey(long userId) {
        return USER_PREFIX + userId + "_like_index";
    }

    public static String getSchoolTopTenIndexKey(int schoolId) {
        return SCHOOL_PREFIX + schoolId + "_top_ten_index";
    }

    public static int getSchoolIdFromTopTenIndexKey(String key) {
        return Integer.valueOf(key.replaceFirst(SCHOOL_PREFIX, "").replaceFirst("_top_ten_index",
                ""));
    }
}
