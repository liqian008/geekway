/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.geekway.utils;

import com.bruce.foundation.model.result.ApiJsonResult;
import com.bruce.geekway.model.exception.ErrorCode;


public final class JsonResultBuilderUtil {

	public final static int RESULT_SUCCESS = 0X01;

	public final static int RESULT_FAILED = 0X00;
	
	
	public static ApiJsonResult buildSuccessJson() {
		return buildSuccessJson(null);
	}

	public static ApiJsonResult buildSuccessJson(Object data) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		jsonResult.setResult(RESULT_SUCCESS);
		if (data != null) {
			jsonResult.setData(data);
		}
		return jsonResult;
	}

	public static ApiJsonResult buildErrorJson() {
		ApiJsonResult jsonResult = new ApiJsonResult();
		jsonResult.setResult(RESULT_FAILED);
		jsonResult.setErrorcode(ErrorCode.SYSTEM_ERROR);
		return jsonResult;
	}

	public static ApiJsonResult buildErrorJson(int errorCode) {
		String errorMsg = ErrorCode.getMessage(errorCode);
		return buildErrorJson(errorCode, errorMsg);
	}

	public static ApiJsonResult buildErrorJson(int errorCode, String errorMsg) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		jsonResult.setResult(RESULT_FAILED);
		jsonResult.setErrorcode(errorCode);
		jsonResult.setMessage(errorMsg);
		return jsonResult;
	}

}

