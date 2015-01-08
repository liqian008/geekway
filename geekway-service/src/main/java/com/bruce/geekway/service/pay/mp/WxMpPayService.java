package com.bruce.geekway.service.pay.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.model.wx.pay.WxOrderQueryRequest;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.mp.WxBaseService;
import com.bruce.geekway.utils.HttpUtil;

/**
 * 微信支付service(mp包下的service均为对weixin api的封装)
 * 
 * @author liqian
 * 
 */
@Service
public class WxMpPayService extends WxBaseService {
	
	
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;

	/**
	 * 查询订单状态
	 * @param orderData  订单的json串
	 * @return
	 */
	public WxJsonResult queryOrder(WxOrderQueryRequest orderRequest) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = buildAccessTokenParams(accessToken);
		
		String queryJson = JsonUtil.gson.toJson(orderRequest);
		
		//提交查询请求至微信
		String deliverResultStr = HttpUtil.postRequest(ConstWeixin.WX_PAY_QUERY_ORDER_API, params, queryJson);
		
		WxJsonResult wxpayDeliverResult = JsonUtil.gson.fromJson(deliverResultStr, WxJsonResult.class);
		if(wxpayDeliverResult!=null && wxpayDeliverResult.getErrcode()==0){//订单查询成功
			return wxpayDeliverResult;
		}
		return null;
	}
	
	/**
	 * 发货后通知微信
	 * TODO 修改参数对象
	 * @return
	 */
	public WxJsonResult deliverNotify(WxDeliverInfo deliverInfo) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = buildAccessTokenParams(accessToken);
		
		String postInfoStr = JsonUtil.gson.toJson(deliverInfo);
		
		//提交发货请求至微信
		String deliverResultStr = HttpUtil.postRequest(ConstWeixin.WX_PAY_DELIVER_NOTIFY_API, params, postInfoStr);
		
		WxJsonResult wxpayDeliverResult = JsonUtil.gson.fromJson(deliverResultStr, WxJsonResult.class);
		if(wxpayDeliverResult!=null && wxpayDeliverResult.getErrcode()==0){//发货操作成功
			return wxpayDeliverResult;
		}
		return null;
	}

	/**
	 * 维权处理完毕后，通知微信
	 * @return
	 */
	public WxJsonResult dealComplaint(String openId, String feedbackId) {
		
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = buildAccessTokenParams(accessToken);
		params.put("openid", openId);
		params.put("feedbackid", feedbackId);
		
		//发送至微信
		String complaintResultStr = HttpUtil.postRequest(ConstWeixin.WX_PAY_COMPLAINT_DEAL_API, params, null);
		WxJsonResult wxpayComplaintResult = JsonUtil.gson.fromJson(complaintResultStr, WxJsonResult.class);
		if(wxpayComplaintResult!=null && wxpayComplaintResult.getErrcode()==0){//维权处理成功
			return wxpayComplaintResult;
		}
		return null;
	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

}
