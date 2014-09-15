package com.bruce.geekway.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
//import com.bruce.geekway.front.util.ResponseBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil; 

/**
 * Comments for RestExceptionResolver.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-16 下午05:10:37
 */
public class GeekwayExceptionResolver extends AbstractHandlerExceptionResolver {
	/**
	 * Logger for this class
	 */
	// private static final Log logger =
	// LogFactory.getLog(RestExceptionResolver.class);

	private String defaultErrorView = "error";

	private int defaultStatusCode = 500;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
	 * #doResolveException(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		
		int errorCode = ErrorCode.SYSTEM_ERROR;
		if (exception instanceof GeekwayException) {
			errorCode =((GeekwayException)exception).getErrorCode();
		}
		if(logger.isErrorEnabled()){
			logger.error(ErrorCode.getMessage(errorCode), exception);
		}
		String requestURI = request.getRequestURI();
		//json格式
		if (requestURI.toLowerCase().endsWith(".json")){
			// 返回json的modelAndView
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(errorCode));
		} else {//webpage
			response.setStatus(defaultStatusCode);
			Map<String,Object> map = new HashMap<String,Object>();  
	        map.put(JsonViewBuilderUtil.ERROR_MSG,  ErrorCode.getMessage(errorCode));//将错误信息传递给view
			return new ModelAndView(defaultErrorView, map);
		}
	}

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	public int getDefaultStatusCode() {
		return defaultStatusCode;
	}

	public void setDefaultStatusCode(int defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

}
