package com.bruce.geekway.admin.controller.jssdk;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxJsapi;
import com.bruce.geekway.model.WxJsapiCriteria;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.IWxJsapiService;


/**
 * 系统jsapi管理
 * @author bruce
 *
 */
@Controller
@RequestMapping("/jsapi")
public class WxJsapiController {
	
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
	@RequestMapping("/jsapiList")
	public String jsapiList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxJsapiCriteria criteria = new WxJsapiCriteria();
		criteria.setOrderByClause(" id desc");
		
		List<WxJsapi> jsapiList = wxJsapiService.queryByCriteria(criteria);
		model.addAttribute("jsapiList", jsapiList);
		
		return "jsapi/jsapiList";
	}
	
	/**
	 * 添加app
	 * @param model
	 * @param jsapi
	 * @param request
	 * @return
	 */
	@RequestMapping("/jsapiAdd")
	public String jsapiAdd(Model model, WxJsapi jsapi, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("jsapi", jsapi);
		return "jsapi/jsapiAdd";
	} 
	
	@RequestMapping("/jsapiEdit")
	public String appEdit(Model model, HttpServletRequest request, int id) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxJsapi jsapi = wxJsapiService.loadById(id);
		model.addAttribute("jsapi", jsapi);
		return "jsapi/jsapiEdit";
	}
	
	/**
	 * 保存应用
	 * @param model
	 * @param jsapi
	 * @param appSkuValueIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveJsapi", method = RequestMethod.POST)
	public String mySaveApp(Model model, WxJsapi jsapi, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		int result = 0;
		
		Date currentTime = new Date();
		jsapi.setUpdateTime(currentTime);
		if(jsapi!=null&&jsapi.getId()!=null&&jsapi.getId()>0){
			result = wxJsapiService.updateById(jsapi);
			model.addAttribute("redirectUrl", "./jsapiList");
			return "forward:/home/operationRedirect";
		}else{//新增
			jsapi.setCreateTime(currentTime);
			//先设为定值1
			jsapi.setStatus((short) GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
			result = wxJsapiService.save(jsapi);
			model.addAttribute("redirectUrl", "./jsapiList");
			return "forward:/home/operationRedirect";
		}
	}
	
}
