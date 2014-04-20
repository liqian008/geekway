package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxCustomizeMenu;

public interface IWxCustomizeMenuService extends IBaseService<WxCustomizeMenu, Integer>{
	
	public List<WxCustomizeMenu> queryChildrenMenus(int parentId);

	public List<WxCustomizeMenu> querySortedMenus();
	
	
}