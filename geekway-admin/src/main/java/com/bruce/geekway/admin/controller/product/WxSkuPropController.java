package com.bruce.geekway.admin.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.service.product.IWxSkuPropService;


@Controller
@RequestMapping("/product")
public class WxSkuPropController {

	@Autowired
	private IWxSkuPropService wxSkuPropService;
	
	
	@RequestMapping("/skuPropList")
	public String skuPropList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxSkuProp> skuPropList = wxSkuPropService.queryAll();
		model.addAttribute("skuPropList", skuPropList);
		return "product/skuPropList";
	}
	
	
}
