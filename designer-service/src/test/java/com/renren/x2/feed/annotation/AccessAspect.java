package com.renren.x2.feed.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.renren.x2.feed.utils.LogUtils;

@Aspect
public class AccessAspect {

    private static final ThreadLocal<Boolean> rootAccess = new ThreadLocal<Boolean>();
    
    @Around(value = "execution(* *(..)) && @annotation(com.renren.x2.feed.annotation.Access)", argNames = "pjp")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Boolean accessStart = rootAccess.get();
        long startTime = System.currentTimeMillis();
        try {
            if (accessStart == null || accessStart == false) {
                rootAccess.set(true);
                LogUtils.generateLogTag();
            }
            LogUtils.logAccess(pjp.getSignature().getName(), pjp.getArgs());
            Object reval = pjp.proceed();
            LogUtils.logReturn(System.currentTimeMillis() - startTime, reval);
            return reval;
        } catch (Throwable e) {
            LogUtils.exCostHolder.set(System.currentTimeMillis() - startTime);
            LogUtils.logException(e);
            throw e;
        } finally {
            if (accessStart == null || accessStart == false) {
                rootAccess.set(false);
            }
        }
    }
}
