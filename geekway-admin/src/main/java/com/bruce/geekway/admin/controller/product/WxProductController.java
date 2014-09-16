package com.bruce.geekway.admin.controller.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxSkuPropService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;



@Controller
@RequestMapping("/product")
public class WxProductController {

	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxSkuPropService wxSkuPropService;
	@Autowired
	private IWxSkuPropValueService wxSkuPropValueService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	
	
	@RequestMapping("/productList")
	public String productList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxProduct> productList = wxProductService.queryAll();
		model.addAttribute("productList", productList);
		return "product/productList";
	}
	
	@RequestMapping("/productAdd")
	public String productAdd(Model model, WxProduct product, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//计算所有可用的SKU value列表
		List<WxSkuPropValue> skuPropValueList = wxSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//计算的SKU prop分组
		List<WxSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		model.addAttribute("product", product);
		return "product/productAdd";
	}
	
	
	@RequestMapping("/productEdit")
	public String productEdit(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProduct Product = wxProductService.loadById(productId);
		model.addAttribute("product", Product);
		
		//获取所有的sku数据列表
		List<WxSkuPropValue> skuPropValueList =  wxSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//根据数据计算出sku属性列表
		List<WxSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		//获取产品关联的skuValue的idList数据
		List<Integer> productSkuValueIdList =  wxSkuPropValueService.querySkuValueIdListByProductId(productId);
		model.addAttribute("productSkuValueIdList", productSkuValueIdList);
		
		return "product/productEdit";
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
	public String saveProduct(Model model, WxProduct product, Integer[] productSkuValueIds, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		product.setUpdateTime(currentTime);
		if(product!=null&&product.getId()!=null&&product.getId()>0){
			result = wxProductService.updateById(product);
			model.addAttribute("redirectUrl", "./productList");
			return "forward:/home/operationRedirect";
		}else{//新增
			product.setCreateTime(currentTime);
			product.setStatus((short) 0);
			result = wxProductService.save(product);
			
			//保存SKU数据
			int productId = product.getId();
			if(productId>0){
				model.addAttribute("product", product);
				
			    //创建新SKU配置
				if(productSkuValueIds!=null && productSkuValueIds.length>0){
					
					//清除原productSku数据
					wxSkuPropValueService.deleteSkuValuesByProductId(productId);
					
					List<Integer> productSkuValueIdList = Arrays.asList(productSkuValueIds);
					result = wxSkuPropValueService.saveProductSkuValues(productId, productSkuValueIdList);

					// Step2，填写sku属性数据

					// 获取产品关联的skuValue的List数据
					List<WxSkuPropValue> productSkuValueList =  wxSkuPropValueService.querySkuValueListByProductId(productId);
					model.addAttribute("productSkuValueList", productSkuValueList);
					//sku属性列表
					List<WxSkuProp> skuPropList = new ArrayList<WxSkuProp>();
					HashMap<Integer, WxSkuProp> skuPropHm = new HashMap<Integer, WxSkuProp>();
					if(productSkuValueList!=null&&productSkuValueList.size()>0){
						skuPropHm = wxSkuPropService.queryMap();
						for(WxSkuPropValue skuPropValue :productSkuValueList){
							WxSkuProp skuProp = skuPropHm.get(skuPropValue.getSkuPropId());
							if(skuProp!=null&&!skuPropList.contains(skuProp)){
								skuPropList.add(skuProp);
							}
						}
					}
					
//					model.addAttribute("skuPropList", skuPropList);
					
					//计算对应的sku矩阵
					if(productSkuValueList!=null&&productSkuValueList.size()>0){
						HashMap<Integer, List<WxSkuPropValue>> skuGroupMap = new HashMap<Integer, List<WxSkuPropValue>>();
						for(WxSkuPropValue skuPropValue: productSkuValueList){
							int skuPropId = skuPropValue.getSkuPropId();
							
							List<WxSkuPropValue> skuGroupList = skuGroupMap.get(skuPropId);
							if(skuGroupList==null || skuGroupList.size()<=0){//不存在则初始化（默认值1）
								skuGroupList = new ArrayList<WxSkuPropValue>();
								skuGroupMap.put(skuPropId, skuGroupList);
							} 
							skuGroupList.add(skuPropValue);
						}
						
						List<WxSkuPropValue> sizeSkuList = new ArrayList<WxSkuPropValue>();
						List<WxSkuPropValue> colorSkuList = new ArrayList<WxSkuPropValue>();
						List<WxSkuPropValue> materialSkuList = new ArrayList<WxSkuPropValue>();
						
						
						if(skuGroupMap!=null&&skuGroupMap.size()==3){
							sizeSkuList = skuGroupMap.get(1);
							colorSkuList = skuGroupMap.get(2);
							materialSkuList = skuGroupMap.get(3);
						}

						List<String> skuPropertiesNameList = new ArrayList<String>();
						
						for(WxSkuPropValue materialSkuPropValue: materialSkuList){//材质SKU
//							skuNameList.add(sizeSkuPropValue.getName()+"+"+colorSkuPropValue.getName()+"+"+materialSkuPropValue.getName());
//							skuCombineValueList.add(sizeSkuPropValue.getId()+"_"+colorSkuPropValue.getId()+"+"+materialSkuPropValue.getId());
							//构造skuCode
							int materialSkuPropId = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getId();
//							String materialSkuPropName = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getName();
							String materialSkuCode = getSkuCode(materialSkuPropId, materialSkuPropValue.getId());
							
							
							for(WxSkuPropValue colorSkuPropValue: colorSkuList){//颜色SKU
								//构造skuCode
								int colorSkuPropId = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getId();
//								String colorSkuPropName = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getName();
								String colorSkuCode = getSkuCode(colorSkuPropId, colorSkuPropValue.getId());
								
								for(WxSkuPropValue sizeSkuPropValue: sizeSkuList){//尺码SKU
									int sizeSkuPropId = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getId();
//									String sizeSkuPropName = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getName();
									String sizeSkuCode = getSkuCode(sizeSkuPropId, sizeSkuPropValue.getId());
									
									//最后生成的SKUCode，格式为 —— 3:6;2:3;1:1;
									skuPropertiesNameList.add(materialSkuCode + colorSkuCode + sizeSkuCode);
								}
							}
						}
						
						//根据商品的sku配置，生成sku数据，填写价格
						//创建新sku数据
						int i=0;
						for(String skuPropertiesName: skuPropertiesNameList){
							WxProductSku wxProductSku = new WxProductSku();
							wxProductSku.setProductId(productId);
							//设置各sku的缩略图
							wxProductSku.setSkuPicUrl(product.getProductPicUrl());
							wxProductSku.setSkuThumbPicUrl(product.getProductThumbPicUrl());
							
							wxProductSku.setOriginPrice((long) 0);
							wxProductSku.setPrice((long) 0);
							wxProductSku.setNum(0);
							
							wxProductSku.setPropertiesName(skuPropertiesName);
							
							wxProductSku.setCreateTime(currentTime);
							wxProductSku.setUpdateTime(currentTime);
							i++;
							//保存sku
							wxProductSkuService.save(wxProductSku);
						}
					}
				}
			}
//			return "product/productAddSkus";
			
			model.addAttribute("redirectUrl", "./batchEditProductSkus?productId="+productId);
			return "forward:/home/operationRedirect";
		}
	}
	
	
	@RequestMapping(value = "/batchEditProductSkus")
	public String batchEditProductSkus(Model model, int productId, HttpServletRequest request) {
		
		WxProduct wxProduct = wxProductService.loadById(productId);
		model.addAttribute("product", wxProduct);
		
		//获取产品对应的sku列表
		List<WxProductSku> productSkuList = wxProductSkuService.queryAllByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			//获取propValue的map，供构造skuName
			HashMap<Integer, WxSkuPropValue> propValueMap = wxSkuPropValueService.queryMap();
			for(WxProductSku productSku: productSkuList){
				//根据propName动态计算sku显示name，TODO与edit时进行合并
				String skuPropName = productSku.getPropertiesName();
				String[] skuPropNameArray = skuPropName.split(";");
				StringBuilder sb = new StringBuilder();
				if(skuPropNameArray!=null&&skuPropNameArray.length>0){
					
					for(String skuPropItem: skuPropNameArray){
						String skuPropValueIdStr = skuPropItem.substring(skuPropItem.lastIndexOf(":")+1);
						String skuPropValueName = "错误";
						WxSkuPropValue propValue = propValueMap.get(Integer.valueOf(skuPropValueIdStr));
						if(propValue!=null){
							skuPropValueName = propValue.getName();
						}
						sb.append(skuPropValueName+"+");
					}
				}
				if(sb.length()>0)sb.setLength(sb.length()-1);
				productSku.setName(sb.toString());
			}
		}
		
		model.addAttribute("productSkuList", productSkuList);
		
		return "product/productSkusBatchEdit";
	}
	
	
	/**
	 * 批量保存product的完整sku信息
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batchSaveProductSkus", method = RequestMethod.POST)
	public String batchSaveProductSkus(Model model, int productId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		String[] skuIdArray = request.getParameterValues("skuIds");
		if(skuIdArray!=null&&skuIdArray.length>0){
			Date currentTime = new Date();
			for(String skuIdStr: skuIdArray){
				int skuId = NumberUtils.toInt(skuIdStr, 0);
//				
				double skuOriginPrice = NumberUtils.toDouble(request.getParameter("skuOriginPrice_"+skuIdStr), 0);
				double skuPrice = NumberUtils.toDouble(request.getParameter("skuPrice_"+skuIdStr), 0);
				int skuNum = NumberUtils.toInt(request.getParameter("skuNum_"+skuIdStr), 0);
				//保存
				WxProductSku wxSku = new WxProductSku();
				wxSku.setId(skuId);
				wxSku.setOriginPrice((long)skuOriginPrice);
				wxSku.setPrice((long)skuPrice);
				wxSku.setNum(skuNum);
				wxSku.setUpdateTime(currentTime);
				wxProductSkuService.updateById(wxSku);
			}
		}
		model.addAttribute("redirectUrl", "./productSkus?productId="+productId);
		return "forward:/home/operationRedirect";
	}
	
	
	//TODO，与WxProductSkuValueController中的getPropListByValueList方法整合
	/**
	 * 获取sku属性列表
	 * @param skuPropValueList
	 * @return 
	 */
	private List<WxSkuProp> getPropListByValueList(List<WxSkuPropValue> skuPropValueList) {
		List<WxSkuProp> skuPropList = new ArrayList<WxSkuProp>();
		if(skuPropValueList!=null&&skuPropValueList.size()>0){
			HashMap<Integer, WxSkuProp> skuPropHm = wxSkuPropService.queryMap();
			for(WxSkuPropValue skuPropValue :skuPropValueList){
				WxSkuProp skuProp = skuPropHm.get(skuPropValue.getSkuPropId());
				if(skuProp!=null&&!skuPropList.contains(skuProp)){
					skuPropList.add(skuProp);
				}
			}
		}
		return skuPropList;
	}
	
	
	/**
	 * 拼装skuCode，格式为propId:propValueId;
	 * @param pid
	 * @param integer
	 * @param pname
	 * @param vname
	 * @return
	 */
	private static String getSkuCode(int pid1, int vid1){
		StringBuilder sb = new StringBuilder();
		sb.append(pid1+":");
		sb.append(vid1+";");
		return sb.toString();
	}
	
	/**
	 * 拼装skuCode，格式为propId:propValueId:propName:propValueName
	 * @param pid
	 * @param integer
	 * @param pname
	 * @param vname
	 * @return
	 */
	@Deprecated
	private static String getSkuCode(int pid1, int vid1, String pname1, String vname1){
		StringBuilder sb = new StringBuilder();
		sb.append(pid1+":");
		sb.append(vid1+":");
		sb.append(pname1+":");
		sb.append(vname1+";");
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		WxProductController x=  new WxProductController();
		System.out.println(getSkuCode(1, 3, "颜色","红色"));
	}
}
