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
import com.renren.x2.feed.exception.IgnoreableException;
import com.renren.x2.feed.storage.IFeedReaderStorage;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午6:22:46
 */
public class FeedReaderStorageInterceptor implements MethodInterceptor {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FeedReaderStorageInterceptor.class);

    private IFeedReaderStorage feedReaderDao;

    private IFeedReaderStorage feedReaderCache;

    private ProxyPolicy policy = ProxyPolicy.DOUBLE;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = null;
        switch (policy) {
            case DB:
                obj = invokeMethod(invocation, feedReaderDao);
                break;
            case CACHE:
                obj = invokeMethod(invocation, feedReaderCache);
                break;
            default:
                try {
                    if(logger.isDebugEnabled()){
                        logger.debug("InvokeMethod " + invocation.getMethod() + " from cache!");
                    }
                    obj = invokeMethod(invocation, feedReaderCache);
                } catch (Exception e) {
                    if(e.getCause() instanceof IgnoreableException){
                        logger.debug("IinvokeMethod " + invocation.getMethod() + " from db! " + e.getCause().getMessage());
                    }else{
                        logger.error("IinvokeMethod " + invocation.getMethod() + " from db!", e);
                    }
                    obj = invokeMethod(invocation, feedReaderDao);
                }
                break;
        }
        return obj;
    }

    public static Object invokeMethod(MethodInvocation invocation, Object target) throws InvocationTargetException,
            IllegalArgumentException, IllegalAccessException {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        if (method.getDeclaringClass().isInstance(target)) {
            return method.invoke(target, args);
        }
        return null;
    }

    /**
     * @param feedReaderDao the feedReaderDao to set
     */
    public void setFeedReaderDao(IFeedReaderStorage feedReaderDao) {
        this.feedReaderDao = feedReaderDao;
    }

    /**
     * @param feedReaderCache the feedReaderCache to set
     */
    public void setFeedReaderCache(IFeedReaderStorage feedReaderCache) {
        this.feedReaderCache = feedReaderCache;
    }

    /**
     * @param readPolicy the readPolicy to set
     */
    public void setReadPolicy(String readPolicy) {
        ProxyPolicy p = null;
        try {
            p = ProxyPolicy.valueOf(readPolicy.toUpperCase());
        } catch (Exception e) {
            logger.error("setReadPolicy error!", e);
        }
        if (p != null) {
            this.policy = p;
        }
    }

}
