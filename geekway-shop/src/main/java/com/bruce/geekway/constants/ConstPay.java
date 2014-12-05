package com.bruce.geekway.constants;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstPay {
	
	/*微支付的通知地址*/
	public static final String NOTIFY_URL_JS_WXPAY = ConfigUtil.getString("notify_url_js_wxpay");
	
	
	/*alipay的通知地址*/
	public static final String NOTIFY_URL_ALIPAY = ConfigUtil.getString("notify_url_alipay");
	
	/*alipay成功后的页面回调地址*/
	public static final String ALIPAY_SUCCESS_CALLBACK_URL = ConfigUtil.getString("alipay_success_callback_url");
	/*alipay中断后的页面返回地址*/
	public static final String ALIPAY_BREAK_RETURN_URL = ConfigUtil.getString("alipay_break_return_url");
	
	
}
