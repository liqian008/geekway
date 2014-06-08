package com.bruce.geekway.admin.controller.ito;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.service.ito.IItoProductOrderService;



@Controller
@RequestMapping("/ito")
public class ItoProductOrderController {

	@Autowired
	private IItoProductOrderService itoProductOrderService;
	
	/**
	 * 订单列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderListSelf")
	public String orderListSelf(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoProductOrder> orderList = itoProductOrderService.querySelfOrderList();
		model.addAttribute("orderList", orderList);
		return "ito/orderListSelf";
	}
	
	/**
	 * 订单列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderListAlipay")
	public String orderListAlipay(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoProductOrder> orderList = itoProductOrderService.queryAlipayOrderList();
		model.addAttribute("orderList", orderList);
		return "ito/orderListAlipay";
	}
	
	
	
	/**
	 * 查看订单信息
	 * @param model
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/orderDisplay")
	public String orderDisplay(Model model, HttpServletRequest request, String orderSn) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProductOrder productOrder = itoProductOrderService.loadByOrderSn(orderSn);
		if(productOrder!=null){
			model.addAttribute("productOrder", productOrder);
		}
		return "ito/orderDisplay";
	}
	
	
	/**
	 * 修改订单信息
	 * @param model
	 * @param order
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(Model model, ItoProductOrder order, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		order.setUpdateTime(currentTime);
		if(order!=null&&order.getId()!=null&&order.getId()>0){
			result = itoProductOrderService.updateById(order);
		}
		
		model.addAttribute("redirectUrl", "./orderList");
		return "forward:/home/operationRedirect";
	}
}
