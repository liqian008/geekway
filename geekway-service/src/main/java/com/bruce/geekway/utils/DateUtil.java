package com.bruce.geekway.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static long TIME_UNIT_SECOND = 1000*1;
	public static long TIME_UNIT_MINUTE = TIME_UNIT_SECOND*60;
	public static long TIME_UNIT_HOUR = TIME_UNIT_MINUTE*60;
	public static long TIME_UNIT_DAY = TIME_UNIT_HOUR*24;
	
	public static final SimpleDateFormat DATE_FORMAT_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static String date2YMD(Date date){
		if(date!=null){
			return DATE_FORMAT_YMD.format(date);
		}
		return null;
	}
	
	public static String date2YMDHMS(Date date){
		if(date!=null){
			return DATE_FORMAT_YMDHMS.format(date);
		}
		return null;
	}
	
	public static Date calcDatetime(Date date, int days){
		if(date!=null){
			long originTime = date.getTime();
			long period  =(days*TIME_UNIT_DAY);
			long result = originTime+period;
			
			System.out.println(originTime);
			System.out.println(period);
			System.out.println(result);
			
			return new Date(result);
		}
		return null;
	}
	
	/**
	 * 获取linux时间
	 * @param date
	 * @return
	 */
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		return date.getTime()/1000;
	}
	
//	public static void main(String[] args) {
//		System.out.println(calcDate(new Date(), 7));
//	}
}
