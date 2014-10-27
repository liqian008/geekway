package com.bruce.geekway.admin.controller.wx;

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
import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayAlarmCriteria;
import com.bruce.geekway.service.pay.IWxPayAlarmService;


/**
 * 微信告警
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxAlarm")
public class WxAlarmController {
	
	private static final int pageSize = 20;
	
	@Autowired
	private IWxPayAlarmService wxPayAlarmService;

	
	@RequestMapping("/alarmList")
	public String alarmList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayAlarm> alarmList = wxPayAlarmService.queryAll();
		model.addAttribute("alarmList", alarmList);
		return "alarm/alarmList";
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
	@RequestMapping("/alarmPaging")
	public String alarmPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxPayAlarmCriteria criteria =  null;
		//根据模块的需求构造查询条件
		String resourceName = request.getParameter("resourceName");
		if(StringUtils.isNotBlank(resourceName)){
			criteria = new WxPayAlarmCriteria();
			if("get".equalsIgnoreCase(request.getMethod())){
				resourceName = URLDecoder.decode(resourceName);
			}
			model.addAttribute("resourceName", resourceName);
//			criteria.createCriteria().andResourceNameLike("%"+resourceName+"%");
		}
		
		PagingResult<WxPayAlarm> alarmPagingData = wxPayAlarmService.pagingByCriteria(pageNo, pageSize , criteria);
		if(alarmPagingData!=null){
			alarmPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap();
			queryMap.putAll(request.getParameterMap());
			alarmPagingData.setQueryMap(queryMap);
			model.addAttribute("alarmPagingData", alarmPagingData);
		}
		return "alarm/alarmListPaging";
	}
	
	
	
	@RequestMapping("/alarmInfo")
	public String alarmInfo(Model model, HttpServletRequest request, int alarmId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayAlarm alarm = wxPayAlarmService.loadById(alarmId);
		model.addAttribute("alarm", alarm);
		return "alarm/alarmInfo";
	}
	
	
	
}
