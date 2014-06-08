package com.bruce.geekway.constants;

public interface ConstIto {
	
	/*pad填写货到付款*/
	public static final short PAYTYPE_SELF = 0;
	/*alipay二维码支付*/
	public static final short PAYTYPE_ALIPAY = 1;
	/*口袋通支付*/
	public static final short PAYTYPE_KOUDAITONG = 2;
	
	/*pad到付下单*/
	public static final short PAYSTATUS_SELF_APPLIED = 0;
	/*支付宝扫码下单*/
	public static final short PAYSTATUS_ALIPAY_APPLIED = 10;
	/*支付宝支付完毕*/
	public static final short PAYSTATUS_ALIPAY_FINISHED = 11;
	
	/*邮寄状态，无效数据，无需邮寄（通常用于用户下了单，但未付款）*/
	public static final short POSTSTATUS_NULL = 0;
	/*邮寄状态，待邮寄*/
	public static final short POSTSTATUS_CONFIRM = 10;
	/*邮寄状态，已邮寄*/
	public static final short POSTSTATUS_POSTED = 20;

	
}
