package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessageCriteria;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;

public interface IWxHistoryMessageService extends IFoundationPagingService<WxHistoryMessage, Integer, WxHistoryMessageCriteria> {
	
	
	
	
	/**
	 * 记录用户发送来的微信消息
	 * @return
	 */
	public int logRequestMessage(BaseRequest requestMessage, String fullMessage);
	
	/**
	 * 记录自动回复给用户的微信消息
	 * @return
	 */
	public int logResponseMessage(BaseResponse responseMessage, String fullMessage);
	
	/**
	 * 记录客服回复给用户的微信消息
	 * @return
	 */
	public int logCustomReplyMessage(CustomMessage customMessage);
	
	
	/**
	 * 获取用户发送最后一条消息（通常用于判断是否在48小时内，从而进行客服接口的回复）
	 * @param userOpenId
	 * @return
	 */
	public WxHistoryMessage queryUserLastestMessage(String userOpenId);
	
}