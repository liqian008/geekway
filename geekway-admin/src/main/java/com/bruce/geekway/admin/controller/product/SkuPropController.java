package com.bruce.geekway.admin.controller.product;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.SkuProp;
import com.bruce.geekway.service.product.IProductCategoryService;
import com.bruce.geekway.service.product.ISkuPropService;


@Controller
@RequestMapping("/product")
public class SkuPropController {

	@Autowired
	private ISkuPropService skuPropService;
	@Autowired
	private IProductCategoryService productCategoryService;

	
	@RequestMapping("/skuPropList")
	public String skuPropList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<SkuProp> skuPropList = skuPropService.queryAll();
		model.addAttribute("skuPropList", skuPropList);
		return "product/skuPropList";
	}
	
//	/**
//	 * 添加产品的预操作（需要先选择产品分类）
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/skuPropPreAdd")
//	public String skuPropPreAdd(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		//查询出所有分类，以供用户选择产品所属分类
//		List<WxProductCategory> categoryList = wxProductCategoryService.queryAll();
//		
//		model.addAttribute("skuProp", new WxSkuProp());
//		model.addAttribute("categoryList", categoryList);
//		return "product/skuPropPreAdd";
//	}
	
	@RequestMapping("/skuPropAdd")
	public String skuPropAdd(Model model,  SkuProp skuProp, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		if(skuProp!=null){
			skuProp.setSkuCategoryId(1);
		}
		
		
		model.addAttribute("skuProp", skuProp);
		return "product/skuPropEdit";
	}
	
	@RequestMapping("/skuPropEdit")
	public String skuPropEdit(Model model, HttpServletRequest request, int skuPropId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		SkuProp SkuProp = skuPropService.loadById(skuPropId);
		model.addAttribute("skuProp", SkuProp);
		return "product/skuPropEdit";
	}
	
	@RequestMapping(value = "/saveSkuProp", method = RequestMethod.POST)
	public String saveSkuProp(Model model, SkuProp skuProp, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		skuProp.setUpdateTime(currentTime);
		if(skuProp!=null&&skuProp.getId()!=null&&skuProp.getId()>0){
			result = skuPropService.updateById(skuProp);
		}else{
			skuProp.setCreateTime(currentTime);
			result = skuPropService.save(skuProp);
		}
		
		model.addAttribute("redirectUrl", "./skuPropList");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 删除skuProp
	 * @param model
	 * @param sliderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delSkuProp")
	public String delSkuImage(Model model, int skuPropId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//先检查该skuProp是否被使用
		boolean propValueUsed = true;//默认为使用中，不能被删除
		if(propValueUsed){//被使用的情况下，删除会导致数据异常
			model.addAttribute("message", "该Sku属性已经被产品关联，无法删除");
			return "forward:/home/operationResult"; 
		}else{//未被使用，可以删除
			int result = skuPropService.deleteById(skuPropId);
			
			model.addAttribute("redirectUrl", "./skuPropList");
			return "forward:/home/operationRedirect"; 
		}
	}
}
