package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.WxWebUserCriteria;

public interface IWxWebUserService extends IFoundationPagingService<WxWebUser, Integer, WxWebUserCriteria> {
	

	
	/**
	 * 根据用户的unionId获取用户对象
	 * @param unionId
	 * @return
	 */
	public WxWebUser loadByUnionId(String unionId);
	
	
	/**
	 * 根据用户的openId获取用户对象（可能会有碰撞出现）
	 * @param userOpenId
	 * @return
	 */
	public WxWebUser loadByOpenId(String userOpenId);
	
	
}