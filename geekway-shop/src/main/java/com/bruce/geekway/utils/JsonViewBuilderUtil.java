/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.geekway.utils;

import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.data.JsonResultBean;


public final class JsonViewBuilderUtil {

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
		ModelAndView temp = buildJsonView(JsonResultBuilderUtil.buildSuccessJson());
		SUBMIT_SUCCESS_VIEW = temp;
		temp = buildJsonView(JsonResultBuilderUtil.buildErrorJson());
		SUBMIT_FAILED_VIEW = temp;
	}
	
	public static ModelAndView buildJsonView(JsonResultBean jsonResult) {
		ModelAndView mv = new ModelAndView(ConstFront.JSON_VIEW);
		mv.addObject(JSON_RESULT, jsonResult);
		return mv;
	}
	
}

