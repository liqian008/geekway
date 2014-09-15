package com.bruce.geekway.model.wx.json.request;

import java.util.List;

import com.bruce.geekway.model.wx.json.WxMenuBtn;


public class WxMenuCreateJson {

	private List<WxMenuBtn> button;

	public WxMenuCreateJson(List<WxMenuBtn> menuList) {
		this.button = menuList;
	}

	public List<WxMenuBtn> getButton() {
		return button;
	}

	public void setButton(List<WxMenuBtn> button) {
		this.button = button;
	}

}
