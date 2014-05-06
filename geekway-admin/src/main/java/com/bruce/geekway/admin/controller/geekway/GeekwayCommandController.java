package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.WxCodeModule;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.service.IWxCodeModuleService;
import com.bruce.geekway.service.IWxCommandService;

@Controller
@RequestMapping("/geekway") 
public class GeekwayCommandController {

	@Autowired
	private IWxCommandService wxCommandService;
	@Autowired
	private IWxCodeModuleService wxCodeModuleService;
	
	@RequestMapping("/commandList")
	public String commandList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxCommand> commandList = wxCommandService.queryAll();
		model.addAttribute("commandList", commandList);
		return "geekway/commandList";
	}
	
	@RequestMapping("/commandAdd")
	public String commandAdd(Model model, WxCommand command,  @RequestParam(value="type", required=false, defaultValue="1") short commandType, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("command", command);
		model.addAttribute("commandType", commandType);
		
//		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
//		model.addAttribute("codeModuleList", codeModuleList);
		
		return "geekway/commandEdit";
	}
	
	@RequestMapping("/commandEdit")
	public String commandEdit(Model model, HttpServletRequest request, int commandId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxCommand command = wxCommandService.loadById(commandId);
		model.addAttribute("command", command);
		
//		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
//		model.addAttribute("codeModuleList", codeModuleList);
		
		return "geekway/commandEdit";
	}
	
	@RequestMapping(value = "/saveCommand", method = RequestMethod.POST)
	public String saveCommand(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		command.setUpdateTime(currentTime);
		
		if(3==command.getCommandType()){//用户新关注的类型需特别处理
			command.setCommand("subscribe");
		}
		
		if(command!=null&&command.getId()!=null&&command.getId()>0){
			result = wxCommandService.updateById(command);
		}else{
			command.setCreateTime(currentTime);
			result = wxCommandService.save(command);
		}
		
		model.addAttribute("redirectUrl", "./commandList");
		return "forward:/home/operationRedirect";
	}
	
}
