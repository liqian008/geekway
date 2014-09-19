package com.bruce.geekway.controller.wx;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.pay.WxComplaintNotify;
import com.bruce.geekway.model.wx.pay.WxPayAlarmNotify;
import com.bruce.geekway.model.wx.pay.WxPayItemJsObj;
import com.bruce.geekway.model.wx.pay.WxPayOrderNotify;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.pay.IWxPayService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.Md5Util;
import com.bruce.geekway.utils.Sha1Util;
import com.bruce.geekway.utils.WxAuthUtil;
import com.bruce.geekway.utils.WxMpUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayController {

	@Autowired
	private IWxPayService wxPayService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	
	@RequestMapping(value = "/oauth")
	public String oauth(Model model, HttpServletRequest request) {
		return "redirect:"+WxMpUtil.buildOauthUrl(1, "http://wxmp.1758.com/wxpay/oauthResult", "");
	}
	
	@RequestMapping(value = "/oauthResult")
	public String oauthResult(Model model, @RequestParam(required = false, defaultValue = "") String code, HttpServletRequest request) {
		if (!StringUtils.isEmpty(code)) {//回调地址进来的
			System.out.println("==========2=========");
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			System.out.println("==========3=========");
			if (oauthResult != null && oauthResult.getAccess_token() != null) {
				String userAccessToken = oauthResult.getAccess_token();
				System.out.println("=========userAccessToken========="+userAccessToken);
		        HttpSession session = request.getSession();
				session.setAttribute("userAccessToken", userAccessToken);
			}
		}
		return "wxpay/oauthResult";
	}
	
	@RequestMapping(value = "/buy")
	public String buy(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String banktype = "WX";
		String body = "包笑公堂-爆破镖";// 商品名称信息，这里由测试网页填入。
		String fee_type = "1";// 费用类型，这里1为默认的人民币
		String input_charset = "UTF-8";// 字符集，可以使用GBK或者UTF-8
		String notify_url = "http://wxmp.1758.com/wxpay/jspayNotify";// 支付成功后将通知该地址
		String out_trade_no = "12345";// 订单号，商户需要保证该字段对于本商户的唯一性
		String partner = ConstWeixin.WX_PAY_PARTERN_ID;// 测试商户号
		String spbill_create_ip = "127.0.0.1";// 用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
		String total_fee = "1";// 总金额。
		String partnerKey = ConstWeixin.WX_PAY_PARTERN_KEY;// 这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。

		SortedMap<String, String> packageMap = new TreeMap<String, String>();
		packageMap.put("bank_type", banktype);
		packageMap.put("body", body);
		packageMap.put("fee_type", fee_type);
		packageMap.put("input_charset", input_charset);
		packageMap.put("notify_url", notify_url);
		packageMap.put("out_trade_no", out_trade_no);
		packageMap.put("partner", partner);
		packageMap.put("spbill_create_ip", spbill_create_ip);
		packageMap.put("total_fee", total_fee);

		//生成package的串
		String finalPackageText = WxAuthUtil.packageSign(packageMap); 
		System.out.println(finalPackageText);

		// 参与 paySign 签名的字段包括:appid、timestamp、noncestr、package 以及
		// appkey(即paySignkey)。这里 signType 并不参与签名。

		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		String noncestr = timestamp;

		SortedMap<String, String> paySignMap = new TreeMap<String, String>();
		paySignMap.put("appid", ConstWeixin.WX_APP_ID);
		paySignMap.put("appkey", ConstWeixin.WX_PAY_SIGN_KEY);
		paySignMap.put("timestamp", timestamp);
		paySignMap.put("noncestr", noncestr);
		paySignMap.put("package", finalPackageText);

		String paySign = WxAuthUtil.paySign(paySignMap);
		
		
		WxPayItemJsObj itemJsObj = new WxPayItemJsObj();
		itemJsObj.setAppId(ConstWeixin.WX_APP_ID);
		itemJsObj.setTimeStamp(timestamp);
		itemJsObj.setNonceStr(noncestr);
		itemJsObj.setPackageValue(finalPackageText);
		itemJsObj.setSignType("SHA1");
		itemJsObj.setPaySign(paySign);
        model.addAttribute("itemJsObj", itemJsObj);
        
        //构造address
        HttpSession session = request.getSession();
		String userAccessToken = (String)session.getAttribute("userAccessToken");
		
		SortedMap<String, String> addressMap = new TreeMap<String, String>();
		addressMap.put("accessToken", userAccessToken);
		addressMap.put("timestamp", timestamp);
		addressMap.put("noncestr", noncestr);
		String addressSign = WxAuthUtil.formatWxPaySignText(addressMap);
		model.addAttribute("addressSign", addressSign);
		
		return "wxpay/buy";
	}	

	////////////////////////////////////////////////////////
	/////////////////来自微信的回调通知开始/////////////////////
	////////////////////////////////////////////////////////

	@RequestMapping(value = "/jspayNotify")
	public String jspayNotify(Model model, WxPayOrderNotify wxOrderRequest, @RequestBody String xml, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=====jspayNotify====");
		System.out.println("=====jspayNotify xml===="+xml);
		@SuppressWarnings("unchecked")
		Map<String, Object> requestMap = request.getParameterMap();
		if(requestMap!=null){
			for(Entry<String, Object> entry: requestMap.entrySet()){
				String[] value = (String[]) entry.getValue();
				System.out.println("===request key==="+entry.getKey()+"=====value===="+value!=null&&value.length>0?value[0]:"");
			}
		}
		if(wxOrderRequest!=null && xml!=null){
			WxPayOrderNotify tempXmlOrder = parseWxOrderXml(xml);
			if(tempXmlOrder!=null){
				wxOrderRequest.setOpenId(tempXmlOrder.getOpenId());
			}
			// 调用支付service，保存订单信息
			int result = wxPayService.receiverOrder(wxOrderRequest);
		}
		return "wxpay/jspayNotify";
	}

//	private WxPayOrderRequest parseWxPayOrder(HttpServletRequest request) {
//		WxPayOrderRequest t = null;
//		if(request!=null){
//			t.setAppId(request.getParameter(""));
//		}
//		return null;
//	}

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
		//TODO 解析告警的数据
		WxPayAlarmNotify alarmRequest = parseWxAlarmXml(xml);
		int result = wxPayService.receiverAlarm(alarmRequest);
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
		int result = wxPayService.receiverComplaint(complaintRequest);
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
	private static WxPayOrderNotify parseWxOrderXml(String xml){
		WxPayOrderNotify orderRequest = null;
		Element ele;
		try {
			ele = DocumentHelper.parseText(xml).getRootElement();
			String openId = ele.elementText("OpenId");
//			String IsSubscribe = ele.elementText("IsSubscribe");
			
			orderRequest = new WxPayOrderNotify();
			orderRequest.setOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
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
		
		addressMap.put("appid", "wx17ef1eaef46752cb");
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
