/**
 * $Id: ITemplateLoader.java 44050 2011-08-02 07:27:58Z wp.dongtao@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.front.template;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
* Comments for ITemplateLoader.java
*
* @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
* @createTime 2013-3-21 上午11:13:02
*/
public interface ITemplateLoader {
    
    /** 根据模板名处理数据和模板，返回处理结果
     * @param data
     * @param name
     * @return
     */
    public String process(Map<String, Object> data, String name);
    
    /**
     * 根据request和模板，返回处理结果
     * 
     * @param HttpServletRequest request对象
     * @param name
     * @return
     */
    public String process(HttpServletRequest request, String name);
}
