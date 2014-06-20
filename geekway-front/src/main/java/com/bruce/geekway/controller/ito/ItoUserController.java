package com.bruce.geekway.controller.ito;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoUserProfile;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoUserProfileService;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.KlhUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "ito" })
public class ItoUserController {
	
	
	/*用户注册后的key*/
	public static final String KEY_USER_REGISTERED = "user_registed";
	
	
	@Autowired
	private IItoUserProfileService itoUserProfileService;

	@RequestMapping(value = "/usernameExists.json")
	public ModelAndView usernameExists(Model model, String username) {
		boolean usernameExists = false;
		usernameExists = itoUserProfileService.usernameExists(username);
		if (usernameExists) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_NICKNAME_EXISTS));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
	
	@RequestMapping(value = "/mobileExists.json")
	public ModelAndView mobileExists(Model model, String mobile) {
		boolean mobileExists = false;
		mobileExists = itoUserProfileService.mobileExists(mobile);
		if (mobileExists) {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.USER_MOBILE_EXISTS));
		} else {
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson());
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request) {
		return "ito/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerGo(Model model, ItoUserProfile itoUserProfile, HttpServletRequest request,  HttpServletResponse response) {
		int result = itoUserProfileService.save(itoUserProfile);
		if(result>0){
			//重新写入cookie
			Cookie cookie = new Cookie(KEY_USER_REGISTERED, "true");
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
		}
		return "redirect:/ito/gameQrcodes";
	}

}
