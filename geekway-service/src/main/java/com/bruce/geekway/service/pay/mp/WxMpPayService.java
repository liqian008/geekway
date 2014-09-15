package com.bruce.geekway.service.pay.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.mp.WxBaseService;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信群发service(mp包下的service均为对weixin api的封装)
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
	 * 
	 * @return
	 */
	public int query() {
		return 1;
	}
	
	/**
	 * 发货后通知微信
	 * TODO 修改参数对象
	 * @return
	 */
	public WxJsonResult deliverNotify(WxDeliverInfo deliverInfo) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String postInfoStr = JsonUtil.gson.toJson(deliverInfo);
		
		//提交发货请求至微信
		String deliverResultStr = WxHttpUtil.postRequest(ConstWeixin.WX_PAY_DELIVER_NOTIFY_API, params, postInfoStr);
		
		WxJsonResult wxpayDeliverResult = JsonUtil.gson.fromJson(deliverResultStr, WxJsonResult.class);
		if(wxpayDeliverResult!=null && wxpayDeliverResult.getErrcode()!=null && wxpayDeliverResult.getErrcode()==0){//自定义菜单创建成功
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
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		params.put("openid", openId);
		params.put("feedbackid", feedbackId);
		
		//发送至微信
		String complaintResultStr = WxHttpUtil.postRequest(ConstWeixin.WX_PAY_COMPLAINT_DEAL_API, params, null);
		WxJsonResult wxpayComplaintResult = JsonUtil.gson.fromJson(complaintResultStr, WxJsonResult.class);
		if(wxpayComplaintResult!=null && wxpayComplaintResult.getErrcode()!=null && wxpayComplaintResult.getErrcode()==0){//自定义菜单创建成功
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
