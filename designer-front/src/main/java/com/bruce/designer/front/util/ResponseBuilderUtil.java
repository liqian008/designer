/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.front.util;

import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.data.JsonResultBean;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;

public final class ResponseBuilderUtil {

	public final static String JSON_RESULT = "jsonResult";

//	public final static String RESULT_DATA = "data";
//
//	public static final String ERROR_CODE = "errorcode";
//
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
		ModelAndView temp = buildJsonView(buildSuccessJson());
		SUBMIT_SUCCESS_VIEW = temp;
		temp = buildJsonView(buildErrorJson());
		SUBMIT_FAILED_VIEW = temp;
	}
	
	
	public static ModelAndView buildJsonView(JsonResultBean jsonResult) {
		ModelAndView mv = new ModelAndView(ConstFront.JSON_VIEW);
		mv.addObject(JSON_RESULT, jsonResult);
		return mv;
	}

	public static JsonResultBean buildSuccessJson() {
		return buildSuccessJson(null);
	}

	public static JsonResultBean buildSuccessJson(Object data) {
		JsonResultBean jsonResult = new JsonResultBean();
		jsonResult.setResult(RESULT_SUCCESS);
		if (data != null) {
			jsonResult.setData(data);
		}
		return jsonResult;
	}

	public static JsonResultBean buildErrorJson() {
		JsonResultBean jsonResult = new JsonResultBean();
		jsonResult.setResult(RESULT_FAILED);
		jsonResult.setErrorcode(ErrorCode.SYSTEM_ERROR);
		return jsonResult;
	}

	public static JsonResultBean buildErrorJson(int errorCode) {
		String errorMsg = ErrorCode.getMessage(errorCode);
		return buildErrorJson(errorCode, errorMsg);
	}

	public static JsonResultBean buildErrorJson(int errorCode, String errorMsg) {
		JsonResultBean jsonResult = new JsonResultBean();
		jsonResult.setResult(RESULT_FAILED);
		jsonResult.setErrorcode(errorCode);
		jsonResult.setMessage(errorMsg);
		return jsonResult;
	}

}
