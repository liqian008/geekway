package com.bruce.geekway.admin.controller.product;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxProductTagCriteria;
import com.bruce.geekway.model.WxProductTagRelation;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductTagRelationService;
import com.bruce.geekway.service.product.IWxProductTagService;
import com.bruce.geekway.service.product.IWxSkuPropService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;



@Controller
@RequestMapping("/product")
public class WxProductTagController {
	
	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxProductTagService wxProductTagService;
	@Autowired
	private IWxProductTagRelationService wxProductTagRelationService;
	@Autowired
	private IWxSkuPropService wxSkuPropService;
	@Autowired
	private IWxSkuPropValueService wxSkuPropValueService;
	

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
		
		WxProductTagCriteria criteria = new WxProductTagCriteria();
		criteria.setOrderByClause(" id desc");
		WxProductTagCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<WxProductTag> productTagPagingData = wxProductTagService.pagingByCriteria(pageNo, pageSize , criteria);
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
	public String productTagAdd(Model model, WxProductTag productTag, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("productTag", productTag);
		return "product/tagEdit";
	}
	
	@RequestMapping("/productTagEdit")
	public String productTagEdit(Model model, HttpServletRequest request, int productTagId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductTag productTag = wxProductTagService.loadById(productTagId);
		model.addAttribute("productTag", productTag);
		
		return "product/tagEdit";
	}
	
	/**
	 * 保存商品，并跳转至【填写sku信息】界面
	 * @param model
	 * @param productTag
	 * @param productTagSkuValueIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveProductTag", method = RequestMethod.POST)
	public String saveProductTag(Model model, WxProductTag productTag, Integer[] skuPropValueIds, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		productTag.setUpdateTime(currentTime);
		if(productTag!=null&&productTag.getId()!=null&&productTag.getId()>0){
			result = wxProductTagService.updateById(productTag);
		}else{//新增
			productTag.setCreateTime(currentTime);
			productTag.setStatus((short) 0);
			result = wxProductTagService.save(productTag);
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
	public String tagProductSet(Model model, int tagId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxProductTag productTag = wxProductTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);
		
		List<WxProduct> mappedProductList = wxProductService.queryProductsByTagId(tagId);
		model.addAttribute("mappedProductList", mappedProductList);
		
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
	public String tagProductAdd(Model model,int tagId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxProductTag productTag = wxProductTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);
		
		List<WxProduct> unmappedProductList = wxProductService.queryProductsOutTagId(tagId);
		model.addAttribute("unmappedProductList", unmappedProductList);
		
//		return "material/tagProductSetAdd";
		return "product/tagProductSetAdd";
	}
	
	@RequestMapping("/topProduct")
	public String topProduct(Model model, int tagId, int productId) {
		//置顶操作
		wxProductTagRelationService.topProduct(tagId, productId);

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
		
		WxProductTagRelation obj = new WxProductTagRelation();
		obj.setProductTagId(tagId);
		obj.setProductId(productId);
		obj.setTopTime(new Date());
		wxProductTagRelationService.save(obj);
		
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
		
		int result = wxProductTagRelationService.delete(tagId, productId); 
		
		model.addAttribute("redirectUrl", "./tagProductSet?tagId="+tagId);
		return "forward:/home/operationRedirect";
	}
}
