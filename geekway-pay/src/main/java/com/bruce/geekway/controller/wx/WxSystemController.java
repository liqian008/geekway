package com.bruce.geekway.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.foundation.util.UrlUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate;
import com.bruce.geekway.service.product.IWxDeliveryTemplateService;

@Controller
public class WxSystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxSystemController.class);
	
	@Autowired
	private IWxDeliveryTemplateService wxDeliverTemplateService;
	
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request) {
		WxDeliveryTemplate template = wxDeliverTemplateService.loadDeliveryTemplate(1);
		System.out.println(template);
		return "product/index";
	}
	
	
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
	 * 微信oauth请求后的redirect代理，需要根据其中的proxyUrl参数再进行跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wxOauthRedirect")
	public String wxOauthRedirect(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String proxyUrl = request.getParameter("proxyUrl");
		
		String redirectFullUrl = UrlUtil.addParameter(proxyUrl, "code", code);
		redirectFullUrl = UrlUtil.addParameter(redirectFullUrl, "state", state); 
		if(logger.isDebugEnabled()){
			logger.debug("oauth proxyUrl: "+proxyUrl);
			logger.debug("oauth code: "+code);
			logger.debug("oauth state: "+state);
			logger.debug("redirectFullUrl: "+ redirectFullUrl);
		}
		return "redirect:"+redirectFullUrl; 
	}
	
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value ="uploadTest")
	public String index(Model model, HttpServletRequest request) {
		try {
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			String result = uploadService.uploadFile(bytesData, "", file.getName());
//			System.out.println(result);
			
//			UploadImageResult uploadResult = qiniuUploadService.uploadImage(new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg"), "", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			UploadImageResult imageUploadResult = uploadService.uploadImage(bytesData, "", "hands-plant-870x450.jpg", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			System.out.println(imageUploadResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "product/index";
	}


	public IWxDeliveryTemplateService getWxDeliverTemplateService() {
		return wxDeliverTemplateService;
	}


	public void setWxDeliverTemplateService(
			IWxDeliveryTemplateService wxDeliverTemplateService) {
		this.wxDeliverTemplateService = wxDeliverTemplateService;
	}
	
	
	
}
