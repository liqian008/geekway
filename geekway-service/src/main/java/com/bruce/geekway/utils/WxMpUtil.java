package com.bruce.geekway.utils;


public class WxMpUtil {

	public static final String APPID = ConfigUtil.getString("weixinmp_appid");

	public static final String SECRET = ConfigUtil.getString("weixinmp_appsecret");

	public static final String REDIRECT_URI = ConfigUtil.getString("weixinmp_oauth_redirect_url");

	public static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=" + REDIRECT_URI
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";


}
