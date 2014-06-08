package com.bruce.geekway.controller.ito;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoSliderService;
import com.bruce.geekway.service.ito.IItoSystemStatusService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;


/**
 * 系统状态监察
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"api"})
public class ItoController {
	
	@Autowired
	private IItoSystemStatusService itoSystemStatusService;
	
	/**
	 * 滑屏列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/running.json")
	public ModelAndView running() {
		
		ItoSystemStatus systemStatus=  new ItoSystemStatus();
		Date currentTime = new Date();
		systemStatus.setCreateTime(currentTime);
		
		int result =  itoSystemStatusService.save(systemStatus);
		if(result>0){
			return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
}
