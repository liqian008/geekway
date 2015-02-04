package com.bruce.geekway.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.foundation.util.Md5Util;
import com.bruce.foundation.util.Sha1Util;

@Service
public class WxSignUtil {

	private static final Logger logger = LoggerFactory.getLogger(WxSignUtil.class);
	
	/**
	 * Md5方式加密
	 * @param paramMap
	 * @param secretKey
	 * @return
	 */
	public static String signMD5(SortedMap<String, String> paramMap, String secretKey) {
		String content = getFullSignText(paramMap, secretKey);
		String md5Sign = Md5Util.md5Encode(content);
		logger.debug("MD5加密后的文本为："+md5Sign);
		return md5Sign;
	}
	
	/**
	 * Sha1方式加密
	 * @param paramMap
	 * @param secretKey
	 * @return
	 */
	public static String signSHA1(SortedMap<String, String> paramMap, String secretKey) {
		String content = getFullSignText(paramMap, secretKey);
		
		String encrypt = Sha1Util.getSha1(content);
		logger.debug("SHA1加密后的文本为："+encrypt);
		return encrypt;
	}

	/**
	 * @param params
	 * @param secretKey
	 * @return
	 */
	public static String getFullSignText(SortedMap<String, String> params, String secretKey) {
		StringBuffer content = new StringBuffer(convert2SignText(params));
		if(StringUtils.isNotBlank(secretKey)){
			content.append("&key="+secretKey);
		}
		logger.debug("拼接完key文本为："+content);
		return content.toString();
	}

	/**
	 * 规则，不做大小写处理，有空值的不参与签名
	 * @param params
	 * @param secretKey
	 * @return
	 */
	public static String convert2SignText(SortedMap<String, String> params) {
		StringBuffer content = new StringBuffer();
		if (params != null) {
			List<String> keys = new ArrayList<String>(params.keySet());
			Collections.sort(keys);
			for (int i = 0; i < keys.size(); i++) {
				String key = (String) keys.get(i);
				String value = (String) params.get(key);
				if (StringUtils.isNotBlank(value)) {
					content.append((i == 0 ? "" : "&") + key + "=" + value);
				}
			}
		}
		logger.debug("Map转化完的文本为："+content);
		return content.toString();
	}

}