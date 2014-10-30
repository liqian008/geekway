package com.bruce.geekway.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.DateUtil;
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
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, @RequestParam(required=false, defaultValue="false") boolean forceEdit, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}else{
			if(forceEdit){
				return "klh/profile";
			}else{
				KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
				String userOpenId = sessionUserProfile.getUserOpenId();
				//检查是否是首次绑定
				boolean firstBind = !klhUserScoreLogService.hasBind(userOpenId);
				model.addAttribute("firstBind", firstBind);
				if(firstBind){
					return "klh/profile";
				}else{
					return "klh/profilePreview";
				}
			}
		}
	}
	
	@RequestMapping(value = "/profilePreview", method = RequestMethod.GET)
	public String profilePreview(Model model, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}else{
			return "klh/profilePreview";
		}
	}
	
	
	/**
	 * 绑定操作
	 * @param model
	 * @param userProfile
	 * @return
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String bindProfileGo(Model model, KlhUserProfile userProfile, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		if(userProfile!=null){
			KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
			KlhUserProfile dbProfile = klhUserProfileService.loadByOpenid(sessionUserProfile.getUserOpenId());
			Date currentTime = new Date();
			if(dbProfile!=null){//原纪录存在，修改绑定
				userProfile.setUpdateTime(currentTime);
				userProfile.setId(dbProfile.getId());
				klhUserProfileService.updateById(userProfile);
				
				String userOpenId = sessionUserProfile.getUserOpenId();
				sessionUserProfile = klhUserProfileService.loadByOpenid(userOpenId);
				request.getSession().setAttribute("sessionUserProfile", sessionUserProfile); 
			
				//检查是否是首次绑定
				boolean firstBind = !klhUserScoreLogService.hasBind(userOpenId);
				if(firstBind){
					int bindScore = 0;
					
					//新增用户变化积分记录
					KlhSetting klhSetting = klhSettingService.loadKlhSetting();
					if(klhSetting!=null&&klhSetting.getBindScore()!=null){
						bindScore = klhSetting.getBindScore();
						KlhUserScoreLog scoreLog = new KlhUserScoreLog();
						scoreLog.setUserOpenId(userOpenId);
						scoreLog.setScoreType(1);//绑定的scoreType为1
						scoreLog.setScoreChange(bindScore);
						scoreLog.setCreateTime(currentTime);
						scoreLog.setReason("用户绑定资料，增加【"+bindScore+"】积分, "+DateUtil.DATE_FORMAT_YMDHMS.format(currentTime));
						int result =  klhUserScoreLogService.save(scoreLog);
					}
					model.addAttribute("firstBind", firstBind);
					model.addAttribute("bindScore", bindScore);
				}
			}
		}
		return "klh/profileResult";
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
