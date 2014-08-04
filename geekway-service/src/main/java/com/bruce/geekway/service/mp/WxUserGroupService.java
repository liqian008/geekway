package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxGroupInfo;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信组管理
 * @author liqian
 *
 */
@Service
public class WxUserGroupService extends WxBaseService {

	@Autowired
	private WxMpTokenService mpTokenService;

	/**
	 * 创建组
	 * @param groupJson
	 * @return
	 */
	public boolean createGroup(WxGroupInfo.Group groupJsonBean) {

		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		
		String groupCreateResultStr = WxHttpUtil.sendPostRequest(ConfigUtil.getString("weixinmp_group_create_url"), params,  JsonUtil.gson.toJson(groupJsonBean));
		
		WxGroupInfo.Group wxGroupCreateResult = JsonUtil.gson.fromJson(groupCreateResultStr,  WxGroupInfo.Group.class);
		if(wxGroupCreateResult!=null){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 获取组列表
	 * @param accessToken
	 * @return
	 */
	public WxGroupInfo.Groups listGroups() {

		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String groupListResultStr = WxHttpUtil.sendGetRequest(ConfigUtil.getString("weixinmp_group_list_url"), params);
		
		WxGroupInfo.Groups wxGroupList = JsonUtil.gson.fromJson(groupListResultStr, WxGroupInfo.Groups.class);
		return wxGroupList;
	}
	
	/**
	 * 获取用户所在的组
	 * @param openId
	 * @return
	 */
	public WxGroupInfo.UserGroup queryUserGroup(WxGroupInfo.UserGroup userJsonBean) {

		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String userGroupStr = WxHttpUtil.sendPostRequest(ConfigUtil.getString("weixinmp_user_group_url"), params, JsonUtil.gson.toJson(userJsonBean));
		
		return JsonUtil.gson.fromJson(userGroupStr, WxGroupInfo.UserGroup.class);
	}
	
	/**
	 * 修改分组名
	 * @param openId
	 * @return
	 */
	public WxJsonResult updateGroupName(WxGroupInfo.Group groupJson) {

		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String updateResult = WxHttpUtil.sendPostRequest(ConfigUtil.getString("weixinmp_groupname_update_url"), params, JsonUtil.gson.toJson(groupJson));
		
		return JsonUtil.gson.fromJson(updateResult, WxJsonResult.class);
	}
	
	/**
	 * 移动用户到新组
	 * @param openId
	 * @return
	 */
	public WxJsonResult moveUserGroup(WxGroupInfo.UserGroup userGroupJson) {

		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String moveResultStr = WxHttpUtil.sendGetRequest(ConfigUtil.getString("weixinmp_user_group_move_url"), params);
		
		WxJsonResult wxMoveResult = JsonUtil.gson.fromJson(moveResultStr, WxUserInfoResult.class);
		return wxMoveResult;
	}

	public WxMpTokenService getMpTokenService() {
		return mpTokenService;
	}

	public void setMpTokenService(WxMpTokenService mpTokenService) {
		this.mpTokenService = mpTokenService;
	}

}
