package com.bruce.geekway.admin.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.service.klh.IKlhSettingService;

/**
 * 某个setting下的setting操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhSettingController {

	@Autowired
	private IKlhSettingService klhSettingService;
	
	/**
	 * 编辑Setting信息
	 * @param model
	 * @param request
	 * @param settingId
	 * @param settingId
	 * @return
	 */
	@RequestMapping("/setting")
	public String settingEdit(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhSetting setting = klhSettingService.loadById(1);
		if(setting!=null){
			model.addAttribute("setting", setting);
		}
		return "klh/settingEdit";
	}
	
	/**
	 * 保存单个setting信息
	 * @param model
	 * @param setting
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSetting", method = RequestMethod.POST)
	public String saveSettingPropValue(Model model, KlhSetting setting, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(setting!=null&&setting.getId()!=null&&setting.getId()>0){
			setting.setUpdateTime(currentTime);
			result = klhSettingService.updateById(setting);
		}
		
		model.addAttribute("redirectUrl", "./setting");
		return "forward:/home/operationRedirect";
	}
	
}
