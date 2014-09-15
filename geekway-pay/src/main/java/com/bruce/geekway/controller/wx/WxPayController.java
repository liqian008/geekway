package com.bruce.geekway.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.wx.pay.WxOrderInfo;
import com.bruce.geekway.service.pay.IWxPayService;
import com.tenpay.RequestHandler;
import com.tenpay.ResponseHandler;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.client.TenpayHttpClient;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayController {

	@Autowired
	private IWxPayService payService;

	////////////////////////////////////////////////////////
	/////////////////来自微信的回调通知开始/////////////////////
	////////////////////////////////////////////////////////

	@RequestMapping(value = "/jspayNotify")
	public String jspayNotify(Model model, WxOrderInfo wxOrderInfo, @RequestBody String xml, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("=====jspayNotify====");
		
		// 调用支付service，保存订单信息
		int result = payService.receiverOrder(wxOrderInfo);

		return "wxpay/jspayNotify";
	}

	/**
	 * 原生支付成功的通知
	 * 
	 * @param model
	 * @param xml
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/notify")
	public String notify(Model model, @RequestBody String xml, HttpServletRequest request) {
		System.out.println("=====notify====");

		return "wxpay/notify";
	}

	/**
	 * 微信发来的告警通知
	 * @param model
	 * @param xml
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/alarm")
	public String alarm(Model model, @RequestBody String xml, HttpServletRequest request) {
		System.out.println("=====alarm====");
		return "wxpay/alarm";
	}

	/**
	 * 微信发来的维权通知
	 * @param model
	 * @param xml
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rights")
	public String rights(Model model, @RequestBody String xml, HttpServletRequest request) {
		System.out.println("=====rights====");
		return "wxpay/rights";
	}

	// //////////////////////////////////////////////////////
	// ///////////////来自微信的回调通知结束/////////////////////
	// //////////////////////////////////////////////////////

	/**
	 * 调用发货接口
	 * 
	 * @param model
	 * @param xml
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/deliverNotify")
	public String deliverNotify(Model model, HttpServletRequest request) {

		// 调用支付service，保存订单信息
		int result = payService.deliver();

		return "wxpay/jspayNotify";
	}

}
