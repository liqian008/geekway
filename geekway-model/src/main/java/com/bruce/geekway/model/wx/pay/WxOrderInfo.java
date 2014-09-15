package com.bruce.geekway.model.wx.pay;

import java.util.Date;

import com.bruce.geekway.model.WxPayOrderInfo;

/**
 * 微信发来的订单信息
 * @author liqian
 *
 */
public class WxOrderInfo {

	//签名类型,取值:MD5、RSA,默 认:MD5
	public String sign_type;
	//字符集
	public String input_charset;
	//签名
	public String sign;
	//1-即时到账 其他保留
	public String trade_mode;
	//支付结果: 0—成功 其他保留
	public String trade_state;
	//合作商id
	public String partner;
	//银行类型,在微信中使用 WX
	public String bank_type;
	//银行订单号
	public String bank_billno;
	//支付金额，单位分
	public int total_fee;
	//现金支付币种，目前只支持rmb
	public int fee_type;
	//128长度，支付结果通知 id,对于某些特定商户,只返回通知id,要求商户据此查询交易结果
	public String notify_id;
	//交易号,28 位长的数值,其中前 10 位为商户号,之后 8 位为订单产生 的日期,如 20090415,最后 10 位 是流水号。
	public String transaction_id;
	//商户订单号, string32, 商户系统的订单号,与请求一致。
	public String out_trade_no;
	//商户数据包,原样返回,空参数不 传递
	public String attach;
	//支付完成时间,格式	为 yyyyMMddhhmmss,如 2009 年 12 月27日9点10分10秒表示 为 20091227091010 。时区为	GMT+8 beijing
	public String time_end;
	//物流费用,单位分,默认 0。如果 有值,必须保证	transport_fee + product_fee = total_fee
	public int transport_fee;
	//物品费用,单位分。如果有值,必 证保须transport_fee + product_fee=total_fee
	public int product_fee;
	//折扣价格,单位分,如果有值,通 知的total_fee + discount = 请求 的 total_fee
	public int discount;
	
	
	//用户openid
	public String openid;
	public String appId;
	//用户是否关注过
	public int isSubscribe;
	//时间戳
	public long timestamp;
	//随机字符串;字段来源:商户生成的随机字符串;取值范 围:长度为 32 个字符以下。由商户生成后传入。取值范围:32 字符 以下
	public String nonceStr;
	//签名
	public String appSignature;
	//签名方法
	public String signMethod;
	
	
	
	
	public static WxPayOrderInfo convert2WxPayOrderInfo(WxOrderInfo wxOrderInfo){
		
		WxPayOrderInfo wxPayOrderInfo = null;
		if(wxOrderInfo!=null){
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
			wxPayOrderInfo.setTransactionId(wxOrderInfo.transaction_id);
			
			Date timeEnd = new Date();//TODO time_end
			wxPayOrderInfo.setTimeEnd(timeEnd);
			
			
			
			
		}
		return wxPayOrderInfo;
	}
	
	
	
}
