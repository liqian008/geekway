package com.bruce.geekway.utils;

import java.net.URLEncoder;

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
		String scope = "snsapi_base";
		if(scopeType==1){
			scope = "snsapi_userinfo";
		}
		String encodedUrl = URLEncoder.encode(redirectUrl);
		
		String oauthUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConstWeixin.WX_APP_ID + "&redirect_uri=" + encodedUrl+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
		System.out.println("oauthUrl: "+ oauthUrl);
		return oauthUrl;
	}
	
}
