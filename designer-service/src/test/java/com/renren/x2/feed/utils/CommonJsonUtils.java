/**
 * $Id: CommonJsonUtils.java 121813 2012-12-08 08:21:07Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-7-23 下午4:58:27
 */
public class CommonJsonUtils {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CommonJsonUtils.class);

    private static GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");

    public static <T> T fromJson(String json, Class<T> type) {
        if (!StringUtils.isEmpty(json)) {
            try {
                Gson gson = gsonBuilder.create();
                return gson.fromJson(json, type);
            } catch (Exception e) {
                logger.error("Convert json to " + type.getName() + " error", e);
            }
        }
        return null;
    }

    public static <T> T fromJson(String json, Type type) {
        if (!StringUtils.isEmpty(json)) {
            try {
                Gson gson = gsonBuilder.create();
                return gson.fromJson(json, type);
            } catch (Exception e) {
                logger.error("Convert json to " + type.getClass().getName() + " error", e);
            }
        }
        return null;
    }

    public static <T> String toJson(T t) {
        if (t != null) {
            try {
                Gson gson = gsonBuilder.create();
                return gson.toJson(t);
            } catch (Exception e) {
                logger.error("Convert ClientInfo to json error", e);
            }
        }
        return null;
    }
}
