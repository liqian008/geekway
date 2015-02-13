package com.bruce.geekway.controller.api;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.service.IWxJsapiTicketService;
import com.bruce.geekway.utils.WxAuthUtil;
import com.bruce.geekway.utils.WxSignUtil;

@RequestMapping(value = "/api")
@Controller
public class WxJsConfigController {
	// implements LastModified

	private static final String wxConfigFormat = "wx.config({\r\ndebug: %b, \r\nappId: '%s', \r\ntimestamp: %s, \r\nnonceStr: '%s', \r\nsignature: '%s',\r\njsApiList: [%s]\r\n})";
	
	private static final Logger logger = LoggerFactory.getLogger(WxJsConfigController.class);
	
	@Autowired
	private IWxJsapiTicketService wxJsapiTicketService;
	
	@ResponseBody
	@RequestMapping(value = "/wxJsConfigSrc")
	public String wxJsConfigStr(
			@RequestParam(defaultValue = "") String pageUrl, 
			@RequestParam(defaultValue = "false") boolean debug,
			HttpServletRequest request, HttpServletResponse response) {
//		response.addHeader("Content-Type", "application/x-javascript");
		logger.debug("pageUrl参数为："+pageUrl);
		
		if(StringUtils.isNotBlank(pageUrl)){//参数正确
			String timestampStr = String.valueOf(DateUtil.getUnixTime(new Date()));// 时间戳
			String wxNonceStr = WxAuthUtil.createNoncestr();
			String jsapiTicket = wxJsapiTicketService.getCachedJsTicket();
			logger.debug("获取的jsapiTicket为："+jsapiTicket); 
			
			// cached jsapiTicket
			SortedMap<String, String> signMap = new TreeMap<String, String>();

			signMap.put("timestamp", timestampStr);
			signMap.put("noncestr", wxNonceStr);
			signMap.put("jsapi_ticket", jsapiTicket);
			signMap.put("url", pageUrl);

			String signature = WxSignUtil.signSHA1(signMap, null);// 签名
			
			String wxConfig = String.format(wxConfigFormat , debug, ConstWeixin.WX_APP_ID, timestampStr, wxNonceStr, signature, ConstWeixin.WX_JS_API_FULL);
			return wxConfig;
		}
		return "";
	}

}