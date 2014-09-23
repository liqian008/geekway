package com.bruce.geekway.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.UrlUtil;
import com.bruce.geekway.utils.WxMpUtil;

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

		String userOpenId = null;
		String code = request.getParameter("code");//对微信oauth回调的redirect不加限制
		boolean fromWeixinOAuth = StringUtils.isNotBlank(code);
		boolean needAuthorize = !fromWeixinOAuth && getNeedAuthorize(request, handlerMethod);//非微信回调且需要登录
		if (needAuthorize) {//需要授权才能访问
			Cookie[] cookieArray = request.getCookies();
			if(cookieArray!=null&&cookieArray.length>0){
				for(Cookie cookie: cookieArray){
					if(ConstFront.COOKIE_KEY_WX_OPENID.equals(cookie.getName())){
						userOpenId = cookie.getValue();
						break;
					}
				}
			}
			
			if (userOpenId == null) {//用户未登录，无法访问
				// 构造oauth页
				String wxOauthUrl = null;
				if (RequestUtil.isGet(request)) {
					// Get跳回请求地址，增加redirectUrl
					String redirectUrl = UrlUtil.getRequestUrl(request);
					System.out.println("redirectUrl: "+redirectUrl);
					wxOauthUrl = WxMpUtil.buildWeixinOauthUrl(0, redirectUrl, "");
				} else {
					// 其他方法取referer，增加redirectUrl
					String redirectUrl = UrlUtil.getRefererUrl(request);
					System.out.println("redirectUrl: "+redirectUrl);
					wxOauthUrl = WxMpUtil.buildWeixinOauthUrl(0, redirectUrl, "");
				}
				response.sendRedirect(wxOauthUrl);
				return false;
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
	private boolean getNeedAuthorize(HttpServletRequest request, HandlerMethod handlerMethod) {
		NeedAuthorize authorizeOnMethod = handlerMethod.getMethodAnnotation(NeedAuthorize.class);
		NeedAuthorize annotationOnClass = handlerMethod.getBean().getClass().getAnnotation(NeedAuthorize.class);
		boolean needAuthorize = annotationOnClass != null || authorizeOnMethod != null;
		if (needAuthorize) {
			return true;
		}
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// Assert.notNull(userService, "xxxx must not null");
	}
}
