package com.bruce.geekway.controller.klh;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.klh.IKlhUserService;
import com.bruce.geekway.service.mp.WxMpOauthService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class MainController {
	
	@Autowired
	private WxMpOauthService mpOauthService;
//	@Autowired
//	private IKlhUserService klhUserService;
	
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		
		
		String authorizeUrl = mpOauthService.getAuthorizeUrl();
		model.addAttribute("authorizeUrl", authorizeUrl);
		return "klh/index";
	}
	
	
	/**
	 * 个人页
	 * @param model
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(value="code", required=false, defaultValue="") String code, HttpServletRequest request) {
		
		if(!StringUtils.isEmpty(code)){//回调地址进来的
			WxOauthTokenResult oauthResult = mpOauthService.getOauthAccessToken(code);
			if(oauthResult!=null&&oauthResult.getAccess_token()!=null){
				WxUserInfoResult sessionUser = mpOauthService.getOauthUserinfo(oauthResult.getAccess_token(), oauthResult.getOpenid());
				
				if(sessionUser!=null){
					request.getSession().setAttribute("sessionUser", sessionUser);
				}
				System.out.println("===nickname=="+sessionUser.getNickname());
			}
			System.out.println("===oauthResult=="+oauthResult.getOpenid());
		}else{//页面流程
			System.out.println("================");
		}
		return "klh/home";
	}
	
}
