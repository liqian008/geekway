package com.bruce.geekway.admin.controller.jssdk;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;
import com.bruce.geekway.model.WxJsapi;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.IWxAppService;
import com.bruce.geekway.service.IWxJsapiService;


/**
 * 我的微信应用管理
 * @author liqian
 *
 */
@Controller
@RequestMapping("/app")
public class WxMyAppController extends BaseController{
	
	@Autowired
	private IWxAppService wxAppService;
	@Autowired
	private IWxJsapiService wxJsapiService;
	
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/myWxAppList")
	public String myWxAppList(Model model, HttpServletRequest request) {
		
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.setOrderByClause(" id desc");
		WxAppCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andUserIdEqualTo(getUserId());
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			if("get".equalsIgnoreCase(request.getMethod())){
				name = URLDecoder.decode(name);
			}
			subCriteria.andNameLike("%"+name+"%");
			model.addAttribute("name", name);
		}
		
		List<WxApp> appList = wxAppService.queryByCriteria(criteria);
		if(appList!=null){
			model.addAttribute("appList", appList);
		}
		return "app/myWxAppList";
	}
	
	

	/**
	 * 添加app
	 * @param model
	 * @param app
	 * @param request
	 * @return
	 */
	@RequestMapping("/myWxAppAdd")
	public String myWxAppAdd(Model model, WxApp app, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("app", app);
		
		List<WxJsapi> allJsapiList = wxJsapiService.queryAll();
		model.addAttribute("allJsapiList", allJsapiList);
		
		return "app/myWxAppAdd";
	} 
	
	@RequestMapping("/myWxAppEdit")
	public String myWxAppEdit(Model model, HttpServletRequest request, int appId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxApp app = wxAppService.loadMyApp(appId, getUserId());
		model.addAttribute("app", app);
		
		List<WxJsapi> allJsapiList = wxJsapiService.queryAll();
		model.addAttribute("allJsapiList", allJsapiList);
		
		return "app/myWxAppEdit";
	}
	
	/**
	 * 保存应用
	 * @param model
	 * @param app
	 * @param appSkuValueIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveMyWxApp", method = RequestMethod.POST)
	public String mySaveWxApp(Model model, WxApp app, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		int result = 0;
		
		Date currentTime = new Date();
		app.setUpdateTime(currentTime);
		if(app!=null&&app.getId()!=null&&app.getId()>0){
			result = wxAppService.updateById(app);
			model.addAttribute("redirectUrl", "./myAppList");
			return "forward:/home/operationRedirect";
		}else{//新增
			app.setUserId(getUserId());
			app.setCreateTime(currentTime);
			//先设为定值1
			app.setStatus((short) GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
			result = wxAppService.save(app);
			model.addAttribute("redirectUrl", "./myWxAppList");
			return "forward:/home/operationRedirect";
		}
	}
	
}
