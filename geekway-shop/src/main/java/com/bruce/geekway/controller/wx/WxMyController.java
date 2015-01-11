package com.bruce.geekway.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.ProductOrder;
import com.bruce.geekway.model.ProductVoucher;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IProductOrderService;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductVoucherService;
import com.bruce.geekway.utils.HtmlBuildUtils;
import com.bruce.geekway.utils.ResponseBuilderUtil;

/**
 * 个人订单
 * @author liqian
 *
 */
@Controller
public class WxMyController{
	
	@Autowired
	private IProductService productService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	@Autowired
	private IProductVoucherService productVoucherService;
	@Autowired
	private IProductOrderService productOrderService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);
	
	/**
	 * 我的订单
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/orders")
	public String orders(Model model, HttpServletRequest request) {
//		List<WxProduct> productList = productService.queryAvailableList();
//		model.addAttribute("productList", productList);
		if(logger.isDebugEnabled()){
			logger.debug("进入[我的订单], debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
		}
		return "order/myOrderList";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "/moreOrders.json")
	public ModelAndView moreOrders(Model model, @RequestParam("tailId") long tailId, HttpServletRequest request) {
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String userOpenId = wxWebUser.getOpenId();
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多【我的订单】，userOpenId: "+userOpenId+" , tailId: "+tailId);
        }
	    int limit = 2;
		List<ProductOrder> productOrderList = null;
		
		//用户有效
		if(!StringUtils.isBlank(userOpenId)){
			productOrderList = productOrderService.fallLoadCachedUserOrderList(userOpenId, tailId, limit+1);
		}
		
		long nextTailId = 0;
		if (productOrderList == null || productOrderList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多优惠券");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		}
		if (productOrderList.size() > limit) {// 查询数据超过limit，含分页内容
			// 移除最后一个元素
			productOrderList.remove(limit);
			nextTailId = productOrderList.get(limit - 1).getId();//取id
			if(logger.isDebugEnabled()){
                logger.debug("还有更多优惠券，tailId： "+nextTailId);
            }
		}
		String productListHtml = HtmlBuildUtils.buildFallLoadOrdersHtml(productOrderList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		dataMap.put("tailId", String.valueOf(nextTailId));
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	/**
	 * 我的优惠券
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/vouchers")
	public String vouchers(Model model, HttpServletRequest request, HttpServletResponse response) {
		if(logger.isDebugEnabled()){
			logger.debug("进入[我的优惠券], debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
		}
		return "order/myVoucherList";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "/moreVouchers.json")
	public ModelAndView moreVouchers(Model model, @RequestParam("tailId") long tailId, HttpServletRequest request) {
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String userOpenId = wxWebUser.getOpenId();
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多【我的优惠券】，userOpenId: "+userOpenId+" , tailId: "+tailId);
        }
	    int limit = 2;
		List<ProductVoucher> productVoucherList = null;
		
		//用户有效
		if(!StringUtils.isBlank(userOpenId)){
			productVoucherList = productVoucherService.fallLoadUserVoucherList(userOpenId, tailId, limit+1);
		}
		
		long nextTailId = 0;
		if (productVoucherList == null || productVoucherList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多优惠券");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		}
		if (productVoucherList.size() > limit) {// 查询数据超过limit，含分页内容
			// 移除最后一个元素
			productVoucherList.remove(limit);
			nextTailId = productVoucherList.get(limit - 1).getId();//取id
			if(logger.isDebugEnabled()){
                logger.debug("还有更多优惠券，tailId： "+nextTailId);
            }
		}
		String productListHtml = HtmlBuildUtils.buildFallLoadVouchersHtml(productVoucherList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		dataMap.put("tailId", String.valueOf(nextTailId));
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	/**
	 * 提取【优惠码】
	 * 因微信分享策略限制（禁止以奖励的形式促使分享），该接口下线
	 * @param model
	 * @param request
	 * @return
	 */
//	@NeedAuthorize
//	@RequestMapping(value = "/applyVoucher.json")
//	public ModelAndView moreVouchers(Model model, HttpServletRequest request) {
//		String userOpenId = (String)request.getAttribute(ConstFront.CURRENT_USER);
//		if (logger.isDebugEnabled()) {
//			logger.debug("ajax获得优惠券，userOpenId: " + userOpenId);
//		}
//		WxProductVoucher voucher = productVoucherService.applyVoucher(userOpenId);
//		if(voucher!=null){//成功
//			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(voucher));
//		}
//		return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
//	}
	
}
