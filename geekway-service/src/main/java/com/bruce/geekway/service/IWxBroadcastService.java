package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.WxBroadcastCriteria;

public interface IWxBroadcastService extends IFoundationPagingService<WxBroadcast, Integer, WxBroadcastCriteria> {
	
	/**
	 * 群发消息完毕的回调通知
	 * @param msgID
	 * @param totalCount
	 * @param filterCount
	 * @param sentCount
	 * @param errorCount
	 * @return
	 */
	public int broadcastNofify(String msgID, int totalCount, int filterCount, int sentCount, int errorCount);
	
}