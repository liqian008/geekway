package com.bruce.geekway.model.wx.pay;

/**
 * 发货信息对象
 * 
 * @author liqian
 * 
 */
public class WxDeliverInfo {

	public String appid;
	// 用户openid
	public String openid;
	// 交易transid
	public String transid;
	/* 第三方订单号 */
	public String out_trade_no;
	// 发货时间
	public String deliver_timestamp;
	// 发货状态，1为已发货，0为错误，需在deliver_msg中指明原因
	public String deliver_status;
	// 发货消息
	public String deliver_msg;
	/* 签名 */
	public String app_signature;
	/* 签名方法(不计入签名生成) */
	public String sign_method;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getDeliver_timestamp() {
		return deliver_timestamp;
	}

	public void setDeliver_timestamp(String deliver_timestamp) {
		this.deliver_timestamp = deliver_timestamp;
	}

	public String getDeliver_status() {
		return deliver_status;
	}

	public void setDeliver_status(String deliver_status) {
		this.deliver_status = deliver_status;
	}

	public String getDeliver_msg() {
		return deliver_msg;
	}

	public void setDeliver_msg(String deliver_msg) {
		this.deliver_msg = deliver_msg;
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

}
