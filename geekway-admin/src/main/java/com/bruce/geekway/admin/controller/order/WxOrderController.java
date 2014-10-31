package com.bruce.geekway.admin.controller.order;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.service.pay.mp.WxMpPayService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;


/**
 * 微信订单管理
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
	@Autowired
	private WxMpPayService wxMpPayService;
	
	
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
		
		
		//订单状态
		String statusStr = request.getParameter("status");
		short status = NumberUtils.toShort(statusStr, (short) -1);
		if(status>=0){
			subCriteria.andStatusEqualTo(status);
			model.addAttribute("status", status);
		}
		
		//订单时间范围查询
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		
		
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
	
	
	/**
	 * 发货页面
	 * @param model
	 * @param outTradeNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/deliveryInfo")
	public String deliveryInfo(Model model, String outTradeNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductOrder order = wxProductOrderService.loadByTradeNo(outTradeNo);
		model.addAttribute("order", order);
		
		List<WxProductOrderItem> productOrderItemList = wxProductOrderItemService.queryByTradeNo(outTradeNo);
		model.addAttribute("productOrderItemList", productOrderItemList);
		
		return "order/orderInfo";
	}
	
	
	/**
	 * 发货操作
	 * @param model
	 * @param outTradeNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/deliver")
	public String deliver(Model model, String outTradeNo, String postType, String postSn, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductOrder order = wxProductOrderService.loadByTradeNo(outTradeNo);
		if(order!=null){
			//更新订单发货状态
			WxProductOrder updatedOrder = new WxProductOrder();
			updatedOrder.setId(order.getId());
			updatedOrder.setPostType(postType);
			updatedOrder.setPostSn(postSn);
			updatedOrder.setStatus(IWxProductOrderService.StatusEnum.DELIVERED.getStatus());//状态改已发货
			int result = wxProductOrderService.updateById(updatedOrder);//更新操作
			if(result>0){
				//TODO 发货完毕后需要通知微信服务
				//wxMpPayService.deliverNotify(deliverInfo);
			}
		}
		model.addAttribute("redirectUrl", "./orderInfo?outTradeNo="+outTradeNo);
		return "forward:/home/operationRedirect";
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
