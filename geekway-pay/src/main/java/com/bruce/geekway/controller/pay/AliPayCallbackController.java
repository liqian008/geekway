package com.bruce.geekway.controller.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 来自alipay的支付回调
 */
@Controller
@RequestMapping("/alipay")
public class AliPayCallbackController {

	
	private static final Logger logger = LoggerFactory.getLogger(WxPayCallbackController.class);

}
