package com.bruce.geekway.admin.controller.wxpay;

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
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxPayNotifyOrder;
import com.bruce.geekway.model.WxPayNotifyOrderCriteria;
import com.bruce.geekway.service.pay.IWxPayNotifyOrderService;


/**
 * 微信支付通知
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayNotifyController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxPayNotifyOrderService wxPayNotifyOrderService;
	
	@RequestMapping("/notifyList")
	public String notifyList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayNotifyOrder> notifyList = wxPayNotifyOrderService.queryAll();
		model.addAttribute("notifyList", notifyList);
		return "wxpay/notifyList";
	}
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/notifyPaging")
	public String notifyPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxPayNotifyOrderCriteria criteria = new WxPayNotifyOrderCriteria();
		criteria.setOrderByClause(" id desc");
		WxPayNotifyOrderCriteria.Criteria subCriteria = criteria.createCriteria();
		
		//根据模块的需求构造查询条件
		String openId = request.getParameter("openId");
		if(StringUtils.isNotBlank(openId)){
			if("get".equalsIgnoreCase(request.getMethod())){
				openId = URLDecoder.decode(openId);
			}
			subCriteria.andOpenIdEqualTo(openId);
			model.addAttribute("openId", openId);
		}
		
		PagingResult<WxPayNotifyOrder> notifyPagingData = wxPayNotifyOrderService.pagingByCriteria(pageNo, pageSize , criteria);
		if(notifyPagingData!=null){
			notifyPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			notifyPagingData.setQueryMap(queryMap);
			model.addAttribute("notifyPagingData", notifyPagingData);
		}
		return "wxpay/notifyListPaging";
	}
	
	
	
	@RequestMapping("/notifyInfo")
	public String notifyInfo(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayNotifyOrder notify = wxPayNotifyOrderService.loadById(id);
		model.addAttribute("notify", notify);
		return "wxpay/notifyInfo";
	}
	
	
}
