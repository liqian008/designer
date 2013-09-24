package com.bruce.designer.exception.oauth;

import com.bruce.designer.exception.DesignerException;

public class OAuthException extends DesignerException {

    private static final long serialVersionUID = -217642699140091997L;

    public OAuthException(int errorCode) {
        super(errorCode);
    }

    public OAuthException(int errorCode, String message) {
        super(errorCode, message);
    }

    public OAuthException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public OAuthException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
