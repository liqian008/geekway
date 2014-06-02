package com.bruce.geekway.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.KlhUserProfile;


public class KlhUtil {


	/**
	 * 检查session是否可用
	 * @param model
	 * @return
	 */
	public static boolean sessionValid(HttpServletRequest request) {
		KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
		if (sessionUserProfile != null && sessionUserProfile.getUserOpenId() != null) {
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
		//TODO 跳转auth界面
		model.addAttribute(ConstFront.REDIRECT_PROMPT, "登录信息已过期，系统正在为您跳转至登录界面，请稍候...");
		model.addAttribute(ConstFront.REDIRECT_URL, WxMpUtil.AUTHORIZE_URL);
		return "forward:/klh/redirect";
	}
}
