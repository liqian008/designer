/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.cache;

import java.util.List;
import java.util.Map;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-6 下午3:02:29
 */
public interface IFeedRebuildableCache {

    public void addSchoolFeedIndex(int schoolId, List<Long> feedIds);

    public void addUserFeedIndex(int userId, List<Long> feedIds);

    public void addUserAlbumFeedIndex(int userId, List<Long> feedIds);

    public void addUserLikeFeedIndex(int userId, Map<Long, Long> feedUgcIds);

    public void addFeedCommentIndex(long feedId, List<Long> ugcIds);

    public void addFeedFLikeIndex(long feedId, List<Long> ugcIds);

    public void addFeedMLikeIndex(long feedId, List<Long> ugcIds);

    public void rebuildSchoolFeeds(int schoolId);

    public void rebuildUserFollowerFeeds(int userId) throws FeedException;

    public void rebuildUserFeeds(int userId);

    public void rebuildUserAlbumFeeds(int userId);
    
    public void rebuildUserLikeFeeds(int userId) throws FeedException;

    public void rebuildFeedComments(long feedId);

    public void rebuildFeedFLikes(long feedId);

    public void rebuildFeedMLikes(long feedId);

    public Feed reloadFeed(long feedId);

    public Ugc reloadExtUgc(long feedId, long ugcId, ExtUgcType ugcType);

    public List<Feed> rebuildSchoolTopTen(int schoolId);
}
