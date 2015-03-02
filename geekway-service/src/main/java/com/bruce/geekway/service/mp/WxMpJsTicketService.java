package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxJsTicketResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.utils.HttpUtil;

/**
 * 微信的jsTicketService (mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
public class WxMpJsTicketService extends WxBaseService {
	
	/*微信MP中请求js_ticket网络请求的时间预留余量，单位秒*/
	private static final int JS_TICKET_REQUEST_TIME = 200; 
	
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;
	
//	/**
//	 * 获取jsTicket对象
//	 * @return
//	 */
//	public synchronized WxJsTicketResult getMpJsTicket() {
//			//通过网络获取新accessToken
//		String accessToken = wxAccessTokenService.getCachedAccessToken();
//		Map<String, String> params = buildAccessTokenParams(accessToken);
//		params.put("access_token", "client_credential");
//		params.put("type", "jsapi");
//		
//		String jsTicketResultStr = HttpUtil.getRequest(ConstWeixin.WX_JS_TICKET_API, params);
//		WxJsTicketResult wxJsTicketRes = JsonUtil.gson.fromJson(jsTicketResultStr, WxJsTicketResult.class);
//		if(wxJsTicketRes!=null && wxJsTicketRes.getErrcode()==0){//正常的响应结果
//			long expireTime = System.currentTimeMillis() + (wxJsTicketRes.getExpires_in() - JS_TICKET_REQUEST_TIME) * 1000;
//			wxJsTicketRes.setExpiresTime(expireTime);
//			return wxJsTicketRes;
//		}else{
//			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
//		}
//	}
	
	/**
	 * 获取jsTicket对象
	 * @return
	 */
	public synchronized WxJsTicketResult getMpJsTicket(String accessToken) {
		Map<String, String> params = buildAccessTokenParams(accessToken);
		params.put("type", "jsapi");
		
		String jsTicketResultStr = HttpUtil.getRequest(ConstWeixin.WX_JS_TICKET_API, params);
		WxJsTicketResult wxJsTicketRes = JsonUtil.gson.fromJson(jsTicketResultStr, WxJsTicketResult.class);
		if(wxJsTicketRes!=null && wxJsTicketRes.getErrcode()==0){//正常的响应结果
			long expireTime = System.currentTimeMillis() + (wxJsTicketRes.getExpires_in() - JS_TICKET_REQUEST_TIME) * 1000;
			wxJsTicketRes.setExpiresTime(expireTime);
			return wxJsTicketRes;
		}else{
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取jsTicket对象
	 * @return
	 */
	public synchronized WxJsTicketResult getMpJsTicket() {
		String accessToken;
		try {
			accessToken = wxAccessTokenService.getCachedAccessToken();
			return getMpJsTicket(accessToken);
		} catch (CachedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
