/**
 * $Id: CommentDTO.java 120432 2012-12-04 02:17:54Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.beans;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-27 下午1:52:26
 */
public class CommentDTO extends AbstractUgcDTO {

    /**
     * 
     */
    public CommentDTO() {
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
    public CommentDTO(long id, int userId, long createTime, long updateTime, long ugcId, long feedId, String contentInfo,
            int status) {
        super(id, userId, createTime, updateTime, ugcId, feedId, contentInfo, status);
    }
    
}
