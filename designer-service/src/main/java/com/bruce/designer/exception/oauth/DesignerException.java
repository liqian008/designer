package com.bruce.designer.exception.oauth;

public class DesignerException extends Exception {

    private static final long serialVersionUID = 6616433911763611243L;
    
    private int errorCode;
    
    public DesignerException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }
    
    public DesignerException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public DesignerException(int errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }
    
    public DesignerException(int errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
