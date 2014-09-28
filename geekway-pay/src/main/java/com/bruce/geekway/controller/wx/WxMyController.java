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
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
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
	private IWxProductService wxProductService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	@Autowired
	private IWxProductOrderService wxProductOrderService;
	
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
//		List<WxProduct> productList = wxProductService.queryAvailableList();
//		model.addAttribute("productList", productList);
		if(logger.isDebugEnabled()){
			logger.debug("进入[我的订单], debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
		}
		return "order/myOrderList";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "/moreOrders.json")
	public ModelAndView moreOrders(Model model, @RequestParam("tailId") long tailId, HttpServletRequest request) {
		String userOpenId = (String)request.getAttribute(ConstFront.CURRENT_USER);
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多【我的订单】，userOpenId: "+userOpenId+" , tailId: "+tailId);
        }
	    int limit = 2;
		List<WxProductOrder> productOrderList = null;
		
		//用户有效
		if(!StringUtils.isBlank(userOpenId)){
			productOrderList = wxProductOrderService.fallLoadUserOrderList(userOpenId, tailId, limit+1);
		}
		
		long nextTailId = 0;
		if (productOrderList == null || productOrderList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多优惠券");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
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
		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);
//		String userAccessToken = (String) request.getAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN);
//		if(!ConstWeixin.WX_OAUTH_DEBUG){
//			if(!StringUtils.isBlank(code)){//oauth回调后
//				if(logger.isDebugEnabled()){
//					logger.debug("微信oauth回调后进入[我的优惠券], code: "+code);
//				}
//				//根据code换取openId
//				WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
//				if(oauthResult!=null){
//					userOpenId = oauthResult.getOpenid();
//					if(logger.isDebugEnabled()){
//						logger.debug("微信oauth回调后进入[我的优惠券], 换取的userOpenId，并写入cookie: "+userOpenId);
//					}
//					ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_OPENID, userOpenId);
//				}
//			}
//		}
		
		return "order/myVoucherList";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "/moreVouchers.json")
	public ModelAndView moreVouchers(Model model, @RequestParam("tailId") long tailId, HttpServletRequest request) {
		String userOpenId = (String)request.getAttribute(ConstFront.CURRENT_USER);
		
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多【我的优惠券】，userOpenId: "+userOpenId+" , tailId: "+tailId);
        }
	    int limit = 2;
		List<WxProductVoucher> productVoucherList = null;
		
		//用户有效
		if(!StringUtils.isBlank(userOpenId)){
			productVoucherList = wxProductVoucherService.fallLoadUserVoucherList(userOpenId, tailId, limit+1);
		}
		
		long nextTailId = 0;
		if (productVoucherList == null || productVoucherList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多优惠券");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
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
	}
	
	
	@NeedAuthorize
	@RequestMapping(value = "/applyVoucher.json")
	public ModelAndView moreVouchers(Model model, HttpServletRequest request) {
		String userOpenId = (String)request.getAttribute(ConstFront.CURRENT_USER);
		if (logger.isDebugEnabled()) {
			logger.debug("ajax获得优惠券，userOpenId: " + userOpenId);
		}
		WxProductVoucher voucher = wxProductVoucherService.applyVoucher(userOpenId);
		if(voucher!=null){//成功
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(voucher));
		}
		return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
}
