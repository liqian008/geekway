package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.service.IWxDefaultReplyService;


@Controller
@RequestMapping("/geekway")
public class GeekwayDefaultReplyController {

	@Autowired
	private IWxDefaultReplyService wxDefaultReplyService;
	
	
	@RequestMapping("/defaultReply")
	public String defaultReply(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxDefaultReply defaultReply = wxDefaultReplyService.loadById(1);
		model.addAttribute("defaultReply", defaultReply);
		return "defaultReply/defaultReplyEdit";
	}
	
	@RequestMapping(value = "/saveDefaultReply", method = RequestMethod.POST)
	public String saveArticle(Model model, WxDefaultReply defaultReply, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		defaultReply.setUpdateTime(currentTime);
		if(defaultReply!=null&&defaultReply.getId()!=null&&defaultReply.getId()>0){
			result = wxDefaultReplyService.updateById(defaultReply);
		}else{
			defaultReply.setCreateTime(currentTime);
			result = wxDefaultReplyService.save(defaultReply);
		}
		
		model.addAttribute("redirectUrl", "./defaultReply");
		return "forward:/home/operationRedirect";
	}
	
}
