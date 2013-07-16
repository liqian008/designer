/**
 * $Id: UserInfoAdapter.java 125231 2012-12-20 13:10:58Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.adapter;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.utils.LogUtils;
import com.renren.x2.user.UserInfoResult;
import com.renren.x2.user.service.IUserInfoService;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-28 上午9:40:52
 */
public class UserInfoAdapter {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserInfoAdapter.class);

    private IUserInfoService userInfoService;

    @Profiled(tag="Adapter:getUserInfo")
    public UserInfoResult getUserInfo(int userId, int ownerId) throws FeedException {
        try {
            return userInfoService.getUserInfo(userId, ownerId);
        } catch (Exception e) {
            throw LogUtils.logAndGetIceException(logger, e);
        }
    }

    /**
     * @param userInfoService the userInfoService to set
     */
    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
