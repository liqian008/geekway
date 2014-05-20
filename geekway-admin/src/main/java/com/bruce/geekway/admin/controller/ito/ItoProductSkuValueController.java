package com.bruce.geekway.admin.controller.ito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuPropService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;

/**
 * 真正废弃了
 * @author liqian
 *
 */

@Deprecated 
@Controller
@RequestMapping("/ito")
public class ItoProductSkuValueController {

	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoSkuPropService itoSkuPropService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	
	
	/**
	 * 该方法已移至productEdit中了
	 * @param model
	 * @param request
	 * @param productId
	 * @return
	 */
	@RequestMapping("/productSkuValueDisplay")
	public String productSkuValueDisplay(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//获取所有的sku数据列表
		List<ItoSkuPropValue> skuPropValueList =  itoSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//根据数据计算出sku属性列表
		List<ItoSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		//获取产品关联的skuValue的idList数据
		List<Integer> productSkuValueIdList =  itoSkuPropValueService.querySkuValueIdListByProductId(productId);
		model.addAttribute("productSkuValueIdList", productSkuValueIdList);
		
		//商品数据
		ItoProduct Product = itoProductService.loadById(productId);
		model.addAttribute("product", Product);
		return "ito/productSkuValueEdit";
	}
	

	//TODO，与ItoProductController中的getPropListByValueList方法整合
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
