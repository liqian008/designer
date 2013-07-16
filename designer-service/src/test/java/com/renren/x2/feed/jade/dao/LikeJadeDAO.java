/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.jade.dao;

import java.util.List;
import java.util.Map;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.ShardBy;

import com.renren.x2.feed.beans.LikeDTO;
import com.renren.x2.feed.utils.UgcStatusUtils;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:47:25
 */
@DAO
public interface LikeJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_M_LIKES = TABLE_RREFIX + "m_likes";

    final String TABLE_F_LIKES = TABLE_RREFIX + "f_likes";

    final String LIKE_COLUMN = "ugc_id, feed_id, user_id, user_name, user_gender, user_head_url, status, create_time, update_time";

    final String LIKE_COLUMN_FEEDID_START = "feed_id, ugc_id, user_id, user_name, user_gender, user_head_url, status, create_time, update_time";

    
    //Like
    @SQL("INSERT INTO " + TABLE_M_LIKES
            + " (" + LIKE_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.userName, :2.userGender, :2.userHeadUrl, :2.status, :2.createTime, :2.updateTime)")
    public void insertMLike(@ShardBy long feedId, LikeDTO like);

    @SQL("INSERT INTO " + TABLE_F_LIKES
            + " (" + LIKE_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.userName, :2.userGender, :2.userHeadUrl, :2.status, :2.createTime, :2.updateTime)")
    public void insertFLike(@ShardBy long feedId, LikeDTO like);

    @SQL("UPDATE " + TABLE_M_LIKES + " SET status = :3, update_time = :4 WHERE ugc_id = :2")
    public void updateMLike(@ShardBy long feedId, long ugcId, int status, long updateTime);

    @SQL("UPDATE " + TABLE_F_LIKES + " SET status = :3, update_time = :4 WHERE ugc_id = :2")
    public void updateFLike(@ShardBy long feedId, long ugcId, int status, long updateTime);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_M_LIKES + " WHERE feed_id = :1 AND status = " + UgcStatusUtils.STATUS_NORMAL
            + " ORDER BY ugc_id DESC LIMIT :2")
    public List<LikeDTO> getMLikes(@ShardBy long feedId, int size);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_M_LIKES + " WHERE ugc_id IN (:2)  AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public Map<Long, LikeDTO> getMLikes(@ShardBy long shardId, List<Long> ugcIds);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_M_LIKES + " WHERE ugc_id = :2  AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public LikeDTO getMLike(@ShardBy long feedId, long ugcId);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_F_LIKES + " WHERE feed_id = :1 AND status = " + UgcStatusUtils.STATUS_NORMAL
            + " ORDER BY ugc_id DESC LIMIT :2")
    public List<LikeDTO> getFLikes(@ShardBy long feedId, int size);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_F_LIKES + " WHERE ugc_id IN (:2)  AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public Map<Long, LikeDTO> getFLikes(@ShardBy long shardId, List<Long> ugcIds);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_F_LIKES + " WHERE feed_id = :1 AND ugc_id = :2 AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public LikeDTO getFLike(@ShardBy long feedId, long ugcId);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_F_LIKES + " WHERE feed_id = :1 AND user_id = :2 AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public List<LikeDTO> getFUserLike(@ShardBy long feedId, int userId);
    
    @SQL("SELECT " + LIKE_COLUMN_FEEDID_START + " FROM " + TABLE_F_LIKES + " WHERE user_id = :3 AND feed_id IN (:2) AND  status = " + UgcStatusUtils.STATUS_NORMAL)
    public Map<Long, LikeDTO> getFUserLikes(@ShardBy long shardId, List<Long> feedIds, int userId);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_F_LIKES + " WHERE user_id = :2 AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public List<LikeDTO> getFUserLikeByShardId(@ShardBy long shardId, int userId);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_M_LIKES + " WHERE feed_id = :1 AND user_id = :2  AND status = " + UgcStatusUtils.STATUS_NORMAL)
    public List<LikeDTO> getMUserLike(@ShardBy long feedId, int userId);
    
    @SQL("SELECT " + LIKE_COLUMN_FEEDID_START + " FROM " + TABLE_M_LIKES + " WHERE user_id = :3 AND feed_id IN (:2) AND  status = " + UgcStatusUtils.STATUS_NORMAL)
    public Map<Long, LikeDTO> getMUserLikes(@ShardBy long shardId, List<Long> feedIds, int userId);

    @SQL("SELECT " + LIKE_COLUMN + " FROM " + TABLE_M_LIKES + " WHERE user_id = :2 AND  status = " + UgcStatusUtils.STATUS_NORMAL)
    public List<LikeDTO> getMUserLikeByShardId(@ShardBy long shardId, int userId);
}
