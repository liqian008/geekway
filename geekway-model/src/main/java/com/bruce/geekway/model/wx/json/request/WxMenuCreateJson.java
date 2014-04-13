package com.bruce.geekway.model.wx.json.request;

import java.util.List;

import com.bruce.geekway.model.wx.json.WxMenuBtnEntity;


public class WxMenuCreateJson {

	private List<WxMenuBtnEntity> button;

	public WxMenuCreateJson(List<WxMenuBtnEntity> menuList) {
		this.button = menuList;
	}

	public List<WxMenuBtnEntity> getButton() {
		return button;
	}

	public void setButton(List<WxMenuBtnEntity> button) {
		this.button = button;
	}

}
