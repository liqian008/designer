package com.bruce.designer.front.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bruce.designer.model.User;
import com.bruce.designer.front.constants.ConstFront;

public class SessionFilter implements Filter{
	
	private List<String> userUrlList = new ArrayList<String>();
	private List<String> designerUrlList = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String userAuthUrls = filterConfig.getInitParameter("userUrls");
		if(userAuthUrls!=null&&userAuthUrls.length()>0){
			StringTokenizer st = new StringTokenizer(userAuthUrls, ",");
			while(st.hasMoreElements()){
				userUrlList.add(st.nextToken());
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String requestUri = req.getRequestURI();
		requestUri = requestUri.substring(requestUri.lastIndexOf("/")+1);
		
		if(userUrlList.contains(requestUri)||designerUrlList.contains(requestUri)){
			User user = (User) req.getSession().getAttribute(ConstFront.CURRENT_USER);
			if(user==null){
				res.sendRedirect("./login.art");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
