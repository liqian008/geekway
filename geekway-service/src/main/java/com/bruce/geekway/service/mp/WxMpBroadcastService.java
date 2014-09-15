package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.WxBroadcastTypeEnum;
import com.bruce.geekway.model.wx.json.WxBroadcastInfo;
import com.bruce.geekway.model.wx.json.response.WxBroadcastResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信群发service(mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
@Service
public class WxMpBroadcastService extends WxBaseService {

	
//	@Autowired
//	private WxMpTokenService mpTokenService;
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;
	
	/**
	 * 群发文本
	 * @param content
	 * @return
	 */
	public WxBroadcastResult broadcastText(String content) {
		return broadcastMessage(WxBroadcastTypeEnum.TEXT, content, null);
		
	}
	
	/**
	 * 群发多图文消息
	 * @param mediaId
	 * @return
	 */
	public WxBroadcastResult broadcastNews(String mediaId) {
		return broadcastMessage(WxBroadcastTypeEnum.MPNEWS, null, mediaId);
	}
	
	/**
	 * 群发图片
	 * @param data
	 * @return
	 */
	public WxBroadcastResult broadcastImage(String mediaId) {
		return broadcastMessage(WxBroadcastTypeEnum.IMAGE, null, mediaId);
	}
	
	/**
	 * 群发多媒体消息
	 * @param data
	 * @return
	 */
	private WxBroadcastResult broadcastMessage(WxBroadcastTypeEnum broadcastTypeEnum, String content, String mediaId) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		WxBroadcastInfo broadcastInfo = new WxBroadcastInfo(broadcastTypeEnum, content, mediaId);
		
		WxBroadcastInfo.FilterGroup filterGroup = new WxBroadcastInfo.FilterGroup();
		filterGroup.setGroup_id(0);
		broadcastInfo.setFilter(filterGroup);
		String broadcastResultStr = WxHttpUtil.postRequest(ConstWeixin.WX_BROADCAST_API, params,  JsonUtil.gson.toJson(broadcastInfo));
		WxBroadcastResult broadcastResult = JsonUtil.gson.fromJson(broadcastResultStr,  WxBroadcastResult.class);
		return broadcastResult;
	}
	
	
	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

}
