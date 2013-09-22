package com.bruce.designer.front.exception;

import com.bruce.designer.exception.oauth.DesignerException;

public class NotLoginException extends DesignerException {

    private static final long serialVersionUID = 1L;

    public NotLoginException(int errorCode) {
        super(errorCode);
    }

    public NotLoginException(int errorCode, String message) {
        super(errorCode, message);
    }

    public NotLoginException(Throwable cause, int errorCode) {
        super(errorCode, cause);
    }

    public NotLoginException(String message, Throwable cause, int errorCode) {
        super(errorCode, message, cause);
    }

}
