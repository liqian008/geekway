package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.RegUser;
import com.bruce.geekway.model.RegUserCriteria;

public interface IRegUserService extends IFoundationPagingService<RegUser, Integer, RegUserCriteria>{

	/**
	 * 从缓存中查询
	 * @param wxAppId
	 * @return
	 */
	public RegUser loadCachedByRegUserId(int regUserId);

	/**
	 * 查询db
	 * @param wxAppId
	 * @return
	 */
	public RegUser loadByRegUserId(int regUserId);
	
	/**
	 * 用户鉴权
	 * @param email
	 * @param password
	 * @return
	 */
	public RegUser authRegUser(String email, String password); 
	
}