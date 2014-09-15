package com.bruce.geekway.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.service.IWxUserService;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.utils.WxMpUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class OAuthController {
	
	@Autowired
	private WxMpOauthService wxMpOauthService;
	@Autowired
	private IWxUserService wxUserService;


	/**
	 * 个人页
	 * 
	 * @param model
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(required = false, defaultValue = "") String code, HttpServletRequest request) {
		if (!StringUtils.isEmpty(code)) {//回调地址进来的
			System.out.println("==========2=========");
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			System.out.println("==========3=========");
			if (oauthResult != null && oauthResult.getAccess_token() != null) {
				System.out.println("==========4=========");
				System.out.println("===oauthResult==" + oauthResult.getOpenid());
				System.out.println("==========5=========");
				String openId = oauthResult.getOpenid();
				System.out.println("==========6========="+openId);
				model.addAttribute("openId", openId);
				
				WxUser wxUser = wxUserService.loadByOpenId(openId);
				System.out.println("==========7========="+wxUser);
				model.addAttribute("wxUser", wxUser);
				
				//使用accessToken获取userInfo
//				WxUserInfoResult authUserInfoResult = mpOauthService.getOauthUserinfo(oauthResult.getAccess_token(), oauthResult.getOpenid());
//				if (authUserInfoResult != null) {
//					KlhUserProfile sessionUserProfile = klhUserProfileService.loadByOpenid(openid);
//					if (sessionUserProfile== null) {//全新用户
//						System.out.println("==========4=========");
//						sessionUserProfile = new KlhUserProfile();
//						sessionUserProfile.setUserOpenId(openid);
//					}
//					sessionUserProfile.setNickname(authUserInfoResult.getNickname());
//					request.getSession().setAttribute("sessionUserProfile", sessionUserProfile);
					return "home";
//				}
			}
		}else{
			boolean sessionValid = sessionValid(request);
			if(sessionValid){//用户session存在，页面流程
				System.out.println("==========11========="+sessionValid);
				return "home";
			}else{
				System.out.println("==========12========="+sessionValid);
				//跳转到oauth授权界面
				return redirectToOauth(model);
			}
		}
		return "redirect:./error";
	}

	/**
	 * 检查session是否可用
	 * @param model
	 * @return
	 */
	public static boolean sessionValid(HttpServletRequest request) {
		Boolean sessionExists  = (Boolean)request.getSession().getAttribute("hasLogin");
		if (sessionExists!= null && sessionExists) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 跳转至oauth界面
	 * @param model
	 * @return
	 */
	public static String redirectToOauth(Model model) {
		//跳转auth界面
		System.out.println("oauth url: "+WxMpUtil.AUTHORIZE_USERINFO_URL);
		return "redirect:"+WxMpUtil.AUTHORIZE_USERINFO_URL;
		
//		System.out.println("oauth url: "+WxUtil.AUTHORIZE_BASE_URL);
//		return "redirect:"+WxUtil.AUTHORIZE_BASE_URL;
	}

	public WxMpOauthService getWxMpOauthService() {
		return wxMpOauthService;
	}

	public void setWxMpOauthService(WxMpOauthService wxMpOauthService) {
		this.wxMpOauthService = wxMpOauthService;
	}

	public IWxUserService getWxUserService() {
		return wxUserService;
	}

	public void setWxUserService(IWxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	
	
}
