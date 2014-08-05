package com.bruce.geekway.service.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxBroadcastInfo;
import com.bruce.geekway.model.wx.json.response.WxBroadcastResult;

/**
 * 微信群发service
 * @author liqian
 *
 */
@Service
public class WxBroadcastService extends WxBaseService {

	@Autowired
	private WxMpTokenService mpTokenService;
	
	/**
	 * 群发多图文消息
	 * @param mediaId
	 * @return
	 */
	public WxBroadcastResult broadcastNews(WxBroadcastInfo broadcastInfo) {
		return null;
	}
	
	/**
	 * 群发文本
	 * @param content
	 * @return
	 */
	public WxBroadcastResult broadcastText(WxBroadcastInfo broadcastInfo) {
		return null;
	}
	
	
	/**
	 * 群发图片
	 * @param data
	 * @return
	 */
	public WxBroadcastResult broadcastImage(WxBroadcastInfo broadcastInfo) {
		return null;
	}
	
	
	public WxMpTokenService getMpTokenService() {
		return mpTokenService;
	}

	public void setMpTokenService(WxMpTokenService mpTokenService) {
		this.mpTokenService = mpTokenService;
	}

}