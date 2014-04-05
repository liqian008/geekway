package com.bruce.geekway.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.service.IWxTextCodeService;


@Controller
@RequestMapping("/geekway")
public class GeekwayTextCodeController {

	@Autowired
	private IWxTextCodeService wxTextCodeService;
	
	@RequestMapping("/textCodeList")
	public String textCodeList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxTextCode> textCodeList = wxTextCodeService.queryAll();
		model.addAttribute("textCodeList", textCodeList);
		return "textCode/textCodeList";
	}
	
	@RequestMapping("/textCodeAdd")
	public String textCodeAdd(Model model, WxTextCode textCode, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("textCode", textCode);
		return "textCode/textCodeEdit";
	}
	
	@RequestMapping("/textCodeEdit")
	public String textCodeEdit(Model model, HttpServletRequest request, int textCodeId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxTextCode textCode = wxTextCodeService.loadById(textCodeId);
		model.addAttribute("textCode", textCode);
		return "textCode/textCodeEdit";
	}
	
	@RequestMapping(value = "/saveTextCode", method = RequestMethod.POST)
	public String saveTextCode(Model model, WxTextCode textCode, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		textCode.setUpdateTime(currentTime);
		if(textCode!=null&&textCode.getId()!=null&&textCode.getId()>0){
			result = wxTextCodeService.updateById(textCode);
		}else{
			textCode.setCreateTime(currentTime);
			result = wxTextCodeService.save(textCode);
		}
		
		model.addAttribute("redirectUrl", "./textCodeList");
		return "forward:/home/operationRedirect";
	}
	
}
