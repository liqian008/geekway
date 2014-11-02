package com.bruce.geekway.admin.controller.order;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.admin.constants.ConstAdmin;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.service.pay.IWxPayService;
import com.bruce.geekway.service.pay.mp.WxMpPayService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.utils.WxAuthUtil;


/**
 * 微信订单管理
 * @author liqian
 *
 */
@Controller
@RequestMapping("/order")
public class WxOrderController {
	
	private static final int pageSize = ConstAdmin.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxProductOrderService wxProductOrderService;
	@Autowired
	private IWxProductOrderItemService wxProductOrderItemService;
	@Autowired
	private WxMpPayService wxMpPayService;
	@Autowired
	private IWxPayService wxPayService;
	
	
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
		criteria.setOrderByClause(" id desc");
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
	 * 发货操作
	 * @param model
	 * @param openId
	 * @param transId
	 * @param outTradeNo
	 * @param deliverStatus
	 * @param deliverMsg
	 * @param postType
	 * @param postSn
	 * @param request
	 * @return
	 */
	@RequestMapping("/deliver")
	public String deliver(Model model, String openId, String transId, String outTradeNo, int deliverStatus, String deliverMsg, String postType, String postSn, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductOrder order = wxProductOrderService.loadByTradeNo(outTradeNo);
		if(order!=null){
			//构造微信发货对象
			WxDeliverInfo deliveryInfo = buildDeliveryInfo(openId, transId, outTradeNo, deliverStatus, deliverMsg);
			//调用微信发货通知接口
			int result = wxPayService.dealWxDeliver(deliveryInfo);
			if(result>0){
				//更新订单发货状态
				WxProductOrder updatedOrder = new WxProductOrder();
				updatedOrder.setId(order.getId());
				updatedOrder.setPostType(postType);
				updatedOrder.setPostSn(postSn);
				updatedOrder.setStatus(IWxProductOrderService.StatusEnum.DELIVERED.getStatus());//状态改已发货
				result = wxProductOrderService.updateById(updatedOrder);//更新操作
			}
		}
		model.addAttribute("redirectUrl", "./orderInfo?outTradeNo="+outTradeNo);
		return "forward:/home/operationRedirect";
	}
	
	
	
	/**
	 * 构造用于发货的对象
	 * @param openId
	 * @param transId
	 * @param outTradeNo
	 * @param deliverStatus
	 * @param deliverMsg
	 * @return 
	 */
	private static WxDeliverInfo buildDeliveryInfo(String openId, String transId, String outTradeNo, int deliverStatus, String deliverMsg) {
		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		
		SortedMap<String, String> signMap = new TreeMap<String, String>();
		//无需传入appid，签名算法中会自动填充
		signMap.put("appkey", ConstWeixin.WX_APP_SECRET_KEY);
		signMap.put("openid", openId);
		signMap.put("transid", transId);
		signMap.put("out_trade_no", outTradeNo);
		signMap.put("deliver_timestamp", timestamp);
		signMap.put("deliver_status", String.valueOf(deliverStatus));
		signMap.put("deliver_msg", deliverMsg);
		//生成appSign
		String appSign = WxAuthUtil.paySign(signMap);
		
		
		WxDeliverInfo deliveryInfo = new WxDeliverInfo();
		deliveryInfo.setAppid(ConstWeixin.WX_APP_ID);
		deliveryInfo.setOpenid(openId);
		deliveryInfo.setTransid(transId);
		deliveryInfo.setOut_trade_no(outTradeNo);
		deliveryInfo.setDeliver_timestamp(timestamp);
		deliveryInfo.setDeliver_status(String.valueOf(deliverStatus));
		deliveryInfo.setDeliver_msg(deliverMsg);
		deliveryInfo.setApp_signature(appSign);
		deliveryInfo.setSign_method("sha1");
		return deliveryInfo;
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
