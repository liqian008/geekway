package com.bruce.geekway.admin.controller.ito;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.constants.ConstIto;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.service.ito.IItoProductOrderService;



@Controller
@RequestMapping("/ito")
public class ItoProductOrderDeliverController {

	@Autowired
	private IItoProductOrderService itoProductOrderService;
	
	
	/**
	 * 进入发货页面
	 * @param model
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/deliver")
	public String deliver(Model model, HttpServletRequest request, String orderSn) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProductOrder productOrder = itoProductOrderService.loadByOrderSn(orderSn);
		if(productOrder!=null&&productOrder.getPostStatus()!=null&&productOrder.getPostStatus()==ConstIto.POSTSTATUS_POSTED){
			//已经发过货了，无需再次发货
		}else{
			model.addAttribute("productOrder", productOrder);
		}
		return "ito/orderDeliver";
	}
	
	
	/**
	 * 提交发货
	 * @param model
	 * @param order
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postDeliver", method = RequestMethod.POST)
	public String postDeliver(Model model, String orderSn, String deliverCompany, String deliverNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;
		ItoProductOrder productOrder = itoProductOrderService.loadByOrderSn(orderSn);
		if(productOrder!=null){
			if(productOrder.getPostStatus()!=null&&productOrder.getPostStatus()==ConstIto.POSTSTATUS_POSTED){
				//已经发过货了，无需再次发货
			}else{
				if(productOrder.getId()!=null&&productOrder.getId()>0&&StringUtils.isNotBlank(deliverCompany)&& StringUtils.isNotBlank(deliverNo)){
					Date currentTime = new Date();
					ItoProductOrder order = new ItoProductOrder();
					order.setId(productOrder.getId());
					order.setOrderSn(orderSn); 
					order.setDeliverCompany(deliverCompany);
					order.setDeliverNo(deliverNo);
					order.setPostStatus(ConstIto.POSTSTATUS_POSTED);
					order.setDeliverTime(currentTime);
					result = itoProductOrderService.updateById(order);
				}
			}
		}
		model.addAttribute("redirectUrl", "./orderListSelf");
		return "forward:/home/operationRedirect";
	}
}
