package com.bruce.geekway.admin.controller.klh;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhScoreSetting;
import com.bruce.geekway.service.klh.IKlhScoreSettingService;

/**
 * 某个scoreSetting下的scoreSetting操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhScoreSettingController {

	@Autowired
	private IKlhScoreSettingService klhScoreSettingService;
	
	/**
	 * 编辑ScoreSetting信息
	 * @param model
	 * @param request
	 * @param scoreSettingId
	 * @param scoreSettingId
	 * @return
	 */
	@RequestMapping("/scoreSetting")
	public String scoreSettingEdit(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhScoreSetting scoreSetting = klhScoreSettingService.loadById(1);
		if(scoreSetting!=null){
			model.addAttribute("scoreSetting", scoreSetting);
		}
		return "klh/scoreSettingEdit";
	}
	
	/**
	 * 保存单个scoreSetting信息
	 * @param model
	 * @param klhScoreSetting
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveScoreSetting", method = RequestMethod.POST)
	public String saveScoreSettingPropValue(Model model, KlhScoreSetting klhScoreSetting, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(klhScoreSetting!=null&&klhScoreSetting.getId()!=null&&klhScoreSetting.getId()>0){
			klhScoreSetting.setUpdateTime(currentTime);
			result = klhScoreSettingService.updateById(klhScoreSetting);
		}
		
		model.addAttribute("redirectUrl", "./scoreSettingEdit");
		return "forward:/home/operationRedirect";
	}
	
	
	
}
