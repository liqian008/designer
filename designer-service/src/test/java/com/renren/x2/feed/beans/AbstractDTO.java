/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 上午11:52:31
 */
public abstract class AbstractDTO {

    private long id;
    
    private int userId;
    
    private long createTime;
    
    private long updateTime;


    /**
     * 
     */
    public AbstractDTO() {
        super();
    }

    /**
     * @param id
     * @param userId
     * @param createTime
     * @param updateTime
     */
    public AbstractDTO(long id, int userId, long createTime, long updateTime) {
        super();
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    
    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    /**
     * @return the createTime
     */
    public long getCreateTime() {
        return createTime;
    }

    
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    
    /**
     * @return the updateTime
     */
    public long getUpdateTime() {
        return updateTime;
    }

    
    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
