package com.bruce.geekway.controller.ito;

import org.apache.commons.lang3.StringUtils;
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
	
	/**
	 * 展示订单信息，供用户确认
	 * @param model
	 * @param orderSn
	 * @param orderHash
	 * @return
	 */
	@RequestMapping(value = "/order/{orderSn}")
	public String orderDisplay(Model model, @PathVariable String orderSn, String sig) {
		//检查提交的参数
		if(StringUtils.isBlank(orderSn)||StringUtils.isBlank(sig)){
			return "ito/error";
		}
		
		//根据订单号加载信息
		ItoProductOrder itoProductOrder = itoProductOrderService.loadByOrderSn(orderSn);
		if(itoProductOrder!=null){
			model.addAttribute("productOrder", itoProductOrder);
			model.addAttribute("sig", sig);
			return "ito/orderConfirm";
		}else{
			return "ito/orderConfirm";
		}
	}
}
