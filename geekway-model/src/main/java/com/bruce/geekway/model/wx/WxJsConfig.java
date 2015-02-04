package com.bruce.geekway.model.wx;

public class WxJsConfig {

	private boolean debug;
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;
	private String[] jsApiList;
	
	
	public WxJsConfig(boolean debug, String appId, String timestamp,
			String nonceStr, String signature) {
		super();
		this.debug = debug;
		this.appId = appId;
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.signature = signature;
	}
	
	public WxJsConfig(boolean debug, String appId, String timestamp,
			String nonceStr, String signature, String[] jsApiList) {
		super();
		this.debug = debug;
		this.appId = appId;
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.signature = signature;
		this.jsApiList = jsApiList;
	}
	
	

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String[] getJsApiList() {
		return jsApiList;
	}

	public void setJsApiList(String[] jsApiList) {
		this.jsApiList = jsApiList;
	}

}
