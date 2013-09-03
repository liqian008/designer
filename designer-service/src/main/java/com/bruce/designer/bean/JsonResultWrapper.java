package com.bruce.designer.bean;

/**
 * 对数据的json包装，ajax请求及mcp共用此数据类型
 * @author liqian
 *
 */
public class JsonResultWrapper{
    
    public JsonResultWrapper(){
        super();
    }
    
    public JsonResultWrapper(Object data){
        this.message = "";
        this.data = data;
    }
    
    public JsonResultWrapper(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    private int code;
    
    private String message;
    
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
    
}