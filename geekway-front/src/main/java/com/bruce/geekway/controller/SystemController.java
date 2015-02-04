package com.bruce.geekway.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.RegUser;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.IRegUserService;
import com.bruce.geekway.utils.ResponseUtil;


@Controller
public class SystemController {

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private IRegUserService regUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("登录前的redirectUrl参数: "+redirectUrl);
		}
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl); 
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request, @RequestParam(required=true)String email, String password, @RequestParam(defaultValue = "") String verifyCode,
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		
		if(logger.isDebugEnabled()){
			logger.debug("提交登录后的redirectUrl地址: "+ redirectUrl);
		}
		
		if (StringUtils.isEmpty(redirectUrl)) {
			redirectUrl = "./myWxApps";//默认跳转地址
		}
		
		RegUser regUser = regUserService.authRegUser(email.trim(), password);
		if (regUser != null) {
			request.getSession().setAttribute(ConstFront.CURRENT_USER, regUser);
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
			model.addAttribute(ConstFront.REDIRECT_PROMPT, "您好，您已成功登录，现在将转后续页面，请稍候…");
			if(logger.isDebugEnabled()){
				logger.debug("用户["+email+"]登录成功");
			}
			return ResponseUtil.getForwardReirect();
		} else {// 用户身份验证失败
			if(logger.isErrorEnabled()){
				logger.error("用户["+email+"]登录认证失败：账户密码不匹配");
			}
//			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_PASSWORD_NOT_MATCH));
			return "login";
		}
	}
	
	
	@RequestMapping(value = "/registe", method = RequestMethod.GET)
	public String registe(Model model, @RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("注册前的redirectUrl参数: "+redirectUrl);
		}
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl); 
		}
		return "registe";
	}
	
	
	@RequestMapping(value = "/registe", method = RequestMethod.POST)
	public String registerGo(Model model, HttpServletRequest request, String email, String password, String rePassword,
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		//标志为注册tab
		model.addAttribute(ConstFront.REGISTER_ACTIVE, "REGISTER_ACTIVE");
		
		if(logger.isDebugEnabled()){
			logger.debug("提交注册时的redirectUrl地址: " + redirectUrl);
		}
		
		if (StringUtils.isEmpty(redirectUrl)) {
			redirectUrl = "./myWxApps";//默认跳转地址
		}
		
		//验证用户名格式
//		VerifyUtils.verifyEmail(email);
		
		RegUser regUser = new RegUser();
		regUser.setEmail(email.trim());
		regUser.setPassword(password.trim());
		Date currentTime = new Date();
		regUser.setCreateTime(currentTime);
		regUser.setUpdateTime(currentTime);
		int result = regUserService.save(regUser);
		if (result == 1) {
			if(logger.isDebugEnabled()){
				logger.debug("用户["+email+"]注册成功", email);
			}
			regUser = regUserService.authRegUser(email, password);
			if(regUser!=null){
				request.getSession().setAttribute(ConstFront.CURRENT_USER, regUser);
				model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
				
				model.addAttribute(ConstFront.REDIRECT_PROMPT, "您已成功添加微信应用，现在将转配置页，请稍候…");
				//系统发送欢迎消息
//				if(logger.isDebugEnabled()){
//					logger.debug("系统为用户["+email+"]发送欢迎消息", email);
//				}
				return ResponseUtil.getForwardReirect();
			}
		} else {
			if(logger.isErrorEnabled()){
				logger.error("用户["+email+"]注册失败", email);
			}
//			model.addAttribute(ConstFront.REG_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_REGISTER_ERROR));
			return "login/loginAndReg";
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("用户注销登录");
		}
		request.getSession().removeAttribute(ConstFront.CURRENT_USER);
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您已成功注销登录，随后将以游客身份转入首页，请稍候…");
		model.addAttribute(ConstFront.REDIRECT_URL, "./login");
		return ResponseUtil.getForwardReirect();
	}
	
	/**
	 * 跳转请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/redirect")
	public String redirect(HttpServletRequest request) {
		return "redirect";
	}
	
	@RequestMapping(value = "/denied")
	public String denied(HttpServletRequest request) {
		return "denied";
	}

}
