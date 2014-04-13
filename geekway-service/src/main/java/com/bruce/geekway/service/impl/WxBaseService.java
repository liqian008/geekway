package com.bruce.geekway.service.impl;
import java.util.HashMap;
import java.util.Map;

public class WxBaseService {
	
	public Map<String, String> getAccessTokenParams(String accessToken) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("access_token", accessToken);
		return result;
	}

}
