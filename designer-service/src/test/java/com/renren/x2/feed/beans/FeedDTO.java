/**
 * $Id: FeedDTO.java 120432 2012-12-04 02:17:54Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-27 下午1:45:30
 */
public class FeedDTO extends AbstractDTO {

    private long feedId;

    private int schoolId;

    private long nativeUgcId;

    private int nativeUgcType;

    private int mLikeCount;

    private int fLikeCount;

    private int commentCount;


    /**
     * 
     */
    public FeedDTO() {
        super();
    }


    
    /**
     * @param id
     * @param userId
     * @param createTime
     * @param updateTime
     * @param feedId
     * @param schoolId
     * @param nativeUgcId
     * @param nativeUgcType
     * @param mLikeCount
     * @param fLikeCount
     * @param commentCount
     */
    public FeedDTO(long id, int userId, long createTime, long updateTime, long feedId, int schoolId, long nativeUgcId,
            int nativeUgcType, int mLikeCount, int fLikeCount, int commentCount) {
        super(id, userId, createTime, updateTime);
        this.feedId = feedId;
        this.schoolId = schoolId;
        this.nativeUgcId = nativeUgcId;
        this.nativeUgcType = nativeUgcType;
        this.mLikeCount = mLikeCount;
        this.fLikeCount = fLikeCount;
        this.commentCount = commentCount;
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
     * @return the schoolId
     */
    public int getSchoolId() {
        return schoolId;
    }


    
    /**
     * @param schoolId the schoolId to set
     */
    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }


    
    /**
     * @return the nativeUgcId
     */
    public long getNativeUgcId() {
        return nativeUgcId;
    }


    
    /**
     * @param nativeUgcId the nativeUgcId to set
     */
    public void setNativeUgcId(long nativeUgcId) {
        this.nativeUgcId = nativeUgcId;
    }


    
    /**
     * @return the nativeUgcType
     */
    public int getNativeUgcType() {
        return nativeUgcType;
    }


    
    /**
     * @param nativeUgcType the nativeUgcType to set
     */
    public void setNativeUgcType(int nativeUgcType) {
        this.nativeUgcType = nativeUgcType;
    }


    
    /**
     * @return the mLikeCount
     */
    public int getmLikeCount() {
        return mLikeCount;
    }


    
    /**
     * @param mLikeCount the mLikeCount to set
     */
    public void setmLikeCount(int mLikeCount) {
        this.mLikeCount = mLikeCount;
    }


    
    /**
     * @return the fLikeCount
     */
    public int getfLikeCount() {
        return fLikeCount;
    }


    
    /**
     * @param fLikeCount the fLikeCount to set
     */
    public void setfLikeCount(int fLikeCount) {
        this.fLikeCount = fLikeCount;
    }


    
    /**
     * @return the commentCount
     */
    public int getCommentCount() {
        return commentCount;
    }


    
    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
