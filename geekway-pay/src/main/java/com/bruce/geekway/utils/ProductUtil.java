package com.bruce.geekway.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductTag;


public class ProductUtil {

	private static final String PRODUCT_SKU_URL = ConfigUtil.getString("geekway_product_sku_link");
	
	/**
	 * 商品sku链接
	 * @param articleId
	 * @return
	 */
	public static String getProductSkuLink(int productSkuId){
		String link = String.format(PRODUCT_SKU_URL, productSkuId);
		return link;
	}
	
	
	@Deprecated
	public static List<String> buildProductSkuPicList(WxProductSku productSku) {
		if(productSku!=null&&productSku.getId()!=null){
			List<String> skuPicList = new ArrayList<String>();
			String pic1Url = productSku.getSkuPic1Url();
			if(StringUtils.isNotBlank(pic1Url)){
				skuPicList.add(UploadUtil.getQiniuResizeImageUrl(pic1Url, 600, 0));
			}
			String pic2Url = productSku.getSkuPic2Url();
			if(StringUtils.isNotBlank(pic2Url)){
				skuPicList.add(UploadUtil.getQiniuResizeImageUrl(pic2Url, 600, 0));
			}
			String pic3Url = productSku.getSkuPic3Url();
			if(StringUtils.isNotBlank(pic3Url)){
				skuPicList.add(UploadUtil.getQiniuResizeImageUrl(pic3Url, 600, 0));
			}
			String pic4Url = productSku.getSkuPic4Url();
			if(StringUtils.isNotBlank(pic4Url)){
				skuPicList.add(UploadUtil.getQiniuResizeImageUrl(pic4Url, 600, 0));
			}
			return skuPicList;
		}
		return null;
	}
	
	
	/**
	 * 构建tag的slideList
	 * @param productTag
	 * @return
	 */
	public static List<SlideImage> buildSlideImageList(WxProductSku productSku) {
		if(productSku!=null&&productSku.getId()!=null){
			List<SlideImage> slideImageList = new ArrayList<SlideImage>();
			int specWidth = 600;
			String link = "javascript:void(0)";
			
			String pic1Url = productSku.getSkuPic1Url();
			if(StringUtils.isNotBlank(pic1Url)){
				String image1Url = UploadUtil.getQiniuResizeImageUrl(pic1Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image1Url, link));
			}
			
			String pic2Url = productSku.getSkuPic2Url();
			if(StringUtils.isNotBlank(pic2Url)){
				String image2Url = UploadUtil.getQiniuResizeImageUrl(pic2Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image2Url, link));
			}
			
			String pic3Url = productSku.getSkuPic3Url();
			if(StringUtils.isNotBlank(pic3Url)){
				String image3Url = UploadUtil.getQiniuResizeImageUrl(pic3Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image3Url, link));
			}
			
			String pic4Url = productSku.getSkuPic4Url();
			if(StringUtils.isNotBlank(pic4Url)){
				String image4Url = UploadUtil.getQiniuResizeImageUrl(pic4Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image4Url, link));
			}
			return slideImageList;
		}
		return null;
	}
	
	
	/**
	 * 构建tag的slideList
	 * @param productTag
	 * @return
	 */
	public static List<SlideImage> buildSlideImageList(WxProductTag productTag) {
		if(productTag!=null&&productTag.getId()!=null){
			List<SlideImage> slideImageList = new ArrayList<SlideImage>();
			int specWidth = 600;
			
			String pic1Url = productTag.getTagPic1Url();
			if(StringUtils.isNotBlank(pic1Url)){
				String image1Url = UploadUtil.getQiniuResizeImageUrl(pic1Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image1Url, productTag.getTagLink1()));
			}
			
			String pic2Url = productTag.getTagPic2Url();
			if(StringUtils.isNotBlank(pic2Url)){
				String image2Url = UploadUtil.getQiniuResizeImageUrl(pic2Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image2Url, productTag.getTagLink2()));
			}
			
			String pic3Url = productTag.getTagPic3Url();
			if(StringUtils.isNotBlank(pic3Url)){
				String image3Url = UploadUtil.getQiniuResizeImageUrl(pic3Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image3Url, productTag.getTagLink3()));
			}
			
			String pic4Url = productTag.getTagPic4Url();
			if(StringUtils.isNotBlank(pic4Url)){
				String image4Url = UploadUtil.getQiniuResizeImageUrl(pic4Url, specWidth, 0);
				slideImageList.add(buildSlideImage(image4Url, productTag.getTagLink4()));
			}
			return slideImageList;
		}
		return null;
	}
	
	
	public static SlideImage buildSlideImage(String imageUrl, String link){
		if(StringUtils.isBlank(link)){
			link = "javascript:void(0)";
		}
		return new SlideImage(imageUrl, link);
	}
	
	
	public static class SlideImage{
		private String imageUrl;
		private String link;
		
		public SlideImage(String imageUrl, String link) {
			this.imageUrl = imageUrl;
			this.link = link;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		
	}
	
}
