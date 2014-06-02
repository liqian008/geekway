package com.bruce.geekway.controller.klh;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.service.klh.IKlhVoteOptionService;
import com.bruce.geekway.service.klh.IKlhVoteService;
import com.bruce.geekway.utils.KlhUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class VoteController {
	
	@Autowired
	private IKlhVoteService klhVoteService;
	@Autowired
	private IKlhVoteOptionService klhVoteOptionService;
	
	
	/**
	 * 投票详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/voteList")
	public String voteInfo(Model model, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		List<KlhVote> voteList = klhVoteService.queryAll();
		if(voteList!=null&&voteList.size()>0){
			model.addAttribute("voteList", voteList);
		}
		return "klh/voteList";
	}
	
	
	/**
	 * 投票详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/voteInfo")
	public String voteInfo(Model model, int voteId, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		KlhVote voteInfo = klhVoteService.loadById(voteId);
		if(voteInfo!=null&&voteInfo.getId()>0){
			model.addAttribute("voteInfo", voteInfo);
			List<KlhVoteOption> voteOptionList = klhVoteOptionService.queryByVoteId(voteId);
			
			model.addAttribute("voteOptionList", voteOptionList);
		}
		return "klh/voteInfo";
	}
	

	/**
	 * 投票
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vote", method=RequestMethod.POST)
	public String vote(Model model, int voteId, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		KlhVote voteInfo = klhVoteService.loadById(voteId);
		if(voteInfo!=null&&voteInfo.getId()>0){
			model.addAttribute("voteInfo", voteInfo);
			
		}
		return "klh/voteInfo";
	}
}
