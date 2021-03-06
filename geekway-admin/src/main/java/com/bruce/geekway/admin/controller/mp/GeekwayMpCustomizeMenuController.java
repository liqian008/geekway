package com.bruce.geekway.admin.controller.mp;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.wx.json.WxMenuBtnEntity;
import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;
import com.bruce.geekway.model.wx.json.response.WxMenuQueryResult;
import com.bruce.geekway.service.IWxCustomizeMenuService;
import com.bruce.geekway.service.mp.WxMenuService;


@Controller
@RequestMapping("/geekway")
public class GeekwayMpCustomizeMenuController {

	@Autowired
	private WxMenuService wxMenuService;
	@Autowired
	private IWxCustomizeMenuService wxCustomizeMenuService;
	
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
		
		
//		mock的数据
//		model.addAttribute("menuButtonList", buildMenuList(new ArrayList<WxMenuBtnEntity>(), getMockMenuList(), 1));
		
		return "customizeMenu/mpCustomizeMenuList";
	}
	
	@RequestMapping("/mpMenuCreate")
	public String mpMenuCreate(Model model, HttpServletRequest request) {
		
		List<WxCustomizeMenu> customizeMenuList = wxCustomizeMenuService.querySortedMenus();
		
		List<WxMenuBtnEntity> mpMenuList = groupMpMenuList(customizeMenuList);
		if(mpMenuList!=null&&mpMenuList.size()>0){
			WxMenuCreateJson menuCreateJson = new WxMenuCreateJson(mpMenuList);
			wxMenuService.menuCreate(menuCreateJson);
		}
		model.addAttribute("redirectUrl", "./mpMenuList");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 构造微信格式的menuList
	 * @param customizeMenuList
	 * @return
	 */
	private List<WxMenuBtnEntity> groupMpMenuList(List<WxCustomizeMenu> customizeMenuList) {
		if(customizeMenuList!=null&&customizeMenuList.size()>0){
			List<WxMenuBtnEntity> groupedList = new ArrayList<WxMenuBtnEntity>();
			for(WxCustomizeMenu loopMenu1: customizeMenuList){
				int level1MenuId = 0;
				if(0==loopMenu1.getParentId()){
					level1MenuId = loopMenu1.getId();
					//构造一级菜单
					WxMenuBtnEntity level1MpMenu = new WxMenuBtnEntity(loopMenu1.getMenuKey(), loopMenu1.getMenuName(), loopMenu1.getMenuType(), loopMenu1.getUrl());
					groupedList.add(level1MpMenu);
					for(WxCustomizeMenu loopMenu2: customizeMenuList){
						if(level1MenuId==loopMenu2.getParentId()){
							//向一级菜单中加入二级菜单项
							WxMenuBtnEntity level2MpMenu = new WxMenuBtnEntity(loopMenu2.getMenuKey(), loopMenu2.getMenuName(), loopMenu2.getMenuType(), loopMenu2.getUrl());
							level1MpMenu.addSubButton(level2MpMenu);
						}
					}
				}
			}
			return groupedList;
		}
		return null;
	}
	
	
	@RequestMapping("/mpMenuDelete")
	public String mpMenuDelete(Model model, HttpServletRequest request) {
		//删除自定义菜单
		wxMenuService.menuDelete();
		model.addAttribute("redirectUrl", "./mpMenuList");
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
		WxMenuBtnEntity root1 = new WxMenuBtnEntity("menu1","menu1", "click");
		WxMenuBtnEntity root2 = new WxMenuBtnEntity("menu2","menu2", "click");
		
		WxMenuBtnEntity level21 = new WxMenuBtnEntity("menu21","menu21", "click");
		WxMenuBtnEntity level22 = new WxMenuBtnEntity("menu22","menu22", "click");
		WxMenuBtnEntity level23 = new WxMenuBtnEntity("menu23","menu23", "click");
		
		WxMenuBtnEntity level211 = new WxMenuBtnEntity("menu211","menu211", "click");
		WxMenuBtnEntity level212 = new WxMenuBtnEntity("menu212","menu212", "click");
		WxMenuBtnEntity level213 = new WxMenuBtnEntity("menu213","menu213", "click");
		
		WxMenuBtnEntity level231 = new WxMenuBtnEntity("menu231","menu231", "click");
		WxMenuBtnEntity level232 = new WxMenuBtnEntity("menu232","menu232", "click");
		WxMenuBtnEntity level233 = new WxMenuBtnEntity("menu233","menu233", "click");
		
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
