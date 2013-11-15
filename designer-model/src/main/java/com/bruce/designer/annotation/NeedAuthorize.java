package com.bruce.designer.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAuthorize {
    
	/**
	 * 认证类型，分为普通用户与设计师
	 * @return
	 */
    public AuthorizeType authorizeType() default AuthorizeType.USER;
    
    public enum AuthorizeType{
    	USER, DESIGNER
    }

//    public AccessType autype() default AccessType.WEB_PAGE;
//    
//    public enum AccessType{
//        WEB_PAGE, JSON
//    }
}
