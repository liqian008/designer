/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.dao.ISequenceDao;
import com.renren.x2.feed.jade.dao.SequenceJadeDAO;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午5:51:00
 */
public class SequenceDaoImpl implements ISequenceDao {

    @Autowired
    private SequenceJadeDAO sequenceJadeDAO;

    @Override
    public long getNextUgcId(int userId) {
        long feedId = sequenceJadeDAO.nextId(SequenceJadeDAO.SEQ_FEED_ID);
        feedId = (feedId * FeedParamConstants.SHARDING_NUM) + (userId % FeedParamConstants.SHARDING_NUM);
        return feedId;
    }

    @Override
    public long getNextFeedId(int userId) {
        long ugcId = sequenceJadeDAO.nextId(SequenceJadeDAO.SEQ_UGC_ID);
        ugcId = (ugcId * FeedParamConstants.SHARDING_NUM) + (userId % FeedParamConstants.SHARDING_NUM);
        return ugcId;
    }

    /**
     * @param sequenceJadeDAO the sequenceJadeDAO to set
     */
    public void setSequenceJadeDAO(SequenceJadeDAO sequenceJadeDAO) {
        this.sequenceJadeDAO = sequenceJadeDAO;
    }

}
