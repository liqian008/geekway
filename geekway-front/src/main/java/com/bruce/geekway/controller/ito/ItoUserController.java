package com.bruce.geekway.controller.ito;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	public String registerGo(Model model, ItoUserProfile itoUserProfile, HttpServletRequest request) {
		int result = itoUserProfileService.save(itoUserProfile);
		
		return "redirect:/ito/regedQrcode";
	}

}
