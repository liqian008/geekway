package com.bruce.geekway.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.KlhUtil;

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
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	@Autowired
	private IKlhSettingService klhSettingService;
	
	
	@RequestMapping(value = {"mockSession"})
	public String mockSession(Model model, HttpServletRequest request) {
		
//		KlhUserProfile userProfile = new KlhUserProfile();
//		userProfile.setId(62);
//		userProfile.setUserOpenId("oxGeHjg87dS82dsp4iP4SE1iVujA");
//		userProfile.setNickname("test");
//		userProfile.setCreateTime(new Date());
//		userProfile.setStatus((short) 1);
		
		KlhUserProfile userProfile = klhUserProfileService.loadById(62);
		request.getSession().setAttribute("sessionUserProfile", userProfile);
		
		return "klh/index";
	}
	
	
	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/","/index"})
	public String index(Model model) {
		return "klh/index";
	}
	
//	/**
//	 * 带用户模块模式的首页
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/indexFull")
//	public String indexFull(Model model) {
//		return "klh/index_full";
//	}

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
			System.out.println("==========oauthResult.code========="+code);
			WxOauthTokenResult oauthResult = mpOauthService.getOauthAccessToken(code);
			if (oauthResult != null && oauthResult.getAccess_token() != null) {
				System.out.println("==========oauthResult.getAccess_token()========="+oauthResult.getAccess_token());
				String userOpenId = oauthResult.getOpenid();
				System.out.println("==========oauthResult.getOpenid()()========="+userOpenId);
				//使用accessToken获取userInfo
				WxUserInfoResult authUserInfoResult = mpOauthService.getOauthUserinfo(oauthResult.getAccess_token(), oauthResult.getOpenid());
				if (authUserInfoResult != null) {
					System.out.println("==========getNickname========="+authUserInfoResult.getNickname());
					KlhUserProfile userProfile = klhUserProfileService.loadByOpenid(userOpenId);
					int result = 0;
					if (userProfile== null) {//全新用户
						Date currentTime = new Date();
						//TODO 新增用户
						System.out.println("==========4=========");
						userProfile = new KlhUserProfile();
						userProfile.setUserOpenId(userOpenId);
						userProfile.setNickname(authUserInfoResult.getNickname());
						userProfile.setCreateTime(currentTime);
						userProfile.setStatus((short) 1);
						klhUserProfileService.save(userProfile);
						
					}else{
						result = 1;
					}
					if(result>0){
						request.getSession().setAttribute("sessionUserProfile", userProfile);
					}
					return "klh/index";
				}
			}
			System.out.println("=============");
		}else{
			if(KlhUtil.sessionValid(request)){// 页面流程
				return "klh/index";
			}else{
				return KlhUtil.redirectToOauth(model);
			}
		}
		return "redirect:./error";
	}
	
	/**
	 * 错误请求
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error")
	public String error(Model model) {
		return "klh/error";
	}
	
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
