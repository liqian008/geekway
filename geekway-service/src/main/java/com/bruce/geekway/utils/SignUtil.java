package com.bruce.geekway.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 用于api对请求的参数签名
 * @author liqian
 *
 */
public class SignUtil {

	public static String sign(String secretKey, Map<String, String> paramMap) {
		String content = SignUtil.getSignData(paramMap, secretKey);
		String md5Sign = DigestUtils.md5Hex(content);
		return md5Sign;
	}
	
	/**
	 * 计算签名
	 * @param params
	 * @param secretKey
	 * @return
	 */
	public static String getSignData(Map<String, String> params, String secretKey) {
		StringBuffer content = new StringBuffer(getOriginalPlainSignText(params));
		if(StringUtils.isNotBlank(secretKey)){
			content.append(secretKey);
		}
		return content.toString();
	}
	
	/**
	 * 计算最原始的text
	 * @param params
	 * @param secretKey
	 * @return
	 */
	public static String getOriginalPlainSignText(Map<String, String> params) {
		StringBuffer content = new StringBuffer();
		if(params!=null){
			// 按照 key 做排序
			List<String> keys = new ArrayList<String>(params.keySet());
			Collections.sort(keys);
			for (int i = 0; i < keys.size(); i++) {
				String key = (String) keys.get(i);
				if ("sign".equals(key) || "sign_type".equals(key)) {
					continue;
				}
				String value = (String) params.get(key);
				if (value != null) {
					content.append((i == 0 ? "" : "&") + key + "=" + value);
				} else {
					content.append((i == 0 ? "" : "&") + key + "=");
				}
			}
		}
		return content.toString();
	}
	
	
		
	public static void main(String[] args) {
//		System.out.println(sign("appKey=00a4fd2bf1749386&orderId=aaa", null));
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("state", "[\"0\", \"0\"");
		System.out.println(sign("2s#2dEr3", paramMap));
	}

}