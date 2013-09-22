package com.bruce.designer.exception;

import java.io.Serializable;

/**
 * 对数据的json包装，ajax请求及mcp共用此数据类型
 * 
 * @author liqian
 * 
 */
public class JsonResultObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int result;

    private String message;

    private Object data;

    public JsonResultObject() {
        super();
    }
    
    public JsonResultObject(int result, String message, Object data){
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
//  private String reason;

//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }

}