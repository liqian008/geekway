package com.bruce.geekway.admin.controller.geekway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult.OpenIdList;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.service.mp.WxUserService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMpUserController {

	@Autowired
	private IWxMpUserService wxMpUserService;
	@Autowired
	private WxUserService wxUserService;
	

	@RequestMapping("/mpUserList")
	public String mpUserList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMpUser> mpUserList = wxMpUserService.queryAll();
		model.addAttribute("mpUserList", mpUserList);
		return "geekway/mpUserList";
	}

	@RequestMapping("/mpUserInfo")
	public String mpUserInfo(Model model, HttpServletRequest request,
			int mpUserId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMpUser mpUser = wxMpUserService.loadById(mpUserId);
		model.addAttribute("mpUser", mpUser);

		// List<WxMpUser> mpUserList = wxMpUserService.queryAll();
		// model.addAttribute("mpUserList", mpUserList);

		return "geekway/mpUserInfo";
	}
	
	
	@RequestMapping("/syncRemoteUser")
	public String syncRemoteUser(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxUserListResult userListResult =   wxUserService.getUsers(null);
		if(userListResult!=null&&userListResult.getErrcode()==null){
			OpenIdList openIdList = userListResult.getData();
			if(openIdList!=null&&openIdList.getOpenid()!=null){
				for(String userOpenId: openIdList.getOpenid()){
					wxMpUserService.newSubscribeUser(userOpenId);
				}
			}
		}
		model.addAttribute("redirectUrl", "./mpUserList");
		return "forward:/home/operationRedirect";
	}

}
