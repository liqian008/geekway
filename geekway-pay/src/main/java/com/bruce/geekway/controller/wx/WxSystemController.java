package com.bruce.geekway.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.utils.UrlUtil;

@Controller
public class WxSystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxSystemController.class);
	
	
	/**
	 * 跳转请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/redirect")
	public String redirect(HttpServletRequest request) {
		return "product/redirect";
	}
	
	/**
	 * oauth请求后的redirect，需要再进行跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/oauthRedirect")
	public String redirect(String code, String state, String proxyUrl, HttpServletRequest request) {
		logger.debug("oauth code: "+code);
		logger.debug("oauth state: "+state);
		logger.debug("oauth proxyUrl: "+proxyUrl);
		
		String redirectFullUrl = UrlUtil.addParameter(proxyUrl, "code", code);
		redirectFullUrl = UrlUtil.addParameter(proxyUrl, "state", state);
		logger.debug("redirectFullUrl: "+ redirectFullUrl);
		return "redirect:"+redirectFullUrl;
	}
	
}
