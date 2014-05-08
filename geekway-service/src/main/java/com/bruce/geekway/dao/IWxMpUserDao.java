package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxMpUser;

public interface IWxMpUserDao extends IBaseDao<WxMpUser, Integer> {

	public int insertOrUpdate(WxMpUser wxMpUser); 

}
