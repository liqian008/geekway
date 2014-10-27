package com.bruce.geekway.admin.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.service.pay.IWxPayComplaintService;


/**
 * 微信维权通知
 * @author liqian
 *
 */
@Controller
@RequestMapping("/wxComplaint")
public class WxComplaintController {
	
	@Autowired
	private IWxPayComplaintService wxPayComplaintService;

	
	@RequestMapping("/complaintList")
	public String complaintList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxPayComplaint> complaintList = wxPayComplaintService.queryAll();
		model.addAttribute("complaintList", complaintList);
		return "complaint/complaintList";
	}
	
	@RequestMapping("/complaintInfo")
	public String complaintInfo(Model model, HttpServletRequest request, int complaintId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxPayComplaint complaint = wxPayComplaintService.loadById(complaintId);
		model.addAttribute("complaint", complaint);
		return "complaint/complaintInfo";
	}
	
	
	
}
