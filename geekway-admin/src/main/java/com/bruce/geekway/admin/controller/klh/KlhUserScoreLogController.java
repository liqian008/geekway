package com.bruce.geekway.admin.controller.klh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;

/**
 * 某个userScoreLog下的userScoreLog操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhUserScoreLogController {

	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	@RequestMapping("/scoreLogList")
	public String scoreLogList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserScoreLog> userScoreLogList = klhUserScoreLogService.queryAll();
		model.addAttribute("userScoreLogList", userScoreLogList);
		return "klh/userScoreLogList";
	}
	
	
	@RequestMapping("/userScoreLogList")
	public String userScoreLogList(Model model, String userOpenId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserScoreLog> userScoreLogList = klhUserScoreLogService.queryByUserOpenId(userOpenId);
		model.addAttribute("userScoreLogList", userScoreLogList);
		return "klh/userScoreLogList";
	}

	
	/**
	 * 编辑UserScoreLog信息
	 * @param model
	 * @param request
	 * @param userScoreLogId
	 * @param userScoreLogId
	 * @return
	 */
	@RequestMapping("/userScoreLogDisplay")
	public String userScoreLogEdit(Model model, HttpServletRequest request, int userScoreLogId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhUserScoreLog userScoreLog = klhUserScoreLogService.loadById(userScoreLogId);
		if(userScoreLog!=null){
			model.addAttribute("userScoreLog", userScoreLog);
		}
		return "klh/userScoreLogDisplay";
	}
	
}
