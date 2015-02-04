package com.bruce.geekway.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.foundation.util.RequestUtil;
import com.bruce.foundation.util.UrlUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeType;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.RegUser;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.google.gson.Gson;

/**
 * Comments for AuthorizeInterceptor.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-18 下午10:37:27
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	private static final String LOGIN_URL = "/login";
	
//	private static final String ACCESS_DENIED_URL = "/denied";

	/**
	 * 用户操作拦截检查，需区别登陆用户与设计师
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		AuthorizeType authorizeType = getNeedAuthorize(request, handlerMethod);
		if (authorizeType != null) {//需要授权才能访问
			HttpSession session = request.getSession();
			// 取得session中的用户信息, 以便判断是否登录了系统
			RegUser currentRegUser = (RegUser) session.getAttribute(ConstFront.CURRENT_USER);
			if (currentRegUser == null) {//用户未登录，无法访问
				// 需要跳转到登陆页
				// System.out.println("需要跳转到登陆页");
				if (RequestUtil.isJsonRequest(request)) {// json类型
					writeJson(response, ErrorCode.AUTHORIZE_NEED_LOGIN);
					return false;
				} else {// webpage
					String loginUrl = request.getContextPath() + LOGIN_URL;//UrlUtil.getFullUrl(LOGIN_URL);
					
					if (RequestUtil.isGet(request)) {
						// Get跳回请求地址，增加redirectUrl
						loginUrl = UrlUtil.addParameter(loginUrl, ConstFront.REDIRECT_URL, UrlUtil.getRequestUrl(request));
					} else {
						// 其他方法取referer，增加redirectUrl
						loginUrl = UrlUtil.addParameter(loginUrl, ConstFront.REDIRECT_URL, UrlUtil.getRefererUrl(request));
					}
					response.sendRedirect(loginUrl);
					return false;
				}
			}
		}
		return true;
	}

	private void writeJson(HttpServletResponse response, int errorCode) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		writer.write(gson.toJson(JsonResultBuilderUtil.buildErrorJson(errorCode)));
		writer.flush();
		writer.close();
	}

	/**
	 * 需区分普通用户与设计师权限
	 * @param request
	 * @param handlerMethod
	 * @return
	 */
	private AuthorizeType getNeedAuthorize(HttpServletRequest request, HandlerMethod handlerMethod) {
		NeedAuthorize authorizeOnMethod = handlerMethod.getMethodAnnotation(NeedAuthorize.class);
		NeedAuthorize annotationOnClass = handlerMethod.getBean().getClass().getAnnotation(NeedAuthorize.class);
		boolean needAuthorize = annotationOnClass != null || authorizeOnMethod != null;
		if (needAuthorize) {
			return AuthorizeType.USER;
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// Assert.notNull(userService, "userService must not null");
	}
}
