package com.bruce.geekway.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.UrlUtil;
import com.bruce.geekway.utils.WxMpUtil;
import com.google.gson.Gson;

/**
 * 
 * @author liqian
 *
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	/**
	 * 用户操作拦截检查，需区别登陆base与userinfo
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器");
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		
		String code = request.getParameter("code");//对微信oauth回调的redirect不加限制
		boolean fromWeixinOAuth = StringUtils.isNotBlank(code);
		
		AuthorizeStrategy authorizeStrategy = getNeedAuthorize(request, handlerMethod);
		
		boolean needAuthorize = !fromWeixinOAuth && authorizeStrategy!=null;//非微信回调且需要登录
		if (needAuthorize) {//需要授权才能访问
			String userOpenId = null;
			
			if(AuthorizeStrategy.COOKIE_ALLOW.equals(authorizeStrategy)){//可以使用cookie中的信息
				//检查cookie中是否存在用户信息
				Cookie[] cookieArray = request.getCookies();
				if(cookieArray!=null&&cookieArray.length>0){
					for(Cookie cookie: cookieArray){
						if(ConstFront.COOKIE_KEY_WX_OPENID.equals(cookie.getName())){
							userOpenId = cookie.getValue();
							System.out.println("userOpenId from cookie: "+userOpenId);
							break;
						}
					}
				}
			}
			
			if (StringUtils.isNotBlank(userOpenId)) {//用户信息存在，写入request，供controller获取使用
				request.setAttribute(ConstFront.CURRENT_USER, userOpenId);
			}else{//用户身份信息不存在，无法访问
				if (RequestUtil.isJsonRequest(request)) {//来路为json请求
					writeJson(response, ErrorCode.AUTHORIZE_NEED_LOGIN);
					return false;
				}else{//来路为webpage页
					// 构造oauth的请求
					String wxOauthUrl = null;
					if (RequestUtil.isGet(request)) {
						// Get跳回请求地址，增加redirectUrl
						String redirectUrl = UrlUtil.getRequestUrl(request);
						wxOauthUrl = WxMpUtil.buildWeixinOauthProxyUrl(0, redirectUrl, "");
					} else {
						// 其他方法取referer，增加redirectUrl
						String redirectUrl = UrlUtil.getRefererUrl(request);
						System.out.println("redirectUrl: "+redirectUrl);
						wxOauthUrl = WxMpUtil.buildWeixinOauthProxyUrl(0, redirectUrl, "");
					}
					response.sendRedirect(wxOauthUrl);
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * 判断是否需要登录
	 * @param request
	 * @param handlerMethod
	 * @return
	 */
	private AuthorizeStrategy getNeedAuthorize(HttpServletRequest request, HandlerMethod handlerMethod) {
		NeedAuthorize authorizeOnMethod = handlerMethod.getMethodAnnotation(NeedAuthorize.class);
		NeedAuthorize annotationOnClass = handlerMethod.getBean().getClass().getAnnotation(NeedAuthorize.class);
		boolean needAuthorize = annotationOnClass != null || authorizeOnMethod != null;
		if (needAuthorize) {
			// 检查策略
			if ((authorizeOnMethod != null && authorizeOnMethod.authorizeStrategy() == AuthorizeStrategy.COOKIE_DENY)
					|| (annotationOnClass != null && annotationOnClass.authorizeStrategy() == AuthorizeStrategy.COOKIE_DENY)) {
				return AuthorizeStrategy.COOKIE_DENY;
			}
			return AuthorizeStrategy.COOKIE_ALLOW; 
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// Assert.notNull(userService, "xxxx must not null");
	}
	
	
	private void writeJson(HttpServletResponse response, int errorCode) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		writer.write(gson.toJson(ResponseBuilderUtil.buildErrorJson(errorCode)));
		writer.flush();
		writer.close();
	}
}
