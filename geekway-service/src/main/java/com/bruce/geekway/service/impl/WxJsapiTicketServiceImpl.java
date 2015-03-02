package com.bruce.geekway.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.model.wx.json.response.WxJsTicketResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.IWxJsapiTicketService;
import com.bruce.geekway.service.mp.WxBaseService;
import com.bruce.geekway.service.mp.WxMpJsTicketService;

@Service
public class WxJsapiTicketServiceImpl extends WxBaseService implements IWxJsapiTicketService {
	
	/*微信MP中请求jsTicket网络请求的时间预留余量，单位秒*/
	private static final int JS_TICKET_REQUEST_TIME = 200;
	
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;
	@Autowired
	private WxMpJsTicketService wxMpJsTicketService;

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_MP_JSTICKET+"'")
	public String getCachedJsTicket() throws CachedException {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
//		Map<String, String> params = buildAccessTokenParams(accessToken);
//		params.put("type", "jsapi");
		
//		String jsapiTicketStr = HttpUtil.getRequest(ConstWeixin.WX_JS_TICKET_API, params);
//		if(StringUtils.isNotBlank(jsapiTicketStr)){
//			WxJsTicketResult wxJsTicketResult = JsonUtil.gson.fromJson(jsapiTicketStr, WxJsTicketResult.class);
//			if(wxJsTicketResult!=null && wxJsTicketResult.getErrcode()==0){//正常的响应结果
//				long expireTime = System.currentTimeMillis() + (wxJsTicketResult.getExpires_in() - JS_TICKET_REQUEST_TIME) * 1000;
//				wxJsTicketResult.setExpiresTime(expireTime);
//				return wxJsTicketResult.getTicket();
//			}
//		}
		WxJsTicketResult jsTicketResult = wxMpJsTicketService.getMpJsTicket(accessToken);
		if(jsTicketResult!=null){
			return jsTicketResult.getTicket();
		}else{
			throw new CachedException();
		}
	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}
	
	
	
}