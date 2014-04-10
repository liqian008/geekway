package com.bruce.geekway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.handler.MessageHandler;
import com.bruce.geekway.model.wx.response.BaseResponse;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"wx"})
public class WxEntryController {
	
	@Autowired
	private MessageHandler messageHandler;
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		try {
			BaseResponse response =  messageHandler.processMessage(null);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/index";
	}

	
}
