package com.bruce.geekway.service.mp;


import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxMenuBtnEntity;
import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;
import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.model.wx.json.response.WxMenuQueryResult;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxUtil;

@Service
public class WxMenuService extends WxBaseService {
	
//	private String accessToken = "GlKBMMuX_RnMVnrpo-2aH6a_IAv4VA6gKiIr3aP3BUs246ThqU8UKsZixOGWweyS83PvWbz6FWjFqR8DUzQCz3tW_Zmd163BGvNOrYsUeNMo8EMTFqOUVEXcryy52iXUPj4hfENFeVyEKKlRa3u0Gw";
	
	@Autowired
	private WxMpTokenService mpTokenService;
	
	/**
	 * 创建自定义菜单
	 * @param menuCreateJson
	 * @return
	 */
	public WxJsonResult menuCreate(WxMenuCreateJson menuCreateJson) {
		if(menuCreateJson!=null){
//			WxAuthResult authResult = getWxAccessToken();
//			if(authResult!=null && authResult.getErrcode()==null){
//				String accessToken = authResult.getAccess_token();
				
				String accessToken = mpTokenService.getMpAccessToken();
				Map<String, String> params = buildAccessTokenParams(accessToken);
				//创建菜单
				String menuCreateResult = WxUtil.sendPostRequest(ConfigUtil.getString("weixinmp_menu_create_url"), params, new StringEntity(JsonUtil.gson.toJson(menuCreateJson), Consts.UTF_8));
				WxJsonResult wxMenuCreateResult = JsonUtil.gson.fromJson(menuCreateResult, WxJsonResult.class);
				if(wxMenuCreateResult!=null && wxMenuCreateResult.getErrcode()!=null && wxMenuCreateResult.getErrcode()==0){//自定义菜单创建成功
					return wxMenuCreateResult;
				}
//			}
		}
		return null;
	}
	
	/**
	 * 创建自定义菜单
	 * @param menuList
	 * @return
	 */
	@Deprecated
	public WxJsonResult menuCreate(List<WxMenuBtnEntity> menuList) {
		if(menuList!=null&&menuList.size()>0){
//			WxAuthResult authResult = getWxAccessToken();
//			if(authResult!=null && authResult.getErrcode()==null){
//				String accessToken = authResult.getAccess_token();
				
				String accessToken = mpTokenService.getMpAccessToken();
				
				Map<String, String> params = buildAccessTokenParams(accessToken);
				//构造菜单的json对象
				WxMenuCreateJson wrapper = new WxMenuCreateJson(menuList);
				wrapper.setButton(menuList);
				//创建菜单
				String menuCreateResult = WxUtil.sendPostRequest(ConfigUtil.getString("weixinmp_menu_create_url"), params, new StringEntity(JsonUtil.gson.toJson(wrapper), Consts.UTF_8));
				WxJsonResult wxMenuCreateResult = JsonUtil.gson.fromJson(menuCreateResult, WxJsonResult.class);
				if(wxMenuCreateResult!=null && wxMenuCreateResult.getErrcode()!=null && wxMenuCreateResult.getErrcode()==0){//自定义菜单创建成功
					return wxMenuCreateResult;
				}
//			}
		}
		return null;
	}

	
	/**
	 * 获取自定义菜单
	 * @param accessToken
	 * @return
	 */
	public WxMenuQueryResult menuGet() {
		String accessToken = mpTokenService.getMpAccessToken();
		
		Map<String, String> params = buildAccessTokenParams(accessToken);
		
		String menuQueryResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_menu_get_url"), params);
		WxMenuQueryResult wxMenuQueryResult = JsonUtil.gson.fromJson(menuQueryResult, WxMenuQueryResult.class);
		return wxMenuQueryResult;
	}
	
	/**
	 * 删除自定义菜单
	 * @param accessToken
	 * @return
	 */
	public WxJsonResult menuDelete() {
//		WxAuthResult authResult = getWxAccessToken();
//		if(authResult!=null && authResult.getErrcode()==null){
//			String accessToken = authResult.getAccess_token();
		
			String accessToken = mpTokenService.getMpAccessToken();
		
			Map<String, String> params = buildAccessTokenParams(accessToken);
		
			String menuDeleteResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_menu_delete_url"), params);
			
			WxJsonResult wxMenuDeleteResult = JsonUtil.gson.fromJson(menuDeleteResult, WxJsonResult.class);
			return wxMenuDeleteResult;
//		}
//		return null;
	}
	
	
//	public static void main(String[] args) {
//		WxMenuService menuService = new WxMenuService();
//		WxMenuQueryResult result = menuService.menuGet(accessToken);
//		System.out.println(result);
//	}

//	public String getAccessToken() {
//		return accessToken;
//	}
//
//	public void setAccessToken(String accessToken) {
//		this.accessToken = accessToken;
//	}
	
	
	
}
