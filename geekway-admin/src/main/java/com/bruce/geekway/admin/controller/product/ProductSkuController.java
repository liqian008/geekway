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

import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.SkuPropValue;
import com.bruce.geekway.model.SlideImage;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductSkuService;
import com.bruce.geekway.service.product.ISkuPropValueService;
import com.bruce.geekway.service.product.ISlideImageService;

/**
 * 某个product下的sku操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/product")
public class ProductSkuController {

	@Autowired
	private IProductSkuService productSkuService;
	@Autowired
	private IProductService productService;
	@Autowired
	private ISlideImageService slideImageService;
	@Autowired
	private ISkuPropValueService skuPropValueService;
	@Autowired
	private ICounterService counterService;
	
	
	/**
	 * 查看某个商品下的所有sku商品
	 * @param model
	 * @param request
	 * @param productId
	 * @return
	 */
	@RequestMapping("/productSkus")
	public String productSkus(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		Product wxProduct = productService.loadById(productId);
		model.addAttribute("product", wxProduct);
		
		//获取产品对应的sku列表
		List<ProductSku> productSkuList = productSkuService.querySkuListByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			
			//获取propValue的map，供构造skuName
			HashMap<Integer, SkuPropValue> propValueMap = skuPropValueService.queryMap();
			for(ProductSku productSku: productSkuList){
				//根据propName动态计算sku显示name，TODO与edit时进行合并
				String skuPropName = productSku.getPropertiesName();
				String[] skuPropNameArray = skuPropName.split(";");
				StringBuilder sb = new StringBuilder();
				if(skuPropNameArray!=null&&skuPropNameArray.length>0){
					
					for(String skuPropItem: skuPropNameArray){
						String skuPropValueIdStr = skuPropItem.substring(skuPropItem.lastIndexOf(":")+1);
						String skuPropValueName = "错误";
						SkuPropValue propValue = propValueMap.get(Integer.valueOf(skuPropValueIdStr));
						if(propValue!=null){
							skuPropValueName = propValue.getName();
						}
						sb.append(skuPropValueName+"+");
					}
				}
				if(sb.length()>0)sb.setLength(sb.length()-1);
				productSku.setName(sb.toString());
			}
			model.addAttribute("productSkuList", productSkuList);
		}
		
		return "product/productSkuList";
	}
	
	
	/**
	 * 编辑Sku信息
	 * @param model
	 * @param request
	 * @param productId
	 * @param skuId
	 * @return
	 */
	@RequestMapping("/productSkuEdit")
	public String productSkuEdit(Model model, HttpServletRequest request, int productId, int skuId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductSku productSku = productSkuService.loadProductSku(productId, skuId);
		
		//根据propName动态计算sku显示name
		String skuPropName = productSku.getPropertiesName();
		String[] skuPropNameArray = skuPropName.split(";");
		StringBuilder sb = new StringBuilder();
		if(skuPropNameArray!=null&&skuPropNameArray.length>0){
			HashMap<Integer, SkuPropValue> propValueMap = skuPropValueService.queryMap();
			for(String skuPropItem: skuPropNameArray){
				String skuPropValueIdStr = skuPropItem.substring(skuPropItem.lastIndexOf(":")+1);
				String skuPropValueName = "错误";
				SkuPropValue propValue = propValueMap.get(Integer.valueOf(skuPropValueIdStr));
				if(propValue!=null){
					skuPropValueName = propValue.getName();
				}
				sb.append(skuPropValueName+"+");
			}
		}
		if(sb.length()>0)sb.setLength(sb.length()-1);
		productSku.setName(sb.toString());
		
		if(productSku!=null){
			model.addAttribute("productSku", productSku);
			Product product = productService.loadById(productId);
			model.addAttribute("product", product);
			
			//加载该sku商品对应的图片
			List<SlideImage> slideImageList = slideImageService.queryByProductSkuId(skuId);
			model.addAttribute("slideImageList", slideImageList);
		}
		
		return "product/productSkuEdit";
	}
	
	/**
	 * 保存单个sku信息
	 * @param model
	 * @param productSku
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSku", method = RequestMethod.POST)
	public String saveSkuImage(Model model, ProductSku productSku, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		productSku.setUpdateTime(currentTime);
		if(productSku!=null&&productSku.getId()!=null&&productSku.getId()>0){
			productSku.setStock(null);//避免更新库存数，避免数据同步问题
			result = productSkuService.updateById(productSku);
		}
		
		model.addAttribute("redirectUrl", "./productSkus?productId="+productSku.getProductId());
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 增加库存
	 * @param model
	 * @param productId
	 * @param skuId
	 * @param incrStock
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incrStock", method = RequestMethod.POST)
	public String incrStock(Model model, int productId, int skuId, int incrStock, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = counterService.incrProductSkuStock(skuId, incrStock);
		
		model.addAttribute("redirectUrl", "./productSkuEdit?productId="+productId+"&skuId="+skuId);
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * productSku轮播列表
	 */
	@RequestMapping("/productSkuSlideImageList")
	public String productSkuSlideImageList(Model model, int productId, int productSkuId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductSku productSku = productSkuService.loadProductSku(productId, productSkuId);
		model.addAttribute("productSku", productSku);
		
		List<SlideImage> slideImageList = slideImageService.queryByProductSkuId(productSkuId);
		model.addAttribute("slideImageList", slideImageList);
		return "product/productSkuSlideImageList";
	}
	
	/**
	 * 编辑productSku轮播
	 */
	@RequestMapping("/productSkuSlideImageEdit")
	public String productSkuSlideImageEdit(Model model, int productId, int productSkuId, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ProductSku productSku = productSkuService.loadProductSku(productId, productSkuId);
		model.addAttribute("productSku", productSku);
		
		SlideImage slideImage = slideImageService.loadById(id);
		model.addAttribute("slideImage", slideImage);
		return "product/productSkuSlideImageEdit";
	}
	
	/**
	 * 保存productSku轮播
	 */
	@RequestMapping("/saveProductSkuSlideImage")
	public String saveProductSkuSlideImage(Model model, SlideImage slideImage, HttpServletRequest request) {
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
		model.addAttribute("redirectUrl", "./productSkuSlideImageList?productSkuId="+slideImage.getRootId());
		return "forward:/home/operationRedirect";
	}

}
