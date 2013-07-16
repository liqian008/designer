package com.renren.x2.feed.constants;

import com.renren.x2.common.ErrorCode;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-10-29 下午03:34:26
 */
public enum FeedErrorEnum {

    SYSTEM_ERROR(ErrorCode.SystemError, "System error"),
    
    ICE_ERROR(ErrorCode.ErrorIce, "Ice error"),
    
    USER_SYSTEM_ERROR(ErrorCode.UserSystemError, "User system error"),

    SYSTEM_SUCCESS(ErrorCode.SystemSuccess, "Success"),

    ;

    /** 错误描述 */
    private String desc;

    /** 错误码 */
    private ErrorCode code;

    /**
     * @param desc
     * @param code
     */
    private FeedErrorEnum(ErrorCode code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the code
     */
    public ErrorCode getCode() {
        return code;
    }

}
