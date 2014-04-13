package com.bruce.geekway.admin.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxCodeModule;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.service.IWxArticleService;
import com.bruce.geekway.service.IWxCodeModuleService;
import com.bruce.geekway.service.IWxCustomizeMenuService;


@Controller
@RequestMapping("/geekway")
public class GeekwayCustomizeMenuController {

	@Autowired
	private IWxCustomizeMenuService wxCustomizeMenuService;
	@Autowired
	private IWxCodeModuleService wxCodeModuleService;
	
	
	@RequestMapping("/customizeMenuList")
	public String customizeMenuList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCustomizeMenu> customizeMenuList = wxCustomizeMenuService.queryAll();
		model.addAttribute("customizeMenuList", customizeMenuList);
		return "customizeMenu/customizeMenuList";
	}
	
	@RequestMapping("/customizeMenuAdd")
	public String customizeMenuAdd(Model model, WxCustomizeMenu customizeMenu, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
		model.addAttribute("codeModuleList", codeModuleList);
		
		model.addAttribute("customizeMenu", customizeMenu);
		return "customizeMenu/customizeMenuEdit";
	}
	
	@RequestMapping("/customizeMenuEdit")
	public String customizeMenuEdit(Model model, HttpServletRequest request, int customizeMenuId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
		model.addAttribute("codeModuleList", codeModuleList);
		
		WxCustomizeMenu CustomizeMenu = wxCustomizeMenuService.loadById(customizeMenuId);
		model.addAttribute("customizeMenu", CustomizeMenu);
		
		return "customizeMenu/customizeMenuEdit";
	}
	
	@RequestMapping(value = "/saveCustomizeMenu", method = RequestMethod.POST)
	public String saveCustomizeMenu(Model model, WxCustomizeMenu customizeMenu, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		customizeMenu.setUpdateTime(currentTime);
		
		if(1==customizeMenu.getDisplayType()){
			//文本回复情况下，重新初始化数据
			customizeMenu.setModuleDesc("");
			customizeMenu.setModuleId(0);
		}else if(2==customizeMenu.getDisplayType()){
			customizeMenu.setReplyContent("");
			
			int codeModuleId = customizeMenu.getModuleId();
			WxCodeModule codeModule = wxCodeModuleService.loadById(codeModuleId);
			if(codeModule!=null){
				customizeMenu.setModuleDesc(codeModule.getModuleName());
			}
		}
		
		if(customizeMenu!=null&&customizeMenu.getId()!=null&&customizeMenu.getId()>0){
			result = wxCustomizeMenuService.updateById(customizeMenu);
		}else{
			customizeMenu.setCreateTime(currentTime);
			result = wxCustomizeMenuService.save(customizeMenu);
		}
		
		
		model.addAttribute("redirectUrl", "./customizeMenuList");
		return "forward:/home/operationRedirect";
	}
	
}
