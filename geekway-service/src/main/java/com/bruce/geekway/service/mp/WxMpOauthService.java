package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruce.foundation.util.EmojiUtil;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * OauthService (mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
@Service
public class WxMpOauthService {
	
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
		if(wxOauthTokenResult!=null && wxOauthTokenResult.getErrcode()==0){//成功
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
	public WxUserInfoResult getOAuthUserinfo(String oauthAccessToken, String openid) {
		Map<String, String> params = WxHttpUtil.buildParams();
		params.put("access_token", oauthAccessToken);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		
		String userinfoResultStr = WxHttpUtil.getRequest(ConstWeixin.WX_OAUTH_USER_INFO_API, params); 
		//过滤emoji字符
		String emojiFilterResult = EmojiUtil.filterEmoji(userinfoResultStr);
		
		WxUserInfoResult userinfoResult = JsonUtil.gson.fromJson(emojiFilterResult, WxUserInfoResult.class);
		if(userinfoResult!=null && userinfoResult.getErrcode()==0){//成功
			return userinfoResult;
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		WxOauthTokenResult wxOauthTokenResult = JsonUtil.gson.fromJson("{\"access_token\":\"OezXcEiiBSKSxW0eoylIeNBzZXE3G7p6CKBs5AoNdMl7k_pRbM-5NE1JBbXo6zP3CZDqkkvJnkSPLXBsW6dhRZp57xqmnwR35_OHqCQ9Rs2RlrKtHSA4mKCQZ4i9g9PCLQXuhlIGqQmW8nkoMYTj0A\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeNBzZXE3G7p6CKBs5AoNdMl7k_pRbM-5NE1JBbXo6zP3R8rZXQHuE-atqESfC2K-c0EUrP5JlDvLagoEqbCdNoe0KCirUssq3jF6qa8A9_1nRFXUXMwGsMwRrpzh6A4Aeg\",\"openid\":\"oxGeHjg87dS82dsp4iP4SE1iVujA\",\"scope\":\"snsapi_base\"}", WxOauthTokenResult.class);
		System.out.println(wxOauthTokenResult);
		
	}
}
