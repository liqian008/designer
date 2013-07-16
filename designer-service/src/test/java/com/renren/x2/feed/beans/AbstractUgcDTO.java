/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午1:53:42
 */
public abstract class AbstractUgcDTO extends AbstractDTO {
    private long ugcId;
    
    private long feedId;
    
    private String contentInfo;
    
    private int status;

    
    /**
     * 
     */
    public AbstractUgcDTO() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @param id
     * @param userId
     * @param createTime
     * @param updateTime
     * @param ugcId
     * @param feedId
     * @param contentInfo
     * @param status
     */
    public AbstractUgcDTO(long id, int userId, long createTime, long updateTime, long ugcId, long feedId, String contentInfo,
            int status) {
        super(id, userId, createTime, updateTime);
        this.ugcId = ugcId;
        this.feedId = feedId;
        this.contentInfo = contentInfo;
        this.status = status;
    }



    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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


    
    /**
     * @return the contentInfo
     */
    public String getContentInfo() {
        return contentInfo;
    }


    
    /**
     * @param contentInfo the contentInfo to set
     */
    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
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
    
}
