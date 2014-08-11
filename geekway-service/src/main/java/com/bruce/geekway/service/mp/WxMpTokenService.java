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

	//	/*微信MP中accessToken的超时time，单位秒*/
//	private static final int ACCESS_TOKEN_MP_EXPIRES_IN = 7200; 
	/*微信MP中请求accessToken网络请求的TimeOut，单位秒*/
	private static final int ACCESS_TOKEN_REQUEST_TIME = 200; 
//	/*最终确定的Expires_IN*/
//	private static final int ACCESS_TOKEN_EXPIRES_TIME = ACCESS_TOKEN_MP_EXPIRES_IN - ACCESS_TOKEN_REQUEST_TIME;
	
	private WxAuthResult authResult = null;
	
	/**
	 * 获取accessToken对象
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	private WxAuthResult getAccessToken() {
		//如accessToken为null或accessToken过期，需要重取accessToken
		if(authResult==null||authResult.getExpiresTime()<=System.currentTimeMillis()){
			//需要通过网络获取新accessToken
			Map<String, String> params = new HashMap<String, String>();
			params.put("grant_type", "client_credential");
			params.put("appid", ConfigUtil.getString("weixinmp_appid"));
			params.put("secret",  ConfigUtil.getString("weixinmp_appsecret"));
			
			String authResultStr = WxHttpUtil.getRequest(WX_ACCESS_TOKEN_API, params);
			WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResultStr, WxAuthResult.class);
			if(wxAuthRes!=null && wxAuthRes.getErrcode()==null){//正常的响应结果
				authResult = wxAuthRes;
				long expireTime = System.currentTimeMillis() + (wxAuthRes.getExpires_in() - ACCESS_TOKEN_REQUEST_TIME) * 1000;
				authResult.setExpiresTime(expireTime);
				return authResult;
			}else{
				throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
			}
		}else{
			return authResult;
		}
	}

	
	public synchronized String getMpAccessToken() {
		WxAuthResult authResult = getAccessToken();
		if(authResult!=null){
			return authResult.getAccess_token();
		}
		return null;
	}
	
	
//	protected WxAuthResult getWxAccessToken() {
//		WxAuthResult authResult = getAccessToken(ConfigUtil.getString("weixinmp_appid"), ConfigUtil.getString("weixinmp_appsecret"));
//		return authResult;
//	}
	
}
