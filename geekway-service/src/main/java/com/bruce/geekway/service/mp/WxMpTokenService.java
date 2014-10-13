package com.bruce.geekway.service.mp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信的tokenService (mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
@Service
public class WxMpTokenService {
	

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
		params.put("appid", ConstWeixin.WX_APP_ID);
		params.put("secret",  ConstWeixin.WX_APP_SECRET_KEY);
		
		String authResultStr = WxHttpUtil.getRequest(ConstWeixin.WX_ACCESS_TOKEN_API, params);
		WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResultStr, WxAuthResult.class);
		if(wxAuthRes!=null && wxAuthRes.getErrcode()==0){//正常的响应结果
//			WxAuthResult authResult = wxAuthRes;
			long expireTime = System.currentTimeMillis() + (wxAuthRes.getExpires_in() - ACCESS_TOKEN_REQUEST_TIME) * 1000;
			wxAuthRes.setExpiresTime(expireTime);
			return wxAuthRes;
		}else{
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
	}

}
