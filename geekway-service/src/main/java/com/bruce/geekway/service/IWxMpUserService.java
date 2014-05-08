package com.bruce.geekway.service;

import com.bruce.geekway.model.WxMpUser;

public interface IWxMpUserService extends IBaseService<WxMpUser, Integer>{

	
	public int newMpUser(String openId);
	
	
	public int insertOrUpdate(WxMpUser wxMpUser);
	
	
	
}