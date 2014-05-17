package com.bruce.geekway.service.mp;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxUtil;

@Service
public class WxUserService extends WxBaseService {
	
	@Autowired
	private WxMpTokenService mpTokenService;
	
	/**
	 * 获取Wx用户列表
	 * @param accessToken
	 * @return
	 */
	public WxUserListResult getUsers(String nextOpenId) {
//		WxAuthResult authResult = getWxAccessToken();
//		if(authResult!=null && authResult.getErrcode()==null){
//			String accessToken = authResult.getAccess_token();
		
		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = buildAccessTokenParams(accessToken);
		if(!StringUtils.isBlank(nextOpenId)){
			params.put("next_openid", nextOpenId);
//				params.put("count", "10");
		}
		
//			String menuQueryResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_menu_get_url"), params);
		String userListResult = WxUtil.sendGetRequest(ConfigUtil.getString("https://api.weixin.qq.com/cgi-bin/user/get"), params);
		
		WxUserListResult wxUserListResult = JsonUtil.gson.fromJson(userListResult, WxUserListResult.class);
		return wxUserListResult;
	}
	
	/**
	 * 获取wx用户信息
	 * @param accessToken
	 * @return
	 */
	public WxUserInfoResult getUser(String openId) {
//		WxAuthResult authResult = getWxAccessToken();
//		if(authResult!=null && authResult.getErrcode()==null){
//			String accessToken = authResult.getAccess_token();
		
		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = buildAccessTokenParams(accessToken);
		params.put("OPENID", openId);
		params.put("lang","zh_CN");
		
//			String menuQueryResult = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_menu_get_url"), params);
		String userinfoResult = WxUtil.sendGetRequest(ConfigUtil.getString("https://api.weixin.qq.com/cgi-bin/user/info"), params);
		
		WxUserInfoResult wxUserinfoResult = JsonUtil.gson.fromJson(userinfoResult, WxUserInfoResult.class);
		return wxUserinfoResult;
	}
}
