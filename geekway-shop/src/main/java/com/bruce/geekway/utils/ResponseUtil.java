package com.bruce.geekway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bruce.geekway.constants.ConstFront;

/**
 * 
 * @author liqian
 *
 */
public class ResponseUtil {

	/**
	 * 跳转首页
	 * */
	public static String getRedirectHomeString() {
		return "redirect:/";
	}

	/**
	 * 获取跳转字符串
	 * */
	public static String getRedirectString(String url) {
		return "redirect:" + url;
	}

	public static String getForwardString(String url) {
		return "forward:" + url;
	}

	public static String getForwardReirect() {
		return "forward:/redirect";
	}
	
	/**
	 * 跳转页的处理
	 * @param prompt 提示信息
	 * @param redirectUrl 跳转地址
	 * @param request
	 * @return
	 */
	public static String getForwardReirect(String prompt, String redirectUrl, HttpServletRequest request) {
		request.setAttribute(ConstFront.REDIRECT_PROMPT, prompt);
		request.setAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		return "forward:/redirect";
	}
	
//	/**
//	 * 设置cookie
//	 * @param response
//	 * @param key
//	 * @param value
//	 */
//	public static void addCookie(HttpServletResponse response, String key, String value){
//		Cookie cookie = new Cookie(key, value);
//		cookie.setMaxAge(Integer.MAX_VALUE);
//		response.addCookie(cookie);
//	}


}
