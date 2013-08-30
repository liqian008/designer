package com.bruce.designer.exception.oauth;

public class OAuthException extends Exception {

    private static final long serialVersionUID = -217642699140091997L;

    public OAuthException() {
        super();
    }

    public OAuthException(String message) {
        super(message);
    }

    public OAuthException(Exception cause) {
        super(cause);
    }

    public OAuthException(String message, Throwable cause) {

        super(message, cause);

    }

}
