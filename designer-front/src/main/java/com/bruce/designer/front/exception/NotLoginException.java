package com.bruce.designer.front.exception;

import com.bruce.designer.exception.oauth.DesignerException;

public class NotLoginException extends DesignerException {

    public NotLoginException(int errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
        // TODO Auto-generated constructor stub
    }
    
}
