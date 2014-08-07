package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 客服消息service，主要用于回复客服消息
 * @author liqian
 *
 */
@Service
public class WxCustomReplyService extends WxBaseService {

	private static final String WX_REPLY_MESSAGE_API = ConfigUtil.getString("weixinmp_reply_message_url");
	
	@Autowired
	private WxMpTokenService mpTokenService;
	@Autowired
	private IWxHistoryMessageService wxHistoryMessageService;

	/**
	 * 客服回复文本消息
	 * 
	 * @param textMessage
	 * @return
	 */
	public WxJsonResult replyTextMessage(TextMessage textMessage) {
		return replyMessage(textMessage);
	}

	/**
	 * 客服回复图文消息
	 * 
	 * @param textMessage
	 * @return
	 */
	public WxJsonResult replyNewsMessage(NewsMessage newsMessage) {
		return replyMessage(newsMessage);
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @return
	 */
	private WxJsonResult replyMessage(CustomMessage customMessage) {
		if (customMessage != null) {
			//TODO 48小时间隔检查
			
			String accessToken = mpTokenService.getMpAccessToken();
			Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
			String customMessageStr = JsonUtil.gson.toJson(customMessage);
			// 回复客服消息
			String sendResultStr = WxHttpUtil.postRequest(WX_REPLY_MESSAGE_API, params, customMessageStr);
			WxJsonResult wxSendResult = JsonUtil.gson.fromJson(sendResultStr, WxJsonResult.class);
			if (wxSendResult != null && wxSendResult.getErrcode() != null && wxSendResult.getErrcode() == 0) {//消息回复成功
				//回复成功，将回复消息写入历史记录
				int result = wxHistoryMessageService.logCustomReplyMessage(customMessage, customMessageStr);
				return wxSendResult;
			}
		}
		return null;
	}

	public WxMpTokenService getMpTokenService() {
		return mpTokenService;
	}

	public void setMpTokenService(WxMpTokenService mpTokenService) {
		this.mpTokenService = mpTokenService;
	}
}
