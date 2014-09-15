package com.bruce.geekway.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WxAuthUtil {
	
//	public static String weixinToken = ConfigUtil.getString("weixinmp_devmode_token");
	
	private static final Logger log = LoggerFactory.getLogger(WxAuthUtil.class);

	/**
	 * 验证微信消息签名，文档api: http://mp.weixin.qq.com/wiki/index.php?title=%E9%AA%8C%E8%AF%81%E6%B6%88%E6%81%AF%E7%9C%9F%E5%AE%9E%E6%80%A7
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	public static boolean wxMessageAuth(String signature, String timestamp, String nonce, String echostr) {
		String excepted = Sha1Util.getSha1(combineWxMessageParam(timestamp, nonce, ""));
		if (signature == null || !signature.equals(excepted)) {
			log.error("Authentication failed! excepted echostr ->" + excepted);
			log.error("actual ->" + signature);
			return false;
		}
		return true;
	}
	
	
	/**
	 * 组合微信验证消息的字符串
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @return
	 */
	private static String combineWxMessageParam(String timestamp, String nonce, String token) {
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(token);
		String result = "";
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			result += list.get(i);
		}
		return result;
	}
	
	
	/**
	 * 构造wxpay的原始package字符串（去除名为sign的参数）
	 * @param paramMap
	 * @return
	 */
	public static String combineWxPayPackageText(SortedMap<String, String> paramMap){
		if(paramMap!=null&&paramMap.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: paramMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(StringUtils.isBlank(value)||"sign".equals(key)){
					continue;
				}else{
					sb.append(key+"="+value+"&");
				}
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 将所有参数字典表排序，做urlencode后组成一个串（参数名改为小写）
	 * @param paramMap
	 * @param ignoreBlankValue  是否忽略空数据的字段
	 * @param urlEncode  value是否需要做urlencode
	 * @return
	 */
	public static String combineWxPayUrlEncodeText(SortedMap<String, String> paramMap, boolean urlEncode) {
		if(paramMap!=null&&paramMap.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: paramMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(urlEncode){//需要urlencode
					value = URLEncoder.encode(value);
				}
				sb.append(key.toLowerCase()+"="+value+"&");
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		return "";
	}
	
	
	/**
	 * PaySign签名
	 * 对所有待签名参数按照字段名的 ASCII 码从小到大排序(字典序) 后,使用 URL键值对的格式(即 key1=value1&key2=value2...)拼接成字符串 string1。这里需要注意的是所
微信公众号支付接口文档 V2.7 有参数名均为小写字符,例如 appId 在排序后字符串则为 appid;
	 * @param paramMap
	 * @return
	 */
	public static String combineWxPaySignText(SortedMap<String, String> paramMap) {
		if(paramMap!=null&&paramMap.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: paramMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				sb.append(key.toLowerCase()+"="+value+"&");
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		return "";
	}
	
	public static void main(String[] args) {
		
		TreeMap<String,String> paramMap = new TreeMap<String, String>();
		
		paramMap.put("appId", "wx24e31a5fd464b35c");
		paramMap.put("openId", "oLpWZjtmQzS1xYSaIQA3QCkUuehg");
		paramMap.put("appKey", "4jrKLk1I6Ng9snRvPVBpjUfHeecyttHNMtmRPseZ7fZgDCsU9L51AR2QlgR6kVPlVC0Se4iVv72UTA3j0WORFoZwYbyuvSyMJ7Q4pYyS6RWnmU1CU80siecT0FBkW4ER");
		paramMap.put("isSubscribe", "1");
		paramMap.put("timeStamp", "1410514517");
		paramMap.put("nonceStr", "otmNm11FbnPqkFEK");
		
		String flatStr = combineWxPaySignText(paramMap);
		System.out.println(flatStr);
		
		String sign = Sha1Util.getSha1(flatStr);
		
		System.out.println(sign);
	}
	
}
