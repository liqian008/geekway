package com.bruce.geekway.admin.controller.ito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuPropService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;
import com.bruce.geekway.service.ito.IItoSkuService;



@Controller
@RequestMapping("/ito")
public class ItoProductController {

	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoSkuPropService itoSkuPropService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	@Autowired
	private IItoSkuService itoSkuService;
	
	
	@RequestMapping("/productList")
	public String productList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoProduct> productList = itoProductService.queryAll();
		model.addAttribute("productList", productList);
		return "ito/productList";
	}
	
	@RequestMapping("/productAdd")
	public String productAdd(Model model, ItoProduct product, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//计算product对应的SKU value列表
		List<ItoSkuPropValue> skuPropValueList = itoSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//计算product对应的SKU prop列表
		List<ItoSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		model.addAttribute("product", product);
		return "ito/productAdd";
	}
	
	
	@RequestMapping("/productEdit")
	public String productEdit(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProduct Product = itoProductService.loadById(productId);
		model.addAttribute("product", Product);
		
		//获取产品对应的sku列表
		List<ItoSku> skuList = itoSkuService.queryAllByProductId(productId);
		model.addAttribute("skuList", skuList);
		
		return "ito/productEdit";
	}
	
	/**
	 * 保存商品，并跳转至【填写sku信息】界面
	 * @param model
	 * @param product
	 * @param productSkuValueIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(Model model, ItoProduct product, Integer[] productSkuValueIds, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		product.setUpdateTime(currentTime);
		if(product!=null&&product.getId()!=null&&product.getId()>0){
			result = itoProductService.updateById(product);
			model.addAttribute("redirectUrl", "./productList");
			return "forward:/home/operationRedirect";
		}else{//新增
			product.setCreateTime(currentTime);
			product.setStatus((short) 0);
			result = itoProductService.save(product);
			
			//保存SKU数据
			int productId = product.getId();
			if(productId>0){
				model.addAttribute("product", product);
				
			    //创建新SKU配置
				if(productSkuValueIds!=null && productSkuValueIds.length>0){
					List<Integer> productSkuValueIdList = Arrays.asList(productSkuValueIds);
					result = itoSkuPropValueService.saveProductSkuValues(productId, productSkuValueIdList);

					// Step2，填写sku属性数据

					// 获取产品关联的skuValue的List数据
					List<ItoSkuPropValue> productSkuValueList =  itoSkuPropValueService.querySkuValueListByProductId(productId);
					model.addAttribute("productSkuValueList", productSkuValueList);
					//sku属性列表
					List<ItoSkuProp> skuPropList = new ArrayList<ItoSkuProp>();
					HashMap<Integer, ItoSkuProp> skuPropHm = new HashMap<Integer, ItoSkuProp>();
					if(productSkuValueList!=null&&productSkuValueList.size()>0){
						skuPropHm = itoSkuPropService.queryMap();
						for(ItoSkuPropValue skuPropValue :productSkuValueList){
							ItoSkuProp skuProp = skuPropHm.get(skuPropValue.getSkuPropId());
							if(skuProp!=null&&!skuPropList.contains(skuProp)){
								skuPropList.add(skuProp);
							}
						}
					}
					
					model.addAttribute("skuPropList", skuPropList);
					//计算sku矩阵
					if(productSkuValueList!=null&&productSkuValueList.size()>0){
						HashMap<Integer, List<ItoSkuPropValue>> skuGroupMap = new HashMap<Integer, List<ItoSkuPropValue>>();
						for(ItoSkuPropValue skuPropValue: productSkuValueList){
							int skuPropId = skuPropValue.getSkuPropId();
							List<ItoSkuPropValue> skuGroupList = skuGroupMap.get(skuPropId);
							if(skuGroupList==null || skuGroupList.size()<=0){//不存在则初始化（默认值1）
								skuGroupList = new ArrayList<ItoSkuPropValue>();
								skuGroupMap.put(skuPropId, skuGroupList);
							} 
							skuGroupList.add(skuPropValue);
						}
						
						List<ItoSkuPropValue> sizeSkuList = new ArrayList<ItoSkuPropValue>();
						List<ItoSkuPropValue> colorSkuList = new ArrayList<ItoSkuPropValue>();
						List<ItoSkuPropValue> materialSkuList = new ArrayList<ItoSkuPropValue>();
						
						
						if(skuGroupMap!=null&&skuGroupMap.size()==3){
							sizeSkuList = skuGroupMap.get(1);
							colorSkuList = skuGroupMap.get(2);
							materialSkuList = skuGroupMap.get(3);
						}

						List<String> skuCombineLabelList = new ArrayList<String>();
						List<String> skuCombineValueList = new ArrayList<String>();
						List<String> skuCodeList = new ArrayList<String>();
						
						for(ItoSkuPropValue sizeSkuPropValue: sizeSkuList){//尺码SKU
							int sizeSkuPropId = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getId();
							String sizeSkuPropName = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getName();
							String sizeSkuCode = getSkuCode(sizeSkuPropId, sizeSkuPropValue.getId(), sizeSkuPropName, sizeSkuPropValue.getName());
							
							for(ItoSkuPropValue colorSkuPropValue: colorSkuList){//颜色SKU
								//构造skuCode
								int colorSkuPropId = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getId();
								String colorSkuPropName = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getName();
								String colorSkuCode = getSkuCode(colorSkuPropId, colorSkuPropValue.getId(), colorSkuPropName, colorSkuPropValue.getName());
								
								for(ItoSkuPropValue materialSkuPropValue: materialSkuList){//材质SKU
									skuCombineLabelList.add(sizeSkuPropValue.getName()+"+"+colorSkuPropValue.getName()+"+"+materialSkuPropValue.getName());
									skuCombineValueList.add(sizeSkuPropValue.getId()+"_"+colorSkuPropValue.getId()+"+"+materialSkuPropValue.getId());
									//构造skuCode
									int materialSkuPropId = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getId();
									String materialSkuPropName = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getName();
									String materialSkuCode = getSkuCode(materialSkuPropId, materialSkuPropValue.getId(), materialSkuPropName, materialSkuPropValue.getName());
									
									//SKUCode
									skuCodeList.add(sizeSkuCode + colorSkuCode + materialSkuCode);
								}
							}
						}
						
						
						model.addAttribute("skuCombineLabelList", skuCombineLabelList);
						model.addAttribute("skuCombineValueList", skuCombineValueList);
						model.addAttribute("skuCodeList", skuCodeList);
					}
				}
			}
			return "ito/productSkuEdit";
		}
	}
	
	
	/**
	 * 保存product的完整sku信息
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveProductSku", method = RequestMethod.POST)
	public String saveProductSku(Model model, int productId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		String[] skuCombineArray = request.getParameterValues("skuCombines");
		if(skuCombineArray!=null&&skuCombineArray.length>0){
			Date currentTime = new Date();
			for(String skuCombine: skuCombineArray){
				double skuOriginPrice = NumberUtils.toDouble(request.getParameter("skuOriginPrice_"+skuCombine), 0);
				double skuPrice = NumberUtils.toDouble(request.getParameter("skuPrice_"+skuCombine), 0);
				int skuNum = NumberUtils.toInt(request.getParameter("skuQuality_"+skuCombine), 0);
				String skuName = StringUtils.defaultString(request.getParameter("skuName_"+skuCombine), "");
				String skuCode = StringUtils.defaultString(request.getParameter("skuCode_"+skuCombine), "");
				
//				String skuSn = StringUtils.defaultString(request.getParameter("skuSn_"+skuCombine), "");
				
				//保存
				ItoSku itoSku = new ItoSku();
				itoSku.setProductId(productId);
				itoSku.setOriginPrice(skuOriginPrice);
				itoSku.setPrice(skuPrice);
//				itoSku.setOutId(skuSn);
				itoSku.setQuality(skuNum);
				itoSku.setName(skuName);
				itoSku.setPropertiesName(skuCode);
				itoSku.setCreateTime(currentTime);
				itoSku.setUpdateTime(currentTime);
				itoSkuService.save(itoSku);
			}
		}

		model.addAttribute("redirectUrl", "./productList");
		return "forward:/home/operationRedirect";
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
	
	/**
	 * 拼装skuCode
	 * @param pid
	 * @param integer
	 * @param pname
	 * @param vname
	 * @return
	 */
	private static String getSkuCode(int pid1, int vid1, String pname1, String vname1){
		StringBuilder sb = new StringBuilder();
		sb.append(pid1+":");
		sb.append(vid1+":");
		sb.append(pname1+":");
		sb.append(vname1+";");
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		ItoProductController x=  new ItoProductController();
		System.out.println(getSkuCode(1, 3, "颜色","红色"));
	}
}
