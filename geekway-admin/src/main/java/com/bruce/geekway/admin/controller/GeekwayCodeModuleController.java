package com.bruce.geekway.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxCodeModule;
import com.bruce.geekway.service.IWxCodeModuleService;


@Controller
@RequestMapping("/geekway")
public class GeekwayCodeModuleController {

	@Autowired
	private IWxCodeModuleService wxCodeModuleService;
	
	@RequestMapping("/codeModuleList")
	public String codeModuleList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
		model.addAttribute("codeModuleList", codeModuleList);
		return "codeModule/codeModuleList";
	}
	
	@RequestMapping("/codeModuleAdd")
	public String codeModuleAdd(Model model, WxCodeModule CodeModule, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("codeModule", CodeModule);
		return "codeModule/codeModuleEdit";
	}
	
	@RequestMapping("/codeModuleEdit")
	public String codeModuleEdit(Model model, HttpServletRequest request, int codeModuleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxCodeModule CodeModule = wxCodeModuleService.loadById(codeModuleId);
		model.addAttribute("codeModule", CodeModule);
		return "codeModule/codeModuleEdit";
	}
	
	@RequestMapping(value = "/saveCodeModule", method = RequestMethod.POST)
	public String saveCodeModule(Model model, WxCodeModule CodeModule, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		CodeModule.setUpdateTime(currentTime);
		if(CodeModule!=null&&CodeModule.getId()!=null&&CodeModule.getId()>0){
			result = wxCodeModuleService.updateById(CodeModule);
		}else{
			CodeModule.setCreateTime(currentTime);
			result = wxCodeModuleService.save(CodeModule);
		}
		
		model.addAttribute("redirectUrl", "./codeModuleList");
		return "forward:/home/operationRedirect";
	}
	
}
