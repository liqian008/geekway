package com.bruce.geekway.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.geekway.handler.MessageHandler;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.utils.MessageMocker;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wx")
public class WxEntryController {

	@Autowired
	private MessageHandler messageHandler;

	@ResponseBody
	@RequestMapping(value="/api", method = { RequestMethod.GET})
	public String authGet(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) {
		if (WxAuthUtil.validateAuth(signature, timestamp, nonce, echostr)) {
			return echostr;
		}
		return "";
	}

	@RequestMapping(value="/api", method = {RequestMethod.POST})
	public String message(Model model, @RequestBody String xml) {
		String response =  "";
		try {
			System.out.println("wx xml: "+xml);
			BaseResponse baseResponse = messageHandler.processMessage(xml);
			if (baseResponse != null) {
				response = messageHandler.parseXMLResp(baseResponse);
				System.out.println("response: "+response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("response", response);
		return "wx/response";
	}

	@RequestMapping(value = "/mockMessage")
	public String mockMessage(Model model, String type) throws Exception {
		BaseResponse baseResponse = null;
		if ("text".equalsIgnoreCase(type)) {
			baseResponse = messageHandler.processMessage(MessageMocker.MSG_TEXT_XML);
		} else if ("click".equalsIgnoreCase(type)) {
			baseResponse = messageHandler.processMessage(MessageMocker.MSG_EVENT_CLICK_XML);
		} else if ("subscribe".equalsIgnoreCase(type)) {
			baseResponse = messageHandler.processMessage(MessageMocker.MSG_EVENT_SUBSCRIBE_XML);
		} else if ("img".equalsIgnoreCase(type)) {
			baseResponse = messageHandler.processMessage(MessageMocker.MSG_IMG_XML);
		}
		System.out.println(baseResponse);
		String response = messageHandler.parseXMLResp(baseResponse);
		if(response==null){
			response = "";
		}
		model.addAttribute("response", response);
		return "wx/response";
	}

}
