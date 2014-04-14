package com.bruce.geekway.service.mp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxUtil;

@Service
public class WxAuthService {
	
	private static final Logger log = LoggerFactory.getLogger(WxAuthService.class);
	
	public WxAuthResult getAccessToken(String appid, String appsecret) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", appsecret);
		
		String authResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_access_token_url"), params);
		WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResult, WxAuthResult.class);
		return wxAuthRes;
	}

}
