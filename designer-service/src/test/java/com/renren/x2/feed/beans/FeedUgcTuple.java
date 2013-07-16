/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午4:19:21
 */
public class FeedUgcTuple {
    private long feedId;
    
    private long ugcId;

    
    /**
     * @param feedId
     * @param ugcId
     */
    public FeedUgcTuple(long feedId, long ugcId) {
        super();
        this.feedId = feedId;
        this.ugcId = ugcId;
    }


    /**
     * @return the feedId
     */
    public long getFeedId() {
        return feedId;
    }

    
    /**
     * @param feedId the feedId to set
     */
    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    
    /**
     * @return the ugcId
     */
    public long getUgcId() {
        return ugcId;
    }

    
    /**
     * @param ugcId the ugcId to set
     */
    public void setUgcId(long ugcId) {
        this.ugcId = ugcId;
    }
}
