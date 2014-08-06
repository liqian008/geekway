package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.service.IWxHistoryMessageService;

/**
 * 消息群发controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayHistoryMessageController {

	@Autowired
	private IWxHistoryMessageService wxHistoryMessageService;
	
	/**
	 * 历史消息列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/historyMessageList")
	public String historyMessageList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxHistoryMessage> historyMessageList = wxHistoryMessageService.queryAll(" id desc");
		model.addAttribute("historyMessageList", historyMessageList);
		return "geekway/historyMessageList";
	}
	
	/**
	 * 用户的对话消息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/historyMessageDialog")
	public String historyMessageDialog(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxHistoryMessage> userMessageList = wxHistoryMessageService.queryAll(" id desc");
		model.addAttribute("userMessageList", userMessageList);
		return "geekway/historyMessageDialog";
	}
	
}
