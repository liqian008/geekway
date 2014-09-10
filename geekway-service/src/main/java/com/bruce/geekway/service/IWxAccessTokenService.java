package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxAccessToken;
import com.bruce.geekway.model.WxAccessTokenCriteria;

public interface IWxAccessTokenService extends IFoundationService<WxAccessToken, Integer, WxAccessTokenCriteria>{
	
	public String getCachedAccessToken();
	
	public int cachedAccessToken(WxAccessToken accessToken);
	
}