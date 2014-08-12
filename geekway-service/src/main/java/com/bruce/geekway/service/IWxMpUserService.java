package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;
import com.bruce.geekway.utils.ConfigUtil;

public interface IWxMpUserService extends IFoundationService<WxMpUser, Integer, WxMpUserCriteria> {
	
	/**
	 * 根据用户的openId获取用户对象
	 * @param userOpenId
	 * @return
	 */
	public WxMpUser loadByOpenId(String userOpenId);
	
	/**
	 * 用户新关注的处理(获取详细资料后写入)
	 * @param userOpenId
	 * @return
	 */
	public int newSubscribeUser(String userOpenId);
	
	
	/**
	 * 用户重复订阅（关注后又退订）的处理
	 * @param userOpenId
	 * @return
	 */
	public int reSubscribeUser(String userOpenId);
	
	/**
	 * 用户退订的处理
	 * @param userOpenId
	 * @return
	 */
	public int unsubscribeUser(String userOpenId);
	
	/**
	 * 根据用户所发消息中的openid，找回用户openId（避免更服的时候遗漏用户）
	 * @param userOpenId
	 * @return
	 */
	public int logUserFromMessage(String userOpenId);
	
	/**
	 * 根据同步状态获取用户列表（通常用于取出未同步的数据，然后进行同步）
	 * @param syncStatus
	 * @return
	 */
	public List<WxMpUser> getMpUserListBySyncStatus(short syncStatus);
	
	
}