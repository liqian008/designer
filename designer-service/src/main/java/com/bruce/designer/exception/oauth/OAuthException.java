package com.bruce.designer.exception.oauth;

public class OAuthException extends Exception {
    
    private static final long serialVersionUID = 5290576576083099467L;

    public OAuthException() {
        super();
    }
    
    public OAuthException(String msg) {
        super(msg);
    }

    public OAuthException(Exception cause) {
        super(cause);
    }
    
}
