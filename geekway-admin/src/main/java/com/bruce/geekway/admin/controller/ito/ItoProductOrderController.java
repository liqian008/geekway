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
	private IItoProductOrderService itoSkuPropValueService;
	
	
	@RequestMapping("/orderList")
	public String orderList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoProductOrder> orderList = itoSkuPropValueService.queryAll();
		model.addAttribute("orderList", orderList);
		return "ito/orderList";
	}
	
	
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(Model model, ItoProductOrder order, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		order.setUpdateTime(currentTime);
		if(order!=null&&order.getId()!=null&&order.getId()>0){
			result = itoSkuPropValueService.updateById(order);
		}else{
			order.setCreateTime(currentTime);
			result = itoSkuPropValueService.save(order);
		}
		
		model.addAttribute("redirectUrl", "./orderList");
		return "forward:/home/operationRedirect";
	}
}
