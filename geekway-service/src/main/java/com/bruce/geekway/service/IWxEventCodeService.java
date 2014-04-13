package com.bruce.geekway.service;

import com.bruce.geekway.model.WxEventCode;

public interface IWxEventCodeService extends IBaseService<WxEventCode, Integer>{

	@Deprecated
	public WxEventCode loadByCode(String eventCode);
	
	public WxEventCode loadByTypeCode(short eventType, String eventCode);
	
}