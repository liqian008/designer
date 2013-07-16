/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.perf4j.aop.Profiled;

import com.renren.x2.feed.annotation.Access;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.resolver.FeedServerResultProduce;
import com.renren.x2.feed.service.IFeedReaderService;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feed.storage.IFeedWriterStorage;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feed.utils.SchoolTopTenUtil;
import com.renren.x2.feed.utils.UgcTypeUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;
import com.renren.x2.feedapi.model.AlbumMedia;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcUser;
import com.renren.x2.feedapi.result.FeedListResult;
import com.renren.x2.feedapi.result.FeedResult;
import com.renren.x2.feedapi.result.UgcListResult;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午10:53:30
 */
public class FeedReaderServiceImpl extends AbstractFeedHelper implements IFeedReaderService {

    private static FeedServerResultProduce feedServerResultProduce = new FeedServerResultProduce();

    private static final AlbumComparator albumComparator = new AlbumComparator();

    private IFeedReaderStorage feedReaderStorage;

    private IFeedWriterStorage feedWriterStorage;

    @Access
    @Profiled
    @Override
    public FeedResult getFeedById(int userId, long feedId) throws FeedException {
        UgcUser user = getUgcUser(userId);
        Feed feed = feedReaderStorage.getFeedById(feedId);
        fillComments2Feed(feed);
        fillIsLiked(userId, feed, UgcTypeUtils.getLikeTypeByGender(user.getGender()));
        return feedServerResultProduce.produceFeedResult(feed);
    }

