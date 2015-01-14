package com.bruce.geekway.model.wx.json.response;

public class WxJsTicketResult extends WxJsonResult {

	private String js_ticket;
	private long expires_in;

	/* 自己增加的access的 expires time，基本等同与expires_in + 7200s */
	private long expiresTime;

	public String getJs_ticket() {
		return js_ticket;
	}

	public void setJs_ticket(String js_ticket) {
		this.js_ticket = js_ticket;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public long getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}

}
