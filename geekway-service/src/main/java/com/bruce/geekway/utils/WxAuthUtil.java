package com.bruce.geekway.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstWeixin;

@Service
public class WxAuthUtil {
	
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
	 * package的方式进行签名(先根据参数map先生成sign，再将sign拼接到package最后)
	 * @param paramMap
	 * @return
	 */
	public static String packageSign(SortedMap<String, String> paramMap){
		if(paramMap!=null&&paramMap.size()>0){
			//根据参数map先生成sign
			String unsignPackageText = formatWxPayPackageText(paramMap) + "&key="+ConstWeixin.WX_PAY_PARTERN_KEY;//最后拼接key
			String sign = Md5Util.md5Encode(unsignPackageText).toUpperCase();
			//urlencode
			String encodedPackageText = WxAuthUtil.formatWxPayUrlEncodeText(paramMap, true);
			// 将sign拼接到package最后
			return encodedPackageText + "&sign=" + sign;
		}
		return "";
	}
	
	
	/**
	 * paySign的方式进行签名，无需额外提供
	 * @param paramMap
	 * @return
	 */
	public static String paySign(SortedMap<String, String> paramMap){
		if(paramMap!=null&&paramMap.size()>0){
			String unsignedStr = WxAuthUtil.formatWxPaySignText(paramMap);
			return Sha1Util.getSha1(unsignedStr);
		}
		return "";
	}
	
	
	/**
	 * 构造wxpay的原始package字符串（去除名为sign的参数）
	 * @param packageMap
	 * @return
	 */
	public static String formatWxPayPackageText(SortedMap<String, String> packageMap){
		if(packageMap!=null&&packageMap.size()>0){
			packageMap.put("partner", ConstWeixin.WX_PAY_PARTERN_ID);//无需外部传入partner
			packageMap.remove("key");//key不参与package的生成
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: packageMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(StringUtils.isBlank(value)||"sign".equals(key)){////不参与签名的参数
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
	 * @param packageMap
	 * @param ignoreBlankValue  是否忽略空数据的字段
	 * @param urlEncode  value是否需要做urlencode
	 * @return
	 */
	public static String formatWxPayUrlEncodeText(SortedMap<String, String> packageMap, boolean urlEncode) {
		if(packageMap!=null&&packageMap.size()>0){
			packageMap.put("partner", ConstWeixin.WX_PAY_PARTERN_ID);//无需外部传入partner
			packageMap.remove("key");//key不参与package的生成
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: packageMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(urlEncode){//需要urlencode
					if("sign".equals(key)){////不参与签名的参数
						value = URLEncoder.encode(value);
					}
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
	public static String formatWxPaySignText(SortedMap<String, String> paramMap) {
		if(paramMap!=null&&paramMap.size()>0){
			paramMap.put("appkey", ConstWeixin.WX_PAY_SIGN_KEY);//外部无需传入appkey
			paramMap.put("appid", ConstWeixin.WX_APP_ID);//外部无需传入appid
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> entry: paramMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(!"sign_method".equals(key)&&!"app_signature".equals(key)){//不参与签名的参数
					sb.append(key.toLowerCase()+"="+value+"&");
				}
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 生成指定长度的nonce随机串
	 * @param length
	 * @return
	 */
	public static String createNoncestr(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.indexOf(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
	
	/**
	 * 生成长度16的nonce随机串
	 * @return
	 */
	public static String createNoncestr() {
		return createNoncestr(16);
	}
	
	
	public static void main(String[] args) {
		
		TreeMap<String,String> paramMap = new TreeMap<String, String>();
		
		paramMap.put("appId", "wx24e31a5fd464b35c");
		paramMap.put("openId", "oLpWZjtmQzS1xYSaIQA3QCkUuehg");
		paramMap.put("appKey", "4jrKLk1I6Ng9snRvPVBpjUfHeecyttHNMtmRPseZ7fZgDCsU9L51AR2QlgR6kVPlVC0Se4iVv72UTA3j0WORFoZwYbyuvSyMJ7Q4pYyS6RWnmU1CU80siecT0FBkW4ER");
		paramMap.put("isSubscribe", "1");
		paramMap.put("timeStamp", "1410514517");
		paramMap.put("nonceStr", "otmNm11FbnPqkFEK");
		
		String flatStr = formatWxPaySignText(paramMap);
		System.out.println(flatStr);
		
		String sign = Sha1Util.getSha1(flatStr);
		
		System.out.println(sign);
	}
	
}
