package com.bruce.geekway.admin.controller.geekway;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.WxWebUserCriteria;
import com.bruce.geekway.service.IWxWebUserService;

@Controller
@RequestMapping("/geekway")
public class GeekwayWebUserController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;

	@Autowired
	private IWxWebUserService wxWebUserService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxWebUserPaging")
	public String wxWebUserPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxWebUserCriteria criteria = new WxWebUserCriteria();
		criteria.setOrderByClause(" id desc");
		
		PagingResult<WxWebUser> wxWebUserPagingData = wxWebUserService.pagingByCriteria(pageNo, pageSize , criteria);
		if(wxWebUserPagingData!=null){
			wxWebUserPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			wxWebUserPagingData.setQueryMap(queryMap);
			model.addAttribute("wxWebUserPagingData", wxWebUserPagingData);
		}
		return "geekway/wxWebUserListPaging";
	}

	@RequestMapping("/wxWebUserInfo")
	public String wxWebUserInfo(Model model, HttpServletRequest request,
			int mpUserId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxWebUser wxWebUser = wxWebUserService.loadById(mpUserId);
		model.addAttribute("wxWebUser", wxWebUser);

		// List<WxWebUser> mpUserList = wxWebUserService.queryAll();
		// model.addAttribute("mpUserList", mpUserList);

		return "geekway/wxWebUserInfo";
	}
	

}
