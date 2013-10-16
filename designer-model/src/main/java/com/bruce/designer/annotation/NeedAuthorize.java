package com.bruce.designer.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAuthorize {
    
    public AccessType type() default AccessType.WEB_PAGE;
    
    public enum AccessType{
        WEB_PAGE, JSON
    }

}
