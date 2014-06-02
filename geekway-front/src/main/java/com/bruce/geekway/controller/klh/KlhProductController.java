package com.bruce.geekway.controller.klh;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.service.klh.IKlhProductService;

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
	@RequestMapping(value = "/productList")
	public String productList(Model model) {
		List<KlhProduct> productList =  klhProductService.queryAll();
		if(productList!=null&&productList.size()>0){
			model.addAttribute("productList", productList);
		}
		return "klh/productList";
	}
	
	/**
	 * 兑换产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productInfo")
	public String productInfo(Model model, int productId) {
		KlhProduct productInfo = klhProductService.loadById(productId);
		if(productInfo!=null&&productInfo.getId()>0){
			model.addAttribute("productInfo", productInfo);
		}
		return "klh/productInfo";
	}
	
	

	/**
	 * 兑换产品
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productApply")
	public String productApply(Model model, int productId) {
		KlhProduct productInfo = klhProductService.loadById(productId);
		if(productInfo!=null&&productInfo.getId()>0){
			model.addAttribute("productInfo", productInfo);
		}
		return "klh/productInfo";
	}
}
