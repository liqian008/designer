/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.renren.ad.jedis.Response;
import com.renren.x2.cache.client.redis.PipelineJedisCommands;
import com.renren.x2.cache.client.redis.RedisClusterPoolClient;
import com.renren.x2.feed.cache.IFeedRebuildableCache;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.exception.IgnoreableException;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feed.utils.FeedCacheUtils;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.UgcStatus;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午5:42:32
 */
public class FeedReaderCacheImpl implements IFeedReaderStorage {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FeedReaderCacheImpl.class);

    private static final String _FIELD_FEED_UGCSTATUS = "_ugcStatus";

    public static final long MARK = -1;

    public static final long START = 0;

    public static final String MARKSTR = String.valueOf(MARK);

    public static final String STARTSTR = String.valueOf(START);

    private RedisClusterPoolClient client;

    private IFeedReaderStorage feedReaderStorage;

    private IFeedRebuildableCache feedRebuildableCache;

    @Override
    public List<Feed> getSchoolFeeds(int schoolId, long feedId, boolean forward, int size,
            int offset) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get school[" + schoolId + "] feeds from cache");
        }
        String key = FeedCacheUtils.getSchoolFeedIndexKey(schoolId);
        List<Feed> feeds = getFeeds(key, feedId, forward, size, offset);
        if (feeds == null) {
            feedRebuildableCache.rebuildSchoolFeeds(schoolId);
            feeds = getFeeds(key, feedId, forward, size, offset);
        }
        return feeds;
    }

    @Override
    public List<Feed> getUserFeeds(int userId, long feedId, boolean forward, int size, int offset) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get user[" + userId + "] feeds from cache");
        }
        String key = FeedCacheUtils.getUserFeedIndexKey(userId);
        List<Feed> feeds = getFeeds(key, feedId, forward, size, offset);
        if (feeds == null) {
            feedRebuildableCache.rebuildUserFeeds(userId);
            feeds = getFeeds(key, feedId, forward, size, offset);
        }
        return feeds;
    }

    @Override
    public List<Feed> getUserFollowerFeeds(int userId, long feedId, boolean forward, int size,
            int offset) throws FeedException {
        if (logger.isDebugEnabled()) {
            logger.debug("Get user[" + userId + "] follower feeds from cache");
        }
        String key = FeedCacheUtils.getUserFollowerIndexKey(userId);
        List<Feed> feeds = getFeeds(key, feedId, forward, size, offset);
        if (feeds == null) {
            feedRebuildableCache.rebuildUserFollowerFeeds(userId);
            feeds = getFeeds(key, feedId, forward, size, offset);
        }
        return feeds;
    }

    @Override
    public List<Feed> getUserAlbumFeeds(int userId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get user[" + userId + "] album feeds from cache");
        }
        String key = FeedCacheUtils.getUserAlbumFeedIndexKey(userId);
        List<Feed> feeds = getFeeds(key, FeedParamConstants.DEFAULT_FEED_ID,
                FeedParamConstants.DEFAULT_PAGE_FORWARD, FeedParamConstants.DEFAULT_PAGE_SIZE,
                FeedParamConstants.DEFAULT_OFFSET);
        if (feeds == null) {
            feedRebuildableCache.rebuildUserAlbumFeeds(userId);
            feeds = getFeeds(key, FeedParamConstants.DEFAULT_FEED_ID,
                    FeedParamConstants.DEFAULT_PAGE_FORWARD, FeedParamConstants.DEFAULT_PAGE_SIZE,
                    FeedParamConstants.DEFAULT_OFFSET);
        }
        return feeds;
    }

    @Override
    public Feed getFeedById(long feedId) {
        String key = FeedCacheUtils.getFeedEntryKey(feedId);
        Map<String, String> feedMap = client.hgetAll(key);
        Feed feed = new Feed();
        if (feedMap == null || feedMap.isEmpty()) {
            feed = feedRebuildableCache.reloadFeed(feedId);
        } else {
            FeedCacheUtils.fromField(feedMap, feed);
            String statusCacheStr = feedMap.get(_FIELD_FEED_UGCSTATUS);
            String statusStr = FeedCacheUtils.fromCacheValue(statusCacheStr, String.class);
            UgcStatus status = UgcStatus.convert(statusStr);
            feed.getNativeUgc().setStatus(status);
        }
        return feed;
    }

    @Override
    public Boolean isUserLiked(int userId, long feedId, ExtUgcType likeType) throws FeedException {
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        if (!validateMark(key)) {
            feedRebuildableCache.rebuildUserLikeFeeds(userId);
        }
        Double score = client.zscore(key, String.valueOf(feedId));
        if (score != null && score > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Boolean> isUserLiked(int userId, List<Long> feedIds, ExtUgcType likeType)
            throws FeedException {
        Map<Long, Boolean> result = new HashMap<Long, Boolean>();
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        if (!validateMark(key)) {
            feedRebuildableCache.rebuildUserLikeFeeds(userId);
        }
        PipelineJedisCommands pipeline = client.pipelined();
        List<Response<Double>> responses = new ArrayList<Response<Double>>();
        for (Long feedId : feedIds) {
            Response<Double> response = pipeline.zscore(key, String.valueOf(feedId));
            responses.add(response);
        }
        pipeline.sync();
        for (int i = 0; i < feedIds.size(); i++) {
            Double score = responses.get(i).get();
            result.put(feedIds.get(i), (score != null && score > 0));
        }
        return result;
    }

    @Override
    public Ugc getUserLike(int userId, long feedId, ExtUgcType likeType) throws FeedException {
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        if (validateMark(key)) {
            feedRebuildableCache.rebuildUserLikeFeeds(userId);
        }
        Double score = client.zscore(key, String.valueOf(feedId));
        Ugc ugc = null;
        if (score != null && score > 0) {
            long ugcId = score.longValue();
            ugc = getUgc(ugcId);
            if (ugc == null) {
                ugc = feedRebuildableCache.reloadExtUgc(feedId, ugcId, likeType);
            }
        }
        return ugc;
    }

    @Override
    public Map<Long, Feed> getFeeds(List<Long> feedIds) {
        Map<Long, Feed> feedMap = new HashMap<Long, Feed>();
        for (long feedId : feedIds) {
            Feed feed = getFeedById(feedId);
            if (feed != null) {
                feedMap.put(feedId, feed);
            }
        }
        return feedMap;
    }

    @Override
    public Map<Long, Ugc> getUserLikeFeeds(int userId, ExtUgcType likeType){
        throw new IgnoreableException("Have no impl of getUserLikes in cache!");
    }

    @Override
    public List<Ugc> getFeedComments(long feedId, long ugcId, boolean forward, int size, int offset) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get feed[" + feedId + "] comments from cache");
        }
        String key = FeedCacheUtils.getFeedCommentIndexKey(feedId);
        List<Ugc> ugcs = getUgcs(key, feedId, forward, size, offset, ExtUgcType.Comment);
        if (ugcs == null) {
            feedRebuildableCache.rebuildFeedComments(feedId);
            ugcs = getUgcs(key, feedId, forward, size, offset, ExtUgcType.Comment);
        }
        return ugcs;
    }

    @Override
    public List<Ugc> getFeedFLikes(long feedId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get feed[" + feedId + "] flike from cache");
        }
        String key = FeedCacheUtils.getFeedFLikeIndexKey(feedId);
        List<Ugc> ugcs = getUgcs(key, feedId, true, FeedParamConstants.DEFAULT_PAGE_SIZE,
                FeedParamConstants.DEFAULT_OFFSET, ExtUgcType.FLike);
        if (ugcs == null) {
            feedRebuildableCache.rebuildFeedFLikes(feedId);
            ugcs = getUgcs(key, feedId, true, FeedParamConstants.DEFAULT_PAGE_SIZE,
                    FeedParamConstants.DEFAULT_OFFSET, ExtUgcType.FLike);
        }
        return ugcs;
    }

    @Override
    public List<Ugc> getFeedMLikes(long feedId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get feed[" + feedId + "] mlike from cache");
        }
        String key = FeedCacheUtils.getFeedMLikeIndexKey(feedId);
        List<Ugc> ugcs = getUgcs(key, feedId, true, FeedParamConstants.DEFAULT_PAGE_SIZE,
                FeedParamConstants.DEFAULT_OFFSET, ExtUgcType.MLike);
        if (ugcs == null) {
            feedRebuildableCache.rebuildFeedMLikes(feedId);
            ugcs = getUgcs(key, feedId, true, FeedParamConstants.DEFAULT_PAGE_SIZE,
                    FeedParamConstants.DEFAULT_OFFSET, ExtUgcType.MLike);
        }
        return ugcs;
    }

    @Override
    public Map<Long, Ugc> getExtUgcs(long feedId, List<Long> ugcIds, ExtUgcType ugcType) {
        Map<Long, Ugc> ugcs = new HashMap<Long, Ugc>();
        for (Long ugcId : ugcIds) {
            Ugc ugc = getUgc(ugcId);
            if (ugc == null) {
                ugc = feedRebuildableCache.reloadExtUgc(feedId, ugcId, ugcType);
            }
            ugcs.put(ugcId, ugc);
        }
        return ugcs;
    }

    /**
     * 取topTen列表 若未取到 从数据库取 并添加到缓存
     */
    @Override
    public List<Feed> getTopTenCurrent(int schoolId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get school [" + schoolId + "] topTen from cache");
        }
        String key = FeedCacheUtils.getSchoolTopTenIndexKey(schoolId);

        List<String> feedIdList = client.lrange(key, START, MARK);

        List<Feed> feedList = new ArrayList<Feed>();

        if (null == feedIdList) {
            feedList = feedRebuildableCache.rebuildSchoolTopTen(schoolId);
        } else {
            for (String str : feedIdList) {
                long id = Long.valueOf(str);
                if (id > 0) {
                    feedList.add(getFeedById(id));
                }
            }
        }
        return feedList;
    }

    @Override
    public List<List<Feed>> getTopTenHistory(int schoolId, int offset, int limit) {
        throw new IgnoreableException("getTopTenHistory from database");
    }

    @Override
    public List<Feed> getHistoryHotPost(int schoolId, int offset, int limit) {
        throw new IgnoreableException("getHistoryHotPost from database");
    }

    @Override
    public Map<Integer, Integer> getHistoryHotUser(int schoolId, int offset, int limit) {
        throw new IgnoreableException("getHistoryHotUser from database");
    }

    @Override
    public List<Integer> getSchoolList() {
        throw new IgnoreableException("getSchoolList from database");
    }

    public Ugc getUgc(long ugcId) {
        String key = FeedCacheUtils.getUgcEntryKey(ugcId);
        Map<String, String> feedMap = client.hgetAll(key);
        if (feedMap == null || feedMap.isEmpty()) {
            return null;
        }
        Ugc ugc = new Ugc();
        FeedCacheUtils.fromField(feedMap, ugc);
        return ugc;
    }

    protected List<Feed> getFeeds(String key, long feedId, boolean forward, int size, int offset) {
        //标志位校验
        if (!validateMark(key)) {
            return null;
        }
        long index = FeedCacheUtils.getIndex(client, key, String.valueOf(feedId));
        int[] range = FeedCacheUtils.getRange(index, size, offset, forward);
        Set<String> feedStrIds = client.zrevrange(key, range[0], range[1]);
        List<Long> feedIds = new ArrayList<Long>();
        for (String feed : feedStrIds) {
            Long _feedId = Long.parseLong(feed);
            if (_feedId != null && _feedId > 0) feedIds.add(_feedId);
        }
        Map<Long, Feed> cachedFeeds = getFeeds(feedIds);
        List<Feed> feeds = FeedCommonUtils.fillFeedFromMap(feedIds, cachedFeeds);
        return feeds;
    }

    //根据key，分页方式取sortedSet，要求member均为ugcId
    protected List<Ugc> getUgcs(String key, long feedId, boolean forward, int size, int offset,
            ExtUgcType ugcType) {
        //标志位校验
        if (!validateMark(key)) {
            return null;
        }
        long index = FeedCacheUtils.getIndex(client, key, String.valueOf(feedId));
        int[] range = FeedCacheUtils.getRange(index, size, offset, forward);
        Set<String> ugcStrIds = client.zrevrange(key, range[0], range[1]);
        List<Long> ugcIds = new ArrayList<Long>();
        for (String ugcStrId : ugcStrIds) {
            Long ugcId = Long.parseLong(ugcStrId);
            if (ugcId != null && ugcId > 0) ugcIds.add(ugcId);
        }
        Map<Long, Ugc> cachedUgcs = getExtUgcs(feedId, ugcIds, ugcType);
        List<Ugc> ugcs = FeedCommonUtils.fillUgcFromMap(ugcIds, cachedUgcs);
        return ugcs;
    }

    public boolean validateMark(String key) {
        Double markScore = client.zscore(key, MARKSTR);
        if (markScore == null || markScore != MARK) {
            return false;
        }
        return true;
    }

    /**
     * @param client the client to set
     */
    public void setClient(RedisClusterPoolClient client) {
        this.client = client;
    }

    /**
     * @return the feedReaderStorage
     */
    public IFeedReaderStorage getFeedReaderStorage() {
        return feedReaderStorage;
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to set
     */
    public void setFeedReaderStorage(IFeedReaderStorage feedReaderStorage) {
        this.feedReaderStorage = feedReaderStorage;
    }

    /**
     * @param feedRebuildableCache the feedRebuildableCache to set
     */
    public void setFeedRebuildableCache(IFeedRebuildableCache feedRebuildableCache) {
        this.feedRebuildableCache = feedRebuildableCache;
    }
}
