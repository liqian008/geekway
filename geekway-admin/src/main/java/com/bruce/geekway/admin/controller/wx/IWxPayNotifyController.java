package com.bruce.geekway.admin.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxPayNotifyOrder;
import com.bruce.geekway.service.pay.IWxPayNotifyOrderService;


/**
 * 微信支付通知
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxNotify")
public class IWxPayNotifyController {
	
	@Autowired
	private IWxPayNotifyOrderService wxPayNotifyOrderService;
	
	@RequestMapping("/notifyList")
	public String notifyList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayNotifyOrder> notifyList = wxPayNotifyOrderService.queryAll();
		model.addAttribute("notifyList", notifyList);
		return "notify/notifyList";
	}
	
	@RequestMapping("/notifyInfo")
	public String notifyInfo(Model model, HttpServletRequest request, int notifyId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayNotifyOrder notify = wxPayNotifyOrderService.loadById(notifyId);
		model.addAttribute("notify", notify);
		return "notify/notifyInfo";
	}
	
	
}
