/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.storage;

import java.util.List;

import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 上午11:39:33
 */
public interface IFeedWriterStorage {

    public void saveFeed(int schoolId, Feed feed);

    public void saveExtUgc(long feedId, Ugc ugc, ExtUgcType ugcType);

    public void addSchoolFeedIndex(int schoolId, long feedId);

    public void addUserFeedIndex(int userId, long feedId);

    public void addUserAlbumFeedIndex(int userId, long feedId);

    public void addUserFollowerFeedIndex(int userId, long feedId);

    public void addUserFollowerFeedIndex(int userId, List<Long> feedIds);

    public void addUserLikeFeedIndex(int userId, long feedId, long ugcId);

    public void addFeedCommentIndex(long feedId, long ugcId);

    public void addFeedFLikeIndex(long feedId, long ugcId);

    public void addFeedMLikeIndex(long feedId, long ugcId);

    public void removeUserLikeFeedIndex(int userId, long feedId);

    public void removeUserFollowerFeedIndex(int userId, List<Long> feedIds);

    public void incrFeedFLike(long feedId);

    public void decrFeedFLike(long feedId);

    public void incrFeedMLike(long feedId);

    public void decrFeedMLike(long feedId);

    public void incrFeedComment(long feedId);

    public void deleteFeed(long feedId);

    public void deleteExtUgc(long feedId, long ugcId, ExtUgcType ugcType);

    public void updateFeed(Feed feed);

    public void updateSchoolTopTen(int schoolId, List<Long> feedIdList, List<Integer> userIdList);
}
