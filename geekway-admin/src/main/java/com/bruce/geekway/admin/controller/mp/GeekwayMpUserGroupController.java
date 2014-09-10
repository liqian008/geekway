package com.bruce.geekway.admin.controller.mp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.wx.json.WxGroupInfo;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult.OpenIdList;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.service.mp.WxUserGroupService;
import com.bruce.geekway.service.mp.WxUserService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMpUserGroupController {

	@Autowired
	private WxUserGroupService wxUserGroupService;
	

	@RequestMapping("/mpUserGroupList")
	public String mpUserList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxGroupInfo.Groups mpGroupList = wxUserGroupService.listGroups();
		model.addAttribute("mpGroupList", mpGroupList);
		return "geekway/mpGroupList";
	}

}
