package com.bruce.geekway.admin.controller.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.ProductCategory;
import com.bruce.geekway.model.ProductCategoryCriteria;
import com.bruce.geekway.model.SlideImage;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.product.IProductCategoryService;
import com.bruce.geekway.service.product.ISlideImageService;



@Controller
@RequestMapping("/product")
public class ProductCategoryController {
	

	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	

	@Autowired
	private IProductCategoryService productCategoryService;
	@Autowired
	private ISlideImageService slideImageService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/productCategoryPaging")
	public String productCategoryPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		ProductCategoryCriteria criteria = new ProductCategoryCriteria();
		criteria.setOrderByClause(" id desc");
		ProductCategoryCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<ProductCategory> productCategoryPagingData = productCategoryService.pagingByCriteria(pageNo, pageSize , criteria);
		if(productCategoryPagingData!=null){
			productCategoryPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			productCategoryPagingData.setQueryMap(queryMap);
			model.addAttribute("productCategoryPagingData", productCategoryPagingData);
		}
		return "product/categoryListPaging";
	}
	
	@RequestMapping("/categoryAdd")
	public String categoryAdd(Model model, ProductCategory category, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("category", category);
		return "product/categoryEdit";
	}
	
	@RequestMapping("/categoryEdit")
	public String categoryEdit(Model model, HttpServletRequest request, int categoryId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductCategory ProductCategory = productCategoryService.loadById(categoryId);
		model.addAttribute("category", ProductCategory);
		
		List<SlideImage> slideImageList = slideImageService.queryList(GeekwayEnum.SlideImageTypeEnum.CATEGORY.getValue(), categoryId);//.queryByCategoryId(categoryId);
		model.addAttribute("slideImageList", slideImageList);
		
		return "product/categoryEdit";
	}
	
	@RequestMapping(value = "/saveProductCategory", method = RequestMethod.POST)
	public String saveProductCategory(Model model, ProductCategory category, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		category.setUpdateTime(currentTime);
		if(category!=null&&category.getId()!=null&&category.getId()>0){
			result = productCategoryService.updateById(category);
		}else{
			category.setCreateTime(currentTime);
			result = productCategoryService.save(category);
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
		boolean categoryUsed = true;//默认为使用中，不能被删除
		if(categoryUsed){//被使用的情况下，删除会导致数据异常
			model.addAttribute("message", "该分类已经被产品关联，无法删除");
			return "forward:/home/operationResult"; 
		}else{//未被使用，可以删除
			int result = productCategoryService.deleteById(categoryId);
			
			model.addAttribute("redirectUrl", "./productCategoryPaging");
			return "forward:/home/operationRedirect"; 
		}
	}
	
	
	/**
	 * 分类轮播列表
	 */
	@RequestMapping("/categorySlideImageList")
	public String categorySlideImageList(Model model, int categoryId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductCategory ProductCategory = productCategoryService.loadById(categoryId);
		model.addAttribute("category", ProductCategory);
		
		List<SlideImage> slideImageList = slideImageService.queryList(GeekwayEnum.SlideImageTypeEnum.CATEGORY.getValue(), categoryId);
		model.addAttribute("slideImageList", slideImageList);
		return "product/categorySlideImageList";
	}
	
	/**
	 * 编辑分类轮播
	 */
	@RequestMapping("/categorySlideImageEdit")
	public String categorySlideImageEdit(Model model, int categoryId, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductCategory ProductCategory = productCategoryService.loadById(categoryId);
		model.addAttribute("category", ProductCategory);
		
		SlideImage slideImage = slideImageService.loadById(id);
		model.addAttribute("slideImage", slideImage);
		return "product/categorySlideImageEdit";
	}
	
	/**
	 * 保存分类轮播
	 */
	@RequestMapping("/saveCategorySlideImage")
	public String saveCategorySlideImage(Model model, SlideImage slideImage, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
		int result = 0;
		Date currentTime = new Date();
		if(slideImage!=null&&slideImage.getId()!=null&&slideImage.getId()>0){
			slideImage.setUpdateTime(currentTime);
			result = slideImageService.updateById(slideImage);
		}else{
			slideImage.setCreateTime(currentTime);
			result = slideImageService.save(slideImage);
		}
		model.addAttribute("redirectUrl", "./categorySlideImageList?categoryId="+slideImage.getRootId());
		return "forward:/home/operationRedirect";
	}
}
