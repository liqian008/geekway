package com.bruce.geekway.admin.controller.klh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhUser;
import com.bruce.geekway.service.klh.IKlhUserService;

/**
 * 某个user下的user操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhUserController {

	@Autowired
	private IKlhUserService klhUserService;
	
	@RequestMapping("/userList")
	public String userList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUser> userList = klhUserService.queryAll();
		model.addAttribute("userList", userList);
		return "klh/userList";
	}
	
	
//	@RequestMapping("/userAdd")
//	public String userAdd(Model model, KlhUser user, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		model.addAttribute("user", user);
//		return "klh/userEdit";
//	}
	
	
	/**
	 * 编辑User信息
	 * @param model
	 * @param request
	 * @param userId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userEdit")
	public String userEdit(Model model, HttpServletRequest request, int userId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhUser user = klhUserService.loadById(userId);
		if(user!=null){
			model.addAttribute("user", user);
		}
		return "klh/userEdit";
	}
	
//	/**
//	 * 保存单个user信息
//	 * @param model
//	 * @param klhUser
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
//	public String saveUserPropValue(Model model, KlhUser klhUser, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = 0;
//		
//		Date currentTime = new Date();
//		
//		if(klhUser!=null&&klhUser.getId()!=null&&klhUser.getId()>0){
//			klhUser.setUpdateTime(currentTime);
//			result = klhUserService.updateById(klhUser);
//		}else{
//			klhUser.setCreateTime(currentTime);
//			result = klhUserService.save(klhUser);
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
//	@RequestMapping(value = "/delUser")
//	public String delUserOption(Model model, int userId, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = klhUserService.deleteById(userId);
//		
//		model.addAttribute("redirectUrl", "./userList");
//		return "forward:/home/operationRedirect"; 
//	}
	
}
