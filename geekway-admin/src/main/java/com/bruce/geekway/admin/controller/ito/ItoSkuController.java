package com.bruce.geekway.admin.controller.ito;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuCriteria;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuImageService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;
import com.bruce.geekway.service.ito.IItoSkuService;

/**
 * 某个product下的sku操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/ito")
public class ItoSkuController {

	@Autowired
	private IItoSkuService itoSkuService;
	@Autowired
	private IItoSkuImageService itoSkuImageService;
	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	
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
			model.addAttribute("skuList", skuList);
		}
		
		return "ito/productSkuList";
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
		
		ItoSku productSku = itoSkuService.loadProductSku(productId, skuId);
		
		//根据propName动态计算sku显示name
		String skuPropName = productSku.getPropertiesName();
		String[] skuPropNameArray = skuPropName.split(";");
		StringBuilder sb = new StringBuilder();
		if(skuPropNameArray!=null&&skuPropNameArray.length>0){
			HashMap<Integer, ItoSkuPropValue> propValueMap = itoSkuPropValueService.queryMap();
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
		
		if(productSku!=null){
			model.addAttribute("productSku", productSku);
			ItoProduct product = itoProductService.loadById(productId);
			model.addAttribute("product", product);
			
//			//加载该sku商品对应的图片
//			List<ItoSkuImage> skuImageList = itoSkuImageService.queryAllBySkuId(skuId);
//			model.addAttribute("skuImageList", skuImageList);
		}
		
		return "ito/productSkuEdit";
	}
	
	/**
	 * 保存单个sku信息
	 * @param model
	 * @param itoSku
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSku", method = RequestMethod.POST)
	public String saveSkuImage(Model model, ItoSku itoSku, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		ItoSku dbSku = itoSkuService.loadById(itoSku.getId());
			if(dbSku!=null){
			
			//step1: 获取materialUrl&更新
			if(StringUtils.isNotBlank(itoSku.getMaterialPicUrl())){
				//只将相同materialId的数据更新为materialUrl
				ItoSkuCriteria criteria = new ItoSkuCriteria();
				criteria.createCriteria().andProductIdEqualTo(dbSku.getProductId()).andMaterialIdEqualTo(dbSku.getMaterialId());
				ItoSku updatedSkuInfo = new ItoSku();
				updatedSkuInfo.setMaterialPicUrl(itoSku.getMaterialPicUrl());
				itoSkuService.updateByCriteria(updatedSkuInfo, criteria);
			}
			
			//step:2 获取colorUrl&更新
			if(StringUtils.isNotBlank(itoSku.getColorPicUrl())){
				//只将相同colorId的数据更新为colorUrl
				ItoSkuCriteria criteria = new ItoSkuCriteria();
				criteria.createCriteria().andProductIdEqualTo(dbSku.getProductId()).andMaterialIdEqualTo(dbSku.getMaterialId()).andColorIdEqualTo(dbSku.getColorId());
				ItoSku updatedSkuInfo = new ItoSku();
				updatedSkuInfo.setColorPicUrl(itoSku.getColorPicUrl());
				itoSkuService.updateByCriteria(updatedSkuInfo, criteria);
			}
			
			//step:3 获取sizeUrl&更新
			if(StringUtils.isNotBlank(itoSku.getSizePicUrl())){
				//理应当只将相同sizeId的数据更新为materialUrl，但实际上三个size的图片使用的是同一个，所以没必要单独更新（跟step2同样的策略即可）
				ItoSkuCriteria criteria = new ItoSkuCriteria();
				criteria.createCriteria().andProductIdEqualTo(dbSku.getProductId()).andMaterialIdEqualTo(dbSku.getMaterialId()).andColorIdEqualTo(dbSku.getColorId());
				ItoSku updatedSkuInfo = new ItoSku();
				updatedSkuInfo.setSizePicUrl(itoSku.getSizePicUrl());
				itoSkuService.updateByCriteria(updatedSkuInfo, criteria);
			}
			
			Date currentTime = new Date();
			itoSku.setUpdateTime(currentTime);
			if(itoSku!=null&&itoSku.getId()!=null&&itoSku.getId()>0){
				itoSku.setSkuThumbPicUrl(itoSku.getSkuPicUrl());
				//以下三项无需重新更新（上面step1~3流程中已经全面更新过了）
				itoSku.setMaterialPicUrl(null);
				itoSku.setColorPicUrl(null);
				itoSku.setSizePicUrl(null);
				
				result = itoSkuService.updateById(itoSku);
			}
		}
		model.addAttribute("redirectUrl", "./productSkus?productId="+itoSku.getProductId());
		return "forward:/home/operationRedirect";
	}
	
	///////////////////////////////////////////////////////////////////////
	//////////////////////    sku 图片的处理   //////////////////////////////
	///////////////////////////////////////////////////////////////////////	
	
	@Deprecated
	@RequestMapping("/skuImageAdd")
	public String skuImageAdd(Model model, int productId, int skuId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoSku productSku = itoSkuService.loadProductSku(productId, skuId);
		if(productSku!=null){
			model.addAttribute("productSku", productSku);
			
			ItoSkuImage skuImage = new ItoSkuImage();
			skuImage.setProductId(productId);
			skuImage.setSkuId(skuId);
			model.addAttribute("skuImage", skuImage);
		}
		return "ito/skuImageEdit";
	}
	
	@Deprecated
	@RequestMapping("/skuImageEdit")
	public String skuImageEdit(Model model, HttpServletRequest request, int skuImageId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		ItoSkuImage skuImage = itoSkuImageService.loadById(skuImageId);
		model.addAttribute("skuImage", skuImage);
		return "ito/skuImageEdit";
	}
	
	
	/**
	 * 保存sku商品的图片
	 * @param model
	 * @param skuImage
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/saveSkuImage", method = RequestMethod.POST)
	public String saveSkuImage(Model model, ItoSkuImage skuImage, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		skuImage.setUpdateTime(currentTime);
		if(skuImage!=null&&skuImage.getId()!=null&&skuImage.getId()>0){
			result = itoSkuImageService.updateById(skuImage);
		}else{
			skuImage.setCreateTime(currentTime);
			result = itoSkuImageService.save(skuImage);
		}
		
		//以下操作是为了同步更新skuInfo中的缩略图
		if(result>0){
			updateSkuInfoPic(skuImage.getSkuId());
		}
		
		model.addAttribute("redirectUrl", "./productSkuEdit?productId="+skuImage.getProductId()+"&skuId="+skuImage.getSkuId());
		return "forward:/home/operationRedirect";
		
	}

	
	/**
	 * 删除sku的图片
	 * @param model
	 * @param productId
	 * @param skuId
	 * @param skuImageId
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/delSkuImage")
	public String delSkuImage(Model model, int productId, int skuId, int skuImageId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = itoSkuImageService.deleteById(skuImageId);
		if(result>0){
			//以下操作是为了同步更新skuInfo中的缩略图
			if(result>0){
				updateSkuInfoPic(skuId);
			}
		}
		
		model.addAttribute("redirectUrl", "./productSkuEdit?productId="+productId+"&skuId="+skuId);
		return "forward:/home/operationRedirect"; 
	}
	
	
	
	
	/**
	 * 使用imageList的第一个来更新skuInfo中的图片url
	 * @param skuId
	 */
	@Deprecated
	private void updateSkuInfoPic(int skuId) {
		//加载该sku商品对应的图片
		List<ItoSkuImage> skuImageList = itoSkuImageService.queryAllBySkuId(skuId);
		if(skuImageList!=null&&skuImageList.size()>0){
			for(ItoSkuImage loopImage: skuImageList){
				String skuImagePicUrl = loopImage.getSkuPicUrl();
				if(skuImagePicUrl!=null){
					ItoSku sku = new ItoSku();
					sku.setId(skuId);
					sku.setSkuPicUrl(skuImagePicUrl);
					itoSkuService.updateById(sku);
					break;
				}
			}
		}
	}
}
