package com.bruce.geekway.service.mp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

@Service
public class WxMpTokenService {
	
private static final String WX_ACCESS_TOKEN_API = ConfigUtil.getString("weixinmp_access_token_url");

	/*微信MP中请求accessToken网络请求的时间预留余量，单位秒*/
	private static final int ACCESS_TOKEN_REQUEST_TIME = 200; 
	
	/**
	 * 获取accessToken对象
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public synchronized WxAuthResult getMpAccessToken() {
			//通过网络获取新accessToken
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", ConfigUtil.getString("weixinmp_appid"));
		params.put("secret",  ConfigUtil.getString("weixinmp_appsecret"));
		
		String authResultStr = WxHttpUtil.getRequest(WX_ACCESS_TOKEN_API, params);
		WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResultStr, WxAuthResult.class);
		if(wxAuthRes!=null && wxAuthRes.getErrcode()==null){//正常的响应结果
//			WxAuthResult authResult = wxAuthRes;
			long expireTime = System.currentTimeMillis() + (wxAuthRes.getExpires_in() - ACCESS_TOKEN_REQUEST_TIME) * 1000;
			wxAuthRes.setExpiresTime(expireTime);
			return wxAuthRes;
		}else{
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
	}

}
