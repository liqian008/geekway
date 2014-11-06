package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.service.product.IWxProductCategoryService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductTagService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.service.upload.impl.UploadLocalServiceImpl;
import com.bruce.geekway.service.upload.impl.UploadQiniuServiceImpl;
import com.bruce.geekway.utils.CartUtil;
import com.bruce.geekway.utils.HtmlBuildUtils;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.UploadUtil;

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
	@Autowired
	private IUploadService uploadQiniuService; 
	@Autowired
	private IUploadService uploadLocalService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);

	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = {"/","/index"})
	public String index(Model model, HttpServletRequest request) {
		try {
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			String result = uploadService.uploadFile(bytesData, "", file.getName());
//			System.out.println(result);
			
//			UploadImageResult uploadResult = qiniuUploadService.uploadImage(new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg"), "", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			UploadImageResult imageUploadResult = uploadService.uploadImage(bytesData, "", "hands-plant-870x450.jpg", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			System.out.println(imageUploadResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "product/index";
	}
	
	
	/**
	 * 根据分类加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "products/c-{categoryId}")
	public String productListByCategory(Model model, @PathVariable int categoryId, HttpServletRequest request) {
		WxProductCategory productCategory = wxProductCategoryService.loadById(categoryId);
		model.addAttribute("productCategory", productCategory);
		
		List<String> categoryPicList = buildCategoryPicList(productCategory);
		model.addAttribute("categoryPicList", categoryPicList);
		
		return "product/productListByCategory";
	}
	
	/**
	 * 根据Tag加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
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
	@NeedAuthorize
	@RequestMapping(value = "moreProducts.json")
	public ModelAndView moreProducts(HttpServletRequest request, @RequestParam("categoryId") int categoryId, @RequestParam("tailId") int tailId, @RequestParam(required=false, defaultValue="6")int limit) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多商品，tailId: "+tailId);
        }
	    if(limit>10||limit<1){
	    	limit = 6;
	    }
	    
	    if(logger.isDebugEnabled()){
            logger.debug("根据商品分类查询");
        }
	    List<WxProductSku> productSkuList = wxProductSkuService.fallLoadCategoryProductSkuList(categoryId, tailId, limit + 1);

		int nextTailId = 0;
		if (productSkuList == null || productSkuList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多商品");
			}
		
			productSkuList = new ArrayList<WxProductSku>();
		}
		
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
	
	
	
	
	/**
	 * sku商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/product/{productId}/{productSkuId}")
	public String product(Model model, @PathVariable int productId, @PathVariable int productSkuId, HttpServletRequest request) {
		WxProduct product = wxProductService.loadById(productId);
		model.addAttribute("product", product);
		
		WxProductSku currentProductSku = null;
		
		//加载productSku，用于构造json的map，使得前端切换时能取到相应的数据
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
				
				List<String> skuPicList = CartUtil.buildProductSkuPicList(currentProductSku);
				model.addAttribute("skuPicList", skuPicList);
				
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
	
	/**
	 * 商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/product/{productId}")
	public String productInfo(Model model, @PathVariable int productId, HttpServletRequest request) {
		return product(model, productId, 0, request);
	}
	
	

	@RequestMapping(value = "recommendProducts.json")
	public ModelAndView recommendProducts(HttpServletRequest request, @RequestParam(required=false, defaultValue="4")int limit) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax获取推荐商品");
        }
	    if(limit>10||limit<1){
	    	limit = 4;
	    }
	    List<WxProduct> productList = wxProductService.queryAvailableList();
	    String productListHtml = HtmlBuildUtils.buildRecommendProductsHtml(productList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	
	
	
	private List<String> buildCategoryPicList(WxProductCategory productCategory) {
		if(productCategory!=null&&productCategory.getId()!=null){
			List<String> productPicList = new ArrayList<String>();
			String pic1Url = productCategory.getCategoryPic1Url();
			if(StringUtils.isNotBlank(pic1Url)){
				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic1Url, 600, 0));
			}
			String pic2Url = productCategory.getCategoryPic2Url();
			if(StringUtils.isNotBlank(pic2Url)){
				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic2Url, 600, 0));
			}
			String pic3Url = productCategory.getCategoryPic3Url();
			if(StringUtils.isNotBlank(pic3Url)){
				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic3Url, 600, 0));
			}
			String pic4Url = productCategory.getCategoryPic4Url();
			if(StringUtils.isNotBlank(pic4Url)){
				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic4Url, 600, 0));
			}
			return productPicList;
		}
		return null;
	}
	
	
	
	
	
	
}
