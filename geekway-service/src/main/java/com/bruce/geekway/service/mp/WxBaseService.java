package com.bruce.geekway.service.mp;

import java.util.HashMap;
import java.util.Map;

import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxUtil;

public class WxBaseService {
	
	public WxAuthResult getAccessToken(String appid, String appsecret) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", appsecret);
		
		String authResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_access_token_url"), params);
		WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResult, WxAuthResult.class);
		return wxAuthRes;
	}

	
	public Map<String, String> getAccessTokenParams(String accessToken) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("access_token", accessToken);
		return result;
	}

}
