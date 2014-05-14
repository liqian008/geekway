package com.bruce.geekway.controller.ito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.ito.IItoProductOrderService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"m"})
public class ConfirmOrderController {
	
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;
	
	
	@RequestMapping(value = "/order/{orderSn}")
	public String article(Model model, @PathVariable String orderSn) {
//		WxMaterialArticle article = wxMaterialArticleService.loadById(articleId);
//		if(article!=null){
//			model.addAttribute("article", article);
//		}
		
		ItoProductOrder itoProductOrder = itoProductOrderService.loadById(1);
		if(itoProductOrder!=null){
			model.addAttribute("productOrder", itoProductOrder);
			return "ito/orderConfirm";
		}else{
			return "ito/orderConfirm";
		}
	}
}
