package com.bruce.geekway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping(value="/auth", method = { RequestMethod.GET})
	public String authGet(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) {
		if (WxAuthUtil.validateAuth(signature, timestamp, nonce, echostr)) {
			return echostr;
		}
		return "";
	}

	@ResponseBody
	@RequestMapping(value="/message", method = {RequestMethod.GET, RequestMethod.POST})
	public String message(Model model) {
		try {
			BaseResponse baseResponse = messageHandler.processMessage(null);
			if (baseResponse != null) {
				String response = messageHandler.parseXMLResp(baseResponse);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@ResponseBody
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
		return response;
	}

}
