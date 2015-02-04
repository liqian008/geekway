package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;

public interface IWxAppService extends IFoundationPagingService<WxApp, Integer, WxAppCriteria>{

	/**
	 * 从缓存中查询
	 * @param wxAppId
	 * @return
	 */
	public WxApp loadCachedByWxAppId(String wxAppId);

	/**
	 * 查询db
	 * @param wxAppId
	 * @return
	 */
	public WxApp loadByWxAppId(String wxAppId); 
	
	public WxApp loadMyApp(int id, int userId);
	
	public WxApp loadCachedMyApp(int id, int userId);
	
	public List<WxApp> queryMyApp(int userId);
	
	public List<WxApp> queryCachedMyApp(int userId);
	
}