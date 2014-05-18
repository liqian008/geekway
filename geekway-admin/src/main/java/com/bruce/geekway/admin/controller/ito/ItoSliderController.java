package com.bruce.geekway.admin.controller.ito;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.service.ito.IItoSliderService;

/**
 * 某个slider下的slider操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/ito")
public class ItoSliderController {

	@Autowired
	private IItoSliderService itoSliderService;
	
	@RequestMapping("/sliderList")
	public String sliderList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoSlider> sliderList = itoSliderService.queryAll();
		model.addAttribute("sliderList", sliderList);
		return "ito/sliderList";
	}
	
	
	@RequestMapping("/sliderAdd")
	public String sliderAdd(Model model, ItoSlider slider, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("slider", slider);
		return "ito/sliderEdit";
	}
	
	
	/**
	 * 编辑Slider信息
	 * @param model
	 * @param request
	 * @param sliderId
	 * @param sliderId
	 * @return
	 */
	@RequestMapping("/sliderEdit")
	public String sliderEdit(Model model, HttpServletRequest request, int sliderId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoSlider slider = itoSliderService.loadById(sliderId);
		if(slider!=null){
			model.addAttribute("slider", slider);
		}
		
		return "ito/sliderEdit";
	}
	
	/**
	 * 保存单个slider信息
	 * @param model
	 * @param itoSlider
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSlider", method = RequestMethod.POST)
	public String saveSliderPropValue(Model model, ItoSlider itoSlider, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(itoSlider!=null&&itoSlider.getId()!=null&&itoSlider.getId()>0){
			itoSlider.setSliderThumbPicUrl(itoSlider.getSliderPicUrl()); 
			itoSlider.setUpdateTime(currentTime);
			result = itoSliderService.updateById(itoSlider);
		}else{
			itoSlider.setCreateTime(currentTime);
			itoSlider.setSliderThumbPicUrl(itoSlider.getSliderPicUrl()); 
			result = itoSliderService.save(itoSlider);
		}
		
		model.addAttribute("redirectUrl", "./sliderList");
		return "forward:/home/operationRedirect";
	}
	
	
	
}
