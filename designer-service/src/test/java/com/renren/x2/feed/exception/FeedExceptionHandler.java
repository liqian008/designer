package com.renren.x2.feed.exception;

import com.renren.x2.common.BaseResult;
import com.renren.x2.feed.constants.FeedErrorEnum;
import com.renren.x2.feed.utils.LogUtils;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-10-29 下午03:48:18
 */
public class FeedExceptionHandler {

    public <T extends BaseResult> T handleException(Exception e, T result) {
        if (e instanceof FeedException) {
            FeedException fe = (FeedException) e;
            result.setCode(fe.getErrorEnum().getCode());
            result.setErrorMessage(fe.getErrorEnum().getDesc());
        } else {
            result.setCode(FeedErrorEnum.SYSTEM_ERROR.getCode());
            result.setErrorMessage(FeedErrorEnum.SYSTEM_ERROR.getDesc());
        }
        LogUtils.logExReturn(result);
        return result;
    }
}
