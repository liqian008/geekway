package com.bruce.geekway.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.bruce.foundation.util.DateUtil;
import com.bruce.foundation.util.RandomNumberUtil;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;

public class OrderUtil {
	
	private static final String YEAR_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String MONTH_STR = "1234567890AB";
	private static final String DAY_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345";
	
	
	
	/**
	 * 生成系统订单号（长度16位）
	 * @return
	 */
	public static String generateOrderSn(Date date) {
		if(date==null){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		int year = calc.get(Calendar.YEAR);
		int month = calc.get(Calendar.MONTH);
		int day = calc.get(Calendar.DAY_OF_MONTH);
		int hour = calc.get(Calendar.HOUR_OF_DAY);
		int minute = calc.get(Calendar.MINUTE);
		int second = calc.get(Calendar.SECOND);
		int milliSecond = calc.get(Calendar.MILLISECOND);
		
		StringBuilder sb= new StringBuilder();
		sb.append(buildServerFlag());//分布式服务器标志， 0位
		sb.append(buildYearStr(year));//年，1位
		sb.append(buildMonthStr(month));//月，1位
		sb.append(buildDayStr(day));//日， 1位
		sb.append(buildSecondStr(hour, minute, second));//秒数，5位
		sb.append(leftFill(5, milliSecond));//豪秒数， 3位
		sb.append(RandomNumberUtil.getRandomString(3));//3位随机数
//		System.out.println(sb.length());
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 从环境变量中获取服务器的标志(不超过两位)
	 * @param year
	 * @return
	 */
	private static String buildServerFlag(){
//		String serverFlag = System.getenv("SERVER_FLAG");
//		if(StringUtils.isBlank(serverFlag) || serverFlag.length()>2){
//			serverFlag="S1";
//		}
//		return serverFlag;
		return "";
	}
	
	
	
	/**
	 * 构造订单的年份（A~Z中的1个字符，代表2014～2039）
	 * @param year
	 * @return
	 */
	private static String buildYearStr(int year){
		int interval = year - 2014;
		if(interval<0||interval>=YEAR_STR.length()){
			interval = 0;
		}
		return String.valueOf(YEAR_STR.charAt(interval));
	}
	
	/**
	 * 构造订单的月份（1～B中的12个字符，代表1~12月）
	 * @param month
	 * @return
	 */
	private static String buildMonthStr(int month){
		if(month<0||month>11){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
		return String.valueOf(MONTH_STR.charAt(month));
	}
	
	/**
	 * 构造订单的日期（A～Z+1～5共31个字符，代表1~31号）
	 * @param month
	 * @return
	 */
	private static String buildDayStr(int day){
		if(day<1||day>31){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
		return String.valueOf(DAY_STR.charAt(day-1));
	}
	
	/**
	 * 获取系统秒数（HHmmss格式的6位转成5位，最多为24*60*60=86400秒，节省1位）
	 * @param date
	 * @return
	 */
	private static String buildSecondStr(int hour, int minute, int second){
		int secondValue = (hour * 60 * 60) +  (minute * 60) +second;
		return leftFill(5, secondValue);
	}
	
	
	/**
	 * 左侧补齐长度，确保订单长度统一
	 * @param length
	 * @param number
	 * @return
	 */
    private static String leftFill(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
	
	public static void main(String[] args) {
		System.out.println(generateOrderSn(new Date()));
		
	}
	
	
	
	/**
	 * 生成系统订单号(50位)
	 * @return
	 */
	@Deprecated
	public static String generateOrderSn() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String orderTimeStr = DateUtil.date2YMDHMS(new Date());
//		return orderTimeStr +"_"+ ConstConfig.SERVER_INDEX +"_"+ uuid;
		return orderTimeStr +"_"+ uuid;
	}
	
	/**
	 * 生成系统订单号（长度32位以下，保证不超过微信的限制）
	 * @return
	 */
	@Deprecated
	public static String generateOrderSn4Wx() {
		//使用uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
}
