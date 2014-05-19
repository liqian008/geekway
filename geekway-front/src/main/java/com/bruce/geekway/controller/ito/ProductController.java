package com.bruce.geekway.controller.ito;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuPropService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;
import com.bruce.geekway.service.ito.IItoSkuService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"api"})
public class ProductController {
	
	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;
	@Autowired
	private IItoSkuService itoSkuService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	@Autowired
	private IItoSkuPropService itoSkuPropService;
	
	/**
	 * 产品列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productList.json")
	public ModelAndView productList() {
		//检查请求合法性
		
		List<ItoProduct> productList =  itoProductService.queryAll();
		if(productList!=null&&productList.size()>0){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("productList", productList);
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
	/**
	 * 搜索产品
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryProducts.json")
	public ModelAndView queryProducts() {
		//检查请求合法性
		List<ItoProduct> productList =  itoProductService.queryAll();
		if(productList!=null&&productList.size()>0){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("productList", productList);
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
	/**
	 * 产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productInfo.json")
	public ModelAndView productInfo(int productId) {
		//检查请求合法性
		
		ItoProduct product =  itoProductService.loadById(productId);
		//检查产品合法性
		if(product!=null&&product.getId()!=null){
			//获取该商品对应的所有sku产品
			List<ItoSku> skuList = itoSkuService.queryAllByProductId(productId);
			
			//TODO 分别获取每个SKU对应的图片，构造数组			
			
			product.setProductSkus(skuList);
			
			
			
			
			
			List<ItoSkuPropValue> skuPropValueList = itoSkuPropValueService.querySkuValueListByProductId(productId);
			List<ItoSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
			
			//主要为了构造该产品对应的sku属性及旗下的值
			for(ItoSkuProp skuProp: skuPropList){
				List<ItoSkuPropValue> valueList = new ArrayList<ItoSkuPropValue>();
				for(ItoSkuPropValue skuPropValue: skuPropValueList){
					if(skuPropValue.getSkuPropId()!=null&&skuPropValue.getSkuPropId().equals(skuProp.getId())){
						valueList.add(skuPropValue);
					}
				}
				if(valueList.size()>0){//有数据时才添加
					skuProp.setSkuPropValueList(valueList);
				}
			}
			
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("product", product);
			dataMap.put("skuPropList", skuPropList);
//			dataMap.put("skuPropValues", skuPropValueList);
			
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
	
	/**
	 * 获取sku属性列表
	 * @param skuPropValueList
	 * @return 
	 */
	private List<ItoSkuProp> getPropListByValueList(List<ItoSkuPropValue> skuPropValueList) {
		List<ItoSkuProp> skuPropList = new ArrayList<ItoSkuProp>();
		if(skuPropValueList!=null&&skuPropValueList.size()>0){
			HashMap<Integer, ItoSkuProp> skuPropHm = itoSkuPropService.queryMap();
			for(ItoSkuPropValue skuPropValue :skuPropValueList){
				ItoSkuProp skuProp = skuPropHm.get(skuPropValue.getSkuPropId());
				if(skuProp!=null&&!skuPropList.contains(skuProp)){
					skuPropList.add(skuProp);
				}
			}
		}
		return skuPropList;
	}
	
}
