package com.bruce.geekway.admin.controller.ito;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProductBg;
import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.service.ito.IItoProductBgService;

/**
 * ITO APP 中的背景图片
 * @author liqian
 *
 */
@Controller
@RequestMapping("/ito")
public class ItoProductBgController {

	@Autowired
	private IItoProductBgService wxProductBgService;
	
	@RequestMapping("/productBgList")
	public String sliderList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoProductBg> productBgList = wxProductBgService.queryAll();
		model.addAttribute("productBgList", productBgList);
		return "ito/productBgList";
	}
	
	
	
	
	@RequestMapping("/productBgEdit")
	public String sliderEdit(Model model, HttpServletRequest request, int productBgId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProductBg productBg = wxProductBgService.loadById(productBgId);
		if(productBg!=null){
			model.addAttribute("productBg", productBg);
		}
		
		return "ito/productBgEdit";
	}
	
//	@RequestMapping("/productCoverSetting")
//	public String productCoverSetting(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		ItoProductBg productBg = wxProductBgService.loadById(1);
//		model.addAttribute("productBg", productBg);
//		model.addAttribute("productBgName", "APP封面图");
//		return "ito/productBgEdit";
//	}
	
//	@RequestMapping("/alipayBgSetting")
//	public String alipayBgSetting(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		ItoProductBg productBg = wxProductBgService.loadById(2);
//		model.addAttribute("productBg", productBg);
//		model.addAttribute("productBgName", "支付宝支付界面背景图");
//		return "ito/productBgEdit";
//	}
//	
//	@RequestMapping("/appBuyBgSetting")
//	public String appBuyBgSetting(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		ItoProductBg productBg = wxProductBgService.loadById(3);
//		model.addAttribute("productBg", productBg);
//		model.addAttribute("productBgName", "APP支付界面背景图");
//		return "ito/productBgEdit";
//	}
	
	@RequestMapping(value = "/saveProductBg", method = RequestMethod.POST)
	public String saveAppBuyBgSetting(Model model, ItoProductBg productBg, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		productBg.setUpdateTime(currentTime);
		if(productBg!=null&&productBg.getId()!=null&&productBg.getId()>0){
			result = wxProductBgService.updateById(productBg);
		}
//		String redirectUrl = "";
//		if(productBg.getId()==1){
//			//app封面的图片
//			redirectUrl = "./productBgSetting";
//		}else if(productBg.getId()==2){
//			//支付宝支付页面的背景图
//			redirectUrl = "./alipayBgSetting";
//		}else if(productBg.getId()==3){
//			//自有支付页面的背景图
//			redirectUrl = "./appBuyBgSetting";
//		}		
		model.addAttribute("redirectUrl", "./productBgList");
		return "forward:/home/operationRedirect";
	}
	
}
