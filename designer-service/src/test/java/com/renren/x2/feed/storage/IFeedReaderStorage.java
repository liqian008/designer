/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.storage;

import java.util.List;
import java.util.Map;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午4:22:25
 */
public interface IFeedReaderStorage {

    public List<Feed> getSchoolFeeds(int schoolId, long feedId, boolean forward, int size,
            int offset);

    public List<Feed> getUserFeeds(int userId, long feedId, boolean forward, int size, int offset);

    public List<Feed> getUserFollowerFeeds(int userId, long feedId, boolean forward, int size,
            int offset) throws FeedException;

    public List<Feed> getUserAlbumFeeds(int userId);

    public Feed getFeedById(long feedId);

    public Boolean isUserLiked(int userId, long feedId, ExtUgcType likeType) throws FeedException;

    public Map<Long, Boolean> isUserLiked(int userId, List<Long> feedIds, ExtUgcType likeType)
            throws FeedException;

    public Ugc getUserLike(int userId, long feedId, ExtUgcType likeType) throws FeedException;

    //取user所有like
    public Map<Long, Ugc> getUserLikeFeeds(int userId, ExtUgcType likeType);

    public List<Ugc> getFeedComments(long feedId, long ugcId, boolean forward, int size, int offset);

    public List<Ugc> getFeedFLikes(long feedId);

    public List<Ugc> getFeedMLikes(long feedId);

    public Map<Long, Ugc> getExtUgcs(long feedId, List<Long> ugcIds, ExtUgcType ugcType);

    public Map<Long, Feed> getFeeds(List<Long> feedIds);

    /**
     * 获取当期十大
     * 
     * @param schoolId
     * @return
     */
    public List<Feed> getTopTenCurrent(int schoolId);

    /**
     * 获取历史十大
     * 
     * @param schoolId
     * @param offset
     * @param limit
     * @return
     * @throws 
     */
    public List<List<Feed>> getTopTenHistory(int schoolId, int offset, int limit);

    /**
     * 获取一段时间最热门帖子列表
     * 
     * @param schoolId
     * @param limit
     * @return
     * @throws 
     */
    public List<Feed> getHistoryHotPost(int schoolId, int offset, int limit);

    /**
     * 获取一段时间上十大排行榜用户，未排序
     * 
     * @param schoolId
     * @param offset
     * @param limit
     * @return
     * @throws 
     */
    public Map<Integer, Integer> getHistoryHotUser(int schoolId, int offset, int limit);

    /**
     * 获取学校ID列表
     * 
     * @return
     * @throws 
     */
    public List<Integer> getSchoolList();
}
