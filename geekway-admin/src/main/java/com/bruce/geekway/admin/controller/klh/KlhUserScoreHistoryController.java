package com.bruce.geekway.admin.controller.klh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhUserScoreHistory;
import com.bruce.geekway.service.klh.IKlhUserScoreHistoryService;

/**
 * 某个userScoreHistory下的userScoreHistory操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhUserScoreHistoryController {

	@Autowired
	private IKlhUserScoreHistoryService klhUserScoreHistoryService;
	
	@RequestMapping("/userScoreHistoryList")
	public String userScoreHistoryList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhUserScoreHistory> userScoreHistoryList = klhUserScoreHistoryService.queryAll();
		model.addAttribute("userScoreHistoryList", userScoreHistoryList);
		return "klh/userScoreHistoryList";
	}
	
	
	/**
	 * 编辑UserScoreHistory信息
	 * @param model
	 * @param request
	 * @param userScoreHistoryId
	 * @param userScoreHistoryId
	 * @return
	 */
	@RequestMapping("/userScoreHistoryDisplay")
	public String userScoreHistoryEdit(Model model, HttpServletRequest request, int userScoreHistoryId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhUserScoreHistory userScoreHistory = klhUserScoreHistoryService.loadById(userScoreHistoryId);
		if(userScoreHistory!=null){
			model.addAttribute("userScoreHistory", userScoreHistory);
		}
		return "klh/userScoreHistoryDisplay";
	}
	
}
