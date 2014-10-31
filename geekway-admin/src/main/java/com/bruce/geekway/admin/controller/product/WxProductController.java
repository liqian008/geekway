package com.bruce.geekway.admin.controller.product;

import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.model.WxSkuPropCriteria;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.service.product.IWxProductCategoryService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxSkuPropService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;



@Controller
@RequestMapping("/product")
public class WxProductController {
	
	private static final int pageSize = 1;
	
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxSkuPropService wxSkuPropService;
	@Autowired
	private IWxSkuPropValueService wxSkuPropValueService;
	
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	@Autowired
	private IWxProductCategoryService wxProductCategoryService;
	
	

	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/productPaging")
	public String productPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxProductCriteria criteria = new WxProductCriteria();
		WxProductCriteria.Criteria subCriteria = criteria.createCriteria();
		
		//根据模块的需求构造查询条件
		String errorType = request.getParameter("errorType");
		if(StringUtils.isNotBlank(errorType)){
			if("get".equalsIgnoreCase(request.getMethod())){
				errorType = URLDecoder.decode(errorType);
			}
//			subCriteria.andErrorTypeEqualTo(errorType);
//			model.addAttribute("errorType", errorType);
		}
		String description = request.getParameter("description");
		if(StringUtils.isNotBlank(description)){
			if("get".equalsIgnoreCase(request.getMethod())){
				description = URLDecoder.decode(description);
			}
			subCriteria.andDescriptionLike(description);
			model.addAttribute("description", description);
		}
		
		PagingResult<WxProduct> alarmPagingData = wxProductService.pagingByCriteria(pageNo, pageSize , criteria);
		if(alarmPagingData!=null){
			alarmPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			alarmPagingData.setQueryMap(queryMap);
			model.addAttribute("alarmPagingData", alarmPagingData);
		}
		return "wxpay/alarmListPaging";
	}
	
	
	
	@RequestMapping("/productList")
	public String productList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxProduct> productList = wxProductService.queryAll();
		model.addAttribute("productList", productList);
		return "product/productList";
	}
	
