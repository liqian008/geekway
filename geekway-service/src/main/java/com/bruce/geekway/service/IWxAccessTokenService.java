package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxAccessToken;
import com.bruce.geekway.model.WxAccessTokenCriteria;
import com.bruce.geekway.model.exception.CachedException;

public interface IWxAccessTokenService extends IFoundationService<WxAccessToken, Integer, WxAccessTokenCriteria>{
	
	public String getCachedAccessToken() throws CachedException;
	
//	public int cachedAccessToken(WxAccessToken accessToken);
	
}