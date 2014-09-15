package com.bruce.geekway.service.pay.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.mp.WxBaseService;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信群发service
 * 
 * @author liqian
 * 
 */
@Service
public class WxMpPayService extends WxBaseService {
	// 发货通知api
	private static final String WX_DELIVER_NOTIFY_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	// 维权处理api
	private static final String WX_COMPLAINT_DEAL_API = ConfigUtil.getString("weixinmp_message_broadcast_url");

	@Autowired
	private IWxAccessTokenService wxAccessTokenService;

	
	
	/**
	 * 查询订单状态
	 * 
	 * @return
	 */
	public int query() {
		return 1;
	}
	
	/**
	 * 发货后通知微信
	 * 
	 * @return
	 */
	public int deliverNotify(WxDeliverInfo deliverInfo) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		
		
		String postInfoStr = JsonUtil.gson.toJson(deliverInfo);
		
		//发送至微信
		String sendResultStr = WxHttpUtil.postRequest(WX_DELIVER_NOTIFY_API, params, postInfoStr);
		
		
		return 1;
	}

	/**
	 * 维权处理完毕后，需要通知微信
	 * @return
	 */
	public int dealComplaint() {
		
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		//发送至微信
		String sendResultStr = WxHttpUtil.postRequest(WX_COMPLAINT_DEAL_API, params, "");
		
		
		return 1;
	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

}
