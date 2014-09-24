package com.bruce.geekway.model.wx.pay;

/**
 * 微信共享地址对象，下订单时构造
 * 
 * @author liqian
 * 
 */
public class WxOrderAddressJsObj {

	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String scope = "jsapi_address";
	private String signType = "SHA1";
	private String addrSign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAddrSign() {
		return addrSign;
	}

	public void setAddrSign(String addrSign) {
		this.addrSign = addrSign;
	}

}
