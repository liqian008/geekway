package com.bruce.geekway.constants;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstWeixin {
	
	/*weixin oauth debug switcher*/
	public static final boolean WX_OAUTH_DEBUG = "true".equalsIgnoreCase(ConfigUtil.getString("wx_oauth_debug"));
	
	/*开发者的id&key*/
	public static final String WX_APP_ID = ConfigUtil.getString("weixinmp_appid");
	public static final String WX_APP_SECRET_KEY = ConfigUtil.getString("weixinmp_appsecret");
	
	/*支付财付通的id&key*/
	public static final String WX_PAY_PARTERN_ID = ConfigUtil.getString("weixinmp_pay_partner_id");
	public static final String WX_PAY_PARTERN_KEY = ConfigUtil.getString("weixinmp_pay_partner_key");
	
	public static final String WX_PAY_SIGN_KEY = ConfigUtil.getString("weixinmp_pay_sign_key");
	
	//开发模式的url
	public static final String WX_DEV_MODE_URL = ConfigUtil.getString("weixinmp_devmode_url");
	//开发模式的token
	public static final String WX_DEV_MODE_TOKEN = ConfigUtil.getString("weixinmp_devmode_token");
	//公众帐号的账户类型（服务号|订阅号）
	public static final String WX_ACCOUNT_TYPE = ConfigUtil.getString("weixinmp_account_type");
	//公众帐号的二维码图片url
	public static final String WX_MP_ACCOUNT_QRCODE_URL = ConfigUtil.getString("weixinmp_qrcode_img_url");
	
	//公众帐号的头像显示
	public static final String DEFAULT_WEIXIN_ACCOUNT_AVATAR_URL = ConfigUtil.getString("weixinmp_account_avatar");
	//匿名用户的显示头像
	public static final String DEFAULT_USER_AVATAR_URL = ConfigUtil.getString("weixinmp_anonymous_user_avatar");
	
	//微信获取公众账户accessToken的api
	public static final String WX_ACCESS_TOKEN_API = ConfigUtil.getString("weixinmp_access_token_url");
	
	//微信获取公众账户jsTicket的api
	public static final String WX_JS_TICKET_API = ConfigUtil.getString("weixinmp_js_ticket_url");
	
	//微信oauth accessToken api
	public static final String WX_OAUTH_ACCESS_TOKEN_API = ConfigUtil.getString("weixinmp_oauth_accesstoken_url");
	//微信oauth 获取个人资料api
	public static final String WX_OAUTH_USER_INFO_API = ConfigUtil.getString("weixinmp_oauth_userinfo_url");
	
	// 微信消息群发api
	public static final String WX_BROADCAST_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	// 发送客服消息api
	public static final String WX_REPLY_MESSAGE_API = ConfigUtil.getString("weixinmp_reply_message_url");
	// 上传素材api
	public static final String WX_MEDIA_UPLOAD_API = ConfigUtil.getString("weixinmp_media_upload_url");
	// 删除菜单api
	public static final String WX_MENU_DELETE_API = ConfigUtil.getString("weixinmp_menu_delete_url");
	// 获取菜单api
	public static final String WX_MENU_GET_API = ConfigUtil.getString("weixinmp_menu_get_url");
	// 创建菜单api
	public static final String WX_MENU_CREATE_API = ConfigUtil.getString("weixinmp_menu_create_url");
	
	public static final String WX_USER_INFO_API = ConfigUtil.getString("weixinmp_user_info_url");
	
	
	//创建组的api
	public static final String WX_GROUP_CREATE_API = ConfigUtil.getString("weixinmp_group_create_url");
	//组列表的api
	public static final String WX_GROUP_LIST_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	public static final String WX_USER_GROUP_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	//更新组的api
	public static final String WX_GROUPNAME_UPDATE_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	//移动组的api
	public static final String WX_USER_GROUP_MOVE_API = ConfigUtil.getString("weixinmp_message_broadcast_url");
	
	
	// 发货通知api
	public static final String WX_PAY_PREPAY_API = ConfigUtil.getString("weixinmp_pay_prepay_url");
		
	// 发货通知api
	public static final String WX_PAY_DELIVER_NOTIFY_API = ConfigUtil.getString("weixinmp_pay_deliver_url");
	// 维权处理api
	public static final String WX_PAY_COMPLAINT_DEAL_API = ConfigUtil.getString("weixinmp_pay_complaint_deal_url");
	// 订单查询
	public static final String WX_PAY_QUERY_ORDER_API = ConfigUtil.getString("weixinmp_pay_query_order_url");
	
	
	//微信oauth redirect回调的代理地址
	public static final String WX_OAUTH_REDIRECT_PROXY_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");

	//微信js-sdk的权限
	public static final Object WX_JS_API_FULL = ConfigUtil.getString("weixinmp_jssdk_jsapi");
	
}
