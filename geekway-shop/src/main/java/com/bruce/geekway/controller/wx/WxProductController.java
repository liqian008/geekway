package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeScope;
import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductCategory;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductTag;
import com.bruce.geekway.model.SkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IProductCategoryService;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductSkuService;
import com.bruce.geekway.service.product.IProductTagService;
import com.bruce.geekway.service.product.ISkuPropValueService;
import com.bruce.geekway.utils.HtmlBuildUtils;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.ShopLinkUtil;
import com.bruce.geekway.utils.ShopLinkUtil.SlideImage;
import com.bruce.geekway.utils.WxShareUtil;

/**
 * 商品controller
 * @author liqian
 *
 */
@Controller
public class WxProductController {
	
	@Autowired
	private IProductCategoryService productCategoryService;
	@Autowired
	private IProductTagService productTagService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductSkuService productSkuService;
	@Autowired
	private ISkuPropValueService wxSkuPropValueService;
	@Autowired
	private ICounterService counterService;
	
//	@Autowired
//	private IWxProductVoucherService productVoucherService;
//	@Autowired
//	private MemcachedClient memcachedClient;
	@Autowired
//	private CacheTestService cacheTestService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);
	
	
//	@RequestMapping(value = {"cacheTest.json"})
//	public ModelAndView cacheTest(Model model, HttpServletRequest request){
//		System.out.println(cacheTestService.cacheTest());
////		System.out.println(cacheTestService.secondCache());
//		
//		return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
//	}
	
	
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	@NeedAuthorize
	@RequestMapping(value = {"/","/index"})
	public String index(Model model, HttpServletRequest request) throws TimeoutException, InterruptedException, MemcachedException {
//		boolean memSet = memcachedClient.set("memkey", 60, "test");
//		System.out.println("mem set result: "+memSet);
//		String xxx = memcachedClient.get("memkey");
//		System.out.println("cache get result: "+xxx);
		
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
		ProductCategory productCategory = productCategoryService.loadCachedById(categoryId);
		model.addAttribute("productCategory", productCategory);
		
		
		if(productCategory!=null){

			List<SlideImage> slideImageList = ShopLinkUtil.buildSlideImageList(productCategory);
			model.addAttribute("slideImageList", slideImageList);
			
			//分享对象
			model.addAttribute("wxSharedInfo", WxShareUtil.wxShare(productCategory.getWxShareTitle(), 
											productCategory.getWxShareContent(), 
											productCategory.getWxShareIconUrl(), 
											ShopLinkUtil.getCategoryLink4Mobile(categoryId)));
		}
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
		ProductTag productTag = productTagService.loadCachedById(tagId);
		model.addAttribute("productTag", productTag);
		if(productTag!=null){

			List<SlideImage> slideImageList = ShopLinkUtil.buildSlideImageList(productTag);
			model.addAttribute("slideImageList", slideImageList);
			
//			List<WxProduct> tagProductList = productService.queryProductsByTagId(tagId);
//			model.addAttribute("tagProductList", tagProductList);
			
			//分享对象
			model.addAttribute("wxSharedInfo", WxShareUtil.wxShare(productTag.getWxShareTitle(), 
											productTag.getWxShareContent(), 
											productTag.getWxShareIconUrl(), 
											ShopLinkUtil.getTagLink4Mobile(tagId)));
		}
		return "product/productListByTag";
	}
	
	
	/**
	 * 根据商品分类列出商品
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "moreTagProducts.json")
	public ModelAndView moreTagProducts(HttpServletRequest request, @RequestParam("tagId") int tagId, @RequestParam(value="pageNo", defaultValue="1") int pageNo, @RequestParam(required=false, defaultValue="6")int pageSize) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多Tag商品，tagId: "+tagId+", pageNo: "+pageNo);
        }
	    if(pageSize>10||pageSize<1){
	    	pageSize = 6;
	    }
	    
	    List<Product> productList = productService.queryCachedProductsByTagId(tagId, pageNo, pageSize, true);

		int nextPageNo = 0;
		if (productList == null || productList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多商品");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		}
		
		if (productList.size() > pageSize) {// 查询数据超过limit，含分页内容
			// 移除最后一个元素
			productList.remove(pageSize);
			nextPageNo = pageNo+1;
			if(logger.isDebugEnabled()){
                logger.debug("还有更多商品，nextPageNo： "+nextPageNo);
            }
		}
		String productListHtml = HtmlBuildUtils.buildFallLoadProductHtml(productList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		dataMap.put("nextPageNo", String.valueOf(nextPageNo));
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	
	/**
	 * 根据商品分类列出商品
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "moreCategoryProducts.json")
	public ModelAndView moreCategoryProducts(HttpServletRequest request, @RequestParam("categoryId") int categoryId, @RequestParam(value="pageNo", defaultValue="1") int pageNo, @RequestParam(required=false, defaultValue="6")int pageSize) {
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多Category商品，categoryId: "+categoryId+", pageNo: "+pageNo);
        }
	    if(pageSize>10||pageSize<1){
	    	pageSize = 6;
	    }
	    
//	    List<WxProductSku> productSkuList = productSkuService.fallLoadCategoryProductSkuList(categoryId, pageNo, limit + 1);
	    List<Product> productList = productService.queryCachedProductsByCategoryId(categoryId, pageNo, pageSize, true);
	    

		int nextPageNo = 0;
		if (productList == null || productList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多商品");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		}
		
		if (productList.size() > pageSize) {// 查询数据超过limit，含分页内容
			// 移除最后一个元素
			productList.remove(pageSize);
			nextPageNo = pageNo+1;//取productId
			if(logger.isDebugEnabled()){
                logger.debug("还有更多商品，nextPageNo： "+nextPageNo);
            }
		}
		String productListHtml = HtmlBuildUtils.buildFallLoadProductHtml(productList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		dataMap.put("nextPageNo", String.valueOf(nextPageNo));
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
	public String productInfo(Model model, @PathVariable int productId, @PathVariable int productSkuId, HttpServletRequest request) {
		Product product = productService.loadCachedById(productId);
		model.addAttribute("product", product);
		
		ProductSku currentProductSku = null;
		
		//加载productSku，用于构造json的map，使得前端切换时能取到相应的数据
		List<ProductSku> productSkuList = productSkuService.queryCachedSkuListByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){ 
			SortedMap<String, String> productSkuMap = new TreeMap<String, String>();
			for(ProductSku productSku: productSkuList){
				//置空无用的字段（json序列化时不需要）
				productSku.setCreateTime(null);
				productSku.setUpdateTime(null);
				
				//设定第一条或选定的那条数据为current
				if((productSkuId==0 && currentProductSku==null)||(productSkuId>0 && productSkuId == productSku.getId())){
					currentProductSku = productSku;//当前指定的sku商品
					//获取最新的库存数量
					int productSkuStock = counterService.queryProductSkuStock(currentProductSku.getId());
					currentProductSku.setStock(productSkuStock);
				}
				String key = productSku.getPropertiesName();
				String value = JsonUtil.gson.toJson(productSku);
				productSkuMap.put(key, value);
			}
			model.addAttribute("productSkuMap", productSkuMap);

			//解析当前sku商品对应的属性值，以便在前端高亮显示
			if(currentProductSku!=null){
				model.addAttribute("currentProductSku", currentProductSku);
				
				//分享对象
				model.addAttribute("wxSharedInfo", WxShareUtil.wxShare(product.getWxShareTitle(), 
												product.getWxShareContent(), 
												product.getWxShareIconUrl(), 
												ShopLinkUtil.getProductLink4Mobile(productId)));
				
				List<SlideImage> slideImageList = ShopLinkUtil.buildSlideImageList(currentProductSku);
				model.addAttribute("slideImageList", slideImageList);
				
			}
		}
		
		//获取该商品sku属性值列表，展示以便用户选择
		List<SkuPropValue> skuPropValueList =  wxSkuPropValueService.querySkuPropValueListByProductId(productId);
		//计算该商品的sku矩阵
		if(skuPropValueList!=null&&skuPropValueList.size()>0){
			Map<Integer, List<SkuPropValue>> skuGroupMap = new TreeMap<Integer, List<SkuPropValue>>();
			for(SkuPropValue skuPropValue: skuPropValueList){
				int skuPropId = skuPropValue.getSkuPropId();
				List<SkuPropValue> skuPropValueGroupList = skuGroupMap.get(skuPropId);
				if(skuPropValueGroupList==null || skuPropValueGroupList.size()<=0){//不存在则初始化（默认值1）
					skuPropValueGroupList = new ArrayList<SkuPropValue>();
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
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_USERINFO)
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
	@RequestMapping(value = "productStock.json")
	public ModelAndView productStock(HttpServletRequest request, int productSkuId) {
		if(logger.isDebugEnabled()){
            logger.debug("ajax获取商品库存");
        }
		int stock = counterService.queryProductSkuStock(productSkuId);
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(stock));
	}
	

	@RequestMapping(value = "recommendProducts.json")
	public ModelAndView recommendProducts(HttpServletRequest request, @RequestParam(required=false, defaultValue="4")int limit) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax获取推荐商品");
        }
	    if(limit>10||limit<1){
	    	limit = 4;
	    }
	    List<Product> productList = productService.queryAvailableList();
	    String productListHtml = HtmlBuildUtils.buildRecommendProductsHtml(productList);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("html", productListHtml);
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	
	
//	
//	private List<String> buildCategoryPicList(WxProductCategory productCategory) {
//		if(productCategory!=null&&productCategory.getId()!=null){
//			List<String> productPicList = new ArrayList<String>();
//			String pic1Url = productCategory.getCategoryPic1Url();
//			if(StringUtils.isNotBlank(pic1Url)){
//				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic1Url, 600, 0));
//			}
//			String pic2Url = productCategory.getCategoryPic2Url();
//			if(StringUtils.isNotBlank(pic2Url)){
//				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic2Url, 600, 0));
//			}
//			String pic3Url = productCategory.getCategoryPic3Url();
//			if(StringUtils.isNotBlank(pic3Url)){
//				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic3Url, 600, 0));
//			}
//			String pic4Url = productCategory.getCategoryPic4Url();
//			if(StringUtils.isNotBlank(pic4Url)){
//				productPicList.add(UploadUtil.getQiniuResizeImageUrl(pic4Url, 600, 0));
//			}
//			return productPicList;
//		}
//		return null;
//	}
	
	
	
	
}
