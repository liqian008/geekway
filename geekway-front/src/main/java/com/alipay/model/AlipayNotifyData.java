package com.alipay.model;

import java.util.Date;

public class AlipayNotifyData {

	/* 合作商账户 */
	private String partner;
	/* 二维码链接 */
	private String qrcode;
	/* 商品名称 */
	private String subject;
	/* 支付宝的交易码 */
	private String trade_no;
	/* 业务系统（ITO）中的订单号 */
	private String out_trade_no;
	/* 总金额 */
	private double total_fee;
	/* 订单状态 */
	private String trade_status;
	/* 买家email */
	private String buyer_email;
	/* 交易创建时间 */
	private Date gmt_create;
	/* 交易付款时间 */
	private Date gmt_payment;

	public AlipayNotifyData() {
		super();
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

}
