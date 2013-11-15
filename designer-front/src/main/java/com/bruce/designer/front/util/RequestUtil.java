package com.bruce.designer.front.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.User;


/**
 * Comments for RequestUtil.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-21 下午05:32:56
 */
public class RequestUtil {

    public static final String JSON_SUFFIX = ".json";
    
    /**
     * 是否为post请求
     * @param request
     * @return
     */
    public static boolean isPost(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("post", request.getMethod());
    }

    /**
     * 是否为get请求
     * @param request
     * @return
     */
    public static boolean isGet(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("get", request.getMethod());
    }
    
    /**
     * 是否为json请求
     * @param request
     * @return
     */
    public static boolean isJsonRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return StringUtils.endsWith(uri, JSON_SUFFIX);
    }
    
    /**
     * 是否为json请求
     * @param request
     * @return
     */
    public static User getSessionUser(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		// 取得session中的用户信息, 以便判断是否登录了系统
		User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
		if(currentUser==null){
			throw new DesignerException(ErrorCode.SYSTEM_ERROR);
		}
		return currentUser;
    }
    
    
    
}
