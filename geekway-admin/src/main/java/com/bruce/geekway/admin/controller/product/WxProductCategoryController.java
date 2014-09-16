package com.bruce.geekway.admin.controller.product;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.service.product.IWxProductCategoryService;



@Controller
@RequestMapping("/product")
public class WxProductCategoryController {

	@Autowired
	private IWxProductCategoryService wxProductCategoryService;
	
	@RequestMapping("/categoryList")
	public String categoryList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxProductCategory> categoryList = wxProductCategoryService.queryAll();
		model.addAttribute("IWxProductCategory", categoryList);
		return "product/categoryList";
	}
	
	@RequestMapping("/categoryAdd")
	public String categoryAdd(Model model, WxProductCategory category, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("category", category);
		return "product/categoryEdit";
	}
	
	@RequestMapping("/categoryEdit")
	public String categoryEdit(Model model, HttpServletRequest request, int categoryId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductCategory ProductCategory = wxProductCategoryService.loadById(categoryId);
		model.addAttribute("category", ProductCategory);
		return "product/categoryEdit";
	}
	
	@RequestMapping(value = "/saveProductCategory", method = RequestMethod.POST)
	public String saveProductCategory(Model model, WxProductCategory category, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		category.setUpdateTime(currentTime);
		if(category!=null&&category.getId()!=null&&category.getId()>0){
			result = wxProductCategoryService.updateById(category);
		}else{
			category.setCreateTime(currentTime);
			result = wxProductCategoryService.save(category);
		}
		
		model.addAttribute("redirectUrl", "./categoryList");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 删除category
	 * @param model
	 * @param sliderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delProductCategory")
	public String delSkuImage(Model model, int categoryId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//先检查该category是否被使用
		boolean propValueUsed = true;//默认为使用中，不能被删除
//		int usedCount = wxProductSkuRelationService.queryCountByProductCategoryId(categoryId);
//		if(usedCount<=0){
//			propValueUsed = false;//未使用，则可以删除
//		}
		if(propValueUsed){//被使用的情况下，删除会导致数据异常
			model.addAttribute("message", "该Sku属性已经被产品关联，无法删除");
			return "forward:/home/operationResult"; 
		}else{//未被使用，可以删除
			int result = wxProductCategoryService.deleteById(categoryId);
			
			model.addAttribute("redirectUrl", "./categoryList");
			return "forward:/home/operationRedirect"; 
		}
	}
}
