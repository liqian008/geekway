package com.bruce.geekway.controller.wx;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.foundation.util.Sha1Util;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.utils.HttpUtil;
import com.bruce.geekway.utils.WxAuthUtil;

@Controller
public class WxJsConfigController {
	
	@ResponseBody
	@RequestMapping(value = "wxJsConfig")
	public String wxJsConfig(String pageUrl, HttpServletRequest request) {
		String appId = "wx24e31a5fd464b35c";
		String wxConfigFormat = "wx.config({debug: %b, appId: '%s', timestamp: %s, nonceStr: '%s', signature: '%s',jsApiList: [%s]})";
		String requestUrl = request.getQueryString();
		String timestampStr = String.valueOf(System.currentTimeMillis()/1000);//时间戳
		String nonceStr = WxAuthUtil.createNoncestr();
		
		String jsapiTicket = buildJsApiTicket("﻿5xWr9iiGFP6VWHwbEjy9mCZSRpRCRX6x8Xb_Yb0exHY6uAQMOqboaDsBa24zAhDqD7nuiHFDHdxmtNOjbqxinKYkjTHkH3-88JUTRvv7GQs");
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		
		dataMap.put("timestamp", timestampStr);
		dataMap.put("noncestr", nonceStr);
		dataMap.put("jsapi_ticket", jsapiTicket);
		dataMap.put("url", requestUrl);
		
		String signature  = Sha1Util.getSha1(buildPlainText(dataMap));
		String jsApiListStr = "'onMenuShareTimeline',  'onMenuShareAppMessage'";
		String wxConfig = String.format(wxConfigFormat , true, appId, timestampStr, nonceStr, signature, jsApiListStr);
		return wxConfig;
	}
	
	
	private static String buildAccessToken(String appId, String secretKey) {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", appId);
		map.put("secret", secretKey);
		String acResponse = HttpUtil.getRequest(url, map);
		System.out.println("acResponse: "+acResponse);
		Bean bean = JsonUtil.gson.fromJson(acResponse, Bean.class);
		return bean.getAccess_token();
		
	}
	
	
	private static String buildJsApiTicket(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("access_token", accessToken);
		map.put("type", "jsapi");
		String jsapiTicketResponse = HttpUtil.getRequest(url, map);
		System.out.println("response: "+jsapiTicketResponse);
		Bean bean = JsonUtil.gson.fromJson(jsapiTicketResponse, Bean.class);
		return bean.getTicket();
	}


	public static void main(String[] args) {
//		String appId = "wx24e31a5fd464b35c";
		String appId = ConstWeixin.WX_APP_ID;
		String secretKey = ConstWeixin.WX_APP_SECRET_KEY;
		
		String wxConfigFormat = "wx.config({debug: %b, appId: '%s', timestamp: %s, nonceStr: '%s', signature: '%s',jsApiList: [%s]})";
		String requestUrl = "http://bruce-lee.cn/js1.html";
		String timestampStr = String.valueOf(System.currentTimeMillis()/1000);//时间戳
		String nonceStr = WxAuthUtil.createNoncestr();
		
		String accessToken = buildAccessToken(appId, secretKey);
		String jsapiTicket = buildJsApiTicket(accessToken);
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		
		dataMap.put("timestamp", timestampStr);
		dataMap.put("noncestr", nonceStr);
		dataMap.put("jsapi_ticket", jsapiTicket);
		dataMap.put("url", requestUrl);
		
		String signature  = Sha1Util.getSha1(buildPlainText(dataMap));
		String jsApiListStr = "'checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage'";
		String wxConfig = String.format(wxConfigFormat , true, appId, timestampStr, nonceStr, signature, jsApiListStr);
		System.out.println(wxConfig);
	}
	
	
	public static String buildPlainText(SortedMap<String, String> dataMap){
		if(dataMap!=null&&dataMap.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: dataMap.entrySet()){
				String key = entry.getKey().toLowerCase();
				String value = entry.getValue();
				sb.append(key+"="+value+"&");
			}
			sb.setLength(sb.length()-1);
			String result = sb.toString();
			return result;
		}
		return "";
	}
	
	public static class Bean{
		private int errcode;
		private String ticket;
		private String access_token;
		
		public int getErrcode() {
			return errcode;
		}
		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}
		public String getTicket() {
			return ticket;
		}
		public void setTicket(String ticket) {
			this.ticket = ticket;
		}
		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
	}
}
