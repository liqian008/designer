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

import com.renren.x2.feed.beans.AlbumDTO;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:41:49
 */
@DAO
public interface AlbumJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_ALBUMS = TABLE_RREFIX + "albums";

    final String ALBUM_COLUMN = "ugc_id, feed_id, user_id, content_info, current_photo, status, create_time, update_time";

    //Album
    @SQL("INSERT INTO "
            + TABLE_ALBUMS
            + " (" + ALBUM_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.contentInfo, :2.currentPhoto, :2.status, :2.createTime, :2.updateTime)")
    public void insertAlbum(@ShardBy int userId, AlbumDTO album);

    @SQL("UPDATE " + TABLE_ALBUMS + " SET current_photo = :2.currentPhoto , update_time = :2.updateTime WHERE ugc_id = :2.ugcId")
    public void updateAlbum(@ShardBy int userId, AlbumDTO albumDTO);

    @SQL("SELECT " + ALBUM_COLUMN + " FROM " + TABLE_ALBUMS + " WHERE ugc_id IN (:2)")
    public Map<Long, AlbumDTO> getAlbums(@ShardBy long shardId, List<Long> albumsIds);

    @SQL("SELECT " + ALBUM_COLUMN + " FROM " + TABLE_ALBUMS + " WHERE ugc_id = :2")
    public AlbumDTO getAlbum(@ShardBy long shardId, long ugcId);

    @SQL("UPDATE " + TABLE_ALBUMS + " SET status = :3, update_time = :4 WHERE ugc_id = :2")
    public void updateAlbum(@ShardBy long shardId, long ugcId, int status, long updateTime);
}