//	/**
//	 * 添加产品的预操作（需要先选择产品分类）
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/productPreAdd")
//	public String productAdd(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		//查询出所有分类，以供用户选择产品所属分类
//		List<WxProductCategory> categoryList = wxProductCategoryService.queryAll();
//		
//		model.addAttribute("product", new WxProduct());
//		model.addAttribute("categoryList", categoryList);
//		return "product/productPreAdd";
//	}
	
	/**
	 * 添加产品品(需要在上一步将分类id传入)
	 * @param model
	 * @param product
	 * @param request
	 * @return
	 */
	@RequestMapping("/productAdd")
	public String productAdd(Model model, WxProduct product, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		if(product!=null){
			product.setSkuCategoryId(1);
		}
		
		//TODO 需要与edit中的方法重构
		//查询categoryId隶属下的skuPropList
		WxSkuPropCriteria skuPpropCriteria = new WxSkuPropCriteria();
		skuPpropCriteria.createCriteria().andSkuCategoryIdEqualTo(1);
		List<WxSkuProp> skuPropList = wxSkuPropService.queryByCriteria(skuPpropCriteria);
		model.addAttribute("skuPropList", skuPropList);
		
		List<Integer> skuPropIdList = null;
		if(skuPropList!=null&&skuPropList.size()>0){
			skuPropIdList = new ArrayList<Integer>();
			for(WxSkuProp skuProp: skuPropList){
				skuPropIdList.add(skuProp.getId());
			}
		}
		
		//根据以上的skuPropList查询所对应的propValueList
		List<WxSkuPropValue> skuPropValueList = wxSkuPropValueService.querySkuPropValueListByPropIdList(skuPropIdList);
		model.addAttribute("skuPropValueList", skuPropValueList);
		
		model.addAttribute("product", product);
		return "product/productAdd";
	}
	
	@RequestMapping("/productEdit")
	public String productEdit(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProduct product = wxProductService.loadById(productId);
		model.addAttribute("product", product);
		
		//TODO 需要与save中的方法重构
		//查询categoryId隶属下的skuPropList
		WxSkuPropCriteria skuPpropCriteria = new WxSkuPropCriteria();
		skuPpropCriteria.createCriteria().andSkuCategoryIdEqualTo(1);
		List<WxSkuProp> skuPropList = wxSkuPropService.queryByCriteria(skuPpropCriteria);
		model.addAttribute("skuPropList", skuPropList);
		
		List<Integer> skuPropIdList = null;
		if(skuPropList!=null&&skuPropList.size()>0){
			skuPropIdList = new ArrayList<Integer>();
			for(WxSkuProp skuProp: skuPropList){
				skuPropIdList.add(skuProp.getId());
			}
		}
		
		//根据以上的skuPropList查询所对应的propValueList
		List<WxSkuPropValue> skuPropValueList = wxSkuPropValueService.querySkuPropValueListByPropIdList(skuPropIdList);
		model.addAttribute("skuPropValueList", skuPropValueList);
		
		//获取产品关联的skuValue的idList数据
		List<Integer> productSkuValueIdList =  wxSkuPropValueService.querySkuPropValueIdListByProductId(productId);
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
	public String saveProduct(Model model, WxProduct product, Integer[] skuPropValueIds, HttpServletRequest request) {
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
				if(skuPropValueIds!=null && skuPropValueIds.length>0){
					// Step2，填写sku属性数据

					// 获取产品关联的skuValue的List数据
					List<WxSkuPropValue> skuPropValueList =  wxSkuPropValueService.querySkuPropValueListByIdList(Arrays.asList(skuPropValueIds));
					
					model.addAttribute("skuPropValueList", skuPropValueList);
					
					//计算对应的sku矩阵
					if(skuPropValueList!=null&&skuPropValueList.size()>0){
						HashMap<Integer, List<WxSkuPropValue>> skuGroupMap = new HashMap<Integer, List<WxSkuPropValue>>();
						for(WxSkuPropValue skuPropValue: skuPropValueList){
							int skuPropId = skuPropValue.getSkuPropId();
							
							List<WxSkuPropValue> skuGroupList = skuGroupMap.get(skuPropId);
							if(skuGroupList==null || skuGroupList.size()<=0){//不存在则初始化（默认值1）
								skuGroupList = new ArrayList<WxSkuPropValue>();
								skuGroupMap.put(skuPropId, skuGroupList);
							} 
							skuGroupList.add(skuPropValue);
						}
						//只有两级, 1、颜色；2、尺码
						List<WxSkuPropValue> colorSkuList = new ArrayList<WxSkuPropValue>();
						List<WxSkuPropValue> sizeSkuList = new ArrayList<WxSkuPropValue>();
						
						if(skuGroupMap!=null&&skuGroupMap.size()==2){
							colorSkuList = skuGroupMap.get(1);
							sizeSkuList = skuGroupMap.get(2);
						}

						for(WxSkuPropValue colorSkuPropValue: colorSkuList){//颜色SKU
							//构造skuCode
							int colorSkuPropId = colorSkuPropValue.getSkuPropId();
							String colorSkuCode = getSkuCode(colorSkuPropId, colorSkuPropValue.getId());
							
							for(WxSkuPropValue sizeSkuPropValue: sizeSkuList){//尺码SKU
								int sizeSkuPropId = sizeSkuPropValue.getSkuPropId();
								String sizeSkuCode = getSkuCode(sizeSkuPropId, sizeSkuPropValue.getId());
								//最后生成的SKUCode格式为 —— 3:6;2:3;
								String skuProperty = colorSkuCode + sizeSkuCode;
								String skuPropertyName =  colorSkuPropValue.getName() + "（" + sizeSkuPropValue.getName()+"）";
								
								//根据商品的sku配置，生成sku数据，填写价格
								WxProductSku wxProductSku = new WxProductSku();
								wxProductSku.setProductId(productId);
								
								wxProductSku.setName(product.getName());
								wxProductSku.setSkuName(skuPropertyName);
								
								//设置各sku的缩略图
//								wxProductSku.setSkuPicUrl(product.getProductPicUrl());
//								wxProductSku.setSkuThumbPicUrl(product.getProductThumbPicUrl());
								
								wxProductSku.setOriginPrice((double) 0);
								wxProductSku.setPrice((double) 0);
								wxProductSku.setStock(0);
								wxProductSku.setPropertiesName(skuProperty);
								
								wxProductSku.setSkuColorId(colorSkuPropValue.getSkuPropId());
								wxProductSku.setSkuColorValueId(colorSkuPropValue.getId());
								wxProductSku.setSkuSizeId(sizeSkuPropValue.getSkuPropId());
								wxProductSku.setSkuSizeValueId(sizeSkuPropValue.getId());
								
								wxProductSku.setCreateTime(currentTime);
								//保存sku
								result = wxProductSkuService.save(wxProductSku);
							}
						}
					}
				}
			}
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
				int skuStock = NumberUtils.toInt(request.getParameter("skuAmount_"+skuIdStr), 0);
				//保存
				WxProductSku wxSku = new WxProductSku();
				wxSku.setId(skuId);
				wxSku.setOriginPrice(skuOriginPrice); 
				wxSku.setPrice(skuPrice);
				wxSku.setStock(skuStock);
				wxSku.setUpdateTime(currentTime);
				wxProductSkuService.updateById(wxSku);
			}
		}
		model.addAttribute("redirectUrl", "./productSkus?productId="+productId);
		return "forward:/home/operationRedirect";
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
		WxProductController x =  new WxProductController();
		System.out.println(getSkuCode(1, 3, "颜色","红色"));
	}
	
	
}
