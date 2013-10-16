package com.bruce.designer.front.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.User;

public class LoginInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {
        // HandlerMethod handlerMethod = (HandlerMethod) handler;
        // Login Login = AnnotationUtils.findAnnotation(handler.getClass(),
        // Login.class);
        // Login Login2 = AnnotationUtils.findAnnotation(handler2.getMethod(),
        // Login.class);
        //
        // Login loginOnMethod = handler2.getMethodAnnotation(Login.class);
        //
        // Login annotationOnClass =
        // handler2.getBean().getClass().getAnnotation(Login.class);
        // System.out.println(Login);
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NeedAuthorize authorizeOnMethod = handlerMethod
                .getMethodAnnotation(NeedAuthorize.class);
        NeedAuthorize annotationOnClass = handlerMethod.getBean().getClass()
                .getAnnotation(NeedAuthorize.class);
        boolean needAuthorize = annotationOnClass != null
                || authorizeOnMethod != null;
        if(needAuthorize){
            HttpSession session = request.getSession();
            // 取得session中的用户信息, 以便判断是否登录了系统
            User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
            if(currentUser==null){
                response.sendRedirect("./login.art");
                return false;
            }
        }
        return true;
    }
    
    @Override  
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {  
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {  
    }  

}
