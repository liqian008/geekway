package com.bruce.geekway.utils;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.bruce.geekway.constants.ConstWeixin;


public class WxMpUtil {


	public static final String REDIRECT_URI = ConfigUtil.getString("weixinmp_oauth_redirect_url");

	public static final String AUTHORIZE_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + REDIRECT_URI
			+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	public static final String AUTHORIZE_USERINFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + REDIRECT_URI
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	
	/**
	 * redirectUrl需要urlEncode
	 * @param scopeType
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	public static String buildWeixinOauthUrl(int scopeType, String redirectUrl, String state){
		//获取scope
		String scope = getScopeType(scopeType);
		
		String encodedUrl = URLEncoder.encode(redirectUrl);
		
		String oauthUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + encodedUrl+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
		return oauthUrl;
	}
	
	

	/**
	 * 构造代理的回调地址，但其中参数不同，从而跳转的不同的地址上
	 * @param scopeType
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	public static String buildWeixinOauthProxyUrl(int scopeType, String proxyUrl, String state){
		//获取scope
		String scope = getScopeType(scopeType);
		
		String redirectUrl = "http://wxpay.geekway.com.cn/oauthRedirect";
		if(StringUtils.isNotBlank(proxyUrl)){
			redirectUrl = UrlUtil.addParameter(redirectUrl, "proxyUrl", proxyUrl);
		} 
		String encodedUrl = URLEncoder.encode(redirectUrl);
		
		String oauthUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=1&redirect_uri=" + encodedUrl+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
		return oauthUrl;
	}


	/**
	 * 
	 * @param scopeType
	 * @return
	 */
	private static String getScopeType(int scopeType) {
		String scope = "snsapi_base";
		if(scopeType==1){
			scope = "snsapi_userinfo";
		}
		return scope;
	}
	
	
	public static void main(String[] args) {
		System.out.println(buildWeixinOauthProxyUrl(1, "http://www.baidu.com", null));
	}
}
