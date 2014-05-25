package com.bruce.geekway.admin.controller.ito;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.service.ito.IItoSystemStatusService;


@Controller
@RequestMapping("/ito")
public class ItoSystemStatusController {

	@Autowired
	private IItoSystemStatusService itoSystemStatusService;
	
	
	@RequestMapping("/systemStatusList")
	public String systemStatusList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoSystemStatus> systemStatusList = itoSystemStatusService.queryAll();
		model.addAttribute("systemStatusList", systemStatusList);
		return "ito/systemStatusList";
	}
	
	
}
