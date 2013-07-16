package com.renren.x2.feed.annotation;

import org.apache.log4j.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.renren.x2.feed.beans.TimeHolder;
import com.renren.x2.feed.beans.TimeHolderList;


@Aspect
public class TimeStatistics {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger("statistics");

    private static final ThreadLocal<TimeHolderList> holder = new ThreadLocal<TimeHolderList>();

    @Around(value="execution(* com.renren.x2.feed.service..*(..)) || execution(* com.renren.x2.feed.cache..*(..)) || execution(* com.renren.x2.feed.dao..*(..))", argNames="pjp")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        TimeHolderList thl = holder.get();
        boolean top = false;
        if(thl == null){
            thl = new TimeHolderList();
            holder.set(thl);
            top = true;
        }
        TimeHolder th = new TimeHolder();
        th.setMethodName(pjp.getSignature().getName());
        thl.add(th);
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally{
            long end = System.currentTimeMillis();
            th.setCost(end - start);
            if(top){
                if(logger.isInfoEnabled()){
                    logger.info(thl);
                }
                holder.set(null);
            }
        }
    }
}
