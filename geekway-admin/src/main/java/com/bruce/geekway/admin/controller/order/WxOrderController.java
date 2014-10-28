package com.bruce.geekway.admin.controller.order;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;


/**
 * 微信支付通知
 * @author liqian
 *
 */
@Controller
@RequestMapping("/order")
public class WxOrderController {
	
	private static final int pageSize = 1;
	
	@Autowired
	private IWxProductOrderService wxProductOrderService;
	@Autowired
	private IWxProductOrderItemService wxProductOrderItemService;
	
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/orderPaging")
	public String productPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		WxProductOrderCriteria.Criteria subCriteria = criteria.createCriteria();
		
		//根据模块的需求构造查询条件
		String outTradeNo = request.getParameter("outTradeNo");
		if(StringUtils.isNotBlank(outTradeNo)){
			if("get".equalsIgnoreCase(request.getMethod())){
				outTradeNo = URLDecoder.decode(outTradeNo);
			}
			subCriteria.andOutTradeNoEqualTo(outTradeNo);
			model.addAttribute("outTradeNo", outTradeNo);
		}
		
		PagingResult<WxProductOrder> orderPagingData = wxProductOrderService.pagingByCriteria(pageNo, pageSize , criteria);
		if(orderPagingData!=null){
			orderPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			orderPagingData.setQueryMap(queryMap);
			model.addAttribute("orderPagingData", orderPagingData);
		}
		return "order/orderListPaging";
	}

	
	@RequestMapping("/orderInfo")
	public String orderInfo(Model model, String outTradeNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductOrder order = wxProductOrderService.loadByTradeNo(outTradeNo);
		model.addAttribute("order", order);
		
		List<WxProductOrderItem> productOrderItemList = wxProductOrderItemService.queryByTradeNo(outTradeNo);
		model.addAttribute("productOrderItemList", productOrderItemList);
		
		return "order/orderInfo";
	}
	
	
//	@Deprecated
//	@RequestMapping("/productOrderList")
//	public String productOrderList(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		List<WxProductOrder> productOrderList = wxProductOrderService.queryAll();
//		model.addAttribute("productOrderList", productOrderList);
//		return "order/productOrderList";
//	}
	
}
