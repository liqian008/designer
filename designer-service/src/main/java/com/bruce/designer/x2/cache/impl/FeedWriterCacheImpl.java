/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.cache.impl;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import com.renren.x2.cache.client.redis.RedisClusterPoolClient;
import com.renren.x2.feed.service.impl.AbstractFeedHelper;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feed.storage.IFeedWriterStorage;
import com.renren.x2.feed.utils.FeedCacheUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.UgcStatus;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午5:41:45
 */
public class FeedWriterCacheImpl extends AbstractFeedHelper implements IFeedWriterStorage {

    private static final String _FIELD_FEED_NATIVEUGC = "_nativeUgc";

    private static final String _FIELD_FEED_UGCSTATUS = "_ugcStatus";

    private static final String FIELD_FEED_FLIKE_COUNT = "fLikesCount";

    private static final String FIELD_FEED_MLIKE_COUNT = "mLikesCount";

    private static final String FIELD_FEED_COMMENT_COUNT = "commentsCount";

    private static final String FIELD_UGC_STATUS = "status";

    public static final long MARK = -1;

    public static final String MARKSTR = "-1";

    private RedisClusterPoolClient client;

    private IFeedReaderStorage feedReaderStorage;

	private static final Logger logger = Logger.getLogger(FeedWriterCacheImpl.class);

    @Override
    public void saveFeed(int schoolId, Feed feed) {
        Map<String, String> feedMap = FeedCacheUtils.getFieldValueMap(feed);
        feedMap.put(_FIELD_FEED_NATIVEUGC, String.valueOf(feed.getNativeUgc().getUgcId()));
        feedMap.put(_FIELD_FEED_UGCSTATUS, feed.getNativeUgc().getStatus().name());
        String key = FeedCacheUtils.getFeedEntryKey(feed.getFeedId());
        client.hmset(key, feedMap);
        addFeedCommentIndex(feed.getFeedId(), MARK);
        addFeedFLikeIndex(feed.getFeedId(), MARK);
        addFeedMLikeIndex(feed.getFeedId(), MARK);
    }

    @Override
    public void saveExtUgc(long feedId, Ugc ugc, ExtUgcType ugcType) {
        Map<String, String> ugcMap = FeedCacheUtils.getFieldValueMap(ugc);
        String key = FeedCacheUtils.getUgcEntryKey(ugc.getUgcId());
        client.hmset(key, ugcMap);
    }

    @Override
    public void addSchoolFeedIndex(int schoolId, long feedId) {
        String key = FeedCacheUtils.getSchoolFeedIndexKey(schoolId);
        client.zadd(key, feedId, String.valueOf(feedId));
    }

    @Override
    public void addUserFeedIndex(int userId, long feedId) {
        String key = FeedCacheUtils.getUserFeedIndexKey(userId);
        client.zadd(key, feedId, String.valueOf(feedId));
    }

    @Override
    public void addUserAlbumFeedIndex(int userId, long feedId) {
        String key = FeedCacheUtils.getUserAlbumFeedIndexKey(userId);
        client.zadd(key, feedId, String.valueOf(feedId));
    }

    @Override
    public void addUserFollowerFeedIndex(int userId, long feedId) {
        String key = FeedCacheUtils.getUserFollowerIndexKey(userId);
        client.zadd(key, feedId, String.valueOf(feedId));
    }

