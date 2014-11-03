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
import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayAlarmCriteria;
import com.bruce.geekway.service.pay.IWxPayAlarmService;


/**
 * 微信告警
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WxAlarmController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxPayAlarmService wxPayAlarmService;

	
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/alarmPaging")
	public String alarmPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxPayAlarmCriteria criteria = new WxPayAlarmCriteria();
		criteria.setOrderByClause(" id desc");
		WxPayAlarmCriteria.Criteria subCriteria = criteria.createCriteria();
		
		//根据模块的需求构造查询条件
		String errorType = request.getParameter("errorType");
		if(StringUtils.isNotBlank(errorType)){
			if("get".equalsIgnoreCase(request.getMethod())){
				errorType = URLDecoder.decode(errorType);
			}
			subCriteria.andErrorTypeEqualTo(errorType);
			model.addAttribute("errorType", errorType);
		}
		String description = request.getParameter("description");
		if(StringUtils.isNotBlank(description)){
			if("get".equalsIgnoreCase(request.getMethod())){
				description = URLDecoder.decode(description);
			}
			subCriteria.andDescriptionLike(description);
			model.addAttribute("description", description);
		}
		
		PagingResult<WxPayAlarm> alarmPagingData = wxPayAlarmService.pagingByCriteria(pageNo, pageSize , criteria);
		if(alarmPagingData!=null){
			alarmPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			alarmPagingData.setQueryMap(queryMap);
			model.addAttribute("alarmPagingData", alarmPagingData);
		}
		return "wxpay/alarmListPaging";
	}
	
	
	
	@RequestMapping("/alarmInfo")
	public String alarmInfo(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayAlarm alarm = wxPayAlarmService.loadById(id);
		model.addAttribute("alarm", alarm);
		return "wxpay/alarmInfo";
	}
	
	

	@RequestMapping("/alarmList")
	public String alarmList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayAlarm> alarmList = wxPayAlarmService.queryAll();
		model.addAttribute("alarmList", alarmList);
		return "wxpay/alarmList";
	}
	
}
