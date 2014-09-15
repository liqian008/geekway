package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.utils.ConfigUtil;

/**
 * 普通指令controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayCommandController {

	@Autowired
	private IWxCommandService wxCommandService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
//	@Autowired
//	private IWxMaterialNewsService wxMaterialNewsService;
	
	/**
	 * 接入信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/wxConfig")
	public String config(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
		model.addAttribute("devmodeUrl", ConstWeixin.WX_DEV_MODE_URL);
		model.addAttribute("devmodeToken", ConstWeixin.WX_DEV_MODE_TOKEN);
		model.addAttribute("qrcodeUrl", ConstWeixin.WX_MP_ACCOUNT_QRCODE_URL);
		
		return "geekway/wxConfig";
	}
	
	@RequestMapping("/commandList")
	public String commandList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
		List<WxCommand> commandList = wxCommandService.queryGeneralCommandList();
		model.addAttribute("commandList", commandList);
		return "geekway/commandList";
	}
	
	/**
	 * 创建文本接入指令
	 * @param model
	 * @param command
	 * @param request
	 * @return
	 */
	@RequestMapping("/textCommandAdd")
	public String textCommandAdd(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		command.setCommandType((short) 0);
		model.addAttribute("command", command);
		
		return "geekway/commandEdit";
	}
	
	/**
	 * 创建菜单接入指令
	 * @param model
	 * @param command
	 * @param request
	 * @return
	 */
	@RequestMapping("/menuCommandAdd")
	public String menuCommandAdd(Model model, WxCommand command, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		command.setCommandType((short) 1);
		model.addAttribute("command", command);
		
		return "geekway/commandEdit";
	}
	
	@RequestMapping("/commandEdit")
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
		return "geekway/commandEdit";
	}
	
	@RequestMapping(value = "/saveCommand", method = RequestMethod.POST)
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
			
			String fullKey = command.getCommand();
			String[] keys = fullKey.split(",");
			if(keys!=null&&keys.length>0){
				for(String key: keys){
					command.setId(null);
					command.setCommand(key.trim());
					result = wxCommandService.save(command); 
				}
			}
			
		}
		model.addAttribute("redirectUrl", "./commandList");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/delCommand")
	public String delCommand(Model model,  int commandId) {
		//TODO 删除资源的关联
//		wxCommandMaterialService.deleteByCommandId(commandId);
		
		//删除command实体
		wxCommandService.deleteById(commandId);
		
		model.addAttribute("redirectUrl", "./commandList");
		return "forward:/home/operationRedirect";
	}
}