    @Override
    public void addUserFollowerFeedIndex(int userId, List<Long> feedIds) {
        String key = FeedCacheUtils.getUserFollowerIndexKey(userId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(feedIds);
        client.zadd(key, map);
    }

    @Override
    public void addUserLikeFeedIndex(int userId, long feedId, long ugcId) {
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        client.zadd(key, ugcId, String.valueOf(feedId));
    }

    @Override
    public void addFeedCommentIndex(long feedId, long ugcId) {
        String key = FeedCacheUtils.getFeedCommentIndexKey(feedId);
        client.zadd(key, ugcId, String.valueOf(ugcId));
    }

    @Override
    public void addFeedFLikeIndex(long feedId, long ugcId) {
        String key = FeedCacheUtils.getFeedFLikeIndexKey(feedId);
        client.zadd(key, ugcId, String.valueOf(ugcId));
    }

    @Override
    public void addFeedMLikeIndex(long feedId, long ugcId) {
        String key = FeedCacheUtils.getFeedMLikeIndexKey(feedId);
        client.zadd(key, ugcId, String.valueOf(ugcId));
    }

    @Override
    public void removeUserLikeFeedIndex(int userId, long feedId) {
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        client.zrem(key, String.valueOf(feedId));
    }

    @Override
    public void removeUserFollowerFeedIndex(int userId, List<Long> feedIds) {
        String key = FeedCacheUtils.getUserFollowerIndexKey(userId);
        Set<String> feedIdSet = new HashSet<String>(feedIds.size());
        for (Long feedId : feedIds) {
            feedIdSet.add(feedId.toString());
        }
        client.zrem(key, feedIdSet);
    }

    @Override
    public void incrFeedFLike(long feedId) {
        incrFeedElement(feedId, FIELD_FEED_FLIKE_COUNT);
    }

    @Override
    public void decrFeedFLike(long feedId) {
        decrFeedElement(feedId, FIELD_FEED_FLIKE_COUNT);
    }

    @Override
    public void incrFeedMLike(long feedId) {
        incrFeedElement(feedId, FIELD_FEED_MLIKE_COUNT);
    }

    @Override
    public void decrFeedMLike(long feedId) {
        decrFeedElement(feedId, FIELD_FEED_MLIKE_COUNT);
    }

    @Override
    public void incrFeedComment(long feedId) {
        incrFeedElement(feedId, FIELD_FEED_COMMENT_COUNT);
    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedWriterStorage#updateSchoolTopTen(int, java.util.List, java.util.List)
     */
    @Override
    public void updateSchoolTopTen(int schoolId, List<Long> feedIdList, List<Integer> userIdList) {

        if ((null != feedIdList) && (feedIdList.size() > 0)) {
	
			logger.debug("schoolId : " + schoolId + " feedId : " + feedIdList.get(0));

            String key = FeedCacheUtils.getSchoolTopTenIndexKey(schoolId);

            if (BooleanUtils.isTrue(client.exists(key))) {
                client.del(key);
            }
            List<String> values = new ArrayList<String>();

            for (long id : feedIdList) {
                if (id > 0) {
                    values.add(String.valueOf(id));
                }
            }
            client.lpush(key, values);
        }

    }

    protected void incrFeedElement(long feedId, String eleName) {
        String key = FeedCacheUtils.getFeedEntryKey(feedId);
        String countStr = client.hget(key, eleName);
        if (StringUtils.isNotBlank(countStr)) {
            int count = FeedCacheUtils.fromCacheValue(countStr, Integer.class);
            client.hset(key, eleName, FeedCacheUtils.toCacheValue(String.valueOf(count + 1)));
        }
    }

    protected void decrFeedElement(long feedId, String eleName) {
        String key = FeedCacheUtils.getFeedEntryKey(feedId);
        String countStr = client.hget(key, eleName);
        if (StringUtils.isNotBlank(countStr)) {
            int count = FeedCacheUtils.fromCacheValue(countStr, Integer.class);
            client.hset(key, eleName,
                    FeedCacheUtils.toCacheValue(String.valueOf(Math.max(0, count - 1))));
        }
    }

    @Override
    public void deleteFeed(long feedId) {
        String key = FeedCacheUtils.getFeedEntryKey(feedId);
        if (BooleanUtils.isTrue(client.exists(key))) {
            client.hset(key, _FIELD_FEED_UGCSTATUS, FeedCacheUtils.toCacheValue(UgcStatus.Deleted));
        }
    }

    @Override
    public void deleteExtUgc(long feedId, long ugcId, ExtUgcType ugcType) {
        String key = FeedCacheUtils.getUgcEntryKey(ugcId);
        if (BooleanUtils.isTrue(client.exists(key))) {
            client.hset(key, FIELD_UGC_STATUS, FeedCacheUtils.toCacheValue(UgcStatus.Deleted));
        }
    }

    @Override
    public void updateFeed(Feed feed) {
        saveFeed(0, feed);
    }

    /**
     * @param client the client to set
     */
    public void setClient(RedisClusterPoolClient client) {
        this.client = client;
    }

    @Override
    public IFeedReaderStorage getFeedReaderStorage() {
        return this.feedReaderStorage;
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to set
     */
    public void setFeedReaderStorage(IFeedReaderStorage feedReaderStorage) {
        this.feedReaderStorage = feedReaderStorage;
    }

    /**
     * @return the client
     */
    public RedisClusterPoolClient getClient() {
        return client;
    }

}
