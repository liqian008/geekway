package com.bruce.geekway.model.wx.request;


/**
 * 扫描带参数二维码事件 的请求<br>
 * MsgId 字段无效
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class ScanEventRequest extends EventRequest {

	private static final long serialVersionUID = 1L;

	/** 事件KEY值，qrscene_为前缀，后面为二维码的参数值 */
	private String eventKey;

	/** 二维码的ticket，可用来换取二维码图片 */
	private String ticket;

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

	@Override
	public String toString() {
		return "ScanEventRequest [Event=" + event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
				+ ", MsgId=" + msgId + ", EventKey=" + eventKey + ", Ticket=" + ticket + "]";
	}

}
