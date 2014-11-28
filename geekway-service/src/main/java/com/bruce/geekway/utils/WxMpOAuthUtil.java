package com.bruce.geekway.utils;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bruce.foundation.util.UrlUtil;
import com.bruce.geekway.constants.ConstWeixin;


public class WxMpOAuthUtil {

	private static final Logger logger = LoggerFactory.getLogger(WxMpOAuthUtil.class);
	
	public static final String REDIRECT_URI = ConfigUtil.getString("weixinmp_oauth_redirect_url");

	public static final String AUTHORIZE_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + REDIRECT_URI
			+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	public static final String AUTHORIZE_USERINFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + REDIRECT_URI
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	
//	/**
//	 * redirectUrl需要urlEncode
//	 * @param scopeType
//	 * @param redirectUrl
//	 * @param state
//	 * @return
//	 */
//	public static String buildWeixinOauthUrl(int scopeType, String redirectUrl, String state){
//		//获取scope
//		String scope = getScopeType(scopeType);
//		
//		String encodedUrl = URLEncoder.encode(redirectUrl);
//		
//		String oauthUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + encodedUrl+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
//		return oauthUrl;
//	}
	

	/**
	 * 构造代理的回调地址，但其中参数不同，从而跳转的不同的地址上
	 * @param scopeType
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	public static String buildWeixinOauthProxyUrl(String scope, String proxyUrl, String state){
		if(!"snsapi_userinfo".equals(scope)){
			scope = "snsapi_base";
		}
		
		String redirectUrl = ConstWeixin.WX_OAUTH_REDIRECT_PROXY_URL;
		if(StringUtils.isNotBlank(proxyUrl)){
			redirectUrl = UrlUtil.addParameter(redirectUrl, "proxyUrl", proxyUrl);
		}
		String encodedUrl = URLEncoder.encode(redirectUrl);
		
		String oauthUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConstWeixin.WX_APP_ID+"&redirect_uri=" + encodedUrl+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
		if(logger.isDebugEnabled()){
			logger.debug("redirectUrl: "+redirectUrl);
			logger.debug("oauthUrl: "+oauthUrl);
		}
		
		return oauthUrl;
	}

//
//	/**
//	 * 
//	 * @param scopeType
//	 * @return
//	 */
//	private static String getScopeType(int scopeType) {
//		String scope = "snsapi_base";
//		if(scopeType==1){
//			scope = "snsapi_userinfo";
//		}
//		return scope;
//	}
	
	
	public static void main(String[] args) {
		System.out.println(buildWeixinOauthProxyUrl("", "http://www.baidu.com", null));
	}
}
