package com.renren.x2.feed.exception;

import com.renren.x2.feed.constants.FeedErrorEnum;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-10-29 下午03:29:34
 */
public class FeedException extends Exception {

    private static final long serialVersionUID = 1L;

    public FeedErrorEnum errorEnum;

    public FeedException(FeedErrorEnum errorEnum, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorEnum = errorEnum;
    }

    public FeedException(FeedErrorEnum errorEnum, String errorMessage) {
        super(errorMessage);
        this.errorEnum = errorEnum;
    }
    
    public FeedException(FeedErrorEnum errorEnum) {
        super(errorEnum.getDesc());
        this.errorEnum = errorEnum;
    }
    
    /**
     * @param message
     */
    public FeedException(String message) {
        super(message);
    }

    public FeedErrorEnum getErrorEnum() {
        return errorEnum;
    }

    
    /**
     * @param errorEnum the errorEnum to set
     */
    public void setErrorEnum(FeedErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

}
