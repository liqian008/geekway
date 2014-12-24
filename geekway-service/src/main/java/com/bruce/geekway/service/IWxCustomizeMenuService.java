package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxCustomizeMenuCriteria;

public interface IWxCustomizeMenuService extends IFoundationService<WxCustomizeMenu, Integer, WxCustomizeMenuCriteria>{
	
	public List<WxCustomizeMenu> queryChildrenMenus(int parentId);

	public List<WxCustomizeMenu> querySortedMenus();
	
	
}