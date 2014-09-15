package com.bruce.geekway.model.wx.json;

import java.util.ArrayList;
import java.util.List;

public class WxMenuBtn {

	private String key;
	private String url;
	private String name;
	private String type;
	private int level;
	private WxMenuBtn parent_button;
	private List<WxMenuBtn> sub_button;
	
	public WxMenuBtn(){
		super();
	}
	
	public WxMenuBtn(String key, String name, String type){
		this.key = key;
		this.name = name;
		this.type = type;
	}
	
	public WxMenuBtn(String key, String name, String type, String url){
		this.key = key;
		this.name = name;
		this.type = type;
		this.url = url;
	}
	
	public void addSubButton(WxMenuBtn sub){
		if(sub_button==null || sub_button.size()<=0){
			sub_button = new ArrayList<WxMenuBtn>();
		}
		sub_button.add(sub);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WxMenuBtn getParent_button() {
		return parent_button;
	}

	public void setParent_button(WxMenuBtn parent_button) {
		this.parent_button = parent_button;
	}

	public List<WxMenuBtn> getSub_button() {
		return sub_button;
	}

	public void setSub_buttons(List<WxMenuBtn> sub_button) {
		this.sub_button = sub_button;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	@Override
	public String toString() {
		return key+"_"+level;
	}
	
}
