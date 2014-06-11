package com.edb.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class test {
//	protected static String testUrl = "http://vip21.edb01.com/rest/api.aspx";
//	public static final String appkey = "8be065aa";
//	public static final String secret="1386a3ba2f48443990f197dce3d2a2c1";
//	public static final String token   ="0147e3ffaa4f4572956807d69821f236";
//	public static final String dbhost = "edb_a70821";
	
	
	
	protected static String testUrl = "http://vip802.6x86.com/edb2/rest/index.aspx";
	public static final String appkey = "34bfddcc";
	public static final String secret="f58c448e8fa94bde888096c24530b13b";
	public static final String token   ="e9f9813806234492a0759ebdd0a0c1ed";
	public static final String dbhost = "edb_a12345";
	
	public static final String format = "xml";
	
	
	public static void edbTradeGet()
	{
		
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();

		apiparamsMap.put("dbhost", dbhost);
        apiparamsMap.put("format", format);
        apiparamsMap.put("method", "edbTradeGet");
        apiparamsMap.put("slencry","0");
		apiparamsMap.put("ip","192.168.60.80");
        apiparamsMap.put("appkey",appkey);
		apiparamsMap.put("token",token);
        apiparamsMap.put("v", "2.0");
		apiparamsMap.put("fields", "");
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm")
				.format(new Date());
		apiparamsMap.put("timestamp", timestamp);
	
		
		String date_type = Util.encodeUri("");
        String begin_time = Util.encodeUri("");
        String end_time = Util.encodeUri("");
        String payment_status=Util.encodeUri("");
        String order_status = Util.encodeUri("");
        String process_status = Util.encodeUri("");
        String store_houseId = Util.encodeUri("");
        String shop_Id = Util.encodeUri("");
        String express_num = Util.encodeUri("");
        String express = Util.encodeUri("");
        String invoice_Is_print = Util.encodeUri("");
        String key = Util.encodeUri("");
        String invoice_Is_open = Util.encodeUri("");
        String is_split_suit = Util.encodeUri("");
        String is_filterreturngoods = Util.encodeUri("");
        String page_no = Util.encodeUri("1");
        String page_size = Util.encodeUri("100");
        
        apiparamsMap.put("date_type", date_type);
        apiparamsMap.put("begin_time", begin_time);
        apiparamsMap.put("end_time", end_time);
        apiparamsMap.put("payment_status", payment_status);
        apiparamsMap.put("order_status", order_status);
        apiparamsMap.put("process_status", process_status);
        apiparamsMap.put("store_houseId", store_houseId);
        apiparamsMap.put("shop_id", shop_Id);
        apiparamsMap.put("express_num", express_num);
        apiparamsMap.put("express", express);
        apiparamsMap.put("invoice_Is_print", invoice_Is_print);
        apiparamsMap.put("key", key);
        apiparamsMap.put("invoice_Is_open", invoice_Is_open);
        apiparamsMap.put("is_split_suit", is_split_suit);
        apiparamsMap.put("is_filterreturngoods", is_filterreturngoods);
        apiparamsMap.put("page_no", page_no);
        apiparamsMap.put("page_size", page_size);
        
  		String sign = Util.md5Signature(apiparamsMap, secret);
  		apiparamsMap.put("sign", sign);
		
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			param.append("&").append(e.getKey()).append("=").append(e.getValue());

		 }
		
		String PostData="";
		PostData=param.toString().substring(1);
		System.out.println(testUrl+"?"+PostData);
		String result="";
		result=Util.getResult(testUrl,PostData);
		System.out.println(result);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test.edbTradeGet();
	}

}
