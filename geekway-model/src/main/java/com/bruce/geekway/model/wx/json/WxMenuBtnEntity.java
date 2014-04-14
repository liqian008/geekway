package com.bruce.geekway.model.wx.json;

import java.util.ArrayList;
import java.util.List;

public class WxMenuBtnEntity {

	private String key;
	private String url;
	private String name;
	private String type;
	private int level;
	private WxMenuBtnEntity parent_button;
	private List<WxMenuBtnEntity> sub_button;
	
	public WxMenuBtnEntity(){
		super();
	}
	
	public WxMenuBtnEntity(String key, String name){
		this.key = key;
		this.name = name;
	}
	
	public void addSubButton(WxMenuBtnEntity sub){
		if(sub_button==null || sub_button.size()<=0){
			sub_button = new ArrayList<WxMenuBtnEntity>();
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

	public WxMenuBtnEntity getParent_button() {
		return parent_button;
	}

	public void setParent_button(WxMenuBtnEntity parent_button) {
		this.parent_button = parent_button;
	}

	public List<WxMenuBtnEntity> getSub_button() {
		return sub_button;
	}

	public void setSub_buttons(List<WxMenuBtnEntity> sub_button) {
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
