package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;

public interface IWxMpUserService extends IFoundationService<WxMpUser, Integer, WxMpUserCriteria> {
	
	/**
	 * 根据用户的openId获取用户对象
	 * @param userOpenId
	 * @return
	 */
	public WxMpUser loadByOpenId(String userOpenId);
	
	/**
	 * 新订阅的用户处理
	 * @param userOpenId
	 * @return
	 */
	public int newSubscribeUser(String userOpenId);

	/**
	 * 重复订阅（关注后又退订）的用户处理
	 * @param userOpenId
	 * @return
	 */
	public int repeatSubscribeUser(String userOpenId);
	
	/**
	 * 退订后的用户处理
	 * @param userOpenId
	 * @return
	 */
	public int unsubscribeUser(String userOpenId);
	
	/**
	 * 根据同步状态获取用户列表（通常用于取出未同步的数据，然后进行同步）
	 * @param syncStatus
	 * @return
	 */
	public List<WxMpUser> getMpUserListBySyncStatus(short syncStatus);
	
	
}