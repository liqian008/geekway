package com.bruce.geekway.controller.klh;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.KlhUserScoreHistory;
import com.bruce.geekway.service.klh.IKlhUserScoreHistoryService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class ScoreController {
	
	@Autowired
	private IKlhUserScoreHistoryService klhUserScoreHistoryService;
	
	/**
	 * 积分记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userScoreHistoryList")
	public String userScoreList(Model model) {
		int userId = 1;
		List<KlhUserScoreHistory> userScoreHistoryList = klhUserScoreHistoryService.queryByUserId(userId);
		if(userScoreHistoryList!=null&&userScoreHistoryList.size()>0){
			model.addAttribute("userScoreHistoryList",userScoreHistoryList);
		}
		return "klh/userScoreHistoryList";
	}

}
