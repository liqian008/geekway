package com.bruce.geekway.utils;


public class ItoOrderUtil {


	private static final String ALIPAY_ORDER_RETURN_URL = ConfigUtil.getString("ito_alipay_order_return_url");

	
	private static final String ALIPAY_ORDER_NOTIFY_URL = ConfigUtil.getString("ito_alipay_order_notify_url");
	
	/**
	 * 构造alipay回调的returnUrl
	 * @param orderSn
	 * @return
	 */
	public static String getAlipayReturnUrl(String orderSn){
		String returnUrl = String.format(ALIPAY_ORDER_RETURN_URL, orderSn, signature(orderSn));
		return returnUrl;
	}
	
	/**
	 * 构造alipay回调的notifyUrl
	 * @param orderSn
	 * @return
	 */
	public static String getAlipayNotifyUrl(String orderSn){
		String notifyUrl = String.format(ALIPAY_ORDER_NOTIFY_URL, orderSn, signature(orderSn));
		return notifyUrl;
	}
	
	public static String signature(String orderSn){
		//TODO signature orderSn
		return orderSn;
	}
	
	public static void main(String[] args) {
		String link = String.format("aaa%sabbb", "1");
		System.out.println(link);
	}
}
