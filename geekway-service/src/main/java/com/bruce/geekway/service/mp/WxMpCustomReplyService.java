package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.ImageMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.model.wx.message.VoiceMessage;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.utils.HttpUtil;

/**
 * 客服消息service，主要用于回复客服消息(mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
public class WxMpCustomReplyService extends WxBaseService {

	
	
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;
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
	 * @param newsMessage
	 * @return
	 */
	public WxJsonResult replyNewsMessage(NewsMessage newsMessage) {
		return replyMessage(newsMessage);
	}
	
	/**
	 * 客服回复图片消息
	 * 
	 * @param imageMessage
	 * @return
	 */
	public WxJsonResult replyImageMessage(ImageMessage imageMessage) { 
		return replyMessage(imageMessage); 
	}
	
	/**
	 * 客服回复语音消息
	 * 
	 * @param voiceMessage
	 * @return
	 */
	public WxJsonResult replyVoiceMessage(VoiceMessage voiceMessage) { 
		return replyMessage(voiceMessage); 
	}

	/**
	 * 回复消息
	 * @param message
	 * @return
	 */
	public WxJsonResult replyMessage(CustomMessage customMessage) {
		if (customMessage != null) {
			//TODO 48小时间隔检查
			try {
				String accessToken = wxAccessTokenService.getCachedAccessToken();
				Map<String, String> params = buildAccessTokenParams(accessToken);
				String customMessageStr = JsonUtil.gson.toJson(customMessage);

				// if(customMessage instanceof TextMessage){//文本消息
				// }else if(customMessage instanceof NewsMessage){//图文消息
				// }else if(customMessage instanceof ImageMessage){//图片消息
				// }else if(customMessage instanceof VoiceMessage){//语音消息
				// }

				// 回复客服消息
				String sendResultStr = HttpUtil.postRequest(ConstWeixin.WX_REPLY_MESSAGE_API, params, customMessageStr);

				WxJsonResult wxSendResult = JsonUtil.gson.fromJson(sendResultStr, WxJsonResult.class);
				if (wxSendResult != null && wxSendResult.getErrcode() == 0) {// 消息回复成功
					// 回复成功，将回复消息写入历史记录
					int result = wxHistoryMessageService.logCustomReplyMessage(customMessage);
					return wxSendResult;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

	public IWxHistoryMessageService getWxHistoryMessageService() {
		return wxHistoryMessageService;
	}

	public void setWxHistoryMessageService(
			IWxHistoryMessageService wxHistoryMessageService) {
		this.wxHistoryMessageService = wxHistoryMessageService;
	}
}
