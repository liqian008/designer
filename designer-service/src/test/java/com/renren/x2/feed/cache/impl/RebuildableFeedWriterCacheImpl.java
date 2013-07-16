/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.cache.impl;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.renren.x2.feed.cache.IFeedRebuildableCache;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.utils.FeedCacheUtils;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feed.utils.UgcTypeUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcUser;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-6 下午4:17:31
 */
public class RebuildableFeedWriterCacheImpl extends FeedWriterCacheImpl implements
        IFeedRebuildableCache {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(RebuildableFeedWriterCacheImpl.class);

    @Override
    public void addSchoolFeedIndex(int schoolId, List<Long> feedIds) {
        String key = FeedCacheUtils.getSchoolFeedIndexKey(schoolId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(feedIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addUserFeedIndex(int userId, List<Long> feedIds) {
        String key = FeedCacheUtils.getUserFeedIndexKey(userId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(feedIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addUserAlbumFeedIndex(int userId, List<Long> feedIds) {
        String key = FeedCacheUtils.getUserAlbumFeedIndexKey(userId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(feedIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addUserLikeFeedIndex(int userId, Map<Long, Long> feedUgcIds) {
        String key = FeedCacheUtils.getUserLikeFeedIndexKey(userId);
        Map<String, Double> map = FeedCacheUtils.convertMap2ScoreMap(feedUgcIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addFeedCommentIndex(long feedId, List<Long> ugcIds) {
        String key = FeedCacheUtils.getFeedCommentIndexKey(feedId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(ugcIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addFeedFLikeIndex(long feedId, List<Long> ugcIds) {
        String key = FeedCacheUtils.getFeedFLikeIndexKey(feedId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(ugcIds);
        getClient().zadd(key, map);
    }

    @Override
    public void addFeedMLikeIndex(long feedId, List<Long> ugcIds) {
        String key = FeedCacheUtils.getFeedMLikeIndexKey(feedId);
        Map<String, Double> map = FeedCacheUtils.convertList2ScoreMap(ugcIds);
        getClient().zadd(key, map);
    }

    @Override
    public void rebuildSchoolFeeds(int schoolId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild school feed index! schoolId=" + schoolId);
        }
        SomethingOnSchoolHolder rebuildSchoolFeedsEvent = new SomethingOnSchoolHolder(schoolId) {

            @Override
            public void doSomething(List<Feed> targetSchoolFeeds) {
                addSchoolFeedIndex(getSchoolId(),
                        FeedCommonUtils.getFeedIdsFromFeeds(targetSchoolFeeds));
            }
        };
        doSomethingOnGetSchoolFeeds(rebuildSchoolFeedsEvent);
        addSchoolFeedIndex(schoolId, MARK);
    }

    @Override
    public void rebuildUserFollowerFeeds(int userId) throws FeedException {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild user follower feed index! userId=" + userId);
        }
        List<Integer> followingIds = getFollowingIds(userId);
        for (int followingId : followingIds) {
            SomethingOnUserHolder<Feed> rebuildUserFollowerFeedsEvent = new SomethingOnUserHolder<Feed>(
                    userId, followingId) {

                @Override
                public void doSomething(List<Feed> targetUserFeeds) {
                    addUserFollowerFeedIndex(getCurrentUserId(),
                            FeedCommonUtils.getFeedIdsFromFeeds(targetUserFeeds));
                }
            };
            doSomethingOnGetUserFeeds(rebuildUserFollowerFeedsEvent);
        }
        addUserFollowerFeedIndex(userId, MARK);
    }

    @Override
    public void rebuildUserFeeds(int userId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild user feed index! userId=" + userId);
        }
        SomethingOnUserHolder<Feed> rebuildUserFollowerFeedsEvent = new SomethingOnUserHolder<Feed>(
                userId, userId) {

            @Override
            public void doSomething(List<Feed> targetUserFeeds) {
                addUserFeedIndex(getCurrentUserId(),
                        FeedCommonUtils.getFeedIdsFromFeeds(targetUserFeeds));
            }
        };
        doSomethingOnGetUserFeeds(rebuildUserFollowerFeedsEvent);
        addUserFeedIndex(userId, MARK);
    }

    @Override
    public void rebuildUserLikeFeeds(int userId) throws FeedException {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild user like feed index! userId=" + userId);
        }
        UgcUser user = getUgcUser(userId);
        ExtUgcType likeType = UgcTypeUtils.getLikeTypeByGender(user.getGender());
        Map<Long, Ugc> userLikes = getFeedReaderStorage().getUserLikeFeeds(userId, likeType);
        Map<Long, Long> feedUgcIds = new HashMap<Long, Long>(userLikes.size());
        Iterator<Entry<Long, Ugc>> itr = userLikes.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Long, Ugc> entry = itr.next();
            feedUgcIds.put(entry.getKey(), entry.getValue().getUgcId());
        }
        addUserLikeFeedIndex(userId, feedUgcIds);
        addUserLikeFeedIndex(userId, MARK, MARK);
    }

    @Override
    public void rebuildUserAlbumFeeds(int userId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild user album feed index! userId=" + userId);
        }
        SomethingOnUserHolder<Feed> rebuildUserFollowerFeedsEvent = new SomethingOnUserHolder<Feed>(
                userId, userId) {

            @Override
            public void doSomething(List<Feed> targetUserFeeds) {
                addUserAlbumFeedIndex(getCurrentUserId(),
                        FeedCommonUtils.getFeedIdsFromFeeds(targetUserFeeds));
            }
        };
        doSomethingOnGetUserAlbumFeeds(rebuildUserFollowerFeedsEvent);
        addUserAlbumFeedIndex(userId, MARK);
    }

    @Override
    public void rebuildFeedComments(final long feedId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild feed comment index! feedId=" + feedId);
        }
        SomethingOnUserHolder<Ugc> rebuildFeedCommentsEvent = new SomethingOnUserHolder<Ugc>() {

            @Override
            public void doSomething(List<Ugc> targetUserUgcs) {
                addFeedCommentIndex(feedId, FeedCommonUtils.getUgcIdsFromUgcs(targetUserUgcs));
            }
        };
        doSomethingOnGetComments(feedId, rebuildFeedCommentsEvent);
        addFeedCommentIndex(feedId, MARK);
    }

    @Override
    public void rebuildFeedFLikes(long feedId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild feed flike index! feedId=" + feedId);
        }
        List<Ugc> ugcs = getFeedReaderStorage().getFeedFLikes(feedId);
        addFeedFLikeIndex(feedId, FeedCommonUtils.getUgcIdsFromUgcs(ugcs));
        addFeedFLikeIndex(feedId, MARK);
    }

    @Override
    public void rebuildFeedMLikes(long feedId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild feed mlike index! feedId=" + feedId);
        }
        List<Ugc> ugcs = getFeedReaderStorage().getFeedMLikes(feedId);
        addFeedMLikeIndex(feedId, FeedCommonUtils.getUgcIdsFromUgcs(ugcs));
        addFeedMLikeIndex(feedId, MARK);
    }

    @Override
    public Feed reloadFeed(long feedId) {
        if(logger.isInfoEnabled()){
            logger.info("Reload feed from db! feedId=" + feedId);
        }
        Feed feed = getFeedReaderStorage().getFeedById(feedId);
        if(feed != null)
            saveFeed(FeedParamConstants.DEFAULT_SCHOOL_ID, feed);
        return feed;
    }

    @Override
    public Ugc reloadExtUgc(long feedId, long ugcId, ExtUgcType ugcType) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild ugc from db! feedId=" + feedId + ", ugcId=" + ugcId + ", ugcType=" + ugcType.name());
        }
        List<Long> ugcIds = new ArrayList<Long>();
        ugcIds.add(ugcId);
        Map<Long, Ugc> ugcs = getFeedReaderStorage().getExtUgcs(feedId, ugcIds, ugcType);
        return ugcs.get(ugcId);
    }

    @Override
    public List<Feed> rebuildSchoolTopTen(int schoolId) {
        if(logger.isInfoEnabled()){
            logger.info("Rebuild school top ten feed! schoolId=" + schoolId);
        }
        List<Feed> topTen = getFeedReaderStorage().getTopTenCurrent(schoolId);
        if ((null != topTen) && (topTen.size() > 0)) {
            List<Long> feedIdList = new ArrayList<Long>();
            List<Integer> userIdList = new ArrayList<Integer>();

            for (Feed feed : topTen) {
                if (null != feed) {
                    long feedId = feed.getFeedId();
                    int userId = feed.getNativeUgc().getUserProfile().getUserId();
                    feedIdList.add(feedId);
                    userIdList.add(userId);
                }
            }
            updateSchoolTopTen(schoolId, feedIdList, userIdList);
        }
        return topTen;
    }
}
