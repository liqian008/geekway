package com.bruce.geekway.controller.klh;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.service.klh.IKlhProductService;
import com.bruce.geekway.utils.KlhUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class KlhProductController {
	
	@Autowired
	private IKlhProductService klhProductService;
	
	/**
	 * 兑换产品列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreProductList")
	public String productList(Model model, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
		
		List<KlhProduct> productList =  klhProductService.queryAll();
		if(productList!=null&&productList.size()>0){
			model.addAttribute("productList", productList);
		}
		return "klh/scoreProductList";
	}
	
	/**
	 * 兑换产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreProductInfo")
	public String productInfo(Model model, int productId, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
		
		KlhProduct productInfo = klhProductService.loadById(productId);
		if(productInfo!=null&&productInfo.getId()>0){
			model.addAttribute("productInfo", productInfo);
		}
		return "klh/scoreProductInfo";
	}
	
	

	/**
	 * 兑换产品
	 * @param model
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/productApply")
	public String productApply(Model model, int productId, HttpServletRequest request) {
		//TODO 
		return "klh/productInfo";
	}
}
