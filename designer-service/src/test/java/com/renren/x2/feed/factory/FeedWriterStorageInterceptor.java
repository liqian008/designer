/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.factory;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.renren.x2.feed.beans.ProxyPolicy;
import com.renren.x2.feed.storage.IFeedWriterStorage;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午6:22:46
 */
public class FeedWriterStorageInterceptor implements MethodInterceptor {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FeedWriterStorageInterceptor.class);

    private IFeedWriterStorage feedWriterDao;

    private IFeedWriterStorage feedWriterCache;

    private ProxyPolicy policy = ProxyPolicy.DOUBLE;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = null;
        switch (policy) {
            case DB:
                obj = invokeMethod(invocation, feedWriterDao);
                break;
            case CACHE:
                obj = invokeMethod(invocation, feedWriterCache);
            default:
                obj = invokeMethod(invocation, feedWriterDao);
                invokeMethod(invocation, feedWriterCache);
                break;
        }
        return obj;
    }

    public static Object invokeMethod(MethodInvocation invocation, Object target) throws InvocationTargetException {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        try {
            if (method.getDeclaringClass().isInstance(target)) {
                return method.invoke(target, args);
            }
        } catch (IllegalArgumentException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        } catch (InvocationTargetException e) {
            throw e;
        }
        return null;
    }

    /**
     * @param feedWriterDao the feedWriterDao to set
     */
    public void setFeedWriterDao(IFeedWriterStorage feedWriterDao) {
        this.feedWriterDao = feedWriterDao;
    }

    /**
     * @param feedWriterCache the feedWriterCache to set
     */
    public void setFeedWriterCache(IFeedWriterStorage feedWriterCache) {
        this.feedWriterCache = feedWriterCache;
    }

    /**
     * @param writePolicy the writePolicy to set
     */
    public void setWritePolicy(String writePolicy) {
        ProxyPolicy p = null;
        try {
            p = ProxyPolicy.valueOf(writePolicy.toUpperCase());
        } catch (Exception e) {
            logger.error("setWritePolicy error!", e);
        }
        if (p != null) {
            this.policy = p;
        }
    }

}
