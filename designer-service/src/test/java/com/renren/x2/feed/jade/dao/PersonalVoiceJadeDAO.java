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

import com.renren.x2.feed.beans.PersonalVoiceDTO;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-5 上午11:44:19
 */
@DAO
public interface PersonalVoiceJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_PERSONALVOICE = TABLE_RREFIX + "personal_voice";

    final String PERSONALVOICE_COLUMN = "ugc_id, feed_id, user_id, content_info, status, create_time, update_time";

    //PersonalVoice
    @SQL("INSERT INTO "
            + TABLE_PERSONALVOICE
            + " (" + PERSONALVOICE_COLUMN + ") VALUES (:2.ugcId, :2.feedId, :2.userId, :2.contentInfo, :2.status, :2.createTime, :2.updateTime)")
    public void insertPersonalVoice(@ShardBy int userId, PersonalVoiceDTO psersonalVoice);

    @SQL("SELECT " + PERSONALVOICE_COLUMN + " FROM " + TABLE_PERSONALVOICE + " WHERE ugc_id IN (:2)")
    public Map<Long, PersonalVoiceDTO> getPersonalVoice(@ShardBy long shardId, List<Long> personalVoiceIds);

    @SQL("SELECT " + PERSONALVOICE_COLUMN + " FROM " + TABLE_PERSONALVOICE + " WHERE ugc_id = :2")
    public PersonalVoiceDTO getPersonalVoice(@ShardBy int shardId, long ugcId);
}
