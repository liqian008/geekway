package com.bruce.geekway.admin.controller.ito;

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

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.service.ito.IItoProductOrderService;
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
	private IItoProductOrderService itoProductOrderService;
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
		
		//计算所有可用的SKU value列表
		List<ItoSkuPropValue> skuPropValueList = itoSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//计算的SKU prop分组
		List<ItoSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		model.addAttribute("product", product);
		return "ito/productAdd";
	}
	
	
	@RequestMapping("/productEdit")
	public String productEdit(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProduct product = itoProductService.loadById(productId);
		model.addAttribute("product", product);
		
		//获取所有的sku数据列表
		List<ItoSkuPropValue> skuPropValueList =  itoSkuPropValueService.queryAll();
		model.addAttribute("skuPropValueList", skuPropValueList);
		//根据数据计算出sku属性列表
		List<ItoSkuProp> skuPropList = getPropListByValueList(skuPropValueList);
		model.addAttribute("skuPropList", skuPropList);
		
		
		int orderCount = itoProductOrderService.countByProductId(productId);
		if(product.getStatus()!=null&&product.getStatus()<1&&orderCount<=0){
			model.addAttribute("canModifySkuSettings", "true");
		}
		
		//获取产品关联的skuValue的idList数据
		List<Integer> productSkuValueIdList =  itoSkuPropValueService.querySkuValueIdListByProductId(productId);
		model.addAttribute("productSkuValueIdList", productSkuValueIdList);
		
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
		boolean isEdit = false;
		int productId = 0;
		String modifySkuSettings = request.getParameter("modifySkuSettings");//是否修改sku属性的标记
		
		if(product!=null&&product.getId()!=null&&product.getId()>0){
			isEdit = true;
			productId = product.getId();
			result = itoProductService.updateById(product);
			product = itoProductService.loadById(productId);//重新加载产品数据
			
			if("true".equals(modifySkuSettings)){
				//检查编辑过程中，是否取消了某个sku属性（只需检查取消的，进行删除操作）
				List<Integer> productSkuValueIdList =  itoSkuPropValueService.querySkuValueIdListByProductId(productId);
				if(productSkuValueIdList!=null&&productSkuValueIdList.size()>0){
					if(productSkuValueIds!=null&&productSkuValueIds.length>0){
						for(Integer loopSkuValueId: productSkuValueIds){
							productSkuValueIdList.remove(loopSkuValueId);//删除未变化的
						}
					}
				}
				
				//此时的productSkuValueIdList应该是差异的部分，需要进行删除操作
				if(productSkuValueIdList!=null&&productSkuValueIdList.size()>0){
					itoSkuPropValueService.deleteBySkuPropValueIds(productId, productSkuValueIdList);//删除skuValue的关联表
					itoSkuService.deleteBySkuPropValueIds(productId, productSkuValueIdList);//删除sku表中的相关数据
				}
			}
			
		}else{//新增
			product.setCreateTime(currentTime);
			product.setStatus((short) 0);
			result = itoProductService.save(product);
			
			//保存SKU数据
			productId = product.getId();
			if(productId>0){
				model.addAttribute("product", product);
			}
//			return "ito/productAddSkus";
		}
		
		
		if("true".equals(modifySkuSettings)){//需要修改sku配置(新创建时也认为是修改)
			//删除
			if(product.getStatus()!=null&&product.getStatus()<1&&productSkuValueIds!=null&&productSkuValueIds.length>0){
				//检查是否有订单
				int orderCount = itoProductOrderService.countByProductId(productId);//已经产生订单了，不能删除
				if(orderCount<=0){
					//取消删除原sku（会导致已有数据被删）
					//result = itoSkuService.deleteByProductId(productId);//不删除sku，直接写db（因为有唯一键约束，不会重复插入）
					
					//创建新SKU配置
					if(productSkuValueIds!=null && productSkuValueIds.length>0){
						
						//取消删除原productSku数据（会导致已有数据被删）
						//itoSkuPropValueService.deleteSkuValuesByProductId(productId);//不删除sku，直接写db（因为有唯一键约束，不会重复插入）
						
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
						
			//			model.addAttribute("skuPropList", skuPropList);
						
						//计算对应的sku矩阵
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
			
							List<String> skuPropertiesNameList = new ArrayList<String>();
							
							for(ItoSkuPropValue materialSkuPropValue: materialSkuList){//材质SKU
			//					skuNameList.add(sizeSkuPropValue.getName()+"+"+colorSkuPropValue.getName()+"+"+materialSkuPropValue.getName());
			//					skuCombineValueList.add(sizeSkuPropValue.getId()+"_"+colorSkuPropValue.getId()+"+"+materialSkuPropValue.getId());
								//构造skuCode
								int materialSkuPropId = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getId();
			//					String materialSkuPropName = skuPropHm.get(materialSkuPropValue.getSkuPropId()).getName();
								String materialSkuCode = getSkuCode(materialSkuPropId, materialSkuPropValue.getId());
								
								
								for(ItoSkuPropValue colorSkuPropValue: colorSkuList){//颜色SKU
									//构造skuCode
									int colorSkuPropId = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getId();
			//						String colorSkuPropName = skuPropHm.get(colorSkuPropValue.getSkuPropId()).getName();
									String colorSkuCode = getSkuCode(colorSkuPropId, colorSkuPropValue.getId());
									
									for(ItoSkuPropValue sizeSkuPropValue: sizeSkuList){//尺码SKU
										int sizeSkuPropId = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getId();
			//							String sizeSkuPropName = skuPropHm.get(sizeSkuPropValue.getSkuPropId()).getName();
										String sizeSkuCode = getSkuCode(sizeSkuPropId, sizeSkuPropValue.getId());
										
										//最后生成的SKUCode，格式为 —— 3:6;2:3;1:1;
										String skuPropertiesName = materialSkuCode + colorSkuCode + sizeSkuCode;
										skuPropertiesNameList.add(skuPropertiesName);
										
										//根据商品的sku配置，生成sku数据，填写价格
										ItoSku itoSku = new ItoSku();
										itoSku.setProductId(productId);
										//设置各sku的缩略图
										itoSku.setSkuPicUrl(product.getProductPicUrl());
										itoSku.setSkuThumbPicUrl(product.getProductThumbPicUrl());
										
										itoSku.setOriginPrice((double) 0);
										itoSku.setPrice((double) 0);
										itoSku.setNum(0);
										
										//为单品设置相应的id（材质，颜色，尺码）
										itoSku.setMaterialId(materialSkuPropValue.getId());
										itoSku.setColorId(colorSkuPropValue.getId());
										itoSku.setSizeId(sizeSkuPropValue.getId());
										
										itoSku.setPropertiesName(skuPropertiesName);
										
										itoSku.setCreateTime(currentTime);
										itoSku.setUpdateTime(currentTime);
										//保存sku
										itoSkuService.save(itoSku);
										
									}
								}
							}
						}
					}
				}
			}	
		}
		
		
		if(isEdit){
			model.addAttribute("redirectUrl", "./productList");
			return "forward:/home/operationRedirect";
		}else{
			model.addAttribute("redirectUrl", "./batchEditProductSkus?productId="+productId);
			return "forward:/home/operationRedirect";
		}
	}
	
	
	@RequestMapping(value = "/batchEditProductSkus")
	public String batchEditProductSkus(Model model, int productId, HttpServletRequest request) {
		
		ItoProduct itoProduct = itoProductService.loadById(productId);
		model.addAttribute("product", itoProduct);
		
		//获取产品对应的sku列表
		List<ItoSku> skuList = itoSkuService.queryAllByProductId(productId);
		if(skuList!=null&&skuList.size()>0){
			//获取propValue的map，供构造skuName
			HashMap<Integer, ItoSkuPropValue> propValueMap = itoSkuPropValueService.queryMap();
			for(ItoSku productSku: skuList){
				//根据propName动态计算sku显示name，TODO与edit时进行合并
				String skuPropName = productSku.getPropertiesName();
				String[] skuPropNameArray = skuPropName.split(";");
				StringBuilder sb = new StringBuilder();
				if(skuPropNameArray!=null&&skuPropNameArray.length>0){
					
					for(String skuPropItem: skuPropNameArray){
						String skuPropValueIdStr = skuPropItem.substring(skuPropItem.lastIndexOf(":")+1);
						String skuPropValueName = "错误";
						ItoSkuPropValue propValue = propValueMap.get(Integer.valueOf(skuPropValueIdStr));
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
		
		model.addAttribute("skuList", skuList);
		
		return "ito/productSkusBatchEdit";
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
				ItoSku itoSku = new ItoSku();
				itoSku.setId(skuId);
				itoSku.setOriginPrice(skuOriginPrice);
				itoSku.setPrice(skuPrice);
				itoSku.setNum(skuNum);
				itoSku.setUpdateTime(currentTime);
				itoSkuService.updateById(itoSku);
			}
		}
		model.addAttribute("redirectUrl", "./productSkus?productId="+productId);
		return "forward:/home/operationRedirect";
	}
	
	
	//TODO，与ItoProductSkuValueController中的getPropListByValueList方法整合
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
	
	
	/**
	 * 删除slider
	 * @param model
	 * @param sliderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delProduct")
	public String delProduct(Model model, int productId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoProduct itoProduct = itoProductService.loadById(productId);
		if(itoProduct!=null){
			if(itoProduct.getStatus()!=null&&itoProduct.getStatus()>0){//启用中的不支持删除
				model.addAttribute("message", "该系列目前处于启用状态，无法删除");
				return "forward:/home/operationResult";
			}
			if(itoProduct.getStatus()!=null&&itoProduct.getStatus()==0){//可以删除
				//检查是否有订单
				int orderCount = itoProductOrderService.countByProductId(productId);//已经产生订单了，不能删除
				if(orderCount>0){
					model.addAttribute("message", "该系列已经产生了订单数据，无法删除");
					return "forward:/home/operationResult";
				}else{
					int result = itoProductService.deleteById(productId);//删除商品
					result = itoSkuService.deleteByProductId(productId);//删除商品sku
					
					result = itoSkuPropValueService.deleteSkuValuesByProductId(productId);//清除product与skuPropValue的关系数据
				}
			}
		}
		model.addAttribute("redirectUrl", "./productList");
		return "forward:/home/operationRedirect"; 
	}
	
	public static void main(String[] args) {
		ItoProductController x=  new ItoProductController();
		System.out.println(getSkuCode(1, 3, "颜色","红色"));
	}
}
