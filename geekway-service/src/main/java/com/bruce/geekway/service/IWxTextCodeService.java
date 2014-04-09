package com.bruce.geekway.service;

import com.bruce.geekway.model.WxTextCode;

public interface IWxTextCodeService extends IBaseService<WxTextCode, Integer>{

	
	public WxTextCode loadByCode(String code);
	
}