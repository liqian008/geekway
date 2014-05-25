package com.bruce.geekway.admin.controller.ito;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuImageService;
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
	
	
//	@RequestMapping("/skuList")
//	public String skuList(Model model, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		List<ItoSku> skuList = itoSkuService.queryAll();
//		model.addAttribute("skuList", skuList);
//		return "ito/skuList";
//	}
	
	
	
	
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
		model.addAttribute("skuList", skuList);
		
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
		if(productSku!=null){
			model.addAttribute("productSku", productSku);
			ItoProduct product = itoProductService.loadById(productId);
			model.addAttribute("product", product);
			
			//加载该sku商品对应的图片
			List<ItoSkuImage> skuImageList = itoSkuImageService.queryAllBySkuId(skuId);
			model.addAttribute("skuImageList", skuImageList);
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
		
		Date currentTime = new Date();
		itoSku.setUpdateTime(currentTime);
		if(itoSku!=null&&itoSku.getId()!=null&&itoSku.getId()>0){
			itoSku.setSkuThumbPicUrl(itoSku.getSkuPicUrl()); 			
			result = itoSkuService.updateById(itoSku);
		}
		
		model.addAttribute("redirectUrl", "./productSkus?productId="+itoSku.getProductId());
		return "forward:/home/operationRedirect";
	}
	
	///////////////////////////////////////////////////////////////////////
	//////////////////////    sku 图片的处理   //////////////////////////////
	///////////////////////////////////////////////////////////////////////	
	
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
