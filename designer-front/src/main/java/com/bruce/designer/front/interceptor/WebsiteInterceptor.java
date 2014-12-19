package com.bruce.designer.front.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.RequestUtil;

/**
 * Comments for AuthorizeInterceptor.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-18 下午10:37:27
 */
public class WebsiteInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	/**
	 * 网站拦截器，注册当前服务版本号（可用于更新css版本号）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!RequestUtil.isJsonRequest(request)) {// 不为json类型
			request.setAttribute(ConstFront.KEY_WEBSITE_VERSION, ConstFront.WEBSITE_VERSION);
		}
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

}
