/**
 * 
 */
package com.bruce.geekway.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxMenuBtnEntity;
import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;
import com.bruce.geekway.model.wx.json.response.WxMenuQueryResult;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxUtil;

@Service
public class WxMenuService extends WxBaseService {
	
	private String accessToken = "GlKBMMuX_RnMVnrpo-2aH6a_IAv4VA6gKiIr3aP3BUs246ThqU8UKsZixOGWweyS83PvWbz6FWjFqR8DUzQCz3tW_Zmd163BGvNOrYsUeNMo8EMTFqOUVEXcryy52iXUPj4hfENFeVyEKKlRa3u0Gw";

	public WxJsonResult menuCreate(String accessToken, List<WxMenuBtnEntity> menuList) {
		Map<String, String> params = getAccessTokenParams(accessToken);
		
		WxMenuCreateJson wrapper = new WxMenuCreateJson(menuList);
		wrapper.setButton(menuList);
		
		String menuCreateResult = WxUtil.sendPostRequest("https://api.weixin.qq.com/cgi-bin/menu/create", params, new StringEntity(JsonUtil.gson.toJson(wrapper), Consts.UTF_8));
		WxJsonResult wxMenuCreateResult = JsonUtil.gson.fromJson(menuCreateResult, WxJsonResult.class);
		return wxMenuCreateResult;
		
	}

	public WxMenuQueryResult menuGet(String accessToken) {
		Map<String, String> params = getAccessTokenParams(accessToken);
		
		String menuQueryResult = WxUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/menu/get", params);
		WxMenuQueryResult wxMenuQueryResult = JsonUtil.gson.fromJson(menuQueryResult, WxMenuQueryResult.class);
		return wxMenuQueryResult;
	}

	public WxJsonResult menuDelete(String accessToken) {
		Map<String, String> params = getAccessTokenParams(accessToken);
		
		String menuDeleteResult = WxUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/menu/get", params);
		
		WxJsonResult wxMenuDeleteResult = JsonUtil.gson.fromJson(menuDeleteResult, WxJsonResult.class);
		return wxMenuDeleteResult;
	}
	
	
//	public static void main(String[] args) {
//		WxMenuService menuService = new WxMenuService();
//		WxMenuQueryResult result = menuService.menuGet(accessToken);
//		System.out.println(result);
//	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	
}
