package com.renren.x2.feed.utils;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-11-6 下午03:13:56
 */
public abstract class AsynTask implements Runnable {

    private final Logger logger = Logger.getLogger(AsynTask.class);

    @Override
    public void run() {
        try {
            runTask();
        } catch (Exception e) {
            logger.error("asyn process task error", e);
        }
    }

    public abstract void runTask();

}
