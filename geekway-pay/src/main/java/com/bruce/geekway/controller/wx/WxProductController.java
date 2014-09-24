package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.product.IWxProductCategoryService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductTagService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;
import com.bruce.geekway.utils.HtmlBuildUtils;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;

/**
 * 商品controller
 * @author liqian
 *
 */
@Controller
public class WxProductController {
	
	@Autowired
	private IWxProductCategoryService wxProductCategoryService;
	@Autowired
	private IWxProductTagService wxProductTagService;
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	@Autowired
	private IWxSkuPropValueService wxSkuPropValueService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);

	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/","/index"})
	public String index(Model model, HttpServletRequest request) {
		return "product/index";
	}
	
	/**
	 * 根据分类加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "products/c-{categoryId}")
	public String productListByCategory(Model model, @PathVariable int categoryId, HttpServletRequest request) {
		WxProductCategory productCategory = wxProductCategoryService.loadById(categoryId);
		model.addAttribute("productCategory", productCategory);
		return "product/productListByCategory";
	}
	
	/**
	 * 根据Tag加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "products/t-{tagId}")
	public String productListByTag(Model model, @PathVariable int tagId, HttpServletRequest request) {
		WxProductTag productTag = wxProductTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);
		return "product/productListByTag";
	}
	
	
	/**
	 * 根据商品分类列出商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "moreProducts.json")
	public ModelAndView moreProducts(HttpServletRequest request, @RequestParam("categoryId") int categoryId, @RequestParam("tailId") int tailId) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多专辑，tailId: "+tailId);
        }
	    int limit = 2;
		List<WxProductSku> productSkuList = null;
	    if(logger.isDebugEnabled()){
            logger.debug("根据商品分类查询");
        }
		productSkuList = wxProductSkuService.fallLoadCategoryProductSkuList(categoryId, tailId, limit + 1);

		int nextTailId = 0;
		if (productSkuList == null || productSkuList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多商品");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (productSkuList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				productSkuList.remove(limit);
				nextTailId = productSkuList.get(limit - 1).getProductId();//取productId
				if(logger.isDebugEnabled()){
	                logger.debug("还有更多商品，tailId： "+nextTailId);
	            }
			}
			String productListHtml = HtmlBuildUtils.buildFallLoadProductHtml(productSkuList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", productListHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	
	/**
	 * 商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{productId}")
	public String productInfo(Model model, @PathVariable int productId, HttpServletRequest request) {
		return productInfo(model, productId, 0, request);
	}
	
	/**
	 * 商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{productId}/${productSkuId}")
	public String productInfo(Model model, @PathVariable int productId, @PathVariable int productSkuId, HttpServletRequest request) {
		WxProduct product = wxProductService.loadById(productId);
		model.addAttribute("product", product);
		
		WxProductSku currentProductSku = null;
		
		//加载productSku，用于构造json的map，使得前端切换时取出相应的数据
		List<WxProductSku> productSkuList = wxProductSkuService.queryAllByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			SortedMap<String, String> productSkuMap = new TreeMap<String, String>();
			for(WxProductSku productSku: productSkuList){
				//置空无用的字段（json序列化时不需要）
				productSku.setCreateTime(null);
				productSku.setUpdateTime(null);
				
				//设定第一条或选定的那条数据为current
				if((productSkuId==0 && currentProductSku==null)||(productSkuId>0 && productSkuId == productSku.getId())){
					currentProductSku = productSku;//当前指定的sku商品
				}
				String key = productSku.getPropertiesName();
				String value = JsonUtil.gson.toJson(productSku);
				productSkuMap.put(key, value);
			}
			model.addAttribute("productSkuMap", productSkuMap);

			//解析当前sku商品对应的属性值，以便在前端高亮显示
			if(currentProductSku!=null){
				model.addAttribute("currentProductSku", currentProductSku);
			}
		}
		
		//获取该商品sku属性值列表，展示以便用户选择
		List<WxSkuPropValue> skuPropValueList =  wxSkuPropValueService.querySkuPropValueListByProductId(productId);
		//计算该商品的sku矩阵
		if(skuPropValueList!=null&&skuPropValueList.size()>0){
			Map<Integer, List<WxSkuPropValue>> skuGroupMap = new TreeMap<Integer, List<WxSkuPropValue>>();
			for(WxSkuPropValue skuPropValue: skuPropValueList){
				int skuPropId = skuPropValue.getSkuPropId();
				List<WxSkuPropValue> skuPropValueGroupList = skuGroupMap.get(skuPropId);
				if(skuPropValueGroupList==null || skuPropValueGroupList.size()<=0){//不存在则初始化（默认值1）
					skuPropValueGroupList = new ArrayList<WxSkuPropValue>();
					skuGroupMap.put(skuPropId, skuPropValueGroupList);
				} 
				skuPropValueGroupList.add(skuPropValue);
			}
			model.addAttribute("skuGroupMap", skuGroupMap);
		}
		return "product/productInfo";
	}
	
}
