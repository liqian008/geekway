package com.bruce.geekway.admin.controller.geekway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult.OpenIdList;
import com.bruce.geekway.service.IWxUserService;
import com.bruce.geekway.service.mp.WxMpUserService;

@Controller
@RequestMapping("/geekway")
public class GeekwayUserController {

	@Autowired
	private IWxUserService wxUserService;
	@Autowired
	private WxMpUserService wxMpUserService;
	

	@RequestMapping("/wxUserList")
	public String userList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxUser> wxUserList = wxUserService.queryAll();
		model.addAttribute("wxUserList", wxUserList);
		return "geekway/wxUserList";
	}

	@RequestMapping("/wxUserInfo")
	public String wxUserInfo(Model model, HttpServletRequest request,
			int mpUserId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxUser wxUser = wxUserService.loadById(mpUserId);
		model.addAttribute("wxUser", wxUser);

		// List<WxUser> mpUserList = wxUserService.queryAll();
		// model.addAttribute("mpUserList", mpUserList);

		return "geekway/wxUserInfo";
	}
	
	@Deprecated
	@RequestMapping("/syncRemoteUser")
	public String syncRemoteUser(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxUserListResult userListResult =   wxMpUserService.getUsers(null);
		if(userListResult!=null&&userListResult.getErrcode()==0){
			OpenIdList openIdList = userListResult.getData();
			if(openIdList!=null&&openIdList.getOpenid()!=null){
				for(String userOpenId: openIdList.getOpenid()){
					wxUserService.newSubscribeUser(userOpenId);
				}
			}
		}
		model.addAttribute("redirectUrl", "./mpUserList");
		return "forward:/home/operationRedirect";
	}

}
