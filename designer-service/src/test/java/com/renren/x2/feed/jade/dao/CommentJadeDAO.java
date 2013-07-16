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

import com.renren.x2.feed.beans.CommentDTO;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:45:48
 */
@DAO
public interface CommentJadeDAO {
    final String TABLE_RREFIX = "x2_";
    
    final String TABLE_COMMENTS = TABLE_RREFIX + "comments";

    final String COMMENT_COLUMN = "ugc_id, feed_id, user_id, content_info, create_time, update_time, status";

    //Comment
    @SQL("INSERT INTO " + TABLE_COMMENTS
            + " (" + COMMENT_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.contentInfo, :2.createTime, :2.updateTime, :2.status)")
    public void insertComment(@ShardBy long feedId, CommentDTO comment);

    @SQL("UPDATE " + TABLE_COMMENTS + " SET status = :3, update_time = :4 WHERE ugc_id = :2")
    public void updateComment(@ShardBy long shardId, long ugcId, int status, long updateTime);

    @SQL("SELECT " + COMMENT_COLUMN + " FROM " + TABLE_COMMENTS
            + " WHERE feed_id = :1 ORDER BY id DESC LIMIT :2")
    public List<CommentDTO> getComments(@ShardBy long feedId, int size);
    
    @SQL("SELECT " + COMMENT_COLUMN + " FROM " + TABLE_COMMENTS + " WHERE ugc_id IN (:2)")
    public Map<Long, CommentDTO> getComments(@ShardBy long shardId, List<Long> ugcIds);

    @SQL("SELECT " + COMMENT_COLUMN + " FROM " + TABLE_COMMENTS
            + " WHERE feed_id = :1 AND ugc_id < :2 ORDER BY id DESC LIMIT :3")
    public List<CommentDTO> getComments(@ShardBy long feedId, long ugcId, int size);

    @SQL("SELECT " + COMMENT_COLUMN + " FROM " + TABLE_COMMENTS + " WHERE feed_id = :1 AND ugc_id = :2")
    public CommentDTO getComment(@ShardBy long feedId, long ugcId);
}
