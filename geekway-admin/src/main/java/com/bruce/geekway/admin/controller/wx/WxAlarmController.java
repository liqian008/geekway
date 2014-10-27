package com.bruce.geekway.admin.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.service.pay.IWxPayAlarmService;


/**
 * 微信告警
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxAlarm")
public class WxAlarmController {
	
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
	
	@RequestMapping("/alarmInfo")
	public String alarmInfo(Model model, HttpServletRequest request, int alarmId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayAlarm alarm = wxPayAlarmService.loadById(alarmId);
		model.addAttribute("alarm", alarm);
		return "alarm/alarmInfo";
	}
	
	
	
}
