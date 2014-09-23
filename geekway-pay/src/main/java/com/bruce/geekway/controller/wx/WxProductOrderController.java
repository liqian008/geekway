package com.bruce.geekway.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
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
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.utils.HtmlBuildUtils;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.ResponseUtil;

/**
 * 个人订单
 * @author liqian
 *
 */
@Controller
public class WxProductOrderController {
	

	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	
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
		List<WxProduct> productList = wxProductService.queryAvailableList();
		model.addAttribute("productList", productList);
		return "product/myOrderList";
	}
	
	/**
	 * 我的订单
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/vouchers")
	public String vouchers(Model model, @RequestParam(required=false)String code, HttpServletRequest request, HttpServletResponse response) {
		if(!StringUtils.isBlank(code)){//oauth回调后
			//根据code换取openId
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			if(oauthResult!=null){
				String userOpenId = oauthResult.getOpenid();
				System.out.println("OAUTH openId: "+userOpenId);
				ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_OPENID, userOpenId);
			}
		}else{
			
		}
		return "product/myVoucherList";
	}
	
	@RequestMapping(value = "/moreVouchers.json")
	public ModelAndView moreVouchers(Model model, @RequestParam("tailId") long tailId, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多【我的优惠券】，tailId: "+tailId);
        }
	    int limit = 2;
		List<WxProductVoucher> productVoucherList = null;
		
	    String userOpenId = "1";
		Cookie[] cookieArray = request.getCookies();
		if(cookieArray!=null&&cookieArray.length>0){
			for(Cookie cookie: cookieArray){
				if(ConstFront.COOKIE_KEY_WX_OPENID.equals(cookie.getName())){
					userOpenId = cookie.getValue();
					break;
				}
			}
		}
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
	
}
