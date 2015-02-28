package com.bruce.geekway.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.model.wx.json.response.WxJsTicketResult;
import com.bruce.geekway.service.IWxJsapiTicketService;
import com.bruce.geekway.service.mp.WxBaseService;
import com.bruce.geekway.service.mp.WxMpJsTicketService;

@Service
public class WxJsapiTicketServiceImpl extends WxBaseService implements IWxJsapiTicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(WxJsapiTicketServiceImpl.class);
	

	@Autowired
	private WxMpJsTicketService wxMpJsTicketService;

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_MP_JSTICKET+"-'+#appId")
	//mpJsTicket-wxc2a0a71ba13664f6
	public String getCachedJsTicket(String appId, String secretKey) {
		logger.info("请求微信Mp获取jsTicket, [appId="+appId+"]");
		WxJsTicketResult wxJsTicketResult  = wxMpJsTicketService.getMpJsTicket(appId, secretKey);
		if(wxJsTicketResult!=null){//成功的响应
			String ticket = wxJsTicketResult.getTicket();
			return ticket;
		}
		return null;
	}
	
//	@Override
//	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_MP_JSTICKET+"-#accessToken'")
//	public String getCachedJsTicket(String accessToken) {
//		
//		WxJsTicketResult wxJsTicketResult  = wxMpJsTicketService.getMpJsTicket(accessToken);
//		if(wxJsTicketResult!=null){//成功的响应
//			String ticket = wxJsTicketResult.getTicket();
//			return ticket;
//		}
//		return null;
//	}

	public WxMpJsTicketService getWxMpJsTicketService() {
		return wxMpJsTicketService;
	}

	public void setWxMpJsTicketService(WxMpJsTicketService wxMpJsTicketService) {
		this.wxMpJsTicketService = wxMpJsTicketService;
	}
	
	
	
}