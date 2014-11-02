package com.bruce.geekway.admin.controller.wxpay;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.admin.constants.ConstAdmin;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayComplaintCriteria;
import com.bruce.geekway.service.pay.IWxPayComplaintService;


/**
 * 微信维权通知
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WxComplaintController {
	

	private static final int pageSize = ConstAdmin.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxPayComplaintService wxPayComplaintService;

	
	@RequestMapping("/complaintList")
	public String complaintList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayComplaint> complaintList = wxPayComplaintService.queryAll();
		model.addAttribute("complaintList", complaintList);
		return "wxpay/complaintList";
	}
	
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/complaintPaging")
	public String complaintPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxPayComplaintCriteria criteria =  null;
		//根据模块的需求构造查询条件
		String resourceName = request.getParameter("resourceName");
		if(StringUtils.isNotBlank(resourceName)){
			criteria = new WxPayComplaintCriteria();
			if("get".equalsIgnoreCase(request.getMethod())){
				resourceName = URLDecoder.decode(resourceName);
			}
			model.addAttribute("resourceName", resourceName);
//			criteria.createCriteria().andResourceNameLike("%"+resourceName+"%");
		}
		
		PagingResult<WxPayComplaint> complaintPagingData = wxPayComplaintService.pagingByCriteria(pageNo, pageSize , criteria);
		if(complaintPagingData!=null){
			complaintPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap();
			queryMap.putAll(request.getParameterMap());
			complaintPagingData.setQueryMap(queryMap);
			model.addAttribute("complaintPagingData", complaintPagingData);
		}
		return "wxpay/complaintListPaging";
	}
	
	@RequestMapping("/complaintInfo")
	public String complaintInfo(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayComplaint complaint = wxPayComplaintService.loadById(id);
		model.addAttribute("complaint", complaint);
		return "wxpay/complaintInfo";
	}
	
}
