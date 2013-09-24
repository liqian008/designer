package com.bruce.designer.front.exception;

import com.bruce.designer.exception.DesignerException;


public class NotLoginException extends DesignerException {

    private static final long serialVersionUID = 1L;

    public NotLoginException(int errorCode) {
        super(errorCode);
    }

    public NotLoginException(int errorCode, String message) {
        super(errorCode, message);
    }

    public NotLoginException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotLoginException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }


}
