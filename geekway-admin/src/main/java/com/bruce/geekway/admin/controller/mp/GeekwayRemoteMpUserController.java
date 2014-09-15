package com.bruce.geekway.admin.controller.mp;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.service.mp.WxMpUserService;


@Controller
@RequestMapping("/geekway")
public class GeekwayRemoteMpUserController {

	@Autowired
	private WxMpUserService wxUserService;
	
	@RequestMapping("/mpUserListRemote") 
	public String mpUserList(Model model, HttpServletRequest request, String nextOpenId, @RequestParam(value="pageSize", required=false, defaultValue="1") int pageSize) {
		
		List<String> openIdList = null;//(List<String>) request.getSession().getAttribute("openIdList");
		
		if(openIdList==null){
			WxUserListResult userListResult = wxUserService.getUsers(nextOpenId);
			if(userListResult!=null){
				
				model.addAttribute("total", userListResult.getTotal());
				model.addAttribute("count", userListResult.getCount());
				model.addAttribute("nextOpenId", userListResult.getNext_openid());
				openIdList = userListResult.getData().getOpenid();
				request.getSession().setAttribute("openIdList", openIdList);
			}
		}
		
		List<WxUserInfoResult> userinfoList = new ArrayList<WxUserInfoResult>();
		int userSize = openIdList.size();
		if(userSize>0){
			int fetchRow = userSize>10?10:userSize;
			for(int i=0;i<fetchRow;i++){
				WxUserInfoResult userInfo = wxUserService.getUser(openIdList.get(i));
				userinfoList.add(userInfo);
			}
		}
		model.addAttribute("userinfoList", userinfoList);
		return "geekway/mpUserListRemote";
	}
	
	
	
}
