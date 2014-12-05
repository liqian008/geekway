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
import com.bruce.foundation.util.Md5Util;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.pay.WxComplaintNotify;
import com.bruce.geekway.model.wx.pay.WxPayAlarmNotify;
import com.bruce.geekway.model.wx.pay.WxPayNotifyOrderRequest;
import com.bruce.geekway.service.pay.WxpayService;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayCallbackController {

	@Autowired
	private WxpayService wxpayService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxPayCallbackController.class);
	
	private static final Logger wxpayLogger = LoggerFactory.getLogger("wxpayLogger");

	
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
			try{
				//检查支付签名是否正确
				checkWxpaySig(wxOrderRequest);
			}catch(Exception e){
				
			}
			
			WxPayNotifyOrderRequest tempXmlOrder = parseWxOrderXml(xml);
			if(tempXmlOrder!=null){
				wxOrderRequest.setOpenId(tempXmlOrder.getOpenId());
			}
			// 调用支付service，保存订单信息
			int result = wxpayService.receiveWxOrder(wxOrderRequest);
		}
		return "wxpay/jspayNotify";
	}

	
	/**
	 * 检查微信支付签名
	 * @param wxOrderRequest
	 */
	private static boolean checkWxpaySig(WxPayNotifyOrderRequest wxOrderRequest) {
		if(wxOrderRequest!=null){
			SortedMap<String, String> packageMap = new TreeMap<String, String>();
//			wxOrderRequest = new WxPayNotifyOrderRequest();
//			wxOrderRequest.setSign_type("MD5");
//			wxOrderRequest.setInput_charset("UTF-8");
//			wxOrderRequest.setTrade_mode("1");
//			wxOrderRequest.setTrade_state("0");
//			wxOrderRequest.setPartner("1220724401");
//			wxOrderRequest.setBank_type("4186");
//			wxOrderRequest.setBank_billno("201412056193435269");
//			wxOrderRequest.setTotal_fee(2000);
//			wxOrderRequest.setFee_type(1);
//			wxOrderRequest.setNotify_id("BoHVE6eXMi8M-1cvtjC_uWZiSbU3NLpS4SMi2GCIrRes4nViB_xtPSY_KMTMG-ym3NYqBB4pE6qG3hix8W6KgOsHge5npu1w");
//			wxOrderRequest.setTransaction_id("1220724401201412056360171322");
//			wxOrderRequest.setOut_trade_no("ABE61754653IMZ");
//			wxOrderRequest.setTime_end("20141205170925");
//			wxOrderRequest.setProduct_fee(2000);
//			wxOrderRequest.setTransport_fee(0);
//			wxOrderRequest.setDiscount(0);
//			wxOrderRequest.setSign("6E4F4E4EB7D3B5B6AF79BE634BD5690C");
			
			packageMap.put("sign_type", wxOrderRequest.getSign_type());
			packageMap.put("input_charset", wxOrderRequest.getInput_charset());
			packageMap.put("trade_mode", wxOrderRequest.getTrade_mode());
			packageMap.put("trade_state", wxOrderRequest.getTrade_state());
			packageMap.put("partner", wxOrderRequest.getPartner());
			packageMap.put("bank_type", wxOrderRequest.getBank_type());
			packageMap.put("bank_billno", wxOrderRequest.getBank_billno());
			
			packageMap.put("notify_id", wxOrderRequest.getNotify_id());
			packageMap.put("transaction_id", wxOrderRequest.getTransaction_id());
			packageMap.put("out_trade_no", wxOrderRequest.getOut_trade_no());
			packageMap.put("time_end", wxOrderRequest.getTime_end());
			
			packageMap.put("total_fee", String.valueOf(wxOrderRequest.getTotal_fee()));
			packageMap.put("fee_type", String.valueOf(wxOrderRequest.getFee_type()));
			packageMap.put("product_fee", String.valueOf(wxOrderRequest.getProduct_fee()));
			packageMap.put("transport_fee", String.valueOf(wxOrderRequest.getTransport_fee()));
			packageMap.put("discount", String.valueOf(wxOrderRequest.getDiscount()));
			
			//根据参数map生成sign
			String unsignPackageText = WxAuthUtil.formatWxPayPackageText(packageMap) + "&key="+ConstWeixin.WX_PAY_PARTERN_KEY;//最后拼接key
			String sign = Md5Util.md5Encode(unsignPackageText).toUpperCase();
			//判断sign是否匹配
			if(sign.equals(wxOrderRequest.getSign())){
				return true;
			}
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	
	public static void main(String[] args) {
		System.out.println(checkWxpaySig(new WxPayNotifyOrderRequest()));;
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
		int result = wxpayService.receiverWxAlarm(alarmRequest);
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
		int result = wxpayService.receiverWxComplaint(complaintRequest);
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

	
//	public static void main(String[] args) {
//		SortedMap<String, String> addressMap = new TreeMap<String, String>();
//		
////		addressMap.put("appid", "wx17ef1eaef46752cb");
//		addressMap.put("accessToken", "OezXcEiiBSKSxW0eoylIeBFk1b8VbNtfWALJ5g6aMgZHaqZwK4euEskSn78Qd5pLsfQtuMdgmhajVM5QDm24W8X3tJ18kz5mhmkUcI3RoLm7qGgh1cEnCHejWQo8s5L3VvsFAdawhFxUuLmgh5FRA");
//		addressMap.put("timestamp", "1384841012");
//		addressMap.put("noncestr", "123456");
//		addressMap.put("url", "http://open.weixin.qq.com/");
//		String addressSign = WxAuthUtil.formatWxPaySignText(addressMap);
//		
//		
//		String example = "accesstoken=OezXcEiiBSKSxW0eoylIeBFk1b8VbNtfWALJ5g6aMgZHaqZwK4euEskSn78Qd5pLsfQtuMdgmhajVM5QDm24W8X3tJ18kz5mhmkUcI3RoLm7qGgh1cEnCHejWQo8s5L3VvsFAdawhFxUuLmgh5FRA&appid=wx17ef1eaef46752cb&noncestr=123456&timestamp=1384841012&url=http://open.weixin.qq.com/";
//		System.out.println("example: "+example);
//		System.out.println(example.equals(addressSign));
//		System.out.println("addressSign: "+addressSign);
//		System.out.println("addressSign: "+ Sha1Util.getSha1(example));
//	}
}
