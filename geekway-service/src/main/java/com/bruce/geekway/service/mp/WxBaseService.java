package com.bruce.geekway.service.mp;


public class WxBaseService {
	
//	public static final String APPID =  ConfigUtil.getString("weixinmp_appid");
//	
//	public static final String SECRET =  ConfigUtil.getString("weixinmp_appsecret");
	
	
////	/*微信MP中accessToken的超时time，单位秒*/
////	private static final int ACCESS_TOKEN_MP_EXPIRES_IN = 7200; 
//	/*微信MP中请求accessToken网络请求的TimeOut，单位秒*/
//	private static final int ACCESS_TOKEN_REQUEST_TIME = 200; 
////	/*最终确定的Expires_IN*/
////	private static final int ACCESS_TOKEN_EXPIRES_TIME = ACCESS_TOKEN_MP_EXPIRES_IN - ACCESS_TOKEN_REQUEST_TIME;
//	
//	private static WxAuthResult authResult = null;
//	
//	/**
//	 * 获取accessToken对象
//	 * @param appid
//	 * @param appsecret
//	 * @return
//	 */
//	public synchronized WxAuthResult getAccessToken() {
//		
//		//如accessToken为null或accessToken过期，需要重取accessToken
//		if(authResult==null||authResult.getExpiresTime()<=System.currentTimeMillis()){
//			//需要通过网络获取新accessToken
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("grant_type", "client_credential");
//			params.put("appid", ConfigUtil.getString("weixinmp_appid"));
//			params.put("secret",  ConfigUtil.getString("weixinmp_appsecret"));
//			
//			String authResultStr = WxUtil.sendGetRequest(ConfigUtil.getString("weixinmp_access_token_url"), params);
//			WxAuthResult wxAuthRes = JsonUtil.gson.fromJson(authResultStr, WxAuthResult.class);
//			if(wxAuthRes!=null && wxAuthRes.getErrcode()==null){//正常的响应结果
//				authResult = wxAuthRes;
//				long expireTime = System.currentTimeMillis() + (wxAuthRes.getExpires_in() - ACCESS_TOKEN_REQUEST_TIME) * 1000;
//				authResult.setExpiresTime(expireTime);
//				return authResult;
//			}else{
//				throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
//			}
//		}
//		return authResult;
//	}
//	
////	protected WxAuthResult getWxAccessToken() {
////		WxAuthResult authResult = getAccessToken(ConfigUtil.getString("weixinmp_appid"), ConfigUtil.getString("weixinmp_appsecret"));
////		return authResult;
////	}
//	
//	protected String getMpAccessToken() {
//		WxAuthResult authResult = getAccessToken();
//		if(authResult!=null){
//			return authResult.getAccess_token();
//		}
//		return null;
//	}
	
//	public Map<String, String> buildAccessTokenParams(String accessToken) {
//		Map<String, String> result = buildParams();
//		result.put("access_token", accessToken);
//		return result;
//	}
//	
//	public Map<String, String> buildParams() {
//		Map<String, String> result = new HashMap<String, String>();
//		return result;
//	}

}
