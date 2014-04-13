package com.bruce.geekway.model.wx.json.response;

import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;


public class WxMenuQueryResult extends WxJsonResult{
	
	private WxMenuCreateJson menu;

	public WxMenuCreateJson getMenu() {
		return menu;
	}

	public void setMenu(WxMenuCreateJson menu) {
		this.menu = menu;
	}

}
