package com.bruce.geekway.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.KlhUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "klh" })
public class KlhUserController {

	@Autowired
	private IKlhUserProfileService klhUserProfileService;
	@Autowired
	private IKlhSettingService klhSettingService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;

	@RequestMapping(value = "/bindProfile", method = RequestMethod.GET)
	public String bindProfile(Model model, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}else{
			return "klh/bindProfile";
		}
	}

	/**
	 * 绑定操作
	 * @param model
	 * @param userProfile
	 * @return
	 */
	@RequestMapping(value = "/bindProfile", method = RequestMethod.POST)
	public String bindProfileGo(Model model, KlhUserProfile userProfile, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		if(userProfile!=null){
			String userOpenId = userProfile.getUserOpenId();
			if(!StringUtils.isEmpty(userOpenId)){
				KlhUserProfile dbProfile = klhUserProfileService.loadByOpenid(userOpenId);
				Date currentTime = new Date();
				if(dbProfile!=null){//原纪录存在，修改绑定
					userProfile.setUpdateTime(currentTime);
					userProfile.setId(dbProfile.getId());
					int result = klhUserProfileService.updateById(userProfile);
				}else{
					userProfile.setCreateTime(currentTime);
					int result = klhUserProfileService.bindProfile(userOpenId, userProfile);
				}
			}else{
				//异常流程
				System.out.println("====bindProfile 绑定出错====");
			}
		}
		return "redirect:./bindProfile";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model) {
//		return "klh/login";
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginGo(Model model) {
//		return "klh/login";
//	}
//
//	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	public String register(Model model) {
//		return "klh/register";
//	}
//
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String registerGo(Model model) {
//		return "klh/register";
//	}
}
