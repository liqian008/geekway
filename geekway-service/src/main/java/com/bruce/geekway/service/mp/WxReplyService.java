package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;
import com.bruce.geekway.utils.WxHttpUtil;
//import org.apache.http.Consts;
//import org.apache.http.entity.StringEntity;

@Service
public class WxReplyService extends WxBaseService {

	private static final String WX_REPLY_MESSAGE_API = ConfigUtil.getString("weixinmp_reply_message_url");
	
	@Autowired
	private WxMpTokenService mpTokenService;

	/**
	 * 发送文本消息
	 * 
	 * @param textMessage
	 * @return
	 */
	public WxJsonResult replyTextMessage(TextMessage textMessage) {
		return replyMessage(JsonUtil.gson.toJson(textMessage));
	}

	/**
	 * 发送图文消息
	 * 
	 * @param textMessage
	 * @return
	 */
	public WxJsonResult replyNewsMessage(NewsMessage newsMessage) {
		return replyMessage(JsonUtil.gson.toJson(newsMessage));
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @return
	 */
	private WxJsonResult replyMessage(String message) {
		if (message != null) {
			// WxAuthResult authResult = getWxAccessToken();
			// if(authResult!=null && authResult.getErrcode()==null){
			String accessToken = mpTokenService.getMpAccessToken();
			Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);

			// 回复消息
			String sendResultStr = WxHttpUtil.postRequest(WX_REPLY_MESSAGE_API, params, message);
			WxJsonResult wxSendResult = JsonUtil.gson.fromJson(sendResultStr, WxJsonResult.class);
			if (wxSendResult != null && wxSendResult.getErrcode() != null && wxSendResult.getErrcode() == 0) {// 自定义菜单创建成功
				return wxSendResult;
			}
			// }
		}
		return null;
	}

}
