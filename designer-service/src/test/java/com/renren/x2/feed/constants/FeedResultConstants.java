package com.renren.x2.feed.constants;

import com.renren.x2.common.BaseResult;
import com.renren.x2.common.ErrorCode;


public class FeedResultConstants {
    public static final BaseResult SUCCESS = new BaseResult(ErrorCode.SystemSuccess, "Success");
    
    public static final BaseResult ICE_ERROR = new BaseResult(ErrorCode.ErrorIce, "Ice Error");
}
