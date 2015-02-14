package com.bruce.geekway.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.foundation.util.CookieUtils;
import com.bruce.foundation.util.DateUtil;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.foundation.util.UrlUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeScope;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxWebUserService;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
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
	@Autowired
	private IWxWebUserService wxWebUserService;

	private static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);
	
	/**
	 * 用户操作拦截检查，需区别登陆base与userinfo
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String queryString = request.getQueryString();
		logger.debug("进入拦截器, requestURI: "+requestUri+", queryString: "+ queryString);

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		//获取cookie中的渠道标志
		String cookieChannel = CookieUtils.getCookie(request, ConstFront.COOKIE_KEY_CHANNEL);
		if(StringUtils.isBlank(cookieChannel)){
			String requestChannel = request.getParameter("chn");
			if(StringUtils.isNotBlank(requestChannel)){
				CookieUtils.saveCookie(response, ConstFront.COOKIE_KEY_CHANNEL, requestChannel, (int)DateUtil.TIME_UNIT_WEEK, "/", null);
				cookieChannel = requestChannel;
			}
		}
		
		logger.debug("weixin oauth debug: " +ConstWeixin.WX_OAUTH_DEBUG);
		if((ConstWeixin.WX_OAUTH_DEBUG)){//微信调试模式
			WxWebUser wxWebUser = new WxWebUser();
			wxWebUser.setOpenId("debug_openid");
			wxWebUser.setNickname("Debug模式");
			wxWebUser.setUnionId("debug_unionid");
			wxWebUser.setHeadImgUrl("http://tp3.sinaimg.cn/1407260150/180/5614558400/1");
			//http://tp3.sinaimg.cn/1420410030/180/5682952735/0
			
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
			if(requestUri.endsWith("wxOauthRedirect")||requestUri.endsWith("wxJsConfigSrc")){
				if(logger.isDebugEnabled()){
					logger.debug("微信oauth回调后进入[拦截器]，进入代理proxyUrl"+requestUri);
				}
				return true;
			}
			
			//根据code换取openId
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			if(oauthResult!=null){//成功的响应
				String oauthUserOpenId = oauthResult.getOpenid();
				String userAccessToken = oauthResult.getAccess_token();
				if(logger.isDebugEnabled()){
					logger.debug("微信oauth回调后进入[拦截器], 换取的userOpenId："+oauthUserOpenId+", accessToken: "+ userAccessToken);
				}
				WxWebUser wxWebUser = newUser(oauthUserOpenId);//默认为隐式授权，对象中至少有个openId
				if(StringUtils.isNotBlank(oauthUserOpenId)&&StringUtils.isNotBlank(userAccessToken)){
					//获取需要使用的scope
					String scope = getAuthorizeScope(request, handlerMethod).getScope();
					if(logger.isDebugEnabled()){
						logger.debug("微信oauth回调后进入[拦截器], 授权scope："+scope);
					}
					if("snsapi_userinfo".equals(scope)){//显示授权
						//读取oauth授权用户的资料&写入DB
						WxUserInfoResult wxUserInfoResult = wxMpOauthService.getOAuthUserinfo(userAccessToken, oauthUserOpenId);
						if(logger.isDebugEnabled()){
							logger.debug("使用accessToken获取wxUserInfo："+wxUserInfoResult);
						}
						if(wxUserInfoResult!=null){//正确的响应 
							wxWebUser = buildFullOAuthWebUser(wxUserInfoResult);
							//额外写入用户表saveOrUpdate
							wxWebUserService.save(wxWebUser);
						}
					}
					
					String webUserCookie = JsonUtil.gson.toJson(wxWebUser);
					try {
						webUserCookie = URLEncoder.encode(webUserCookie, "utf-8");
					} catch (UnsupportedEncodingException e) {
						throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
					}
//					ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_USER, webUserCookie);
					CookieUtils.saveCookie(response, ConstFront.COOKIE_KEY_WX_USER, webUserCookie, (int)DateUtil.TIME_UNIT_MONTH, "/", null);
					request.setAttribute(ConstFront.CURRENT_USER, wxWebUser);
					if(logger.isDebugEnabled()){
						logger.debug("微信oauth回调后进入[拦截器], 换取的userAccessToken，并置入Attribute: "+userAccessToken);
					}
					request.setAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN, userAccessToken);
					return true;
				}
			}else{
				throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
			}
		}else{//进入业务系统内的处理
			if(logger.isDebugEnabled()){
				logger.debug("进入业务系统内处理");
			}
			
			String cookiedWebUserJson = null;
			//读取 cookie中数据
			cookiedWebUserJson = CookieUtils.getCookie(request, ConstFront.COOKIE_KEY_WX_USER);
			if(logger.isDebugEnabled()){
				logger.debug("webUserJson from cookie: " +cookiedWebUserJson);
			}
			//从cookie中读出的webUser对象
			WxWebUser webUser = null;
			if(StringUtils.isNotBlank(cookiedWebUserJson)){
				try{
					cookiedWebUserJson= URLDecoder.decode(cookiedWebUserJson, "utf-8");
					webUser = JsonUtil.gson.fromJson(cookiedWebUserJson, WxWebUser.class);
				}catch(Exception e){
				}
			}
			
			AuthorizeStrategy authorizeStrategy = getAuthorizeStratege(request, handlerMethod);
			boolean needAuthorize = authorizeStrategy!=null;//调用的接口需要进行登录
			if (needAuthorize) {//需要用户身份才能访问
				if(AuthorizeStrategy.COOKIE_DENY.equals(authorizeStrategy)){//允许从cookie中读取用户信息
					webUser=null;
				}
				
				boolean needOauth = true;
				String scope = getAuthorizeScope(request, handlerMethod).getScope();//获取需要使用的scope
				if(logger.isDebugEnabled()){
					logger.debug("mapping scope: " +scope);
				}
				if("snsapi_base".equals(scope)){
					//检查是否有openid
					if (webUser!=null&&StringUtils.isNotBlank(webUser.getOpenId())) {//用户信息存在，写入request，供controller获取使用
						needOauth = false;
					}
				}else if("snsapi_userinfo".equals(scope)){
					
					//检查是否有昵称
					if (webUser!=null&&StringUtils.isNotBlank(webUser.getOpenId())&&StringUtils.isNotBlank(webUser.getNickname())) {
						needOauth = false;
					}
				}
				
				if (!needOauth) {//无需oauth，使用缓存的用户信息，写入request，供controller获取使用
					webUser.setChannel(cookieChannel);//将渠道信息放到用户对象中
					request.setAttribute(ConstFront.CURRENT_USER, webUser);
				}else{//用户身份信息不存在，无法访问
					if(logger.isDebugEnabled()){
						logger.debug("用户身份信息不存在，需要进行oauth获取"); 
					}
					
					
					if (RequestUtil.isJsonRequest(request)) {//来路为json请求
						writeJson(response, ErrorCode.AUTHORIZE_NEED_LOGIN);
						return false;
					}else{//来路为webpage页
						// 构造oauth的请求
						String wxOauthUrl = null;
						if (RequestUtil.isGet(request)) {
							// Get跳回请求地址，增加redirectUrl
							String redirectUrl = UrlUtil.getRequestUrl(request);
							logger.debug("微信get redirectUrl, redirectUrl: "+redirectUrl);
							
							wxOauthUrl = WxMpOAuthUtil.buildWeixinOauthProxyUrl(scope, redirectUrl, "");
						} else {
							// 其他方法取referer，增加redirectUrl
							String redirectUrl = UrlUtil.getRefererUrl(request);
							logger.debug("微信post redirectUrl, redirectUrl: "+redirectUrl);
							
							wxOauthUrl = WxMpOAuthUtil.buildWeixinOauthProxyUrl(scope, redirectUrl, "");
						}
						response.sendRedirect(wxOauthUrl);
						return false;
					}
				}
			}else{//无NeedAuthorize属性，也存attribute
				if(webUser!=null){
					webUser.setChannel(cookieChannel);//将渠道信息放到用户对象中
					request.setAttribute(ConstFront.CURRENT_USER, webUser);
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
	
	public WxWebUser newUser(String userOpenId){
		WxWebUser wxWebUser = new WxWebUser();
		wxWebUser.setOpenId(userOpenId);
		return wxWebUser;
	}
	
	/**
	 * oauth返回webUser对象
	 * @param wxUserInfoResult
	 * @return
	 */
	private WxWebUser buildFullOAuthWebUser(WxUserInfoResult wxUserInfoResult) {
		WxWebUser webUser = new WxWebUser();
		webUser.setUserType(GeekwayEnum.UserTypeEnum.MP_MEINIUR.getValue());//美妞公众账户用户类型
		webUser.setOpenId(wxUserInfoResult.getOpenid());
		webUser.setUnionId(wxUserInfoResult.getUnionid());
		webUser.setNickname(wxUserInfoResult.getNickname());
		webUser.setCountry(wxUserInfoResult.getCountry());
		webUser.setProvince(wxUserInfoResult.getProvince());
		webUser.setCity(wxUserInfoResult.getCity());
		String headImgUrl = ConstWeixin.DEFAULT_USER_AVATAR_URL;//增加一个默认头像
		if(StringUtils.isNotBlank(wxUserInfoResult.getHeadimgurl())){
			headImgUrl = wxUserInfoResult.getHeadimgurl();
		}
		webUser.setHeadImgUrl(headImgUrl);
		webUser.setSex(wxUserInfoResult.getSex());
		webUser.setCreateTime(new Date());
		return webUser;
	}
	
	public WxMpOauthService getWxMpOauthService() {
		return wxMpOauthService;
	}


	public void setWxMpOauthService(WxMpOauthService wxMpOauthService) {
		this.wxMpOauthService = wxMpOauthService;
	}


	public IWxWebUserService getWxWebUserService() {
		return wxWebUserService;
	}


	public void setWxWebUserService(IWxWebUserService wxWebUserService) {
		this.wxWebUserService = wxWebUserService;
	}
	
	
}
