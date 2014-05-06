package com.bruce.geekway.admin.controller.geekway;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.service.IWxCustomizeMenuService;


@Controller
@RequestMapping("/geekway")
public class GeekwayCustomizeMenuController {

	@Autowired
	private IWxCustomizeMenuService wxCustomizeMenuService;
//	@Autowired
//	private IWxCodeModuleService wxCodeModuleService;
	
	
	@RequestMapping("/customizeMenuList")
	public String customizeMenuList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCustomizeMenu> customizeMenuList = wxCustomizeMenuService.querySortedMenus();
		
		List<WxCustomizeMenu> groupedMenuList = groupCustomizeMenu(customizeMenuList);
		
		model.addAttribute("customizeMenuList", groupedMenuList);
		return "customizeMenu/customizeMenuList";
	}

	private List<WxCustomizeMenu> groupCustomizeMenu(List<WxCustomizeMenu> customizeMenuList) {
		if(customizeMenuList!=null&&customizeMenuList.size()>0){
			List<WxCustomizeMenu> groupedList = new ArrayList<WxCustomizeMenu>();
			for(WxCustomizeMenu loopMenu1: customizeMenuList){
				int level1MenuId = 0;
				if(0==loopMenu1.getParentId()){
					level1MenuId = loopMenu1.getId();
					groupedList.add(loopMenu1);
					for(WxCustomizeMenu loopMenu2: customizeMenuList){
						if(level1MenuId==loopMenu2.getParentId()){
							groupedList.add(loopMenu2);
						}
					}
				}
			}
			return groupedList;
		}
		return null;
	}
	
	@RequestMapping("/customizeMenuAdd")
	public String customizeMenuAdd(Model model, WxCustomizeMenu customizeMenu, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//构造父菜单列表
		List<WxCustomizeMenu> parentMenus = wxCustomizeMenuService.queryChildrenMenus(0);
		WxCustomizeMenu rootMenu = new WxCustomizeMenu();
		rootMenu.setId(0);
		rootMenu.setMenuName("--顶级菜单--");
		parentMenus.add(0, rootMenu);
		model.addAttribute("parentMenus", parentMenus);
		
		model.addAttribute("customizeMenu", customizeMenu);
		return "customizeMenu/customizeMenuEdit";
	}
	
	@RequestMapping("/customizeMenuEdit")
	public String customizeMenuEdit(Model model, HttpServletRequest request, int customizeMenuId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//构造父菜单列表
		List<WxCustomizeMenu> parentMenus = wxCustomizeMenuService.queryChildrenMenus(0);
		WxCustomizeMenu rootMenu = new WxCustomizeMenu();
		rootMenu.setId(0);
		rootMenu.setMenuName("--顶级菜单--");
		parentMenus.add(0, rootMenu);
		model.addAttribute("parentMenus", parentMenus);
		
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
