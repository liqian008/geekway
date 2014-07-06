package com.bruce.geekway.controller.klh;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.controller.ito.ItoUserController;
import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.klh.KlhEdbOrder;
import com.bruce.geekway.service.klh.IKlhProductService;
import com.bruce.geekway.utils.KlhUtil;
/**
 * 可乐惠中易店宝订单查询
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"klh"})
public class KlhOrderController {
	
	private static final String KLH_USER_MOBILE = "klh_user_mobile";

	/**
	 * 检查用户cookie中存的手机号
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edbOrderMobile")
	public String edbOrderMobile(Model model, HttpServletRequest request) {
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				//检查是否填写过手机号码
				if(KLH_USER_MOBILE.equals(cookie.getName())){
					String userMobile = cookie.getValue();
					model.addAttribute("userMobile", userMobile);
					break;
				}
			}
		}
		return "klh/orderMobile";
	}
	
	/**
	 * 易店宝订单列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edbOrderList")
	public String edbOrderList(Model model, @RequestParam(required=true)String userMobile, int periodType,  HttpServletRequest request,  HttpServletResponse response) {
		//将userMobile写入cookie
		Cookie cookie = new Cookie(KLH_USER_MOBILE, userMobile);
		cookie.setMaxAge(999999999);
		response.addCookie(cookie);
		
		model.addAttribute("periodType", periodType);
		
//		long startTime = System.currentTimeMillis();
		List<KlhEdbOrder> edbOrderList = EdbApiUtil.edbTradeGet(periodType);
		if(edbOrderList!=null&&edbOrderList.size()>0){
			//移除非制定手机号码的数据
			for(int i=edbOrderList.size()-1;i>=0;i--){
				KlhEdbOrder order = edbOrderList.get(i);
				if(!userMobile.equals(order.getMobile())){//手机号不相同，需要移除
					edbOrderList.remove(order);
				}
			}
		}
		model.addAttribute("userMobile", userMobile);
		model.addAttribute("edbOrderList", edbOrderList);
		
//		long endTime = System.currentTimeMillis();
//		long costTime = endTime - startTime;
//		System.out.println("==="+(costTime)+"=====edbOrderListStr: "+ edbOrderList);
		
		return "klh/orderList";
	}
	
//	/**
//	 * 易店宝订单详情
//	 * @param model
//	 * @param productId
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/edbOrderInfo")
//	public String edbOrderInfo(Model model, int productId, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
//		
//		return "klh/edb/orderInfo";
//	}
	

}
