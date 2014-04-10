package com.bruce.geekway.service;

import com.bruce.geekway.model.WxCustomizeMenu;

public interface IWxCustomizeMenuService extends IBaseService<WxCustomizeMenu, Integer>{
	
	public WxCustomizeMenu loadByCode(String menuCode);
	
}