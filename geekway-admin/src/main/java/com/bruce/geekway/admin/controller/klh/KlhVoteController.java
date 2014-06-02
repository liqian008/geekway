package com.bruce.geekway.admin.controller.klh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.service.klh.IKlhVoteOptionService;
import com.bruce.geekway.service.klh.IKlhVoteResultService;
import com.bruce.geekway.service.klh.IKlhVoteService;

/**
 * 某个vote下的vote操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhVoteController {

	@Autowired
	private IKlhVoteService klhVoteService;
	@Autowired
	private IKlhVoteOptionService klhVoteOptionService;
	@Autowired
	private IKlhVoteResultService klhVoteResultService;
	
	@RequestMapping("/voteList")
	public String voteList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhVote> voteList = klhVoteService.queryAll();
		model.addAttribute("voteList", voteList);
		return "klh/voteList";
	}
	
	
	@RequestMapping("/voteAdd")
	public String voteAdd(Model model, KlhVote vote, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("vote", vote);
		return "klh/voteEdit";
	}
	
	
	/**
	 * 编辑Vote信息
	 * @param model
	 * @param request
	 * @param voteId
	 * @param voteId
	 * @return
	 */
	@RequestMapping("/voteEdit")
	public String voteEdit(Model model, HttpServletRequest request, int voteId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhVote vote = klhVoteService.loadById(voteId);
		if(vote!=null){
			model.addAttribute("vote", vote);
			
			List<KlhVoteOption> voteOptionList = klhVoteOptionService.queryAll();
			model.addAttribute("voteOptionList", voteOptionList);
		}
		return "klh/voteEdit";
	}
	
	/**
	 * 保存单个vote信息
	 * @param model
	 * @param klhVote
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveVote", method = RequestMethod.POST)
	public String saveVotePropValue(Model model, KlhVote klhVote, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(klhVote!=null&&klhVote.getId()!=null&&klhVote.getId()>0){
			klhVote.setUpdateTime(currentTime);
			result = klhVoteService.updateById(klhVote);
		}else{
			klhVote.setCreateTime(currentTime);
			result = klhVoteService.save(klhVote);
		}
		
		model.addAttribute("redirectUrl", "./voteList");
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 删除vote
	 * @param model
	 * @param voteId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delVote")
	public String delVoteOption(Model model, int voteId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//删除投票
		int result = klhVoteService.deleteById(voteId);
		
		model.addAttribute("redirectUrl", "./voteList");
		return "forward:/home/operationRedirect"; 
	}
	
	
	
	
	
	////////////////////////////操作投票选项
	
	/**
	 * 
	 * @param model
	 * @param voteId
	 * @param voteOptionId
	 * @param request
	 * @return
	 */
	@RequestMapping("/voteOptionAdd")
	public String voteOptionAdd(Model model, int voteId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhVote vote = klhVoteService.loadById(voteId);
		if(vote!=null){
			model.addAttribute("vote", vote);
			
			KlhVoteOption voteOption = new KlhVoteOption();
			voteOption.setVoteId(voteId);
			model.addAttribute("voteOption", voteOption);
		}
		return "klh/voteOptionEdit";
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param voteOptionId
	 * @return
	 */
	@RequestMapping("/voteOptionEdit")
	public String voteOptionEdit(Model model, HttpServletRequest request, int voteOptionId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KlhVoteOption voteOption = klhVoteOptionService.loadById(voteOptionId);
		if(voteOption!=null){
			model.addAttribute("voteOption", voteOption);
			KlhVote vote = klhVoteService.loadById(voteOption.getVoteId());
			if(vote!=null){
				model.addAttribute("vote", vote);
			}
		}
		
		return "klh/voteOptionEdit";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param voteOption
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveVoteOption", method = RequestMethod.POST)
	public String saveVoteOption(Model model, KlhVoteOption voteOption, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		voteOption.setUpdateTime(currentTime);
		if(voteOption!=null&&voteOption.getId()!=null&&voteOption.getId()>0){
			result = klhVoteOptionService.updateById(voteOption);
		}else{
			voteOption.setCreateTime(currentTime);
			result = klhVoteOptionService.save(voteOption);
		}
		
		model.addAttribute("redirectUrl", "./voteEdit?voteId="+voteOption.getVoteId());
		return "forward:/home/operationRedirect";
		
	}

	
	
	
	/**
	 * 
	 * @param model
	 * @param voteId
	 * @param voteOptionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delVoteOption")
	public String delVoteOption(Model model, int voteId, int voteOptionId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = klhVoteOptionService.deleteById(voteOptionId);
		
		model.addAttribute("redirectUrl", "./voteEdit?voteId="+voteId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 查看投票结果
	 * @param model
	 * @param voteId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/voteResult")
	public String voteResult(Model model, int voteId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhVote vote = klhVoteService.loadById(voteId);
		if(vote!=null){
			List<KlhVoteOption> voteOptionList = klhVoteOptionService.queryAll();
			List<KlhVoteResult> voteResultList = klhVoteResultService.queryAll();
			
			model.addAttribute("vote", vote);
			model.addAttribute("voteOptionList", voteOptionList);
			model.addAttribute("voteResultList", voteResultList);
		}
		
		return "klh/voteResult";
	}
	
}
