/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.service.impl;

import com.renren.x2.common.BaseResult;
import com.renren.x2.feed.constants.FeedResultConstants;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.service.IFeedWriterService;
import com.renren.x2.feed.service.IMqService;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:09:31
 */
public class FeedWriterServiceAsyncImpl implements IFeedWriterService {

    private IMqService mqService;

    @Override
    public BaseResult postPosts(int userId, int schoolId, Ugc ugc, int publishRenn)
            throws FeedException {
        mqService.postPosts(userId, schoolId, ugc, publishRenn);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult postAlbum(int userId, Ugc ugc) throws FeedException {
        mqService.postAlbum(userId, ugc);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult postLoveStatus(int userId, Ugc ugc) throws FeedException {
        //do nothing
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult postPersonalVoice(int userId, Ugc ugc) throws FeedException {
        //do nothing
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult postComment(int userId, long feedId, Ugc ugc) throws FeedException {
        mqService.postComment(userId, feedId, ugc);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult postLike(int userId, long feedId) throws FeedException {
        mqService.postLike(userId, feedId);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult updateCurrentAlbum(int userId, int photoId) throws FeedException {
        //do nothing
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult deletePosts(int userId, long feedId, long ugcId) throws FeedException {
        mqService.deletePosts(userId, feedId, ugcId);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult deleteAlbum(int userId, long feedId, long ugcId) throws FeedException {
        mqService.deleteAlbum(userId, feedId, ugcId);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult deleteComment(int userId, long feedId, long ugcId) throws FeedException {
        mqService.deleteComment(userId, feedId, ugcId);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult deleteLike(int userId, long feedId) throws FeedException {
        mqService.deleteLike(userId, feedId);
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult addFollowerEvent(int userId, int flowerId) {
        //do nothing
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult deleteFollowerEvent(int userId, int flowerId) {
        //do nothing
        return FeedResultConstants.SUCCESS;
    }

    @Override
    public BaseResult updateSchoolTopTen(int schoolId, int limit) {
        return FeedResultConstants.SUCCESS;
    }

    /**
     * @param mqService the mqService to set
     */
    public void setMqService(IMqService mqService) {
        this.mqService = mqService;
    }

}
