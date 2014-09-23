package com.bruce.geekway.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.service.product.IWxProductService;

/**
 * 个人订单
 * @author liqian
 *
 */
@Controller
public class WxProductOrderController {
	
	@Autowired
	private IWxProductService wxProductService;
	
	/**
	 * 我的订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orders")
	public String orders(Model model, HttpServletRequest request) {
		List<WxProduct> productList = wxProductService.queryAvailableList();
		model.addAttribute("productList", productList);
		return "product/myOrderList";
	}
	
	/**
	 * 我的订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/vouchers")
	public String vouchers(Model model, HttpServletRequest request) {
//		List<WxProduct> productList = wxProductService.queryAvailableList();
//		model.addAttribute("productList", productList);
		
		return "product/myVouchers";
	}
}
