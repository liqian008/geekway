package com.bruce.geekway.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.foundation.util.UrlUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeScope;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.ResponseUtil;
import com.bruce.geekway.utils.WxMpOAuthUtil;
import com.google.gson.Gson;

/**
 * 
 * @author liqian
 *
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	@Autowired
	private WxMpOauthService wxMpOauthService;

	private static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);
	
	/**
	 * 用户操作拦截检查，需区别登陆base与userinfo
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("进入拦截器, requestURI: "+request.getRequestURI());

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		String userOpenId = null;
		logger.debug("weixin oauth debug: " +ConstWeixin.WX_OAUTH_DEBUG);
		if((ConstWeixin.WX_OAUTH_DEBUG)){//微信调试模式
			WxWebUser wxWebUser = new WxWebUser();
			wxWebUser.setOpenId("debug_openid");
			wxWebUser.setNickname("Debug模式");
			wxWebUser.setUnionId("debug_unionid");
			
			//attribute中存放json对象
			request.setAttribute(ConstFront.CURRENT_USER, wxWebUser);
			return true;
		}
		String code = request.getParameter("code");//对微信oauth回调的redirect不加限制
		boolean wxOAuthCallback = StringUtils.isNotBlank(code);
		if(wxOAuthCallback){//处理来自微信oauth回调
			if(logger.isDebugEnabled()){
				logger.debug("微信oauth回调后进入[拦截器], code: "+code);
			}
			//排除例外，如处理redirectUrl的proxy接口
			if(request.getRequestURI().endsWith("wxOauthRedirect")){
				if(logger.isDebugEnabled()){
					logger.debug("微信oauth回调后进入[拦截器]，进入代理proxyUrl"+request.getRequestURI());
				}
				return true;
			}
			
			//根据code换取openId
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			if(oauthResult!=null){
				userOpenId = oauthResult.getOpenid();
				String userAccessToken = oauthResult.getAccess_token();
				if(logger.isDebugEnabled()){
					logger.debug("微信oauth回调后进入[拦截器], 换取的userOpenId，并写入cookie: "+userOpenId);
				}
				if(StringUtils.isNotBlank(userOpenId)){
					WxWebUser webUser = newUser(userOpenId);//拦截器只置入openid，如业务如需更多个人资料，需要在controller中根据下面的accessToken自行处理
					ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_USER, JsonUtil.gson.toJson(webUser));
					request.setAttribute(ConstFront.CURRENT_USER, webUser);
					if(logger.isDebugEnabled()){
						logger.debug("微信oauth回调后进入[拦截器], 换取的userAccessToken，并置入Attribute: "+userAccessToken);
					}
					request.setAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN, userAccessToken);
					return true;
				}
			}else{
				throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
			}
		}else{//自己业务系统内的处理
			AuthorizeStrategy authorizeStrategy = getAuthorizeStratege(request, handlerMethod);
			boolean needAuthorize = authorizeStrategy!=null;//调用的接口需要进行登录
			if (needAuthorize) {//需要用户身份才能访问
				String webUserJson = null;
				if(AuthorizeStrategy.COOKIE_ALLOW.equals(authorizeStrategy)){//允许从cookie中读取用户信息
					//检查cookie中是否存在用户信息
					Cookie[] cookieArray = request.getCookies();
					if(cookieArray!=null&&cookieArray.length>0){
						for(Cookie cookie: cookieArray){
							if(ConstFront.COOKIE_KEY_WX_USER.equals(cookie.getName())){
								webUserJson = cookie.getValue();
								logger.debug("webUserJson from cookie:: " +webUserJson);
								break;
							}
						}
					}
				}
				
				//从cookie中读出的webUser对象
				WxWebUser webUser = null;
				if(webUserJson!=null){
					try{
						webUser = JsonUtil.gson.fromJson(webUserJson, WxWebUser.class);
					}catch(Exception e){
					}
				}
				
				if (webUser!=null&&StringUtils.isNotBlank(webUser.getOpenId())) {//用户信息存在，写入request，供controller获取使用
					request.setAttribute(ConstFront.CURRENT_USER, webUser);
				}else{//用户身份信息不存在，无法访问
					//获取需要使用的scope
					String scope = getAuthorizeScope(request, handlerMethod).getScope();
					
					if (RequestUtil.isJsonRequest(request)) {//来路为json请求
						writeJson(response, ErrorCode.AUTHORIZE_NEED_LOGIN);
						return false;
					}else{//来路为webpage页
						// 构造oauth的请求
						String wxOauthUrl = null;
						if (RequestUtil.isGet(request)) {
							// Get跳回请求地址，增加redirectUrl
							String redirectUrl = UrlUtil.getRequestUrl(request);
							wxOauthUrl = WxMpOAuthUtil.buildWeixinOauthProxyUrl(scope, redirectUrl, "");
						} else {
							// 其他方法取referer，增加redirectUrl
							String redirectUrl = UrlUtil.getRefererUrl(request);
							System.out.println("redirectUrl: "+redirectUrl);
							wxOauthUrl = WxMpOAuthUtil.buildWeixinOauthProxyUrl(scope, redirectUrl, "");
						}
						response.sendRedirect(wxOauthUrl);
						return false;
					}
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
	private AuthorizeStrategy getAuthorizeStratege(HttpServletRequest request, HandlerMethod handlerMethod) {
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
	
	/**
	 * 判断需要使用的scope
	 * @param request
	 * @param handlerMethod
	 * @return
	 */
	private AuthorizeScope getAuthorizeScope(HttpServletRequest request, HandlerMethod handlerMethod) {
		NeedAuthorize authorizeOnMethod = handlerMethod.getMethodAnnotation(NeedAuthorize.class);
		NeedAuthorize annotationOnClass = handlerMethod.getBean().getClass().getAnnotation(NeedAuthorize.class);
		boolean needAuthorize = annotationOnClass != null || authorizeOnMethod != null;
		if (needAuthorize) {
			// 检查策略，如果有snsapi_userinfo，则使用
			if ((authorizeOnMethod != null && authorizeOnMethod.AuthorizeScope() == AuthorizeScope.WX_SNSAPI_USERINFO)
					|| (annotationOnClass != null && annotationOnClass.AuthorizeScope() == AuthorizeScope.WX_SNSAPI_USERINFO)) {
				return AuthorizeScope.WX_SNSAPI_USERINFO;
			}
			//默认为隐式的snsapi_base
			return AuthorizeScope.WX_SNSAPI_BASE;
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


	public WxMpOauthService getWxMpOauthService() {
		return wxMpOauthService;
	}


	public void setWxMpOauthService(WxMpOauthService wxMpOauthService) {
		this.wxMpOauthService = wxMpOauthService;
	}
	
	
	public WxWebUser newUser(String userOpenId){
		WxWebUser wxWebUser = new WxWebUser();
		wxWebUser.setOpenId(userOpenId);
		return wxWebUser;
	}
	
}
