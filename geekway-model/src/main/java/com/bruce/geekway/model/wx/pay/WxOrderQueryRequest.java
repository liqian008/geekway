package com.bruce.geekway.model.wx.pay;

import com.google.gson.annotations.SerializedName;

/**
 * 提交微信订单查询的对象（需要序列化为json）
 * 
 * @author liqian
 * 
 */
public class WxOrderQueryRequest {

	private String appid;
	// 使用别名
	@SerializedName("package")
	private String packageValue;
	private String timestamp;
	private String app_signature;
	private String sign_method;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getApp_signature() {
		return app_signature;
	}

	public void setApp_signature(String app_signature) {
		this.app_signature = app_signature;
	}

	public String getSign_method() {
		return sign_method;
	}

	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}

	public String getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}
}
