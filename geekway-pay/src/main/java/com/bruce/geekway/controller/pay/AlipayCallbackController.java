package com.bruce.geekway.controller.pay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.model.wx.pay.WxPayNotifyOrderRequest;
import com.bruce.geekway.service.pay.AlipayService;

/**
 * 用于接收alipay支付相关的回调
 */
@Controller
@RequestMapping("/alipay")
public class AlipayCallbackController {

	private static final Logger logger = LoggerFactory.getLogger(AlipayCallbackController.class);
	
	private static final Logger alipayNotifyLogger = LoggerFactory.getLogger("alipayNotifyLogger");
	
	@Autowired
	private AlipayService alipayService;
	
	////////////////////////////////////////////////////////
	/////////////////来自ali的回调通知开始/////////////////////
	////////////////////////////////////////////////////////

	/**
	 * js支付的微信回调
	 * @param model
	 * @param wxOrderRequest
	 * @param xml
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/alipayNotify")
	public String alipayNotify(Model model, WxPayNotifyOrderRequest wxOrderRequest, @RequestBody String xml, HttpServletRequest request) throws Exception {
		logger.debug("=====alipayNotify====");
		alipayNotifyLogger.info("alipay回调的通知数据: "+JsonUtil.gson.toJson(wxOrderRequest) + ", xml: "+xml);
		
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		
		//RSA签名解密
	   	if(AlipayConfig.sign_type.equals("0001")) {
	   		params = AlipayNotify.decrypt(params);
	   	}
		//XML解析notify_data数据
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		//商户订单号
		String outTradeNo = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
		//支付宝交易号
		String transId = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
		//交易状态
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		boolean verifyResult = AlipayNotify.verifyNotify(params);
		
		alipayNotifyLogger.info("alipay回调的参数验证： "+verifyResult);
		
		if(verifyResult){//验证成功
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			alipayNotifyLogger.info("alipay回调的订单状态： "+trade_status);
			if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
				alipayNotifyLogger.info("alipay回调的订单参数：  outTradeNo："+outTradeNo+", transId: "+transId);
				int result = alipayService.receiveAlipayOrder(outTradeNo, transId);
				if(result>0){
					return "success";//请不要修改或删除
				}
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
		}
		return "fail";
	}

	
}
