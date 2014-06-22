package com.bruce.geekway.controller.ito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoSliderService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"api"})
public class SliderController {
	
	private static final Logger logger = LoggerFactory.getLogger("ItoAppLogger");
	
	@Autowired
	private IItoSliderService itoSliderService;
	
	/**
	 * 滑屏列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sliderList.json")
	public ModelAndView sliderList() {
		
		logger.info("App客户端获取轮播图片列表");
		
		List<ItoSlider> sliderList =  itoSliderService.queryAll();
		if(sliderList!=null&&sliderList.size()>0){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("sliderList", sliderList);
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
}
