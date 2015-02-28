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
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.IWxAppService;
import com.bruce.geekway.service.IWxJsapiTicketService;
import com.bruce.geekway.utils.WxAuthUtil;
import com.bruce.geekway.utils.WxSignUtil;

@Controller
public class WxJsConfigController {
	// implements LastModified

	private static final String wxConfigFormat = "wx.config({\r\ndebug: %b, \r\nappId: '%s', \r\ntimestamp: %s, \r\nnonceStr: '%s', \r\nsignature: '%s',\r\njsApiList: [%s]\r\n})";
//	private static final String[] WX_JSAPI_ARRAY = ConstWeixin.WX_JS_API_FULL.replace("'", "").split(","); 
	
	private static final Logger logger = LoggerFactory.getLogger(WxJsConfigController.class);
	
	@Autowired
	private IWxJsapiTicketService wxJsapiTicketService;
	@Autowired
	private IWxAppService wxAppService;
	
	@ResponseBody
	@RequestMapping(value = "api/wxJsConfigSrc")
	public String wxJsConfigStr(String wxAppId,
			@RequestParam(defaultValue = "") String nonce,
			@RequestParam(defaultValue = "") String pageUrl, 
			@RequestParam(defaultValue = "") String sign,
			@RequestParam(defaultValue = "false") boolean debug,
			HttpServletRequest request, HttpServletResponse response) {
//		response.addHeader("Content-Type", "application/x-javascript");
		logger.debug("pageUrl参数为："+pageUrl);
		
		if(StringUtils.isNotBlank(wxAppId)&&StringUtils.isNotBlank(pageUrl)&&StringUtils.isNotBlank(sign)){//参数正确
			WxApp wxApp = wxAppService.loadCachedByWxAppId(wxAppId);//加载
			if(wxApp!=null&&wxApp.getId()!=null&&wxApp.getStatus()!=null){
				
				String wxSecret = wxApp.getWxAppSecret();//该app对应的secretkey
				if(StringUtils.isBlank(wxSecret)){
					throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO secret未设置
				}
				
				//校验签名
				//validateSign(wxAppId, wxSecret, pageUrl, nonce, sign);
				
				String timestampStr = String.valueOf(DateUtil.getUnixTime(new Date()));// 时间戳
				String wxNonceStr = WxAuthUtil.createNoncestr();
				String jsapiTicket = wxJsapiTicketService.getCachedJsTicket(wxAppId, wxSecret);
				logger.debug("获取的jsapiTicket为："+jsapiTicket);
				
				// cached jsapiTicket
				SortedMap<String, String> signMap = new TreeMap<String, String>();

				signMap.put("timestamp", timestampStr);
				signMap.put("noncestr", wxNonceStr);
				signMap.put("jsapi_ticket", jsapiTicket);
				signMap.put("url", pageUrl);

				String signature = WxSignUtil.signSHA1(signMap, null);// 签名
				
				String wxConfig = String.format(wxConfigFormat , debug, wxAppId, timestampStr, wxNonceStr, signature, ConstWeixin.WX_JS_API_FULL);
				return wxConfig;
			}
		}
		return "";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "api/wxJsConfig")
//	public WxJsConfig wxJsConfig(String wxAppId,
//			@RequestParam(required=true, defaultValue = "") String nonce,
//			@RequestParam(required=true, defaultValue = "") String pageUrl, 
//			@RequestParam(required=true, defaultValue = "") String sign,
//			@RequestParam(required=false, defaultValue = "false") String debug,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		if(StringUtils.isNotBlank(wxAppId)&&StringUtils.isNotBlank(pageUrl)&&StringUtils.isNotBlank(sign)){//参数正确
//			WxApp wxApp = wxAppService.loadCachedByWxAppId(wxAppId);//加载
//			if(wxApp!=null&&wxApp.getId()!=null&&wxApp.getStatus()!=null){
//				
//				String wxSecret = wxApp.getWxAppSecret();//该app对应的secretkey
//				if(StringUtils.isBlank(wxSecret)){
//					throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO secret未设置
//				}
//				
//				//校验签名
//				//validateSign(wxAppId, wxSecret, pageUrl, nonce, sign);
//				
//				String timestampStr = String.valueOf(DateUtil.getUnixTime(new Date()));// 时间戳
//				String wxNonceStr = WxAuthUtil.createNoncestr();
//				String jsapiTicket = wxJsapiTicketService.getCachedJsTicket(wxAppId, wxSecret);// buildJsApiTicket(accessToken, pageUrl);//TODO get
//				logger.info("获取的jsapiTicket为："+jsapiTicket);
//				
//				// cached jsapiTicket
//				SortedMap<String, String> signMap = new TreeMap<String, String>();
//
//				signMap.put("timestamp", timestampStr);
//				signMap.put("noncestr", wxNonceStr);
//				signMap.put("jsapi_ticket", jsapiTicket);
//				signMap.put("url", pageUrl);
//
//				String signature = WxSignUtil.signSHA1(signMap, wxSecret);// 签名
//
////				response.addHeader("Last-Modified", "Sun, 25 Jan 2015 03:19:28 GMT");
////				response.addHeader("Cache-Control", "max-age=600");
//				
//				WxJsConfig wxConfig = new WxJsConfig(false, wxAppId, timestampStr, wxNonceStr, signature, WX_JSAPI_ARRAY);
//				return wxConfig;
//			}
//		}
//		return null;
//	}
	
	
//	//@CacheControl(policy=CachePolicy.NO_CACHE)
//	@RequestMapping(value = "test",method=RequestMethod.GET)
//	public String test(HttpServletRequest request, HttpServletResponse response) {
////		response.addHeader("Last-Modified", "Sun, 26 Jan 2015 03:19:28 GMT");
//		response.addDateHeader("Last-Modified", 1422249311722l);
//		response.addHeader("Cache-Control", "max-age=6000000");
////		response.setStatus(304);
//		return "test";
//	}

	/**
	 * 校验签名
	 * @param wxAppId
	 * @param wxSecret
	 * @param pageUrl
	 * @param sign
	 */
	private boolean validateSign(String wxAppId, String wxSecret, String pageUrl, String nonce, String sign) {
		SortedMap<String, String> signMap = new TreeMap<String, String>();
		signMap.put("wxAppId", wxAppId);
		signMap.put("pageUrl", pageUrl);
		signMap.put("nonce", nonce);
		
		String text = WxSignUtil.signMD5(signMap, wxSecret);
		if(text.equals(sign)){
			return true;
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}

//	@Override
//	public long getLastModified(HttpServletRequest request) {
//		if (lastModified == 0L) {
//			// TODO 此处更新的条件：如果内容有更新，应该重新返回内容最新修改的时间戳
//			lastModified = System.currentTimeMillis();
//		}
//		return lastModified;
//	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}