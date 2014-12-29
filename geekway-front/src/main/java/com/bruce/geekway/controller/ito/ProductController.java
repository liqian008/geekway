package com.bruce.geekway.controller.ito;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.service.ito.IItoProductBgService;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuImageService;
import com.bruce.geekway.service.ito.IItoSkuPropService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;
import com.bruce.geekway.service.ito.IItoSkuService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;

/**
 * ipad请求接口controller
 * @author bruce
 *
 */
@Controller
@RequestMapping(value={"api"})
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger("ItoAppLogger");
	
	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;
	@Autowired
	private IItoSkuService itoSkuService;
	@Autowired
	private IItoSkuImageService itoSkuImageService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	@Autowired
	private IItoSkuPropService itoSkuPropService;
	@Autowired
	private IItoProductBgService itoProductBgService;
	
	/**
	 * 产品列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productList.json")
	public ModelAndView productList() {
		
		logger.info("App客户端获取产品列表");
		
		//检查请求合法性
		
		List<ItoProduct> productList =  itoProductService.queryAvailableList();
		if(productList==null){
			productList = new ArrayList<ItoProduct>();
		}
//		//额外构造一个ito封面的商品，主要为了给客户端使用
//		ItoProductBg productBg = itoProductBgService.loadById(1);
//		if(productBg!=null){
//			ItoProduct coverProduct = new ItoProduct();
//			coverProduct.setProductPicUrl(productBg.getCoverPicUrl());
//			coverProduct.setProductThumbPicUrl(productBg.getCoverThumbPicUrl());
//			coverProduct.setIsCover((short) 1);
//			productList.add(0, coverProduct);
//		}
		//返回数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("productList", productList);
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
	}
	
	/**
	 * 产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productInfo.json")
	public ModelAndView productInfo(int productId) {
		
		logger.info("App客户端获取产品详情");
		
		//检查请求合法性
		
		ItoProduct product =  itoProductService.loadById(productId);
		//检查产品合法性
		if(product!=null&&product.getId()!=null&&product.getStatus()!=null&&product.getStatus()==1){
			//获取该商品对应的所有sku产品
			List<ItoSku> skuList = itoSkuService.queryAllByProductId(productId);
			product.setProductSkus(skuList);
			
			//获取该商品product对应的所有imageList
			List<ItoSkuImage> skuImageList = itoSkuImageService.queryAllByProductId(productId);
			
			//TODO 分别获取每个SKU对应的图片，构造数组
			if(skuList!=null&&skuList.size()>0){
				for(ItoSku sku: skuList){
					if(skuImageList!=null&&skuImageList.size()>0){
						List<ItoSkuImage> itemSkuImageList = new ArrayList<ItoSkuImage>();
						//循环构造skuImageList
						for(ItoSkuImage loopSkuImage: skuImageList){
							if(loopSkuImage.getSkuId()!=null&&loopSkuImage.getSkuId().equals(sku.getId())){
								//为每个sku设置imageList
								itemSkuImageList.add(loopSkuImage);
							}
						}
						sku.setSkuImageList(itemSkuImageList);
					}
				}
			}
			
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
			
			//为了级连显示，构造级连数据，数据结构为详见wiki描述https://github.com/yuanjiang1107/bruce/wiki
			List<ItoSkuPropValue> materialList = new ArrayList<ItoSkuPropValue>();
			if(skuList!=null&&skuList.size()>0){
				//构造材质列表
				for(ItoSku materialSku: skuList){
					if(!containes(materialSku.getMaterialId(), materialList)){
						ItoSkuPropValue materialPropValue = clonePropValueFromList(materialSku.getMaterialId(), skuPropValueList);
						materialPropValue.setSkuPicUrl(materialSku.getMaterialPicUrl());//构造展示的图片
						materialList.add(materialPropValue);
						
						//构造该材质下对应的颜色列表
						List<ItoSkuPropValue> colorList = new ArrayList<ItoSkuPropValue>();
						for(ItoSku colorSku: skuList){
							if(!containes(colorSku.getColorId(), colorList)){
								if(colorSku.getMaterialId()!=null&&colorSku.getMaterialId().equals(materialPropValue.getId())){
									ItoSkuPropValue colorPropValue = clonePropValueFromList(colorSku.getColorId(), skuPropValueList);
									colorPropValue.setSkuPicUrl(colorSku.getColorPicUrl());//构造展示的颜色图片
									colorList.add(colorPropValue);
									materialPropValue.setColorList(colorList);//将colorList加入材质对象
									
									//构造该颜色下对应的尺码列表
									List<ItoSkuPropValue> sizeList = new ArrayList<ItoSkuPropValue>();
									for(ItoSku sizeSku: skuList){
										if(!containes(sizeSku.getSizeId(), sizeList)){
											if(sizeSku.getMaterialId()!=null&&sizeSku.getMaterialId().equals(materialPropValue.getId())&&sizeSku.getColorId().equals(colorPropValue.getId())){
												ItoSkuPropValue sizePropValue = clonePropValueFromList(sizeSku.getSizeId(), skuPropValueList);
												sizePropValue.setSkuPicUrl(sizeSku.getSizePicUrl());//构造展示的图片
												sizeList.add(sizePropValue);
												colorPropValue.setSizeList(sizeList);//将colorList加入材质对象
											}
										}
									}
								}
							}
						}
					}
				}
				
				//
				for(ItoSku sku: skuList){
					sku.setMaterialPicUrl(null);
					sku.setColorPicUrl(null);
					sku.setSizePicUrl(null);
				}
			}
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("product", product);
//			dataMap.put("skuPropList", skuPropList);
			dataMap.put("materialList", materialList);
			
//			dataMap.put("skuPropValues", skuPropValueList);
			
			
			
			
			return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}
	
	/**
	 * 
	 * @param skuPropValueId
	 * @param skuPropValueList
	 * @return
	 */
	private boolean containes(Integer skuPropValueId, List<ItoSkuPropValue> skuPropValueList) {
		if(skuPropValueId!=null&&skuPropValueList!=null&&skuPropValueList.size()>0){
			for(ItoSkuPropValue loop: skuPropValueList){
				if(skuPropValueId.equals(loop.getId())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据传入的skuPropValueId在list中定位&clone（不影响原ItoSkuPropValue的数据）
	 * @param skuPropValueId
	 * @param skuPropValueList
	 * @return
	 */
	private ItoSkuPropValue clonePropValueFromList(Integer skuPropValueId,
			List<ItoSkuPropValue> skuPropValueList) {
		if(skuPropValueId!=null&&skuPropValueList!=null&&skuPropValueList.size()>0){
			for(ItoSkuPropValue skuPropValue: skuPropValueList){
				if(skuPropValueId.equals(skuPropValue.getId())){
					
					ItoSkuPropValue clonedSkuPropValue = new ItoSkuPropValue();
					clonedSkuPropValue.setId(skuPropValue.getId());
					clonedSkuPropValue.setName(skuPropValue.getName());
					clonedSkuPropValue.setDescription(skuPropValue.getDescription());
					clonedSkuPropValue.setSkuPropId(skuPropValue.getSkuPropId());
					clonedSkuPropValue.setSkuPicUrl(skuPropValue.getSkuPicUrl());
					clonedSkuPropValue.setSort(skuPropValue.getSort());
//					clonedSkuPropValue.setSkuThumbPicUrl(skuPropValue.getSkuThumbPicUrl());
					
					return clonedSkuPropValue;
				}
			}
		}
		return null;
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
