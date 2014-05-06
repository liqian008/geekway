package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxCommand;

public interface IWxCommandDao extends IBaseDao<WxCommand, Integer> {

	@Deprecated
	public WxCommand loadByCode(String eventCode);

	public WxCommand loadByCommandType(short commandType,String command);
	
}
