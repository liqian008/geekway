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
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductSkuService;
import com.bruce.geekway.service.product.ISkuPropValueService;

/**
 * 某个product下的sku操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/product")
public class WxProductSkuController {

	@Autowired
	private IProductSkuService wxProductSkuService;
	@Autowired
	private IProductService wxProductService;
	@Autowired
	private ISkuPropValueService wxSkuPropValueService;
	
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
		
		Product wxProduct = wxProductService.loadById(productId);
		model.addAttribute("product", wxProduct);
		
		//获取产品对应的sku列表
		List<ProductSku> productSkuList = wxProductSkuService.querySkuListByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			
			//获取propValue的map，供构造skuName
			HashMap<Integer, SkuPropValue> propValueMap = wxSkuPropValueService.queryMap();
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
		
		ProductSku productSku = wxProductSkuService.loadProductSku(productId, skuId);
		
		//根据propName动态计算sku显示name
		String skuPropName = productSku.getPropertiesName();
		String[] skuPropNameArray = skuPropName.split(";");
		StringBuilder sb = new StringBuilder();
		if(skuPropNameArray!=null&&skuPropNameArray.length>0){
			HashMap<Integer, SkuPropValue> propValueMap = wxSkuPropValueService.queryMap();
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
			Product product = wxProductService.loadById(productId);
			model.addAttribute("product", product);
			
//			//加载该sku商品对应的图片
//			List<WxSkuImage> skuImageList = wxSkuImageService.queryAllBySkuId(skuId);
//			model.addAttribute("skuImageList", skuImageList);
		}
		
		return "product/productSkuEdit";
	}
	
	/**
	 * 保存单个sku信息
	 * @param model
	 * @param wxProductSku
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSku", method = RequestMethod.POST)
	public String saveSkuImage(Model model, ProductSku wxProductSku, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		wxProductSku.setUpdateTime(currentTime);
		if(wxProductSku!=null&&wxProductSku.getId()!=null&&wxProductSku.getId()>0){
			result = wxProductSkuService.updateById(wxProductSku);
		}
		
		model.addAttribute("redirectUrl", "./productSkus?productId="+wxProductSku.getProductId());
		return "forward:/home/operationRedirect";
	}

}
