package com.bruce.geekway.service;

import com.bruce.geekway.model.WxCommand;

public interface IWxCommandService extends IBaseService<WxCommand, Integer>{

//	@Deprecated
//	public WxCommand loadByCode(String eventCode);
	
	public WxCommand loadByCommandType(short commandType, String command);
	
}