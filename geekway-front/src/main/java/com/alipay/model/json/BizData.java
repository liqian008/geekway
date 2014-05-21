package com.alipay.model.json;

import com.bruce.geekway.model.ItoProductOrder;

public class BizData {

	/* 交易类型 */
	private String trade_type;
	/* 地址 */
	private String need_address;
	/* "http://商户自定义地址/return_ulr.aspx", */
	private String return_url;
	/* "http://商户自定义地址/notify_url.aspx" */
	private String notify_url;
	/* 描述 */
	private String memo;

//	private ExtendInfo ext_info;

	private GoodsInfo goods_info;
	
	public BizData(){
		this("1");
	}
	public BizData(String trade_type){
		this.trade_type = trade_type;
	}
	
//	public BizData(ItoProductOrder productOrder){
//		this.trade_type = "1";
//		this.need_address = "";
//		this.return_url = "";
//		this.notify_url = "";
//	}

//	public BizData(String trade_type, String need_address, String return_url, String notify_url){
//		this.trade_type = trade_type;
//		this.need_address = need_address;
//		this.return_url = return_url;
//		this.notify_url = notify_url;
//	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getNeed_address() {
		return need_address;
	}

	public void setNeed_address(String need_address) {
		this.need_address = need_address;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	

	public GoodsInfo getGoods_info() {
		return goods_info;
	}

	public void setGoods_info(GoodsInfo goods_info) {
		this.goods_info = goods_info;
	}

	
//	public ExtendInfo getExt_info() {
//		return ext_info;
//	}
//
//	public void setExt_info(ExtendInfo ext_info) {
//		this.ext_info = ext_info;
//	}
	
}
