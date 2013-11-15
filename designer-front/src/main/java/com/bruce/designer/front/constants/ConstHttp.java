package com.bruce.designer.front.constants;

/**
 * Comments for HttpConstant.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-3-18 下午06:06:44
 */
public interface ConstHttp{

    //票名称
    String HTTP_COOKIE_TICKET_NAME = "t";
    
    //location
    String HTTP_COOKIE_LOCATION_NAME = "l";

    int HTTP_COOKIE_MAX_AGE = 30 * 24 * 60 * 60;

    int HTTP_COOKIE_LIVE_AGE = -1;

    String HTTP_REQUEST_HOST_NAME = "host";

    String HTTP_REQUEST_LOCATION = "location";

    String LOGIN_REDIRECT_PARAM = "redirect";

    String REQUEST_METHOD_GET = "get";

    String REQUEST_METHOD_POST = "post";

    String REQUEST_METHOD_UPDATE = "update";

    String REQUEST_METHOD_DELETE = "delete";
    
    String RESPONSE_VIEW_TYPE_JSON = "jsonView";

}
