package com.bruce.geekway.service;

import com.bruce.geekway.model.WxMpUser;

public interface IWxMpUserService extends IBaseService<WxMpUser, Integer> {

	public WxMpUser loadByOpenId(String userOpenId);

	public int newSubscribeUser(String userOpenId);

	public int repeatSubscribeUser(String userOpenId);

	public int unsubscribeUser(String userOpenId);

}