package com.bruce.geekway.admin.controller.jssdk;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.foundation.admin.model.security.AdminUser;
import com.bruce.foundation.admin.service.security.AdminRoleService;
import com.bruce.foundation.admin.service.security.AdminUserService;
import com.bruce.foundation.enumeration.StatusEnum;
import com.bruce.foundation.util.ValidatorUtil;


/**
 * 用户注册controller
 * @author bruce
 *
 */
@Controller
public class UserRegisteController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserRegisteController.class);
	
	private static final int pageSize = 50;
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private PasswordEncoder pwEncoder;
	
	
	@RequestMapping("/registe")
	public String registe(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		adminUser.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("adminUser", adminUser);
		return "jssdk/registe";
	}
	
	@RequestMapping(value = "/registeGo", method = RequestMethod.POST)
	public String registeGo(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		String username = adminUser.getUsername();
		if(adminUser==null || StringUtils.isBlank(username)){
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/home/operationResult";
		}
		
		//过滤非法字符
		username = ValidatorUtil.filterUnSafeChar(username).trim();
		adminUser.setUsername(username);
		
		//检查账户是否存在
		
		Date currentTime = new Date();
		adminUser.setUpdateTime(currentTime);
		//创建新用户时对密码进行加密
		String password = adminUser.getPassword();
		adminUser.setPassword(pwEncoder.encode(password));
		adminUser.setCreateTime(currentTime);
		result = adminUserService.save(adminUser);
		
		//创建成功，跳转到登录界面
		model.addAttribute("redirectUrl", "./login");
		return "forward:/home/operationRedirect";
	}
	
}
