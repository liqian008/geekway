package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessageCriteria;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;

public interface IWxHistoryMessageService extends IFoundationPagingService<WxHistoryMessage, Integer, WxHistoryMessageCriteria> {
	
	/**
	 * 记录用户发送来的消息
	 * @return
	 */
	public int logRequestMessage(BaseRequest requestMessage, String fullMessage);
	
	/**
	 * 记录回复给用户的消息
	 * @return
	 */
	public int logResponseMessage(BaseResponse responseMessage, String fullMessage);
	
}