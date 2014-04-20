package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxCustomizeMenu;

public interface IWxCustomizeMenuDao extends IBaseDao<WxCustomizeMenu, Integer> {

//	public WxCustomizeMenu loadByCode(String menuCode);
	
	public List<WxCustomizeMenu> queryChildrenMenus(int parentId);

	public List<WxCustomizeMenu> querySortedMenus();
	
}
