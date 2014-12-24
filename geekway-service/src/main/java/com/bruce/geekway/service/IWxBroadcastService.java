package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.WxBroadcastCriteria;
import com.bruce.geekway.model.wx.json.response.WxBroadcastResult;

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
	
	/**
	 * 广播文本
	 * @param materialId
	 * @return
	 */
	public WxBroadcastResult broadcastMaterialText(String content);
	
	
	/**
	 * 广播单图文
	 * @param materialId
	 * @return
	 */
	public WxBroadcastResult broadcastMaterialArticle(int materialId);
	
	/**
	 * 广播多图文
	 * @param materialId
	 * @return
	 */
	public WxBroadcastResult broadcastMaterialNews(int materialId);
	
	/**
	 * 广播图片消息
	 * @param materialId
	 * @return
	 */
	public WxBroadcastResult broadcastMaterialImage(int materialId);
	
	
}