/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.renren.x2.feed.adapter.RelationAdapter;
import com.renren.x2.feed.adapter.UserInfoAdapter;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.resolver.UserServerResultResolver;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcUser;
import com.renren.x2.user.SimpleUserInfosResult;
import com.renren.x2.user.UserIdsResult;
import com.renren.x2.user.UserInfoResult;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-6 上午11:03:34
 */
public abstract class AbstractFeedHelper {

    private static final UserServerResultResolver userResolver = new UserServerResultResolver();

    private UserInfoAdapter userInfoAdapter;

    private RelationAdapter relationAdapter;

    //取ugcUser
    protected UgcUser getUgcUser(int userId) throws FeedException {
        UserInfoResult userInfoResult = userInfoAdapter.getUserInfo(userId, userId);
        UgcUser user = userResolver.resolveUserInfoResult2User(userInfoResult);
        return user;
    }

    //填充ugc中的user
    protected void fillUgcUser(int userId, Ugc ugc) throws FeedException {
        UgcUser user = getUgcUser(userId);
        ugc.setUserProfile(user);
    }

    protected List<Integer> getFollowerIds(int userId) throws FeedException {
        UserIdsResult userIdsResult = relationAdapter.getFollowers(userId);
        List<Integer> userIds = userResolver.resolveSimpleUserInfosResult2User(userIdsResult);
        return userIds;
    }

    protected List<UgcUser> getFollowings(int userId) throws FeedException {
        SimpleUserInfosResult simpleUserInfosResult = relationAdapter.getFollowings(userId);
        List<UgcUser> users = userResolver.resolveSimpleUserInfosResult2User(simpleUserInfosResult);
        return users;
    }

    protected List<Integer> getFollowingIds(int userId) throws FeedException {
        return getUserIdsFromUsers(getFollowings(userId));
    }

    protected List<Integer> getUserIdsFromUsers(List<UgcUser> users) {
        if (users == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> userIds = new ArrayList<Integer>();
        for (UgcUser user : users) {
            userIds.add(user.getUserId());
        }
        return userIds;
    }

    /***
     * @see AbstractFeedHelper#doSomethingOnGetUserFeeds(int, int,
     *      SomethingOnUserHolder)
     * 
     * @param somethingHolder
     */
    protected void doSomethingOnGetUserFeeds(SomethingOnUserHolder<Feed> somethingHolder) {
        doSomethingOnGetUserFeeds(FeedParamConstants.DEFAULT_RELOAD_PAGE_SIZE, FeedParamConstants.MAX_RELOAD_PAGE_NUM,
                somethingHolder);
    }

    /***
     * To use this method, you must implement the method
     * getFeedReaderStorage before
     * 
     * @param somethingHolder
     * @param pageSize
     * @param maxPage
     */
    protected void doSomethingOnGetUserFeeds(int pageSize, int maxPage, SomethingOnUserHolder<Feed> somethingHolder) {
        long curFeedId = 0L;
        List<Feed> page = null;
        for (int curPage = 0; curPage < maxPage; curPage++) {
            page = getFeedReaderStorage().getUserFeeds(somethingHolder.getTargetUserId(), curFeedId, true, pageSize, 0);
            if (CollectionUtils.isEmpty(page)) {
                break;
            }
            somethingHolder.doSomething(page);
        }
    }

    /***
     * To use this method, you must implement the method
     * getFeedReaderStorage before
     * 
     * @param somethingHolder
     */
    protected void doSomethingOnGetUserAlbumFeeds(SomethingOnUserHolder<Feed> somethingHolder) {
        List<Feed> page = getFeedReaderStorage().getUserAlbumFeeds(somethingHolder.getCurrentUserId());
        somethingHolder.doSomething(page);
    }

    /***
     * To use this method, you must implement the method
     * getFeedReaderStorage before
     * 
     * @param pageSize
     * @param maxPage
     * @param somethingHolder
     */
    protected void doSomethingOnGetSchoolFeeds(int pageSize, int maxPage, SomethingOnSchoolHolder somethingHolder) {
        long curFeedId = 0L;
        List<Feed> page = null;
        for (int curPage = 0; curPage < maxPage; curPage++) {
            page = getFeedReaderStorage().getSchoolFeeds(somethingHolder.getSchoolId(), curFeedId, true, pageSize, 0);
            if (CollectionUtils.isEmpty(page)) {
                break;
            }
            somethingHolder.doSomething(page);
        }
    }

    /***
     * @see AbstractFeedHelper#doSomethingOnGetSchoolFeeds(int, int,
     *      SomethingOnSchoolHolder)
     * 
     * @param somethingHolder
     */
    protected void doSomethingOnGetSchoolFeeds(SomethingOnSchoolHolder somethingHolder) {
        doSomethingOnGetSchoolFeeds(FeedParamConstants.DEFAULT_RELOAD_PAGE_SIZE, FeedParamConstants.MAX_RELOAD_PAGE_NUM,
                somethingHolder);
    }

    /***
     * To use this method, you must implement the method
     * getFeedReaderStorage before
     * 
     * @param somethingHolder
     */
    protected void doSomethingOnGetComments(long feedId, SomethingOnUserHolder<Ugc> somethingHolder) {
        long curUgcId = 0L;
        int pageSize = 5000;
        long maxPage = 6;
        List<Ugc> page = null;
        for (int curPage = 0; curPage < maxPage; curPage++) {
            page = getFeedReaderStorage().getFeedComments(feedId, curUgcId, true, pageSize, 0);
            if (CollectionUtils.isEmpty(page)) {
                break;
            }
            somethingHolder.doSomething(page);
        }
    }

    /**
     * @param userInfoAdapter the userInfoAdapter to set
     */
    public void setUserInfoAdapter(UserInfoAdapter userInfoAdapter) {
        this.userInfoAdapter = userInfoAdapter;
    }

    /**
     * @param relationAdapter the relationAdapter to set
     */
    public void setRelationAdapter(RelationAdapter relationAdapter) {
        this.relationAdapter = relationAdapter;
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to get
     */
    public abstract IFeedReaderStorage getFeedReaderStorage();

    protected abstract class SomethingOnUserHolder<T> {

        private int currentUserId;

        private int targetUserId;

        /**
         * @param currentUserId
         * @param targetUserId
         */
        public SomethingOnUserHolder(int currentUserId, int targetUserId) {
            super();
            this.currentUserId = currentUserId;
            this.targetUserId = targetUserId;
        }

        /**
         * 
         */
        public SomethingOnUserHolder() {
            super();
        }

        public abstract void doSomething(List<T> targetUserTs);

        /**
         * @return the currentUserId
         */
        public int getCurrentUserId() {
            return currentUserId;
        }

        /**
         * @return the targetUserId
         */
        public int getTargetUserId() {
            return targetUserId;
        }
    }

    protected abstract class SomethingOnSchoolHolder {

        private int schoolId;

        /**
         * @param currentUserId
         * @param targetUserId
         */
        public SomethingOnSchoolHolder(int schoolId) {
            super();
            this.schoolId = schoolId;
        }

        public abstract void doSomething(List<Feed> targetSchoolFeeds);

        /**
         * @return the schoolId
         */
        public int getSchoolId() {
            return schoolId;
        }

    }

}
