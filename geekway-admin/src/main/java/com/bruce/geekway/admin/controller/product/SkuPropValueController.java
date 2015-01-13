package com.bruce.geekway.admin.controller.product;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.SkuPropValue;
import com.bruce.geekway.service.product.ISkuPropValueService;



@Controller
@RequestMapping("/product")
public class SkuPropValueController {

	@Autowired
	private ISkuPropValueService skuPropValueService;
//	@Autowired
//	private IWxProductSkuRelationService wxProductSkuRelationService;
	
	
	@RequestMapping("/skuPropValueList")
	public String skuPropValueList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<SkuPropValue> skuPropValueList = skuPropValueService.querySortedSkuPropValues();
		model.addAttribute("skuPropValueList", skuPropValueList);
		return "product/skuPropValueList";
	}
	
	@RequestMapping("/skuPropValueAdd")
	public String skuPropValueAdd(Model model, SkuPropValue skuPropValue, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("skuPropValue", skuPropValue);
		return "product/skuPropValueEdit";
	}
	
	@RequestMapping("/skuPropValueEdit")
	public String skuPropValueEdit(Model model, HttpServletRequest request, int skuPropValueId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		SkuPropValue SkuPropValue = skuPropValueService.loadById(skuPropValueId);
		model.addAttribute("skuPropValue", SkuPropValue);
		return "product/skuPropValueEdit";
	}
	
	@RequestMapping(value = "/saveSkuPropValue", method = RequestMethod.POST)
	public String saveSkuPropValue(Model model, SkuPropValue skuPropValue, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		skuPropValue.setUpdateTime(currentTime);
		if(skuPropValue!=null&&skuPropValue.getId()!=null&&skuPropValue.getId()>0){
			result = skuPropValueService.updateById(skuPropValue);
		}else{
			skuPropValue.setCreateTime(currentTime);
			result = skuPropValueService.save(skuPropValue);
		}
		
		model.addAttribute("redirectUrl", "./skuPropValueList");
		return "forward:/home/operationRedirect";
	}
	
	
//	/**
//	 * 删除skuPropValue
//	 * @param model
//	 * @param sliderId
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/delSkuPropValue")
//	public String delSkuImage(Model model, int skuPropValueId, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		//先检查该skuPropValue是否被使用
//		boolean propValueUsed = true;//默认为使用中，不能被删除
//		int usedCount = wxProductSkuRelationService.queryCountBySkuPropValueId(skuPropValueId);
//		if(usedCount<=0){
//			propValueUsed = false;//未使用，则可以删除
//		}
//		if(propValueUsed){//被使用的情况下，删除会导致数据异常
//			model.addAttribute("message", "该Sku属性已经被产品关联，无法删除");
//			return "forward:/home/operationResult"; 
//		}else{//未被使用，可以删除
//			int result = wxSkuPropValueService.deleteById(skuPropValueId);
//			
//			model.addAttribute("redirectUrl", "./skuPropValueList");
//			return "forward:/home/operationRedirect"; 
//		}
//		
//	}
}
