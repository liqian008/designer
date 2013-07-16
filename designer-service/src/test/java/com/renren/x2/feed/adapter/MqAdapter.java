/**
 * $Id: UserInfoAdapter.java 120820 2012-12-05 03:45:02Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.adapter;

import org.apache.log4j.Logger;

import com.renren.x2.feed.annotation.Access;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.utils.LogUtils;
import com.renren.x2.mq.service.IMqService;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-28 上午9:40:52
 */
public class MqAdapter {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MqAdapter.class);

    private IMqService mqService;

    @Access
    public int sendMq(int mid, String msg) throws FeedException {
        try {
            return mqService.sendMq(mid, msg);
        } catch (Exception e) {
            throw LogUtils.logAndGetIceException(logger, e);
        }
    }
    
    
    public void setMqService(IMqService mqService) {
        this.mqService = mqService;
    }

   
}
