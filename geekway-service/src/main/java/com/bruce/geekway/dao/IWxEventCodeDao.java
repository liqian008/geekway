package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxEventCode;

public interface IWxEventCodeDao extends IBaseDao<WxEventCode, Integer> {

	@Deprecated
	public WxEventCode loadByCode(String eventCode);

	public WxEventCode loadByTypeCode(short eventType,String eventCode);
	
}
