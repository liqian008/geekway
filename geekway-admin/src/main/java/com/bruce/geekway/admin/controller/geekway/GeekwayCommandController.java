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

import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxCommandMaterialService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialService;

@Controller
@RequestMapping("/geekway") 
public class GeekwayCommandController {

	@Autowired
	private IWxCommandService wxCommandService;
//	@Autowired
//	private IWxCommandService wxCommandService;
	@Autowired
	private IWxMaterialService wxMaterialService;
	@Autowired
	private IWxCommandMaterialService wxCommandMaterialService;
	
	
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
		
//		List<WxCommand> commandList = wxCommandService.queryAll();
//		model.addAttribute("commandList", commandList);
		
		return "geekway/commandEdit";
	}
	
	
	@RequestMapping("/commandAddTextEntry")
	public String commandAddTextEntry(Model model,  WxCommand command,  HttpServletRequest request) {
		return commandAdd(model, command, (short) 1, request);
	}
	
	@RequestMapping("/commandAddMenuEntry")
	public String commandAddMenuEntry(Model model,  WxCommand command,  HttpServletRequest request) {
		return commandAdd(model, command, (short) 2, request);
	}
	
	@RequestMapping("/commandAddSubscribeEntry")
	public String commandAddSubscribeEntry(Model model,  WxCommand command,  HttpServletRequest request) {
		return commandAdd(model, command, (short) 1, request);
	}
	
	
	@RequestMapping("/commandEdit")
	public String commandEdit(Model model, HttpServletRequest request, int commandId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxCommand command = wxCommandService.loadById(commandId);
		model.addAttribute("command", command);
		
//		List<WxCommand> commandList = wxCommandService.queryAll();
//		model.addAttribute("commandList", commandList);
		
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
	
	@RequestMapping("/delCommand")
	public String delCommand(Model model,  int commandId) {
		//删除资源的关联
		wxCommandMaterialService.deleteByCommandId(commandId);
		
		//删除command实体
		wxCommandService.deleteById(commandId);
		
		model.addAttribute("redirectUrl", "./commandList");
		return "forward:/home/operationRedirect";
	}
	
	
	
	/**
	 * 列出当前module对应的素材列表
	 * @param model
	 * @param commandId
	 * @param request
	 * @return
	 */
	@RequestMapping("/commandMaterialSet")
	public String commandMaterialSet(Model model,int commandId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxCommand command = wxCommandService.loadById(commandId);
		model.addAttribute("command", command);
		
		List<WxMaterial> mappedMaterialList = wxMaterialService.queryMaterialsByCommandId(commandId);
		model.addAttribute("mappedMaterialList", mappedMaterialList);
		
		return "geekway/commandMaterialSet";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param commandId
	 * @param request
	 * @return
	 */
	@RequestMapping("/commandMaterialSetAdd")
	public String commandMaterialAdd(Model model,int commandId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxCommand command = wxCommandService.loadById(commandId);
		model.addAttribute("command", command);
		
		List<WxMaterial> unmappedMaterialList = wxMaterialService.queryMaterialsOutCommandId(commandId);
		model.addAttribute("unmappedMaterialList", unmappedMaterialList);
		
		return "geekway/commandMaterialSetAdd";
	}
	
	/**
	 * 
	 * @param model
	 * @param commandId
	 * @param request
	 * @return
	 */
	@RequestMapping("/addCommandMaterial")
	public String addCommandMaterial(Model model,int commandId, int materialId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxCommandMaterial obj = new WxCommandMaterial();
		obj.setCommandId(commandId);
		obj.setMaterialId(materialId);
		wxCommandMaterialService.save(obj);
		
		model.addAttribute("redirectUrl", "./commandMaterialSet?commandId="+commandId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 
	 * @param model
	 * @param commandId
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeCommandMaterial")
	public String removeCommandMaterial(Model model, int commandId, int materialId,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		wxCommandMaterialService.delete(commandId, materialId);
		
		model.addAttribute("redirectUrl", "./commandMaterialSet?commandId="+commandId);
		return "forward:/home/operationRedirect";
	}
	
	
	
	
}
