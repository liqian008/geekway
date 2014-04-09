package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxTextCode;

public interface IWxTextCodeDao extends IBaseDao<WxTextCode, Integer> {

	public WxTextCode loadByCode(String textCode);
	
}
