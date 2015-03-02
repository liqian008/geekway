package com.bruce.geekway.service.mp;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.util.EmojiUtil;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.utils.HttpUtil;

/**
 * 微信用户管理service(mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
public class WxMpUserService extends WxBaseService {

	@Autowired
	private IWxAccessTokenService wxAccessTokenService;

	/**
	 * 获取Wx用户列表
	 * 
	 * @param accessToken
	 * @return
	 */
	@Deprecated
	public WxUserListResult getUsers(String nextOpenId) {
		// WxAuthResult authResult = getWxAccessToken();
		// if(authResult!=null && authResult.getErrcode()==null){
		// String accessToken = authResult.getAccess_token();
		try{
			String accessToken = wxAccessTokenService.getCachedAccessToken();
			Map<String, String> params = buildAccessTokenParams(accessToken);
			if (!StringUtils.isBlank(nextOpenId)) {
				params.put("next_openid", nextOpenId);
				// params.put("count", "10");
			}
	
			// String menuQueryResult =
			// WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_menu_get_url"),
			// params);
			String userListResult = HttpUtil.getRequest(ConstWeixin.WX_USER_INFO_API, params);
	
			WxUserListResult wxUserListResult = JsonUtil.gson.fromJson(userListResult, WxUserListResult.class);
			return wxUserListResult;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取wx用户信息
	 * 
	 * @param accessToken
	 * @return
	 */
	public WxUserInfoResult getUser(String openId) {
		try{
			String accessToken = wxAccessTokenService.getCachedAccessToken();
			Map<String, String> params = buildAccessTokenParams(accessToken);
			params.put("OPENID", openId);
			params.put("lang", "zh_CN");
	
			String userinfoResult = HttpUtil.getRequest(ConstWeixin.WX_USER_INFO_API, params);
			
			String emojiFilterResult = EmojiUtil.filterEmoji(userinfoResult);
			
			WxUserInfoResult wxUserinfoResult = JsonUtil.gson.fromJson(emojiFilterResult, WxUserInfoResult.class);
			return wxUserinfoResult;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}
	

}
