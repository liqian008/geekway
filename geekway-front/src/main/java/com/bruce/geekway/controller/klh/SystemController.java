package com.bruce.geekway.controller.klh;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.utils.KlhUtil;
import com.bruce.geekway.utils.WxMpUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "klh" })
public class SystemController {

	@Autowired
	private WxMpOauthService mpOauthService;
	@Autowired
	private IKlhUserProfileService klhUserProfileService;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("authorizeUrl", WxMpUtil.AUTHORIZE_URL);
		return "klh/index";
	}

	/**
	 * 个人页
	 * 
	 * @param model
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(value = "code", required = false, defaultValue = "") String code, HttpServletRequest request) {

		if (!StringUtils.isEmpty(code)) {//回调地址进来的
			WxOauthTokenResult oauthResult = mpOauthService.getOauthAccessToken(code);
			if (oauthResult != null && oauthResult.getAccess_token() != null) {
				System.out.println("==========1=========");
				String openid = oauthResult.getOpenid();
				//使用accessToken获取userInfo
				WxUserInfoResult authUserInfoResult = mpOauthService.getOauthUserinfo(oauthResult.getAccess_token(), oauthResult.getOpenid());
				if (authUserInfoResult != null) {
					System.out.println("==========2=========");
					KlhUserProfile sessionUserProfile = klhUserProfileService.loadByOpenid(openid);
					if (sessionUserProfile== null) {//全新用户
						System.out.println("==========4=========");
						sessionUserProfile = new KlhUserProfile();
						sessionUserProfile.setUserOpenId(openid);
					}
					sessionUserProfile.setNickname(authUserInfoResult.getNickname());
					request.getSession().setAttribute("sessionUserProfile", sessionUserProfile);
					return "klh/home";
				}
			}
			System.out.println("===oauthResult==" + oauthResult.getOpenid());
		}else{
			if(KlhUtil.sessionValid(request)){// 页面流程
				return "klh/home";
			}else{
				return KlhUtil.redirectToOauth(model);
			}
		}
		return "redirect:./error";
	}

	
//	/**
//	 * 错误请求
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/error")
//	public String error(Model model) {
//		return "klh/error";
//	}
	
	
	
	
	/**
	 * 跳转请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/redirect")
	public String redirect(HttpServletRequest request) {
		return "klh/redirect";
	}

}
