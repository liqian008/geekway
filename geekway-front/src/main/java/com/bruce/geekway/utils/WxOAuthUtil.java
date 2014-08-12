package com.bruce.geekway.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.bruce.geekway.constants.ConstFront;


public class WxOAuthUtil {


	/**
	 * 检查session是否可用
	 * @param model
	 * @return
	 */
	public static boolean sessionValid(HttpServletRequest request) {
		Boolean sessionExists  = (Boolean)request.getSession().getAttribute("hasLogin");
		if (sessionExists!= null && sessionExists) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 跳转至oauth界面
	 * @param model
	 * @return
	 */
	public static String redirectToOauth(Model model) {
		//跳转auth界面
//		model.addAttribute(ConstFront.REDIRECT_PROMPT, "登录信息已过期，系统正在为您跳转至登录界面，请稍候...");
//		model.addAttribute(ConstFront.REDIRECT_URL, WxMpUtil.AUTHORIZE_BASE_URL);
		return "redirect:"+WxMpUtil.AUTHORIZE_BASE_URL;
	}
}
