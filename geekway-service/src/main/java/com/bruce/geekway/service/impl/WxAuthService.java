package com.bruce.geekway.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.WxAuthReq;
import com.bruce.geekway.model.wx.json.response.WxAuthResult;
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
		
		String authResult = WxUtil.sendGetRequest("", params);
		WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResult, WxAuthResult.class);
		return wxAuthRes;
	}

	public boolean validateAuth(String signature, String timestamp, String nonce, String echostr) {
		WxAuthReq authReq = new WxAuthReq();
		authReq.setSignature(signature);
		authReq.setTimestamp(timestamp);
		authReq.setNonce(nonce);
		authReq.setEchostr(echostr);

		String excepted = hash(getStringToHash(timestamp, nonce, ""));

		if (signature == null || !signature.equals(excepted)) {
			log.error("Authentication failed! excepted echostr ->" + excepted);
			log.error("actual ->" + signature);
			return false;
		}
		return true;
	}

	private static String getStringToHash(String timestamp, String nonce, String token) {
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(token);

		String result = "";
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			result += list.get(i);
		}
		return result;
	}

	private static String hash(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}


}
