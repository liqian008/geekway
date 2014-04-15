package com.bruce.geekway.admin.controller.mp;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.wx.json.WxMenuBtnEntity;
import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;
import com.bruce.geekway.model.wx.json.response.WxMenuQueryResult;
import com.bruce.geekway.service.mp.WxMenuService;


@Controller
@RequestMapping("/geekway")
public class GeekwayMpCustomizeMenuController {

	@Autowired
	private WxMenuService wxMenuService;
	
	@RequestMapping("/mpMenuList")
	public String mpMenuList(Model model, HttpServletRequest request) {
		WxMenuQueryResult menuQueryResult = wxMenuService.menuGet();
		if(menuQueryResult!=null){
			WxMenuCreateJson menuJsonObj = menuQueryResult.getMenu();
			if(menuJsonObj!=null){
				List<WxMenuBtnEntity> menuButtonList = buildMenuList(new ArrayList<WxMenuBtnEntity>(), menuJsonObj.getButton(), 1);
				model.addAttribute("menuButtonList", menuButtonList);
			}
		}
//		model.addAttribute("menuButtonList", buildMenuList(new ArrayList<WxMenuBtnEntity>(), getMockMenuList(), 1));
		
		return "mp/menuList";
	}
	
	@RequestMapping("/mpMenuDelete")
	public String mpMenuDelete(Model model, HttpServletRequest request) {
		//删除自定义菜单
		//		wxMenuService.menuDelete(wxMenuService.getAccessToken());
		model.addAttribute("redirectUrl", "./menuList");
		return "forward:/home/operationRedirect";
	}
	
	
	private List<WxMenuBtnEntity> buildMenuList(List<WxMenuBtnEntity> container, List<WxMenuBtnEntity> menus, int level){
		if(menus!=null&&menus!=null){
			for(WxMenuBtnEntity loopItem: menus){
				loopItem.setLevel(level);
				container.add(loopItem);
				if(loopItem!=null&&loopItem.getSub_button()!=null&&loopItem.getSub_button().size()>0){
					//递归遍历子菜单
					buildMenuList(container, loopItem.getSub_button(), level+1);
				}
			}
			return container;
		}
		return null;
	}
	
//	private List<WxMenuBtnEntity> buildMenuList(List<WxMenuBtnEntity> container, WxMenuBtnEntity rootMenu, int level){
//		if(rootMenu!=null&&rootMenu.getSub_button()!=null){
//			level = level+1;
//			List<WxMenuBtnEntity> subList = rootMenu.getSub_button();
//			for(WxMenuBtnEntity loopItem: subList){
//				container.add(loopItem);
//				buildMenuList(container, loopItem, level);
//			}
//			return container;
//		}
//		return null;
//	}
	
	
	
	public static void main(String[] args) {
		GeekwayMpCustomizeMenuController instance = new GeekwayMpCustomizeMenuController();
		
		WxMenuService wxMenuService = new WxMenuService();
		WxMenuQueryResult menuQueryResult = wxMenuService.menuGet();
		if(menuQueryResult!=null){
			WxMenuCreateJson menuJsonObj = menuQueryResult.getMenu();
			if(menuJsonObj!=null){
				List<WxMenuBtnEntity> menuList = instance.buildMenuList(new ArrayList<WxMenuBtnEntity>(), menuJsonObj.getButton(), 1);
				System.out.println(menuList);
			}
		}
		
		List<WxMenuBtnEntity> container = new ArrayList<WxMenuBtnEntity>();
		List<WxMenuBtnEntity> total = instance.buildMenuList(container, getMockMenuList(), 1);
//		System.out.println(total);
	}


	private static List<WxMenuBtnEntity> getMockMenuList() {
		WxMenuBtnEntity root1 = new WxMenuBtnEntity("menu1","menu1");
		WxMenuBtnEntity root2 = new WxMenuBtnEntity("menu2","menu2");
		
		WxMenuBtnEntity level21 = new WxMenuBtnEntity("menu21","menu21");
		WxMenuBtnEntity level22 = new WxMenuBtnEntity("menu22","menu22");
		WxMenuBtnEntity level23 = new WxMenuBtnEntity("menu23","menu23");
		
		WxMenuBtnEntity level211 = new WxMenuBtnEntity("menu211","menu211");
		WxMenuBtnEntity level212 = new WxMenuBtnEntity("menu212","menu212");
		WxMenuBtnEntity level213 = new WxMenuBtnEntity("menu213","menu213");
		
		WxMenuBtnEntity level231 = new WxMenuBtnEntity("menu231","menu231");
		WxMenuBtnEntity level232 = new WxMenuBtnEntity("menu232","menu232");
		WxMenuBtnEntity level233 = new WxMenuBtnEntity("menu233","menu233");
		
		root1.addSubButton(level21);
		root1.addSubButton(level22);
		root1.addSubButton(level23);
		
		level21.addSubButton(level211);
		level21.addSubButton(level212);
		level21.addSubButton(level213);
		
		level23.addSubButton(level231);
		level23.addSubButton(level232);
		level23.addSubButton(level233);
		
		
		List<WxMenuBtnEntity> menus = new ArrayList<WxMenuBtnEntity>();
		menus.add(root2);
		menus.add(root1);
		
		return menus;
	}
	
	
	
	
	
//	private List<WxMenuBtnEntity> buildMenuList(WxMenuBtnEntity rootMenu){
//		if(rootMenu!=null&&rootMenu.getSub_button()!=null){
//			List<WxMenuBtnEntity> menuList = rootMenu.getSub_button();
//			for(WxMenuBtnEntity loopButton: menuList){
//				buildMenuList(loopButton);
//			}
//			return menuList;
//		}
//		return null;
//	}
	
//	private List<WxMenuBtnEntity> buildMenuList(List<WxMenuBtnEntity> container, WxMenuCreateJson rootMenu){
//		if(rootMenu!=null&&rootMenu.getButton()!=null){
//			List<WxMenuBtnEntity> menuList = rootMenu.getButton();
//			for(WxMenuBtnEntity loopButton: menuList){
//				container.addAll(buildMenuList(loopButton));
//			}
//			return menuList;
//		}
//		return null;
//	} 
	
	
//	@RequestMapping("/menuCreate")
//	public String customizeMenuAdd(Model model, HttpServletRequest request) {
//		wxMenuService.menuCreate(wxMenuService.getAccessToken(), null);
//		return "mp/customizeMenuList";
//	}
	
	
}
