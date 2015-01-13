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
import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductCriteria;
import com.bruce.geekway.model.ProductTag;
import com.bruce.geekway.model.ProductTagCriteria;
import com.bruce.geekway.model.ProductTagRelation;
import com.bruce.geekway.model.SlideImage;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductTagRelationService;
import com.bruce.geekway.service.product.IProductTagService;
import com.bruce.geekway.service.product.ISkuPropService;
import com.bruce.geekway.service.product.ISkuPropValueService;
import com.bruce.geekway.service.product.ISlideImageService;



@Controller
@RequestMapping("/product")
public class ProductTagController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductTagService productTagService;
	@Autowired
	private ISlideImageService slideImageService;
	@Autowired
	private IProductTagRelationService productTagRelationService;
	@Autowired
	private ISkuPropService skuPropService;
	@Autowired
	private ISkuPropValueService skuPropValueService;
	

	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/productTagPaging")
	public String productTagPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		ProductTagCriteria criteria = new ProductTagCriteria();
		criteria.setOrderByClause(" id desc");
		ProductTagCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<ProductTag> productTagPagingData = productTagService.pagingByCriteria(pageNo, pageSize , criteria);
		if(productTagPagingData!=null){
			productTagPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			productTagPagingData.setQueryMap(queryMap);
			model.addAttribute("productTagPagingData", productTagPagingData);
		}
		return "product/tagListPaging";
	}
	
	
	/**
	 * 添加产品Tag
	 * @param model
	 * @param productTag
	 * @param request
	 * @return
	 */
	@RequestMapping("/productTagAdd")
	public String productTagAdd(Model model, ProductTag productTag, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("productTag", productTag);
		return "product/tagEdit";
	}
	
	@RequestMapping("/productTagEdit")
	public String productTagEdit(Model model, HttpServletRequest request, int productTagId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductTag productTag = productTagService.loadById(productTagId);
		model.addAttribute("productTag", productTag);
		
		List<SlideImage> slideImageList = slideImageService.queryByTagId(productTagId);
		model.addAttribute("slideImageList", slideImageList);
		
		return "product/tagEdit";
	}
	
	/**
	 * 保存
	 * @param model
	 * @param productTag
	 * @param productTagSkuValueIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveProductTag", method = RequestMethod.POST)
	public String saveProductTag(Model model, ProductTag productTag, Integer[] skuPropValueIds, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		productTag.setUpdateTime(currentTime);
		if(productTag!=null&&productTag.getId()!=null&&productTag.getId()>0){
			result = productTagService.updateById(productTag);
		}else{//新增
			productTag.setCreateTime(currentTime);
			productTag.setStatus((short) 0);
			result = productTagService.save(productTag);
		}
		model.addAttribute("redirectUrl", "./productTagPaging");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 列出当前tag对应的商品列表
	 * @param model
	 * @param tagId
	 * @param request
	 * @return
	 */
	@RequestMapping("/tagProductSet")
	public String tagProductSet(Model model, int tagId, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		ProductTag productTag = productTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);
		
//		全量读取已关联的productList，将被下面分页替代
//		List<WxProduct> mappedProductList = wxProductService.queryProductsByTagId(tagId);
//		model.addAttribute("mappedProductList", mappedProductList);
		
		//分页方式查询已关联的产品列表
		ProductCriteria criteria = new ProductCriteria();
		criteria.setOrderByClause(" id desc");
		ProductCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<Product> productPagingData = productService.pagingTagProductsByCriteria(tagId, pageNo, pageSize, criteria);
		if(productPagingData!=null){
			productPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			productPagingData.setQueryMap(queryMap);
			model.addAttribute("productPagingData", productPagingData);
		}
		return "product/tagProductSet";
	}
	
	/**
	 * 
	 * @param model
	 * @param tagId
	 * @param request
	 * @return
	 */
	@RequestMapping("/tagProductSetAdd")
	public String tagProductAdd(Model model,int tagId, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		ProductTag productTag = productTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);

//		全量读取未关联的productList，将被下面分页替代
//		List<WxProduct> unmappedProductList = wxProductService.queryProductsOutTagId(tagId);
//		model.addAttribute("unmappedProductList", unmappedProductList);
		
		//分页方式查询已关联的产品列表
		ProductCriteria criteria = new ProductCriteria();
		criteria.setOrderByClause(" id desc");
		ProductCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<Product> productPagingData = productService.pagingTagOutProductsByCriteria(tagId, pageNo, pageSize, criteria);
		if(productPagingData!=null){
			productPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			productPagingData.setQueryMap(queryMap);
			model.addAttribute("productPagingData", productPagingData);
		}
		
		
//		return "material/tagProductSetAdd";
		return "product/tagProductSetAdd";
	}
	
	@RequestMapping("/topProduct")
	public String topProduct(Model model, int tagId, int productId) {
		//置顶操作
		productTagRelationService.topProduct(tagId, productId);

		model.addAttribute("redirectUrl", "./tagProductSet?tagId="+tagId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 
	 * @param model
	 * @param tagId
	 * @param request
	 * @return
	 */
	@RequestMapping("/addProductTag")
	public String addProductTag(Model model,int tagId, int productId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductTagRelation obj = new ProductTagRelation();
		obj.setProductTagId(tagId);
		obj.setProductId(productId);
		obj.setTopTime(new Date());
		productTagRelationService.save(obj);
		
		model.addAttribute("redirectUrl", "./tagProductSet?tagId="+tagId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 
	 * @param model
	 * @param tagId
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeProductTag")
	public String removeProductTag(Model model, int tagId, int productId,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = productTagRelationService.delete(tagId, productId); 
		
		model.addAttribute("redirectUrl", "./tagProductSet?tagId="+tagId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * tag轮播列表
	 */
	@RequestMapping("/tagSlideImageList")
	public String tagSlideImageList(Model model, int tagId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<SlideImage> slideImageList = slideImageService.queryByTagId(tagId);
		model.addAttribute("slideImageList", slideImageList);
		return "product/tagSlideImageList";
	}
	
	/**
	 * 编辑tag轮播
	 */
	@RequestMapping("/tagSlideImageEdit")
	public String tagSlideImageEdit(Model model, int tagId, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		SlideImage slideImage = slideImageService.loadById(id);
		model.addAttribute("slideImage", slideImage);
		return "product/tagSlideImageEdit";
	}
	
	/**
	 * 保存tag轮播
	 */
	@RequestMapping("/saveCategorySlideImage")
	public String tagSlideImageEdit(Model model, SlideImage slideImage, HttpServletRequest request) {
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
		model.addAttribute("redirectUrl", "./tagSlideImageList?tagId="+slideImage.getRootId());
		return "forward:/home/operationRedirect";
	}
}
