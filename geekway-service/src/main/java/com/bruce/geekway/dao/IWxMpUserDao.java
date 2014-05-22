package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxMpUser;

public interface IWxMpUserDao extends IBaseDao<WxMpUser, Integer> {

//	public int insertOrUpdate(WxMpUser wxMpUser); 

//	public List<WxMpUser> getUnsyncedUserList();
//	
//	public List<WxMpUser> getSyncedUserList(); 
	
	public List<WxMpUser> getUserListBySyncStatus(short syncStatus);

	public int updateUserSubscribeStatus(String userOpenId, short subscribeStatus);

	public WxMpUser loadByOpenId(String userOpenId);  
	
}
