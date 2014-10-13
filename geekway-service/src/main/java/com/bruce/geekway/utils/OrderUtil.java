package com.bruce.geekway.utils;

import java.util.Date;
import java.util.UUID;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.constants.ConstConfig;

public class OrderUtil {
	
	/**
	 * 生成系统订单号(50位)
	 * @return
	 */
	public static String generateOrderSn() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String orderTimeStr = DateUtil.date2YMDHMS(new Date());
		return orderTimeStr +"_"+ ConstConfig.SERVER_INDEX +"_"+ uuid;
	}
	
	/**
	 * 生成系统订单号（长度32位以下，保证不超过微信的限制）
	 * @return
	 */
	public static String generateOrderSn4Wx() {
		//使用uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		System.out.println(generateOrderSn4Wx());
	}

}
