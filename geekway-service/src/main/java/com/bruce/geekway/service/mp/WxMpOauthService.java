package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * OauthService (mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
@Service
public class WxMpOauthService extends WxBaseService {
	
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
		params.put("appid", ConstWeixin.WX_APP_ID);
		params.put("secret",  ConstWeixin.WX_APP_SECRET_KEY);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		String oauthResult = WxHttpUtil.getRequest(ConstWeixin.WX_OAUTH_ACCESS_TOKEN_API, params);
		
		WxOauthTokenResult wxOauthTokenResult = JsonUtil.gson.fromJson(oauthResult, WxOauthTokenResult.class);
		if(wxOauthTokenResult!=null && wxOauthTokenResult.getErrcode()!=null && wxOauthTokenResult.getErrcode()==0){//成功
			return wxOauthTokenResult;
		}
		return null;
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
		
		String userinfoResultStr = WxHttpUtil.getRequest(ConstWeixin.WX_OAUTH_USER_INFO_API, params); 
		
		WxUserInfoResult userinfoResult = JsonUtil.gson.fromJson(userinfoResultStr, WxUserInfoResult.class);
		if(userinfoResult!=null && userinfoResult.getErrcode()!=null && userinfoResult.getErrcode()==0){//成功
			return userinfoResult;
		}
		return null;
	}
}
