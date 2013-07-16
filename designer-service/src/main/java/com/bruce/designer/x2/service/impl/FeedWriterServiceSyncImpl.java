/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.renren.x2.common.BaseResult;
import com.renren.x2.feed.annotation.Access;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.constants.FeedResultConstants;
import com.renren.x2.feed.dao.ISequenceDao;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.resolver.FeedServerResultProduce;
import com.renren.x2.feed.service.IFeedWriterService;
import com.renren.x2.feed.service.IMqService;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feed.storage.IFeedWriterStorage;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feed.utils.SchoolTopTenUtil;
import com.renren.x2.feed.utils.UgcTypeUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;
import com.renren.x2.feedapi.constants.UgcStatus;
import com.renren.x2.feedapi.model.AlbumMedia;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcUser;
import com.renren.x2.feedapi.result.UgcPostResult;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午4:39:03
 */
public class FeedWriterServiceSyncImpl extends AbstractFeedHelper implements IFeedWriterService {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FeedWriterServiceSyncImpl.class);

    private static final FeedServerResultProduce feedProduce = new FeedServerResultProduce();

    private IMqService mqService;

    private IFeedWriterStorage feedWriterStorage;

    private IFeedReaderStorage feedReaderStorage;

    private ISequenceDao sequenceDao;

    @Access
    @Profiled
    @Override
    public BaseResult postPosts(int userId, int schoolId, Ugc ugc, int publishRenn)
            throws FeedException {
        return postNativeUgc(userId, schoolId, ugc, publishRenn, NativeUgcType.Posts);
    }

    @Access
    @Profiled
    @Override
    public BaseResult postAlbum(int userId, Ugc ugc) throws FeedException {
        return postNativeUgc(userId, FeedParamConstants.DEFAULT_SCHOOL_ID, ugc,
                FeedParamConstants.DEFAULT_UGC_ID, NativeUgcType.Album);
    }

    @Access
    @Profiled
    @Override
    public BaseResult postLoveStatus(int userId, Ugc ugc) throws FeedException {
        return postNativeUgc(userId, FeedParamConstants.DEFAULT_SCHOOL_ID, ugc,
                FeedParamConstants.DEFAULT_UGC_ID, NativeUgcType.LoveStatus);
    }

    @Access
    @Profiled
    @Override
    public BaseResult postPersonalVoice(int userId, Ugc ugc) throws FeedException {
        return postNativeUgc(userId, FeedParamConstants.DEFAULT_SCHOOL_ID, ugc,
                FeedParamConstants.DEFAULT_UGC_ID, NativeUgcType.PersonalVoice);
    }

    public BaseResult postNativeUgc(int userId, int schoolId, Ugc ugc, int publishRenn,
            NativeUgcType ugcType) throws FeedException {
        fillUgcUser(userId, ugc);
        generatUgc(userId, ugc);
        Feed feed = generatFeed(userId, ugc, ugcType);
        UgcPostResult result = feedProduce.producePostResult(feed);
        //storage
        feedWriterStorage.saveFeed(schoolId, feed);
        feedWriterStorage.addUserFeedIndex(userId, feed.getFeedId());
        if(ugcType == NativeUgcType.Album){
            feedWriterStorage.addUserAlbumFeedIndex(userId, feed.getFeedId());
        }
        if (UgcTypeUtils.needDispatch2School(ugcType)) {
            feedWriterStorage.addSchoolFeedIndex(schoolId, feed.getFeedId());
        }
        if (UgcTypeUtils.needDispatch2Follower(ugcType)) {
            dispatch2Follower(userId, feed.getFeedId());
        }
        //Mq
        mqService.generateFeed(userId, schoolId, feed);
        if(publishRenn == 1){
            mqService.publishRenn(userId, schoolId, feed);
        }
        return result;
    }

    protected void dispatch2Follower(int userId, long feedId) throws FeedException {
        List<Integer> userIds = getFollowerIds(userId);
        for (int user : userIds) {
            feedWriterStorage.addUserFollowerFeedIndex(user, feedId);
        }
    }

    @Access
    @Profiled
    @Override
    public BaseResult postComment(int userId, long feedId, Ugc ugc) throws FeedException {
        Feed feed = feedReaderStorage.getFeedById(feedId);
        if(feed != null){
            fillUgcUser(userId, ugc);
            generatUgc(userId, ugc);
            feedWriterStorage.saveExtUgc(feedId, ugc, ExtUgcType.Comment);
            feedWriterStorage.addFeedCommentIndex(feedId, ugc.getUgcId());
            feedWriterStorage.incrFeedComment(feedId);
            //Mq
            mqService.generateComment(userId, feed.getNativeUgc().getUserProfile().getUserId(), feedId, ugc);
        }
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult postLike(int userId, long feedId) throws FeedException {
        Feed feed = feedReaderStorage.getFeedById(feedId);
        if(feed != null){
            Ugc ugc = new Ugc();
            fillUgcUser(userId, ugc);
            int gender = ugc.getUserProfile().getGender();
            ExtUgcType likeType = UgcTypeUtils.getLikeTypeByGender(gender);
            if (!feedReaderStorage.isUserLiked(userId, feedId, likeType)) {
                generatUgc(userId, ugc);
                feedWriterStorage.saveExtUgc(feedId, ugc, likeType);
                if (likeType == ExtUgcType.MLike) {
                    feedWriterStorage.addFeedMLikeIndex(feedId, ugc.getUgcId());
                    feedWriterStorage.incrFeedMLike(feedId);
                } else {
                    feedWriterStorage.addFeedFLikeIndex(feedId, ugc.getUgcId());
                    feedWriterStorage.incrFeedFLike(feedId);
                }
                feedWriterStorage.addUserLikeFeedIndex(userId, feedId, ugc.getUgcId());
                //Mq
                mqService.generateLike(userId,feed.getNativeUgc().getUserProfile().getUserId(), feedId, ugc);
            }
        }
        
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult updateCurrentAlbum(int userId, int photoId) throws FeedException {
        List<Feed> albumFeeds = feedReaderStorage.getUserAlbumFeeds(userId);
        long prePhotoId = 0;
        long postPhotoId = 0;
        if (albumFeeds != null) {
            for (Feed feed : albumFeeds) {
                AlbumMedia albumMedia = FeedCommonUtils.getFeedAlbumMedia(feed);
                if (albumMedia.getPhotoId() - photoId == 0) {
                    postPhotoId = albumMedia.getPhotoId();
                    albumMedia.setIsCurrent(true);
                    feedWriterStorage.updateFeed(feed);
                }
            }
            //如果找到新头像再去改变老头像
            if (postPhotoId != 0) {
                for (Feed feed : albumFeeds) {
                    AlbumMedia albumMedia = FeedCommonUtils.getFeedAlbumMedia(feed);
                    if (albumMedia != null && albumMedia.getIsCurrent()
                            && albumMedia.getPhotoId() != postPhotoId) {
                        prePhotoId = albumMedia.getPhotoId();
                        albumMedia.setIsCurrent(false);
                        feedWriterStorage.updateFeed(feed);
                    }
                }
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Update user[" + userId + "] photo to " + photoId + ", preCurrentPhotoId="
                    + prePhotoId + ", postCurrentPhotoId=" + postPhotoId);
        }
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult deletePosts(int userId, long feedId, long ugcId) {
        feedWriterStorage.deleteFeed(feedId);
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult deleteAlbum(int userId, long feedId, long ugcId) {
        feedWriterStorage.deleteFeed(feedId);
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult deleteComment(int userId, long feedId, long ugcId) {
        feedWriterStorage.deleteExtUgc(feedId, ugcId, ExtUgcType.Comment);
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult deleteLike(int userId, long feedId) throws FeedException {
        UgcUser user = getUgcUser(userId);
        ExtUgcType likeType = UgcTypeUtils.getLikeTypeByGender(user.getGender());
        Ugc ugc = feedReaderStorage.getUserLike(userId, feedId, likeType);
        if (ugc != null) {
            feedWriterStorage.deleteExtUgc(feedId, ugc.getUgcId(), likeType);
            if (likeType == ExtUgcType.FLike) {
                feedWriterStorage.decrFeedFLike(feedId);
            }
            if (likeType == ExtUgcType.MLike) {
                feedWriterStorage.decrFeedMLike(feedId);
            }
            feedWriterStorage.removeUserLikeFeedIndex(userId, feedId);
        }
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult addFollowerEvent(int userId, int followerId) {
        SomethingOnUserHolder<Feed> addFollowerEvent = new SomethingOnUserHolder<Feed>(userId,
                followerId) {

            @Override
            public void doSomething(List<Feed> feeds) {
                feedWriterStorage.addUserFollowerFeedIndex(getCurrentUserId(),
                        FeedCommonUtils.getFeedIdsFromFeeds(feeds));
            }
        };
        doSomethingOnGetUserFeeds(addFollowerEvent);
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult deleteFollowerEvent(int userId, int followerId) {
        SomethingOnUserHolder<Feed> deleteFollowerEvent = new SomethingOnUserHolder<Feed>(userId,
                followerId) {

            @Override
            public void doSomething(List<Feed> feeds) {
                feedWriterStorage.removeUserFollowerFeedIndex(getCurrentUserId(),
                        FeedCommonUtils.getFeedIdsFromFeeds(feeds));
            }
        };
        doSomethingOnGetUserFeeds(deleteFollowerEvent);
        return FeedResultConstants.SUCCESS;
    }

    @Access
    @Profiled
    @Override
    public BaseResult updateSchoolTopTen(int schoolId, int limit) {
        List<Feed> recentFeeds = feedReaderStorage.getSchoolFeeds(schoolId, 0, true, limit, 0);
        FeedCommonUtils.removeDeletedFeed(recentFeeds);
        List<Feed> oldTopTen = feedReaderStorage.getTopTenCurrent(schoolId);
        FeedCommonUtils.removeDeletedFeed(oldTopTen);
        List<Feed> newTopTen = SchoolTopTenUtil.getSchoolTopTen(oldTopTen, recentFeeds);
        List<Long> feedIdList = new ArrayList<Long>();
        List<Integer> userIdList = new ArrayList<Integer>();

        if ((null != newTopTen) && (newTopTen.size() > 0)) {
            for (Feed feed : newTopTen) {
                if (null != feed) {
                    feedIdList.add(feed.getFeedId());
                    userIdList.add(feed.getNativeUgc().getUserProfile().getUserId());
                }
            }
            feedWriterStorage.updateSchoolTopTen(schoolId, feedIdList, userIdList);
        }
        return FeedResultConstants.SUCCESS;
    }

    protected void generatUgc(int userId, Ugc ugc) {
        long time = System.currentTimeMillis();
        ugc.setCreateTime(time);
        ugc.setUpdateTime(time);
        long ugcId = sequenceDao.getNextUgcId(userId);
        ugc.setUgcId(ugcId);
        ugc.setStatus(UgcStatus.Normal);
    }

    protected Feed generatFeed(int userId, Ugc ugc, NativeUgcType nativeUgcType) {
        long feedId = sequenceDao.getNextFeedId(userId);
        Feed feed = new Feed();
        feed.setNativeUgc(ugc);
        feed.setNativeUgcType(nativeUgcType);
        feed.setFeedId(feedId);
        feed.setCreateTime(ugc.getCreateTime());
        feed.setUpdateTime(ugc.getUpdateTime());
        return feed;
    }

    /**
     * @param sequenceDao the sequenceDao to set
     */
    public void setSequenceDao(ISequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }

    /**
     * @param mqService the mqService to set
     */
    public void setMqService(IMqService mqService) {
        this.mqService = mqService;
    }

    /**
     * @param feedWriterStorage the feedWriterStorage to set
     */
    public void setFeedWriterStorage(IFeedWriterStorage feedWriterStorage) {
        this.feedWriterStorage = feedWriterStorage;
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to set
     */
    public void setFeedReaderStorage(IFeedReaderStorage feedReaderStorage) {
        this.feedReaderStorage = feedReaderStorage;
    }

    @Override
    public IFeedReaderStorage getFeedReaderStorage() {
        return this.feedReaderStorage;
    }

}
