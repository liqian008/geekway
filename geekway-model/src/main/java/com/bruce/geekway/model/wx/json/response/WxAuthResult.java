package com.bruce.geekway.model.wx.json.response;

public class WxAuthResult extends WxJsonResult {

	private String access_token;
	private long expires_in;
	
	/* 自己增加的access的 expires time，基本等同与expires_in + 7200s*/
	private long expiresTime;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
