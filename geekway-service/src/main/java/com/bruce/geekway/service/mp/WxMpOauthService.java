package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;
import com.bruce.geekway.utils.WxMpUtil;

@Service
public class WxMpOauthService extends WxBaseService {
	
	private static final String WX_OAUTH_ACCESS_TOKEN_API = ConfigUtil.getString("weixinmp_oauth_accesstoken_url");
	
	private static final String WX_OAUTH_USER_INFO_API = ConfigUtil.getString("weixinmp_oauth_userinfo_url");
	
//	protected static final String REDIRECT_URI = ConfigUtil.getString("weixinmp_oauth_redirect_url");
//	
//	protected static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	
//	@Autowired
//	private WxMpTokenService mpTokenService;
	
//	public String getAuthorizeUrl(){
//		return AUTHORIZE_URL;
//	}
	
	
	/**
	 * 根据code换取oauthAccessToken
	 * @param code
	 * @return
	 */
	public WxOauthTokenResult getOauthAccessToken(String code) {
		
		Map<String, String> params = WxHttpUtil.buildParams();
		params.put("appid", WxMpUtil.APPID);
		params.put("secret",  WxMpUtil.SECRET);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		String oauthResult = WxHttpUtil.getRequest(WX_OAUTH_ACCESS_TOKEN_API, params);
		
		WxOauthTokenResult wxOauthTokenResult = JsonUtil.gson.fromJson(oauthResult, WxOauthTokenResult.class);
		return wxOauthTokenResult;
	}
	
	/**
	 * userAccessToken换取userinfo
	 * @param oauthAccessToken
	 * @param openid
	 * @return
	 */
	public WxUserInfoResult getOauthUserinfo(String oauthAccessToken, String openid) {
		Map<String, String> params = WxHttpUtil.buildParams();
		params.put("access_token", oauthAccessToken);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		
		String userinfoResultStr = WxHttpUtil.getRequest(WX_OAUTH_USER_INFO_API, params); 
		
		WxUserInfoResult userinfoResult = JsonUtil.gson.fromJson(userinfoResultStr, WxUserInfoResult.class);
		return userinfoResult;
	}
}
