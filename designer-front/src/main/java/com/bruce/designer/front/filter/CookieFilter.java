package com.bruce.designer.front.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.UserService;

public class CookieFilter implements Filter {

    private List<String> userUrlList = new ArrayList<String>();
    private List<String> designerUrlList = new ArrayList<String>();
    
    @Autowired
    private UserService userService; 

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        String userAuthUrls = filterConfig.getInitParameter("userUrls");
        if (userAuthUrls != null && userAuthUrls.length() > 0) {
            StringTokenizer st = new StringTokenizer(userAuthUrls, ",");
            while (st.hasMoreElements()) {
                userUrlList.add(st.nextToken());
            }
        }
        
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.configureBean(this, "userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestUri = req.getRequestURI();
        requestUri = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        if (userUrlList.contains(requestUri)
                || designerUrlList.contains(requestUri)) {

            Cookie[] cookies = req.getCookies();
            String[] cooks = null;
            String username = null;
            String password = null;
            if (cookies != null) {
                for (Cookie coo : cookies) {
                    String aa = coo.getValue();
                    cooks = aa.split("==");
                    if (cooks.length == 2) {
                        username = cooks[0];
                        password = cooks[1];
                    }
                }
            }

            User user = userService.authUser(username, password);

            if (user == null) {
                res.sendRedirect("./login.art");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
}
