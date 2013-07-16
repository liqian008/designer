/**
 * $Id: LogUtils.java 123820 2012-12-17 11:00:23Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.renren.x2.feed.constants.ErrorMessageConstants;
import com.renren.x2.feed.constants.FeedErrorEnum;
import com.renren.x2.feed.exception.FeedException;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-28 上午9:49:10
 */
public class LogUtils {
    public static final ThreadLocal<Long> exCostHolder = new ThreadLocal<Long>();
    
    public static FeedException logAndGetIceException(Logger logger, Exception e){
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        String caller = "";
        if (!ArrayUtils.isEmpty(stack) && stack.length > 2) {
            caller = stack[1].getClassName() + "#" + stack[1].getMethodName();
            logger.error(caller + " Ice error!", e);
        }
        return new FeedException(FeedErrorEnum.ICE_ERROR, ErrorMessageConstants.ICE_ERROR, e);
    }
    
    private static final Logger accessLogger = Logger.getLogger("access");
    
    private static final Logger infoLogger = Logger.getLogger("info");
    
    private static final Logger exceptionLogger = Logger.getLogger("exception");

    public static void logAccess(String methodName, Object... args){
        StringBuilder sb = new StringBuilder();
        sb.append(getLogTag()).append("[").append(methodName).append("]-{");
        if(args != null){
            for(Object arg : args){
                if(isBaseDataType(arg.getClass()))
                    sb.append(arg);
                else
                    sb.append(CommonJsonUtils.toJson(arg));
                sb.append(", ");
            }
        }
        if(sb.charAt(sb.length() - 2)==',' && sb.charAt(sb.length() - 1) == ' '){
            sb.delete(sb.length()-2, sb.length());
        }
        sb.append("}");
        accessLogger.info(sb.toString());
    }
    
    public static void logReturn(long cost, Object obj){
        infoLogger.info(getLogTag() + "[" + cost +"]-" + CommonJsonUtils.toJson(obj));
    }
    
    public static void logExReturn(Object obj){
        infoLogger.info(getLogTag() + "[" + exCostHolder.get() +"]-" + CommonJsonUtils.toJson(obj));
    }
    
    public static void logException(Throwable ex){
        exceptionLogger.error(getLogTag() + "Exception", ex);
    }
    
    private static boolean isBaseDataType(Class<?> clazz){
         return 
         (  
             clazz.equals(String.class) ||  
             clazz.equals(Integer.class)||  
             clazz.equals(Byte.class) ||  
             clazz.equals(Long.class) ||  
             clazz.equals(Double.class) ||  
             clazz.equals(Float.class) ||  
             clazz.equals(Character.class) ||  
             clazz.equals(Short.class) ||  
             clazz.equals(BigDecimal.class) ||  
             clazz.equals(BigInteger.class) ||  
             clazz.equals(Boolean.class) ||  
             clazz.equals(Date.class) ||  
             clazz.isPrimitive()  
         );  
     }
    
    public static final ThreadLocal<String> logTagHolder = new ThreadLocal<String>();

    public static String generateLogTag() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(Thread.currentThread().getId()).append(":").append(System.currentTimeMillis()).append("]");
        String tag = sb.toString();
        logTagHolder.set(tag);
        return tag;
    }

    public static String getLogTag() {
        String tag = logTagHolder.get();
        if (StringUtils.isEmpty(tag)) tag = generateLogTag();
        return tag;
    }
}
