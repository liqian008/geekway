package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxCodeModule;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.service.IWxCodeModuleService;
import com.bruce.geekway.service.IWxEventCodeService;

@Controller
@RequestMapping("/geekway") 
public class GeekwayEventCodeController {

	@Autowired
	private IWxEventCodeService wxEventCodeService;
	@Autowired
	private IWxCodeModuleService wxCodeModuleService;
	
	@RequestMapping("/eventCodeList")
	public String eventCodeList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxEventCode> eventCodeList = wxEventCodeService.queryAll();
		model.addAttribute("eventCodeList", eventCodeList);
		return "eventCode/eventCodeList";
	}
	
	@RequestMapping("/eventCodeAdd")
	public String eventCodeAdd(Model model, WxEventCode eventCode, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("eventCode", eventCode);
		
		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
		model.addAttribute("codeModuleList", codeModuleList);
		
		return "eventCode/eventCodeEdit";
	}
	
	@RequestMapping("/eventCodeEdit")
	public String eventCodeEdit(Model model, HttpServletRequest request, int eventCodeId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxEventCode eventCode = wxEventCodeService.loadById(eventCodeId);
		model.addAttribute("eventCode", eventCode);
		
		List<WxCodeModule> codeModuleList = wxCodeModuleService.queryAll();
		model.addAttribute("codeModuleList", codeModuleList);
		
		return "eventCode/eventCodeEdit";
	}
	
	@RequestMapping(value = "/saveEventCode", method = RequestMethod.POST)
	public String saveEventCode(Model model, WxEventCode eventCode, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		eventCode.setUpdateTime(currentTime);
		
		if(3==eventCode.getEventType()){//用户新关注的类型需特别处理
			eventCode.setEventCode("subscribe");
		}
		
		
		if(1==eventCode.getDisplayType()){
			//文本回复情况下，重新初始化数据
			eventCode.setModuleDesc("");
			eventCode.setModuleId(0);
			eventCode.setRowLimit((short) 0);
		}else if(2==eventCode.getDisplayType()){
			eventCode.setReplyContent("");
			
			int codeModuleId = eventCode.getModuleId();
			WxCodeModule codeModule = wxCodeModuleService.loadById(codeModuleId);
			if(codeModule!=null){
				eventCode.setModuleDesc(codeModule.getModuleName());
				
				if(eventCode.getRowLimit()!=null&&eventCode.getRowLimit()<=0){
					eventCode.setRowLimit((short) 4);
				}
			}
		}
		
		if(eventCode!=null&&eventCode.getId()!=null&&eventCode.getId()>0){
			result = wxEventCodeService.updateById(eventCode);
		}else{
			eventCode.setCreateTime(currentTime);
			result = wxEventCodeService.save(eventCode);
		}
		
		model.addAttribute("redirectUrl", "./eventCodeList");
		return "forward:/home/operationRedirect";
	}
	
}
