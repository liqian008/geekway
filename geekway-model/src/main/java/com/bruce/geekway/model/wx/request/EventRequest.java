package com.bruce.geekway.model.wx.request;

import com.bruce.geekway.model.wx.WxEventTypeEnum;

/**
 * 被动事件请求（公共部分）
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class EventRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String eventKey;

	private String ticket;

	/** 事件类型 */
	public WxEventTypeEnum event;

	public WxEventTypeEnum getEvent() {
		return event;
	}

	public void setEvent(WxEventTypeEnum event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
