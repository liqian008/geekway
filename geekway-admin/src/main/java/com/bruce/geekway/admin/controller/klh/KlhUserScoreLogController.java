package com.bruce.geekway.admin.controller.klh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;

/**
 * 某个userScoreLog下的userScoreLog操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhUserScoreLogController {

	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	@Autowired
	private IKlhUserProfileService klhUserProfileService;
	
	@RequestMapping("/scoreLogList")
	public String scoreLogList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserScoreLog> userScoreLogList = klhUserScoreLogService.queryAll();
		model.addAttribute("userScoreLogList", userScoreLogList);
		
		if(userScoreLogList!=null&&userScoreLogList.size()>0){
			for(KlhUserScoreLog userScoreLog: userScoreLogList){
				
				KlhUserProfile userProfile = klhUserProfileService.loadByOpenid(userScoreLog.getUserOpenId());
				if(userProfile!=null){
					String nickname = StringUtils.isBlank(userProfile.getNickname())?"":userProfile.getNickname();
					userScoreLog.setUserNickname(nickname);
				}
			}
		}
		return "klh/userScoreLogList";
	}
	
	
	@RequestMapping("/userScoreLogList")
	public String userScoreLogList(Model model, String userOpenId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserScoreLog> userScoreLogList = klhUserScoreLogService.queryByUserOpenId(userOpenId);
		model.addAttribute("userScoreLogList", userScoreLogList);
		
		KlhUserProfile userProfile = klhUserProfileService.loadByOpenid(userOpenId);
		if(userProfile!=null){
			if(userScoreLogList!=null&&userScoreLogList.size()>0){
				for(KlhUserScoreLog userScoreLog: userScoreLogList){
					String nickname = StringUtils.isBlank(userProfile.getNickname())?"":userProfile.getNickname();
					userScoreLog.setUserNickname(nickname);
				}
			}
		}
		
		return "klh/userScoreLogList";
	}

	
	/**
	 * 编辑UserScoreLog信息
	 * @param model
	 * @param request
	 * @param userScoreLogId
	 * @param userScoreLogId
	 * @return
	 */
	@RequestMapping("/userScoreLogDisplay")
	public String userScoreLogEdit(Model model, HttpServletRequest request, int userScoreLogId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhUserScoreLog userScoreLog = klhUserScoreLogService.loadById(userScoreLogId);
		if(userScoreLog!=null){
			model.addAttribute("userScoreLog", userScoreLog);
			
			String userOpenId = userScoreLog.getUserOpenId();
			KlhUserProfile userProfile = klhUserProfileService.loadByOpenid(userOpenId);
			if(userProfile!=null){
				model.addAttribute("userProfile", userProfile);
			}
		}
		return "klh/userScoreLogDisplay";
	}
	
}
