package com.bruce.geekway.admin.controller.klh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhProductOrder;
import com.bruce.geekway.service.klh.IKlhProductOrderService;

/**
 * 某个productOrder下的productOrder操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhProductOrderController {

	@Autowired
	private IKlhProductOrderService klhProductOrderService;
	
	@RequestMapping("/productOrderList")
	public String productOrderList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhProductOrder> productOrderList = klhProductOrderService.queryAll();
		model.addAttribute("productOrderList", productOrderList);
		return "klh/productOrderList";
	}
	
	
	
	/**
	 * 查看ProductOrder信息
	 * @param model
	 * @param request
	 * @param productOrderId
	 * @return
	 */
	@RequestMapping("/productOrderDisplay")
	public String productOrderDisplay(Model model, HttpServletRequest request, int productOrderId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhProductOrder productOrder = klhProductOrderService.loadById(productOrderId);
		if(productOrder!=null){
			model.addAttribute("productOrder", productOrder);
		}
		return "klh/productOrderEdit";
	}
	
	
}
