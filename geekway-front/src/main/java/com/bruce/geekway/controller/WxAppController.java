package com.bruce.geekway.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.RegUser;
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;
import com.bruce.geekway.service.IRegUserService;
import com.bruce.geekway.service.IWxAppService;
import com.bruce.geekway.utils.ResponseUtil;

@Controller
public class WxAppController {

	private static final Logger logger = LoggerFactory.getLogger(WxAppController.class);
	
	@Autowired
	private IWxAppService wxAppService;
	@Autowired
	private IRegUserService regUserService;

//	private static StringBuilder jsSrcTemplateSb = new StringBuilder();
//	static{
//		jsSrcTemplateSb.append("<script>\r\n");
//		jsSrcTemplateSb.append("var currentUrl = location.href.split('#')[0];\r\n");
//		jsSrcTemplateSb.append("var wxJssdkSrc = \"http://jssdk.meiniur.com/api/wxJsConfigSrc?wxAppId=%s&nonce=%s&sign=%s&pageUrl=\"+currentUrl;\r\n");
//		jsSrcTemplateSb.append("document.write(\"<script type='text/javascript' src='\"+wxJssdkSrc+\"'/>\");\r\n");
//		jsSrcTemplateSb.append("</script>");
//	}

	@NeedAuthorize
	@RequestMapping(value = "myWxApps")
	public String myWxApps(Model model, HttpServletRequest request, HttpServletResponse response) {
		RegUser regUser = (RegUser)request.getSession().getAttribute(ConstFront.CURRENT_USER);
		
		List<WxApp> myWxAppList = wxAppService.queryMyApp(regUser.getId());
//		if(myWxAppList!=null&&myWxAppList.size()==1){
//			int appId = myWxAppList.get(0).getId();
//			return ResponseUtil.getRedirectString("./myWxAppInfo?id="+appId);
//		}else{
		model.addAttribute("myWxAppList", myWxAppList);
		return "wxApp/myWxAppList";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "myWxAppAdd")
	public String myWxAppAdd(Model model, WxApp myWxApp, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("myWxApp", myWxApp);
		return "wxApp/myWxAppAdd";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "myWxAppInfo")
	public String myWxAppInfo(Model model, int id, HttpServletRequest request, HttpServletResponse response) {
		RegUser regUser = (RegUser)request.getSession().getAttribute(ConstFront.CURRENT_USER);
		
		WxApp myWxApp = wxAppService.loadMyApp(id, regUser.getId());
		
		if(myWxApp!=null){
			model.addAttribute("myWxApp", myWxApp);
//			String jsSrc = String.format(jsSrcTemplateSb.toString(), myWxApp.getWxAppId(), "nonceVal","signVal");
//			model.addAttribute("jsSrc", jsSrc);
			return "wxApp/myWxAppInfo";
		}
		return "";
	}
	
	@NeedAuthorize
	@RequestMapping(value = "saveMyWxApp")
	public String saveMyWxApp(Model model, WxApp myWxApp, HttpServletRequest request, HttpServletResponse response) {
		
		RegUser regUser = (RegUser)request.getSession().getAttribute(ConstFront.CURRENT_USER);
		myWxApp.setUserId(regUser.getId());
		myWxApp.setCreateTime(new Date());
		int result = wxAppService.save(myWxApp);
		
		model.addAttribute(ConstFront.REDIRECT_PROMPT, "微信应用添加成功！");
		model.addAttribute(ConstFront.REDIRECT_URL, "./myWxAppInfo?id="+myWxApp.getId());
		
		return ResponseUtil.getForwardReirect(); 
	}
	
	@NeedAuthorize
	@RequestMapping(value = "delMyWxApp")
	public String delMyWxApp(Model model, int id, HttpServletRequest request, HttpServletResponse response) {
		
		RegUser regUser = (RegUser)request.getSession().getAttribute(ConstFront.CURRENT_USER);
		
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.createCriteria().andIdEqualTo(id).andUserIdEqualTo(regUser.getId());
		int result = wxAppService.deleteByCriteria(criteria);
		
		model.addAttribute(ConstFront.REDIRECT_PROMPT, "微信应用删除成功！");
		model.addAttribute(ConstFront.REDIRECT_URL, "./myWxApps");
		
		return ResponseUtil.getForwardReirect();
	}
	
	@RequestMapping(value = "wxJsDemo")
	public String wxJsDemo(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "wxApp/wxJsDemo";
	}
	
}