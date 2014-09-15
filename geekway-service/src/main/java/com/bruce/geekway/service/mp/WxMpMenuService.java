package com.bruce.geekway.service.mp;


import java.util.List;
import java.util.Map;

//import org.apache.http.Consts;
//import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxMenuBtn;
import com.bruce.geekway.model.wx.json.request.WxMenuCreateJson;
//import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.model.wx.json.response.WxMenuQueryResult;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

@Service
public class WxMpMenuService extends WxBaseService {
	
//	private String accessToken = "GlKBMMuX_RnMVnrpo-2aH6a_IAv4VA6gKiIr3aP3BUs246ThqU8UKsZixOGWweyS83PvWbz6FWjFqR8DUzQCz3tW_Zmd163BGvNOrYsUeNMo8EMTFqOUVEXcryy52iXUPj4hfENFeVyEKKlRa3u0Gw";
	
	private static final String WX_MENU_DELETE_API = ConfigUtil.getString("weixinmp_menu_delete_url");
	private static final String WX_MENU_GET_API = ConfigUtil.getString("weixinmp_menu_get_url");
	private static final String WX_MENU_CREATE_API = ConfigUtil.getString("weixinmp_menu_create_url");

//	@Autowired
//	private WxMpTokenService mpTokenService;
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;
	
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
				
				String accessToken = wxAccessTokenService.getCachedAccessToken();
				Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
				//创建菜单
				String menuCreateResult = WxHttpUtil.postRequest(WX_MENU_CREATE_API, params, JsonUtil.gson.toJson(menuCreateJson));

				//for httpclient 4.0
				//String menuCreateResult = WxUtil.sendPostRequest(ConfigUtil.getString("weixinmp_menu_create_url"), params, new StringEntity(JsonUtil.gson.toJson(menuCreateJson), Consts.UTF_8));
				
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
	public WxJsonResult menuCreate(List<WxMenuBtn> menuList) {
		if(menuList!=null&&menuList.size()>0){
//			WxAuthResult authResult = getWxAccessToken();
//			if(authResult!=null && authResult.getErrcode()==null){
//				String accessToken = authResult.getAccess_token();
				
				String accessToken = wxAccessTokenService.getCachedAccessToken();
				
				Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
				//构造菜单的json对象
				WxMenuCreateJson wrapper = new WxMenuCreateJson(menuList);
				wrapper.setButton(menuList);
				//创建菜单
				String menuCreateResult = WxHttpUtil.postRequest(WX_MENU_CREATE_API, params, JsonUtil.gson.toJson(wrapper));
				
				//for httpclient 4.0
//				String menuCreateResult = WxUtil.sendPostRequest(ConfigUtil.getString("weixinmp_menu_create_url"), params, new StringEntity(JsonUtil.gson.toJson(wrapper), Consts.UTF_8));
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
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String menuQueryResult = WxHttpUtil.getRequest(WX_MENU_GET_API, params);
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
		
			String accessToken = wxAccessTokenService.getCachedAccessToken();
		
			Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
			String menuDeleteResult = WxHttpUtil.getRequest(WX_MENU_DELETE_API, params);
			
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

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

	
	
}
