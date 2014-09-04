package com.bruce.geekway.admin.controller.klh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.service.klh.IKlhUserProfileService;

/**
 * 某个user下的user操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhUserProfileController {

	@Autowired
	private IKlhUserProfileService klhUserProfileService;
	
	@RequestMapping("/userProfileList")
	public String userProfileList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserProfile> userProfileList = klhUserProfileService.queryAll();
		model.addAttribute("userProfileList", userProfileList);
		return "klh/userProfileList";
	}
	
	
	/**
	 * 编辑UserProfile信息
	 * @param model
	 * @param request
	 * @param userId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userProfileDisplay")
	public String userProfileDisplay(Model model, HttpServletRequest request, String userOpenId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhUserProfile user = klhUserProfileService.loadByOpenid(userOpenId);
		if(user!=null){
			model.addAttribute("user", user);
		}
		return "klh/userProfileDisplay";
	}
	
	
//	/**
//	 * 编辑UserProfile信息
//	 * @param model
//	 * @param request
//	 * @param userId
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping("/userEdit")
//	public String userEdit(Model model, HttpServletRequest request, int userId) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		KlhUserProfile user = klhUserProfileService.loadById(userId);
//		if(user!=null){
//			model.addAttribute("user", user);
//		}
//		return "klh/userEdit";
//	}
	
//	/**
//	 * 保存单个user信息
//	 * @param model
//	 * @param klhUserProfile
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/saveUserProfile", method = RequestMethod.POST)
//	public String saveUserProfilePropValue(Model model, KlhUserProfile klhUserProfile, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = 0;
//		
//		Date currentTime = new Date();
//		
//		if(klhUserProfile!=null&&klhUserProfile.getId()!=null&&klhUserProfile.getId()>0){
//			klhUserProfile.setUpdateTime(currentTime);
//			result = klhUserProfileService.updateById(klhUserProfile);
//		}else{
//			klhUserProfile.setCreateTime(currentTime);
//			result = klhUserProfileService.save(klhUserProfile);
//		}
//		
//		model.addAttribute("redirectUrl", "./userList");
//		return "forward:/home/operationRedirect";
//	}
//	
//	/**
//	 * 删除user
//	 * @param model
//	 * @param userId
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/delUserProfile")
//	public String delUserProfileOption(Model model, int userId, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = klhUserProfileService.deleteById(userId);
//		
//		model.addAttribute("redirectUrl", "./userList");
//		return "forward:/home/operationRedirect"; 
//	}
	
}
