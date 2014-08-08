package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.service.IWxBroadcastService;
import com.bruce.geekway.utils.ConfigUtil;

/**
 * 消息群发controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayBroadcastController {

	private static final String WX_ACCOUNT_TYPE = ConfigUtil.getString("weixinmp_account_type");
	
	
	
	@Autowired
	private IWxBroadcastService wxBroadcastService;
	
	/**
	 * 群发记录列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/broadcastList")
	public String broadcastList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
		boolean isSenior = false;
		//判断是否是服务号（只有服务号才支持群发功能）
		if("senior".equalsIgnoreCase(WX_ACCOUNT_TYPE)){
			isSenior = true;
		}else{
			List<WxBroadcast> broadcastList = wxBroadcastService.queryAll();
			model.addAttribute("broadcastList", broadcastList);
		}
		model.addAttribute("isSenior", isSenior);
		
		return "geekway/broadcastList";
	}
	
	/**
	 * 创建群发任务
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/broadcastAdd")
	public String broadcastAdd(Model model, WxBroadcast broadcast, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("broadcast", broadcast);
		
		return "geekway/broadcastEdit";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param broadcast
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveBroadcast", method = RequestMethod.POST)
	public String saveBroadcast(Model model, WxBroadcast broadcast, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		broadcast.setUpdateTime(currentTime);
		
		if(broadcast!=null&&broadcast.getId()!=null&&broadcast.getId()>0){
			result = wxBroadcastService.updateById(broadcast);
		}
		model.addAttribute("redirectUrl", "./broadcastList");
		return "forward:/home/operationRedirect";
	}
	
//	@RequestMapping("/delBroadcast")
//	public String delBroadcast(Model model, int broadcastId) {
//		
//		//删除broadcast实体
//		wxBroadcastService.deleteById(broadcastId);
//		
//		model.addAttribute("redirectUrl", "./broadcastList");
//		return "forward:/home/operationRedirect";
//	}
}
