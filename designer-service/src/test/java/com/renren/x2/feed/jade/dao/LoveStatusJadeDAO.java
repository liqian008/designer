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

import com.renren.x2.feed.beans.LoveStatusDTO;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:43:03
 */
@DAO
public interface LoveStatusJadeDAO {
    final String TABLE_RREFIX = "x2_";
    
    final String TABLE_LOVESTATUS = TABLE_RREFIX + "love_status";
    
    final String LOVESTATUS_COLUMN = "ugc_id, feed_id, user_id, content_info, status, create_time, update_time";
    
    //LoveStatus
    @SQL("INSERT INTO "
            + TABLE_LOVESTATUS
            + " (" + LOVESTATUS_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.contentInfo, :2.status, :2.createTime, :2.updateTime)")
    public void insertLoveStatus(@ShardBy int userId, LoveStatusDTO loveStatus);

    @SQL("SELECT " + LOVESTATUS_COLUMN + " FROM " + TABLE_LOVESTATUS + " WHERE ugc_id IN (:2)")
    public Map<Long, LoveStatusDTO> getLoveStatus(@ShardBy long shardId, List<Long> loveStatusIds);

    @SQL("SELECT " + LOVESTATUS_COLUMN + " FROM " + TABLE_LOVESTATUS + " WHERE ugc_id = :2")
    public LoveStatusDTO getLoveStatus(@ShardBy long shardId, long ugcId);


}
