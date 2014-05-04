package com.bruce.geekway.admin.controller.ito;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.service.ito.IItoSkuService;


@Controller
@RequestMapping("/ito")
public class ItoSkuController {

	@Autowired
	private IItoSkuService itoSkuService;
	
	
	@RequestMapping("/skuList")
	public String skuList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoSku> skuList = itoSkuService.queryAll();
		model.addAttribute("skuList", skuList);
		return "ito/skuList";
	}
	
	
}
