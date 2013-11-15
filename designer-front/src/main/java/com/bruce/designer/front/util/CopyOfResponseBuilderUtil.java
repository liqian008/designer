/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.front.util;

import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;

public final class CopyOfResponseBuilderUtil {
	
	public final static String RESULT = "result";
	
	public final static String RESULT_DATA = "data";
	
	public static final String ERROR_CODE = "errorcode";
	
	public static final String ERROR_MSG = "message";

	public final static int RESULT_SUCCESS = 0X01;

	public final static int RESULT_FAILED = 0X00;
	
	/**
     * 提交操作的成功返回
     */
    public static final ModelAndView SUBMIT_SUCCESS_VIEW;

    /**
     * 提交操作的失败返回
     */
    public static final ModelAndView SUBMIT_FAILED_VIEW;

	static {
		ModelAndView temp = new ModelAndView(ConstFront.JSON_VIEW);
		temp.addObject(RESULT, RESULT_SUCCESS);
		SUBMIT_SUCCESS_VIEW = temp;
		temp = new ModelAndView(ConstFront.JSON_VIEW);
		temp.addObject(RESULT, RESULT_FAILED);
		SUBMIT_FAILED_VIEW = temp;
	}

	
	/**
	 * 初始化modelAndView
	 */
	private static ModelAndView initJsonView(int resultType){
		ModelAndView mv = new ModelAndView(ConstFront.JSON_VIEW);
		mv.addObject(RESULT, resultType);
		return mv;
	};
	
	public static ModelAndView initSuccessJsonView(){
		return initJsonView(RESULT_SUCCESS);
	};
	
	public static ModelAndView initFailedJsonView(){
		return initJsonView(RESULT_FAILED);
	};
	
	
    public static ModelAndView buildSuccessResponse() {
    	return buildSuccessResponse(null);
    }
	
    public static ModelAndView buildSuccessResponse(Object data) {
    	ModelAndView mv = initSuccessJsonView();
    	if(data!=null){
    		mv.addObject(RESULT_DATA, data);
    	}
    	return mv;
    }
    
    /**
     * 标准的异常信息返回，按编码自动找相应的异常信息
     * 
     * @param errorCode
     * @return
     */
    public static ModelAndView buildErrorResponse(int errorCode) {
        String errorMsg = ErrorCode.getMessage(errorCode);
        return buildErrorResponse(errorCode, errorMsg);
    }

    /**
     * 扩充的异常信息返回，可自定义信息内容
     * 
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static ModelAndView buildErrorResponse(int errorCode, String errorMsg) {
    	ModelAndView mv = initFailedJsonView();
        mv.addObject(ERROR_CODE, errorCode);
        mv.addObject(ERROR_MSG, errorMsg);
        return mv;
    }

}
