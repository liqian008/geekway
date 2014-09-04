package com.bruce.geekway.controller.klh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhDailySignService;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.KlhUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "klh" })
public class ScoreController {

	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	@Autowired
	private IKlhDailySignService klhDailySignService;
	@Autowired
	private IKlhSettingService klhSettingService;
	
	
	/**
	 * 积分介绍
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreIntro")
	public String scoreIntro(Model model, HttpServletRequest request) {
		
		
		KlhSetting klhSetting = klhSettingService.loadKlhSetting();
		model.addAttribute("klhSetting", klhSetting);
		return "klh/scoreIntro";
	}
	
	/**
	 * 积分主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreHome")
	public String scoreHome(Model model, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}

		KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
		
		int userCurrentScore = klhUserScoreLogService.queryCurrentScoreByUserOpenId(sessionUserProfile.getUserOpenId());
		model.addAttribute("userCurrentScore", userCurrentScore);
		
		return "klh/scoreHome";
	}
	
	/**
	 * 积分记录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userScoreLogList")
	public String userScoreList(Model model, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}

		KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
		String userOpenId = sessionUserProfile.getUserOpenId();

		List<KlhUserScoreLog> userScoreLogList = klhUserScoreLogService.queryByUserOpenId(userOpenId);
		if (userScoreLogList != null && userScoreLogList.size() > 0) {
			model.addAttribute("userScoreLogList", userScoreLogList);
		}
		return "klh/userScoreLogList";
	}

	/**
	 * 每日签到
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dailySign")
	public String dailySign(Model model, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			// TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}

		KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
		String userOpenId = sessionUserProfile.getUserOpenId();
		// 签到
		int result = klhDailySignService.sign(userOpenId);
		return "redirect:./userScoreLogList";
	}

}