    public void fillComments2Feed(Feed feed) {
        if (feed != null) {
            List<Ugc> ugcs = feedReaderStorage.getFeedComments(feed.getFeedId(),
                    FeedParamConstants.DEFAULT_UGC_ID, true,
                    FeedCommonUtils.getDefaultCommentSize(), FeedParamConstants.DEFAULT_OFFSET);
            feed.setComments(ugcs);
        }
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getSchoolFeeds(int userId, int schoolId, long feedId, boolean forward,
            int size, int offset) throws FeedException {
        size = FeedCommonUtils.getDefaultFeedSize(size);
        List<Feed> feeds = feedReaderStorage
                .getSchoolFeeds(schoolId, feedId, forward, size, offset);
        FeedCommonUtils.filterDeletedFeed(feeds);
        if(userId > 0){
        	UgcUser user = getUgcUser(userId);
            fillIsLiked(userId, feeds, UgcTypeUtils.getLikeTypeByGender(user.getGender()));
        }
        return feedServerResultProduce.produceFeedListResult(feeds);
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getUserFeeds(int userId, int ownerId, long feedId, boolean forward,
            int size, int offset) throws FeedException {
        UgcUser user = getUgcUser(userId);
        size = FeedCommonUtils.getDefaultFeedSize(size);
        List<Feed> feeds = feedReaderStorage.getUserFeeds(ownerId, feedId, forward, size, offset);
        FeedCommonUtils.filterDeletedFeed(feeds);
        fillIsLiked(userId, feeds, UgcTypeUtils.getLikeTypeByGender(user.getGender()));
        return feedServerResultProduce.produceFeedListResult(feeds);
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getUserFollowerFeeds(int userId, long feedId, boolean forward, int size,
            int offset) throws FeedException {
        UgcUser user = getUgcUser(userId);
        size = FeedCommonUtils.getDefaultFeedSize(size);
        List<Feed> feeds = feedReaderStorage.getUserFollowerFeeds(userId, feedId, forward, size,
                offset);
        FeedCommonUtils.filterDeletedFeed(feeds);
        fillIsLiked(userId, feeds, UgcTypeUtils.getLikeTypeByGender(user.getGender()));
        return feedServerResultProduce.produceFeedListResult(feeds);
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getUserAlbumsFeeds(int userId, int ownerId) throws FeedException {
        UgcUser user = getUgcUser(userId);
        List<Feed> feeds = feedReaderStorage.getUserAlbumFeeds(ownerId);
        FeedCommonUtils.removeDeletedFeed(feeds);
        fillIsLiked(userId, feeds, UgcTypeUtils.getLikeTypeByGender(user.getGender()));
        Collections.sort(feeds, albumComparator);
        return feedServerResultProduce.produceFeedListResult(feeds);
    }

    @Access
    @Profiled
    @Override
    public UgcListResult getComments(int userId, long feedId, long ugcId, boolean forward,
            int size, int offset) {
        size = FeedCommonUtils.getDefaultCommentSize(size);
        List<Ugc> ugcs = feedReaderStorage.getFeedComments(feedId, ugcId, forward, size, offset);
        FeedCommonUtils.filterDeletedUgc(ugcs, ExtUgcType.Comment);
        Collections.reverse(ugcs);
        return feedServerResultProduce.produceUgcListResult(ugcs);
    }

    @Access
    @Profiled
    @Override
    public UgcListResult getFLikes(int userId, long feedId, int size) {
        List<Ugc> ugcs = feedReaderStorage.getFeedFLikes(feedId);
        FeedCommonUtils.filterDeletedUgc(ugcs, ExtUgcType.FLike);
        return feedServerResultProduce.produceUgcListResult(ugcs);
    }

    @Access
    @Profiled
    @Override
    public UgcListResult getMLikes(int userId, long feedId, int size) {
        List<Ugc> ugcs = feedReaderStorage.getFeedMLikes(feedId);
        FeedCommonUtils.filterDeletedUgc(ugcs, ExtUgcType.MLike);
        return feedServerResultProduce.produceUgcListResult(ugcs);
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getSchoolTopTen(int schoolId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Access
    @Profiled
    @Override
    public FeedListResult getSchoolTopTen(int userId, int schoolId) throws FeedException {
        UgcUser user = getUgcUser(userId);
        List<Feed> oldTopTen = feedReaderStorage.getTopTenCurrent(schoolId);
        List<Feed> recentFeedList = new ArrayList<Feed>();
        List<Feed> newTopTen = oldTopTen;
        if ((null == oldTopTen) || (oldTopTen.size() < 1)) {
            recentFeedList = feedReaderStorage.getSchoolFeeds(schoolId, 0, true, 1000, 0);
            FeedCommonUtils.removeDeletedFeed(recentFeedList);
            if ((null != recentFeedList) && (recentFeedList.size() > 0)) {
                newTopTen = SchoolTopTenUtil.getSchoolTopTen(oldTopTen, recentFeedList);
                List<Integer> userIdList = new ArrayList<Integer>();
                List<Long> feedIdList = new ArrayList<Long>();
                if ((null != newTopTen) && (newTopTen.size() > 0)) {
                    for (Feed feed : newTopTen) {
                        if (null != feed) {
                            feedIdList.add(feed.getFeedId());
                            userIdList.add(feed.getNativeUgc().getUserProfile().getUserId());
                        }
                    }
                    feedWriterStorage.updateSchoolTopTen(schoolId, feedIdList, userIdList);
                }
            }
        }

        fillIsLiked(userId, newTopTen, UgcTypeUtils.getLikeTypeByGender(user.getGender()));

        return feedServerResultProduce.produceFeedListResult(newTopTen);
    }

    protected void fillIsLiked(int userId, List<Feed> feeds, ExtUgcType likeType)
            throws FeedException {
        List<Long> feedIds = FeedCommonUtils.getFeedIdsFromFeeds(feeds);
        Map<Long, Boolean> isUserLiked = feedReaderStorage.isUserLiked(userId, feedIds, likeType);
        Iterator<Feed> itr = feeds.iterator();
        while (itr.hasNext()) {
            Feed feed = itr.next();
            long feedId = feed.getFeedId();
            if (BooleanUtils.isTrue(isUserLiked.get(feedId))) {
                feed.setIsLiked(true);
            }
        }
    }

    protected void fillIsLiked(int userId, Feed feed, ExtUgcType likeType) throws FeedException {
        long feedId = feed.getFeedId();
        if (feedReaderStorage.isUserLiked(userId, feedId, likeType)) {
            feed.setIsLiked(true);
        }
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to set
     */
    public void setFeedReaderStorage(IFeedReaderStorage feedReaderStorage) {
        this.feedReaderStorage = feedReaderStorage;
    }

    public void setFeedWriterStorage(IFeedWriterStorage feedWriterStorage) {
        this.feedWriterStorage = feedWriterStorage;
    }

    @Override
    public IFeedReaderStorage getFeedReaderStorage() {
        //do nothing
        return null;
    }

}

class AlbumComparator implements Comparator<Feed> {

    @Override
    public int compare(Feed o1, Feed o2) {
        if (o1.getNativeUgcType() == NativeUgcType.Album) {
            AlbumMedia albumMedia = FeedCommonUtils.getFeedAlbumMedia(o1);
            if (albumMedia != null && albumMedia.getIsCurrent()) {
                return -1;
            }
        }

        if (o2.getNativeUgcType() == NativeUgcType.Album) {
            AlbumMedia albumMedia = FeedCommonUtils.getFeedAlbumMedia(o2);
            if (albumMedia != null && albumMedia.getIsCurrent()) {
                return 1;
            }
        }

        return -((o1.getFLikesCount() + o1.getMLikesCount()) - (o2.getFLikesCount() + o2
                .getMLikesCount()));
    }

}
