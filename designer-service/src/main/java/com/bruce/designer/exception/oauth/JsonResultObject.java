package com.bruce.designer.exception.oauth;

import java.io.Serializable;

/**
 * 对数据的json包装，ajax请求及mcp共用此数据类型
 * 
 * @author liqian
 * 
 */
public class JsonResultObject implements Serializable {

    private int result;

    private String message;

//    private String reason;

    private Object data;

    public JsonResultObject() {
        super();
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

//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }

}