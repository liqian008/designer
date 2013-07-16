/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.jade.dao;

import java.util.List;
import java.util.Map;

import com.renren.x2.feed.beans.PostDTO;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.ShardBy;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:40:21
 */
@DAO
public interface PostJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_POSTS = TABLE_RREFIX + "posts";

    final String POST_COLUMN = "ugc_id, feed_id, user_id, content_info, status, create_time, update_time";

    //Post
    @SQL("INSERT INTO "
            + TABLE_POSTS
            + " ("+ POST_COLUMN +") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.contentInfo, :2.status, :2.createTime, :2.updateTime)")
    public void insertPost(@ShardBy int userId, PostDTO post);

    @SQL("UPDATE " + TABLE_POSTS + " SET status = :3, update_time = :4 WHERE ugc_id = :2")
    public void updatePost(@ShardBy long shardId, long ugcId, int status, long updateTime);

    @SQL("SELECT " + POST_COLUMN + " FROM " + TABLE_POSTS + " WHERE ugc_id IN (:2)")
    public Map<Long, PostDTO> getPosts(@ShardBy long shardId, List<Long> ugcIds);

    @SQL("SELECT " + POST_COLUMN + " FROM " + TABLE_POSTS + " WHERE ugc_id = :2")
    public PostDTO getPost(@ShardBy int shardId, long ugcId);
}
