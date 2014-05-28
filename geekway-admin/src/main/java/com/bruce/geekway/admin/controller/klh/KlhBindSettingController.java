package com.bruce.geekway.admin.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhBindSetting;
import com.bruce.geekway.service.klh.IKlhBindSettingService;

/**
 * 某个bindSetting下的bindSetting操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhBindSettingController {

	@Autowired
	private IKlhBindSettingService klhBindSettingService;
	
	/**
	 * 编辑BindSetting信息
	 * @param model
	 * @param request
	 * @param bindSettingId
	 * @param bindSettingId
	 * @return
	 */
	@RequestMapping("/bindSetting")
	public String bindSettingEdit(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhBindSetting bindSetting = klhBindSettingService.loadById(1);
		if(bindSetting!=null){
			model.addAttribute("bindSetting", bindSetting);
		}
		return "klh/bindSettingEdit";
	}
	
	/**
	 * 保存单个bindSetting信息
	 * @param model
	 * @param klhBindSetting
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveBindSetting", method = RequestMethod.POST)
	public String saveBindSettingPropValue(Model model, KlhBindSetting klhBindSetting, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(klhBindSetting!=null&&klhBindSetting.getId()!=null&&klhBindSetting.getId()>0){
			klhBindSetting.setUpdateTime(currentTime);
			result = klhBindSettingService.updateById(klhBindSetting);
		}
		
		model.addAttribute("redirectUrl", "./bindSettingEdit");
		return "forward:/home/operationRedirect";
	}
	
	
	
}
