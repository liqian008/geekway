package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.json.WxGroupInfo;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxAccessTokenService;
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
	
	private static final String WX_GROUP_CREATE_API = ConfigUtil.getString("weixinmp_group_create_url");
	private static final String WX_GROUP_LIST_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	private static final String WX_USER_GROUP_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	private static final String WX_GROUPNAME_UPDATE_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	private static final String WX_USER_GROUP_MOVE_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	

//	@Autowired
//	private WxMpTokenService mpTokenService;
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;

	/**
	 * 创建组
	 * @param groupJson
	 * @return
	 */
	public boolean createGroup(WxGroupInfo.Group groupJsonBean) {

		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		
		String groupCreateResultStr = WxHttpUtil.postRequest(WX_GROUP_CREATE_API, params,  JsonUtil.gson.toJson(groupJsonBean));
		
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

		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String groupListResultStr = WxHttpUtil.getRequest(WX_GROUP_LIST_API, params);
		
		WxGroupInfo.Groups wxGroupList = JsonUtil.gson.fromJson(groupListResultStr, WxGroupInfo.Groups.class);
		return wxGroupList;
	}
	
	/**
	 * 获取用户所在的组
	 * @param openId
	 * @return
	 */
	public WxGroupInfo.UserGroup queryUserGroup(WxGroupInfo.UserGroup userJsonBean) {

		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String userGroupStr = WxHttpUtil.postRequest(WX_USER_GROUP_API, params, JsonUtil.gson.toJson(userJsonBean));
		
		return JsonUtil.gson.fromJson(userGroupStr, WxGroupInfo.UserGroup.class);
	}
	
	/**
	 * 修改分组名
	 * @param openId
	 * @return
	 */
	public WxJsonResult updateGroupName(WxGroupInfo.Group groupJson) {

		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String updateResult = WxHttpUtil.postRequest(WX_GROUPNAME_UPDATE_API, params, JsonUtil.gson.toJson(groupJson));
		
		return JsonUtil.gson.fromJson(updateResult, WxJsonResult.class);
	}
	
	/**
	 * 移动用户到新组
	 * @param openId
	 * @return
	 */
	public WxJsonResult moveUserGroup(WxGroupInfo.UserGroup userGroupJson) {

		String accessToken = wxAccessTokenService.getCachedAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		
		String moveResultStr = WxHttpUtil.getRequest(WX_USER_GROUP_MOVE_API, params);
		
		WxJsonResult wxMoveResult = JsonUtil.gson.fromJson(moveResultStr, WxUserInfoResult.class);
		return wxMoveResult;
	}

//	public WxMpTokenService getMpTokenService() {
//		return mpTokenService;
//	}
//
//	public void setMpTokenService(WxMpTokenService mpTokenService) {
//		this.mpTokenService = mpTokenService;
//	}
	
	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

}
