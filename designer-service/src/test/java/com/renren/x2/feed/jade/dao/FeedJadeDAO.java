/**
 * $Id: FeedJadeDAO.java 124653 2012-12-19 11:05:34Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.jade.dao;

import java.util.List;
import java.util.Map;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.ShardBy;

import com.renren.x2.feed.beans.FeedDTO;
import com.renren.x2.feed.utils.UgcTypeUtils;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-27 下午1:55:41
 */
@DAO
public interface FeedJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_FEEDS = TABLE_RREFIX + "feeds";

    final String TABLE_SCHOOL_FEED_INDEX = TABLE_RREFIX + "school_feeds_index";

    final String FEED_COLUMN = "feed_id, user_id, school_id, native_ugc_id, native_ugc_type , m_like_count, f_like_count, comment_count, create_time, update_time";

    final String SCHOOL_FEED_INDEX_COLUMN = "school_id, feed_id";

    //Feed
    @SQL("INSERT INTO "
            + TABLE_FEEDS
            + " ("
            + FEED_COLUMN
            + ")  VALUES (:2.feedId, :2.userId, :2.schoolId, :2.nativeUgcId, :2.nativeUgcType, :2.mLikeCount, :2.fLikeCount, :2.commentCount, :2.createTime, :2.updateTime)")
    public void insertFeed(@ShardBy long shardId, FeedDTO feed);

    @SQL("UPDATE " + TABLE_FEEDS + " SET update_time = :2.updateTime WHERE feed_id = :2.feedId")
    public void updateFeed(@ShardBy long shardId, FeedDTO feed);

    @SQL("INSERT INTO " + TABLE_SCHOOL_FEED_INDEX + "(" + SCHOOL_FEED_INDEX_COLUMN
            + ") VALUES (:1, :2)")
    public void insertSchoolFeedIndex(@ShardBy int schoolId, long feedId);

    @SQL("SELECT feed_id FROM " + TABLE_SCHOOL_FEED_INDEX
            + " WHERE school_id = :1 ORDER BY feed_id DESC limit :2")
    public List<Long> getSchoolFeeds(@ShardBy int schoolId, int limit);

    @SQL("SELECT feed_id FROM " + TABLE_SCHOOL_FEED_INDEX
            + " WHERE school_id = :1 AND feed_id > :2 ORDER BY feed_id DESC limit :2")
    public List<Long> getSchoolAfterFeeds(@ShardBy int schoolId, long feedId, int limit);

    @SQL("SELECT feed_id FROM " + TABLE_SCHOOL_FEED_INDEX
            + " WHERE school_id = :1 AND feed_id < :2 ORDER BY feed_id DESC limit :2")
    public List<Long> getSchoolBeforeFeeds(@ShardBy int schoolId, long feedId, int limit);

    @SQL("UPDATE " + TABLE_FEEDS
            + " SET m_like_count = m_like_count+1, update_time = :2  WHERE feed_id = :1")
    public void incrFeedMLike(@ShardBy long feedId, long upateTime);

    @SQL("UPDATE " + TABLE_FEEDS
            + " SET m_like_count = m_like_count-1, update_time = :2  WHERE feed_id = :1")
    public void decrFeedMLike(@ShardBy long feedId, long upateTime);

    @SQL("UPDATE " + TABLE_FEEDS
            + " SET f_like_count = f_like_count+1, update_time = :2  WHERE feed_id = :1")
    public void incrFeedFLike(@ShardBy long feedId, long upateTime);

    @SQL("UPDATE " + TABLE_FEEDS
            + " SET f_like_count = f_like_count-1, update_time = :2  WHERE feed_id = :1")
    public void decrFeedFLike(@ShardBy long feedId, long upateTime);

    @SQL("UPDATE " + TABLE_FEEDS
            + " SET comment_count = comment_count+1, update_time = :2  WHERE feed_id = :1")
    public void incrFeedComment(@ShardBy long feedId, long upateTime);

    //    @SQL("SELECT FROM " + TABLE_COMMENTS + " COUNT(id) WHERE feed_id = :1")
    //    public int getFeedCommentCount(long feedId);
    //
    //    @SQL("SELECT FROM " + TABLE_M_LIKES + " COUNT(id) WHERE feed_id = :1")
    //    public int getFeedMLikeCount(long feedId);
    //
    //    @SQL("SELECT FROM " + TABLE_F_LIKES + " COUNT(id) WHERE feed_id = :1")
    //    public int getFeedFLikeCount(long feedId);

    /**
     * 学校视图只按照某一个用户id的散列表来找,只能取类型为帖子的feed
     */
    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE school_id = :2 AND native_ugc_type = " + UgcTypeUtils.TYPE_POST
            + " ORDER BY id DESC LIMIT :3")
    public List<FeedDTO> getFeedsBySchool(@ShardBy long userId, int schoolId, int size);

    /**
     * 学校视图只按照某一个用户id的散列表来找
     */
    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE school_id = :2 and id < :3 ORDER BY id DESC LIMIT :4")
    public List<FeedDTO> getFeedsBySchool(@ShardBy long userId, int schoolId, long feedId, int size);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE user_id = :1 ORDER BY id DESC LIMIT :2")
    public List<FeedDTO> getFeedsByUser(@ShardBy long userId, int size);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE user_id = :1 AND native_ugc_type IN (:3) ORDER BY id DESC LIMIT :2")
    public List<FeedDTO> getFeedsByUser(@ShardBy long userId, int size,
            List<Integer> nativeUgcTypeList);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS + " WHERE feed_id IN (:2)")
    public Map<Long, FeedDTO> getFeedsByIds(@ShardBy long shardId, List<Long> feedIds);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE user_id = :1 AND id < :2 ORDER BY id DESC LIMIT :3")
    public List<FeedDTO> getFeedsByUser(@ShardBy long userId, long feedId, int size);

    @SQL("SELECT "
            + FEED_COLUMN
            + " FROM "
            + TABLE_FEEDS
            + " WHERE user_id = :1 AND id < :2 AND native_ugc_type IN (:4) ORDER BY id DESC LIMIT :3")
    public List<FeedDTO> getFeedsByUser(@ShardBy long userId, long feedId, int size,
            List<Integer> nativeUgcTypeList);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS
            + " WHERE user_id = :1 AND native_ugc_type = " + UgcTypeUtils.TYPE_ALBUM
            + " ORDER BY id DESC")
    public List<FeedDTO> getFeedsByUser4Album(@ShardBy long userId);

    @SQL("SELECT " + FEED_COLUMN + " FROM " + TABLE_FEEDS + " WHERE feed_id = :1")
    public FeedDTO getFeed(@ShardBy long feedId);

}
