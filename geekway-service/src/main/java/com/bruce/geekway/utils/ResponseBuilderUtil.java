/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.geekway.utils;

import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.exception.ErrorCode;


public final class ResponseBuilderUtil {

//	public final static String JSON_RESULT = "jsonResult";
//
//	public static final String ERROR_MSG = "message";

	public final static int RESULT_SUCCESS = 0X01;

	public final static int RESULT_FAILED = 0X00;


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
