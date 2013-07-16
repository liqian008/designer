/**
 * $Id: LikeDTO.java 120432 2012-12-04 02:17:54Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-27 下午1:50:20
 */
public class LikeDTO extends AbstractUgcDTO {


    private String userName;

    private int userGender;

    private String userHeadUrl;

    public LikeDTO() {
        super();
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
     * @param userName
     * @param userGender
     * @param userHeadUrl
     */
    public LikeDTO(long id, int userId, long createTime, long updateTime, long ugcId, long feedId, String contentInfo, int status,
            String userName, int userGender, String userHeadUrl) {
        super(id, userId, createTime, updateTime, ugcId, feedId, contentInfo, status);
        this.userName = userName;
        this.userGender = userGender;
        this.userHeadUrl = userHeadUrl;
    }


    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    /**
     * @return the userGender
     */
    public int getUserGender() {
        return userGender;
    }

    
    /**
     * @param userGender the userGender to set
     */
    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    
    /**
     * @return the userHeadUrl
     */
    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    
    /**
     * @param userHeadUrl the userHeadUrl to set
     */
    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    

}
