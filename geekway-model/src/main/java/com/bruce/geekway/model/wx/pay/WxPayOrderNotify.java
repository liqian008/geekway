package com.bruce.geekway.model.wx.pay;

import java.util.Date;

import com.bruce.geekway.model.WxPayOrderInfo;

/**
 * 微信发来的订单信息
 * 
 * @author liqian
 * 
 */
public class WxPayOrderNotify {

	// 签名类型,取值:MD5、RSA,默 认:MD5
	private String sign_type;
	// 字符集
	private String input_charset;
	// 签名
	private String sign;

	// 1-即时到账 其他保留
	private String trade_mode;
	// 支付结果: 0—成功 其他保留
	private String trade_state;
	// 合作商id
	private String partner;
	// 银行类型,在微信中使用 WX
	private String bank_type;
	// 银行订单号
	private String bank_billno;
	// 支付金额，单位分
	private int total_fee;
	// 现金支付币种，目前只支持rmb
	private int fee_type;
	// 128长度，支付结果通知 id,对于某些特定商户,只返回通知id,要求商户据此查询交易结果
	private String notify_id;
	// 交易号,28 位长的数值,其中前 10 位为商户号,之后 8 位为订单产生 的日期,如 20090415,最后 10 位 是流水号。
	private String transaction_id;
	// 商户订单号, string32, 商户系统的订单号,与请求一致。
	private String out_trade_no;
	// 商户数据包,原样返回,空参数不 传递
	private String attach;
	// 支付完成时间,格式 为 yyyyMMddhhmmss,如 2009 年 12 月27日9点10分10秒表示 为 20091227091010
	// 。时区为 GMT+8 beijing
	private String time_end;
	// 物流费用,单位分,默认 0。如果 有值,必须保证 transport_fee + product_fee = total_fee
	private int transport_fee;
	// 物品费用,单位分。如果有值,必 证保须transport_fee + product_fee=total_fee
	private int product_fee;
	// 折扣价格,单位分,如果有值,通 知的total_fee + discount = 请求 的 total_fee
	private int discount;
	
	private String is_split;
	private String is_refund;
	//查询结果状态码,0 表明成功,其他表明错误;
	private String ret_code;
	//查询结果出错信息
	private String ret_msg;
	//换算成人民币之后的总金额,单位为分,一般看 total_fee 即可
	private String rmb_total_fee;



	// 用户openid
	private String openId;
	private String appId;
	// 用户是否关注过
	private int isSubscribe;
	// 时间戳
	private long timestamp;
	// 随机字符串;字段来源:商户生成的随机字符串;取值范 围:长度为 32 个字符以下。由商户生成后传入。取值范围:32 字符 以下
	private String nonceStr;
	// 签名
	private String appSignature;
	// 签名方法
	private String signMethod;

	public static WxPayOrderInfo convert2WxPayOrderInfo(WxPayOrderNotify wxOrderInfo) {
		WxPayOrderInfo wxPayOrderInfo = null;
		if (wxOrderInfo != null) {
			wxPayOrderInfo = new WxPayOrderInfo();

			wxPayOrderInfo.setBankType(wxOrderInfo.bank_type);
			wxPayOrderInfo.setBankBillno(wxOrderInfo.bank_billno);
			wxPayOrderInfo.setFeeType(wxOrderInfo.fee_type);

			wxPayOrderInfo.setTotalFee(wxOrderInfo.total_fee);
			wxPayOrderInfo.setProductFee(wxOrderInfo.product_fee);
			wxPayOrderInfo.setDiscount(wxOrderInfo.discount);
			wxPayOrderInfo.setTransportFee(wxOrderInfo.transport_fee);

			wxPayOrderInfo.setAttach(wxOrderInfo.attach);
			wxPayOrderInfo.setNotifyId(wxOrderInfo.notify_id);
			wxPayOrderInfo.setTransactionId(wxOrderInfo.transaction_id);
			wxPayOrderInfo.setOutTradeNo(wxOrderInfo.out_trade_no);
			
			wxPayOrderInfo.setOpenId(wxOrderInfo.openId);

			Date timeEnd = new Date();// TODO time_end
			wxPayOrderInfo.setTimeEnd(timeEnd);
			wxPayOrderInfo.setCreateTime(new Date());
		}
		return wxPayOrderInfo;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTrade_mode() {
		return trade_mode;
	}

	public void setTrade_mode(String trade_mode) {
		this.trade_mode = trade_mode;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getBank_billno() {
		return bank_billno;
	}

	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getFee_type() {
		return fee_type;
	}

	public void setFee_type(int fee_type) {
		this.fee_type = fee_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public int getTransport_fee() {
		return transport_fee;
	}

	public void setTransport_fee(int transport_fee) {
		this.transport_fee = transport_fee;
	}

	public int getProduct_fee() {
		return product_fee;
	}

	public void setProduct_fee(int product_fee) {
		this.product_fee = product_fee;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(int isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getAppSignature() {
		return appSignature;
	}

	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

}
