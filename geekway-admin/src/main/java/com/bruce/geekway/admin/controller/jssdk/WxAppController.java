package com.bruce.geekway.admin.controller.jssdk;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;
import com.bruce.geekway.service.IWxAppService;



@Controller
@RequestMapping("/app")
public class WxAppController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxAppService wxAppService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/appPaging")
	public String appPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.setOrderByClause(" id desc");
		WxAppCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<WxApp> appPagingData = wxAppService.pagingByCriteria(pageNo, pageSize , criteria);
		if(appPagingData!=null){
			appPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			appPagingData.setQueryMap(queryMap);
			model.addAttribute("appPagingData", appPagingData);
		}
		return "app/appListPaging";
	}
	
}
