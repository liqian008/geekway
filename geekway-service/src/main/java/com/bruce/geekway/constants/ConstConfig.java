package com.bruce.geekway.constants;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstConfig {

//	public static final String NEW_SUBSCRIBE = "用户首次关注";
//	
//	public static final String RE_SUBSCRIBE = "用户重复关注";
	
	public static final String APP_ID = "";
	
	//分布式的服务器编号index, 从1开始
	public static final String SERVER_INDEX = ConfigUtil.getString("server_index");
	
	
	
	
}
