package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;

public interface IWxMpUserService extends IFoundationService<WxMpUser, Integer, WxMpUserCriteria> {

	public WxMpUser loadByOpenId(String userOpenId);

	public int newSubscribeUser(String userOpenId);

	public int repeatSubscribeUser(String userOpenId);

	public int unsubscribeUser(String userOpenId);
	
	
	public List<WxMpUser> getMpUserListBySyncStatus(short s);

}