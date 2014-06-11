package com.bruce.geekway.controller.klh;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.klh.KlhEdbOrder;
import com.bruce.geekway.service.klh.IKlhProductService;
import com.bruce.geekway.utils.KlhUtil;

@Controller
@RequestMapping(value={"klh"})
public class KlhOrderController {
	
	
	/**
	 * 易店宝订单列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edbOrderList")
	public String edbOrderList(Model model, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
		
		long startTime = System.currentTimeMillis();
		List<KlhEdbOrder> edbOrderList = EdbApiUtil.edbTradeGet();
		model.addAttribute("edbOrderList", edbOrderList);
		
		long endTime = System.currentTimeMillis();
		long costTime = endTime - startTime;
		System.out.println("==="+(costTime)+"=====edbOrderListStr: "+ edbOrderList);
		
//		List<KlhProduct> edbOrderList = null;// klhProductService.queryAll();
//		if(edbOrderList!=null&&edbOrderList.size()>0){
//			model.addAttribute("edbOrderList", edbOrderList);
//		}
		return "klh/edb/orderList";
	}
	
	/**
	 * 易店宝订单详情
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edbOrderInfo")
	public String edbOrderInfo(Model model, int productId, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		return "klh/edb/orderInfo";
	}
	

}
