package com.bruce.geekway.controller.ito;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoProductService;
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
	private IItoSkuService itoSkuService;
	
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
			
			List<ItoSku> skuList = itoSkuService.queryAllByProductId(productId);
			product.setProductSkus(skuList);
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("product", product);
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
	/**
	 * 提交订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postOrder.json")
	public ModelAndView postOrder(String orderJson){
		//检查请求合法性
		
		//检查参数合法性
		
		//获取支付类型（到付or支付宝）
		
		//到付直接修改更新db
		
		//alipay情况下，生成二维码并返回
		
		return null;
	}
}
