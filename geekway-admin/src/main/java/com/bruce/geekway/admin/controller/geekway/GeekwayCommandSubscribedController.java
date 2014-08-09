package com.bruce.geekway.admin.controller.geekway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandCriteria;
import com.bruce.geekway.service.IWxCommandMaterialService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;

/**
 * 专为订阅操作配置的command
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayCommandSubscribedController {

	@Autowired
	private IWxCommandService wxCommandService;
	@Autowired
	private IWxCommandMaterialService wxCommandMaterialService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
//	@Autowired
//	private IWxMaterialNewsService wxMaterialNewsService;
	
	
	@RequestMapping("/subscribedCommandList")
	public String commandList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCommand> commandList = new ArrayList<WxCommand>();
		WxCommand newSubscribedCommand = wxCommandService.loadNewSubscribedCommand();
		WxCommand reSubscribedCommand = wxCommandService.loadReSubscribedCommand();
		if(newSubscribedCommand!=null){
			commandList.add(newSubscribedCommand);
			model.addAttribute("newSubscribedCommand", newSubscribedCommand);
		}
		if(reSubscribedCommand!=null){
			commandList.add(reSubscribedCommand);
			model.addAttribute("reSubscribedCommand", reSubscribedCommand);
		}
		
		model.addAttribute("commandList", commandList);
		return "geekway/subscribedCommandList";
	}
	
	/**
	 * 创建新订阅的指令
	 * @param model
	 * @param command
	 * @param request
	 * @return
	 */
	@RequestMapping("/newsubscribeCommandAdd")
	public String subscribedCommandAdd(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		command.setCommandType((short) 2);
		model.addAttribute("command", command);
		
		return "geekway/subscribeCommandEdit";
	}
	
	/**
	 * 创建重复关注的指令
	 * @param model
	 * @param command
	 * @param request
	 * @return
	 */
	@RequestMapping("/resubscribeCommandAdd")
	public String menuCommandAdd(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		command.setCommandType((short) 3);
		model.addAttribute("command", command);
		
		return "geekway/subscribeCommandEdit";
	}
	
	@RequestMapping("/subscribeCommandEdit")
	public String commandEdit(Model model, HttpServletRequest request, int commandId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxCommand command = wxCommandService.loadById(commandId);
		
		if(command!=null){
			model.addAttribute("command", command);
			//TODO 查询command对应的素材列表
//			List<WxMaterialArticle> materialList = wxMaterialArticleService.queryMaterialArticlesByCommandId(commandId);
//			model.addAttribute("materialList", materialList);
		}
		return "geekway/subscribeCommandEdit";
	}
	
	@RequestMapping(value = "/saveSubscribeCommand", method = RequestMethod.POST)
	public String saveCommand(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		command.setUpdateTime(currentTime);
		
		if(command!=null&&command.getId()!=null&&command.getId()>0){
			result = wxCommandService.updateById(command);
		}else{
			command.setCreateTime(currentTime);
			result = wxCommandService.save(command);
		}
		model.addAttribute("redirectUrl", "./subscribedCommandList");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/delSubscribedCommand")
	public String delCommand(Model model,  int commandId) {
		//删除command实体
		wxCommandService.deleteById(commandId);
		
		model.addAttribute("redirectUrl", "./subscribedCommandList");
		return "forward:/home/operationRedirect";
	}
}
