package com.bruce.geekway.admin.controller.ito;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.service.ito.IItoSkuPropService;


@Controller
@RequestMapping("/ito")
public class ItoSkuPropController {

	@Autowired
	private IItoSkuPropService itoSkuPropService;
	
	
	@RequestMapping("/skuPropList")
	public String skuPropList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoSkuProp> skuPropList = itoSkuPropService.queryAll();
		model.addAttribute("skuPropList", skuPropList);
		return "ito/skuPropList";
	}
	
	
}
