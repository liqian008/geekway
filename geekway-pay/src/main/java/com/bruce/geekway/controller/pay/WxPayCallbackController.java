package com.bruce.geekway.controller.pay;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.foundation.util.Sha1Util;
import com.bruce.geekway.model.wx.pay.WxComplaintNotify;
import com.bruce.geekway.model.wx.pay.WxPayAlarmNotify;
import com.bruce.geekway.model.wx.pay.WxPayNotifyOrderRequest;
import com.bruce.geekway.service.pay.WxPayService;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayCallbackController {

	@Autowired
	private WxPayService wxPayService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxPayCallbackController.class);

	
	////////////////////////////////////////////////////////
	/////////////////来自微信的回调通知开始/////////////////////
	////////////////////////////////////////////////////////

	/**
	 * js支付的微信回调
	 * @param model
	 * @param wxOrderRequest
	 * @param xml
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jspayNotify")
	public String jspayNotify(Model model, WxPayNotifyOrderRequest wxOrderRequest, @RequestBody String xml, HttpServletRequest request) {
		logger.info("微信回调的通知数据: "+JsonUtil.gson.toJson(wxOrderRequest) + ", xml: "+xml);
		if(wxOrderRequest!=null && xml!=null){
			WxPayNotifyOrderRequest tempXmlOrder = parseWxOrderXml(xml);
			if(tempXmlOrder!=null){
				wxOrderRequest.setOpenId(tempXmlOrder.getOpenId());
			}
			// 调用支付service，保存订单信息
			int result = wxPayService.receiveWxOrder(wxOrderRequest);
		}
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
		System.out.println("=====notify xml===="+xml);
		
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
		System.out.println("=====alarm xml===="+xml);
		//解析告警的数据
		WxPayAlarmNotify alarmRequest = parseWxAlarmXml(xml);
		int result = wxPayService.receiverWxAlarm(alarmRequest);
		if(result>0){
			return "success";
		}
		return "error";
	}

	/**
	 * 微信发来的维权通知
	 * @param model
	 * @param xml
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/rights")
	public String rights(Model model, @RequestBody String xml, HttpServletRequest request) {
		System.out.println("=====rights====");
		//解析投诉的数据
		WxComplaintNotify complaintRequest = parseWxComplaintXml(xml);
		int result = wxPayService.receiverWxComplaint(complaintRequest);
		if(result>0){
			return "success";
		}
		return "error";
	}

	// //////////////////////////////////////////////////////
	// ///////////////来自微信的回调通知结束/////////////////////
	// //////////////////////////////////////////////////////

	/**
	 * 调用发货接口(通常应在后台服务中调用)
	 * 
	 * @param model
	 * @param xml
	 * @param request
	 * @return
//	 */
//	@Deprecated
//	@RequestMapping(value = "/deliverNotify")
//	public String deliverNotify(Model model, HttpServletRequest request) {
//
//		// 调用支付service，保存订单信息
//		int result = payService.dealDeliver();
//
//		return "wxpay/jspayNotify";
//	}
	
	/**
	 * 解析投诉xml
	 * @param xml
	 * @return
	 */
	private static WxPayNotifyOrderRequest parseWxOrderXml(String xml){
		WxPayNotifyOrderRequest orderRequest = null;
		Element ele;
		try {
			ele = DocumentHelper.parseText(xml).getRootElement();
			String openId = ele.elementText("OpenId");
//			String IsSubscribe = ele.elementText("IsSubscribe");
			
			orderRequest = new WxPayNotifyOrderRequest();
			orderRequest.setOpenId(openId);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("notify xml: "+xml);
		}
		return orderRequest;
	}
	
	
	/**
	 * 解析投诉xml
	 * @param xml
	 * @return
	 */
	private static WxComplaintNotify parseWxComplaintXml(String xml){
		WxComplaintNotify complaintRequest = null;
		Element ele;
		try {
			ele = DocumentHelper.parseText(xml).getRootElement();
			String openId = ele.elementText("OpenId");
			String appId = ele.elementText("AppId");
			String timeStamp = ele.elementText("TimeStamp");
			String msgType = ele.elementText("MsgType");
			String feedBackId = ele.elementText("FeedBackId");
			String transId = ele.elementText("TransId");
			String reason = ele.elementText("Reason");
			String solution = ele.elementText("Solution");
			String extInfo = ele.elementText("ExtInfo");
			
			complaintRequest = new WxComplaintNotify();
			complaintRequest.setAppId(appId);
			complaintRequest.setOpenId(openId);
			complaintRequest.setTimestamp(timeStamp);
			complaintRequest.setMsgType(msgType);
			complaintRequest.setFeedbackId(feedBackId);
			complaintRequest.setTransId(transId);
			complaintRequest.setReason(reason);
			complaintRequest.setSolution(solution);
			complaintRequest.setExtInfo(extInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return complaintRequest;
	}
	
	
	/**
	 * 解析投诉xml
	 * @param xml
	 * @return
	 */
	private static WxPayAlarmNotify parseWxAlarmXml(String xml){
		WxPayAlarmNotify alarmRequest = null;
		Element ele;
		try {
			ele = DocumentHelper.parseText(xml).getRootElement();
			String errorType = ele.elementText("ErrorType");
			String description = ele.elementText("Description");
			String alarmContent = ele.elementText("AlarmContent");
			
			alarmRequest = new WxPayAlarmNotify();
			alarmRequest.setErrorType(errorType);
			alarmRequest.setDescription(description);
			alarmRequest.setAlarmContent(alarmContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alarmRequest;
	}

	
	public static void main(String[] args) {
		SortedMap<String, String> addressMap = new TreeMap<String, String>();
		
//		addressMap.put("appid", "wx17ef1eaef46752cb");
		addressMap.put("accessToken", "OezXcEiiBSKSxW0eoylIeBFk1b8VbNtfWALJ5g6aMgZHaqZwK4euEskSn78Qd5pLsfQtuMdgmhajVM5QDm24W8X3tJ18kz5mhmkUcI3RoLm7qGgh1cEnCHejWQo8s5L3VvsFAdawhFxUuLmgh5FRA");
		addressMap.put("timestamp", "1384841012");
		addressMap.put("noncestr", "123456");
		addressMap.put("url", "http://open.weixin.qq.com/");
		String addressSign = WxAuthUtil.formatWxPaySignText(addressMap);
		
		
		String example = "accesstoken=OezXcEiiBSKSxW0eoylIeBFk1b8VbNtfWALJ5g6aMgZHaqZwK4euEskSn78Qd5pLsfQtuMdgmhajVM5QDm24W8X3tJ18kz5mhmkUcI3RoLm7qGgh1cEnCHejWQo8s5L3VvsFAdawhFxUuLmgh5FRA&appid=wx17ef1eaef46752cb&noncestr=123456&timestamp=1384841012&url=http://open.weixin.qq.com/";
		System.out.println("example: "+example);
		System.out.println(example.equals(addressSign));
		System.out.println("addressSign: "+addressSign);
		System.out.println("addressSign: "+ Sha1Util.getSha1(example));
	}
}
