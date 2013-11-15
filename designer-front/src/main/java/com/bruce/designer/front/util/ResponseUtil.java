package com.bruce.designer.front.util;

/**
 * Comments for ResponseUtil.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-18 下午06:07:33
 */
public class ResponseUtil {

    /**
     * 获取跳转字符串
     * */
    public static String getRedirectString(String url) {
        return "redirect:" + url;
    }
    
    /**
     * 跳转首页
     * */
    public static String getRedirectHomeString() {
        return "redirect:/";
    }
    
    public static String getForwardString(String url) {
        return "forward:/";
    }
    
    public static String getForwardReirect() {
        return "forward:/redirect";
    }
}
