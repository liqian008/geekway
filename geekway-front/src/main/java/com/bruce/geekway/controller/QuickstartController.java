package com.bruce.geekway.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.IRegUserService;
import com.bruce.geekway.service.IWxAppService;
import com.bruce.geekway.utils.ResponseUtil;

@Controller
public class QuickstartController {

	private static final Logger logger = LoggerFactory.getLogger(QuickstartController.class);
	
	@Autowired
	private IWxAppService wxAppService;
	@Autowired
	private IRegUserService regUserService;

	
	
	@RequestMapping(value = "quickstart")
	public String quickstart(HttpServletRequest request, HttpServletResponse response) {
		return "quickstart";
	}
	
	@RequestMapping(value = "quickstartGo", method=RequestMethod.POST)
	public String quickstartGo(
			Model model,
			@RequestParam(required = true, defaultValue = "") String email,
			@RequestParam(required = true, defaultValue = "") String password,
			@RequestParam(required = true, defaultValue = "") String mobile,
			@RequestParam(required = true, defaultValue = "") String name,
			@RequestParam(required = true, defaultValue = "") String wxAppId,
			@RequestParam(required = true, defaultValue = "") String wxAppSecret,
			HttpServletRequest request, HttpServletResponse response) {
		//check email
		//check passwd

		RegUser regUser = new RegUser();
		regUser.setEmail(email.trim());
		regUser.setMobile(mobile.trim());		

		regUser.setPassword(password.trim());
		Date currentTime = new Date();
		regUser.setCreateTime(currentTime);
		int result = regUserService.save(regUser);
		if (result == 1) {
			if(logger.isDebugEnabled()){
				logger.debug("用户["+email+"]注册成功", email);
			}
			regUser = regUserService.authRegUser(email, password);
			if(regUser!=null){
				request.getSession().setAttribute(ConstFront.CURRENT_USER, regUser);
//				model.addAttribute(ConstFront.REDIRECT_PROMPT, "您已成功注册，现在将转入首页，请稍候…");
				
				WxApp wxApp = new WxApp();
				wxApp.setUserId(regUser.getId());
				wxApp.setName(name);
				wxApp.setWxAppId(wxAppId);
				wxApp.setWxAppSecret(wxAppSecret);
				wxApp.setCreateTime(currentTime);
				//构造自己的appId和secretKey
				
				result = wxAppService.save(wxApp);
				
				if(logger.isDebugEnabled()){
					logger.debug("系统为用户["+email+"]发送欢迎消息", email);
				}
				String redirectUrl = "./myWxAppInfo?id="+wxApp.getId();//跳转到应用详情页
				model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
				model.addAttribute(ConstFront.REDIRECT_PROMPT, "您已成功创建庄户&微信应用，系统正在为您跳转到配置页，请稍候…");
				
				return ResponseUtil.getForwardReirect();
			}
		} else {
			if(logger.isErrorEnabled()){
				logger.error("用户["+email+"]快速配置失败", email);
			}
//			model.addAttribute(ConstFront.REG_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_REGISTER_ERROR));
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	

	
}