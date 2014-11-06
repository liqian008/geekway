package com.bruce.geekway.admin.controller.geekway;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.model.WxUserCriteria;
import com.bruce.geekway.model.wx.json.response.WxUserListResult;
import com.bruce.geekway.model.wx.json.response.WxUserListResult.OpenIdList;
import com.bruce.geekway.service.IWxUserService;
import com.bruce.geekway.service.mp.WxMpUserService;

@Controller
@RequestMapping("/geekway")
public class GeekwayUserController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;

	@Autowired
	private IWxUserService wxUserService;
	@Autowired
	private WxMpUserService wxMpUserService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxUserPaging")
	public String wxUserPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxUserCriteria criteria = new WxUserCriteria();
		criteria.setOrderByClause(" id desc");
		
		PagingResult<WxUser> wxUserPagingData = wxUserService.pagingByCriteria(pageNo, pageSize , criteria);
		if(wxUserPagingData!=null){
			wxUserPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			wxUserPagingData.setQueryMap(queryMap);
			model.addAttribute("wxUserPagingData", wxUserPagingData);
		}
		return "geekway/wxUserListPaging";
	}

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
		model.addAttribute("redirectUrl", "./wxUserPaging");
		return "forward:/home/operationRedirect";
	}

}
