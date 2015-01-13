package com.bruce.geekway.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;

import com.bruce.geekway.model.ProductCategory;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductTag;
import com.bruce.geekway.model.SlideImage;

/**
 * 产品链接util类
 * @author liqian
 *
 */
public class ShopLinkUtil {
	
	private static final String INDEX_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_index_link");

	private static final String PRODUCT_LINK_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_product_link");
	
	private static final String PRODUCT_SKU_LINK_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_product_sku_link");
	
	private static final String PRODUCT_TAG_LINK_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_product_tag_link");
	
	private static final String PRODUCT_CATEGORY_LINK_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_product_category_link");
	
	private static final String PAYTERMS_LINK_MOBILE_TEMPLATE = ConfigUtil.getString("geekway_pay_terms_link");

	
	/**
	 * 获取mobile上的首页链接
	 * @return
	 */
	public static String getIndexLink4Mobile(){
		String link = String.format(INDEX_MOBILE_TEMPLATE);
		return link;
	}
	
	/**
	 * 获取mobile上的产品链接
	 * @param productId
	 * @return
	 */
	public static String getProductLink4Mobile(int productId){
		String link = String.format(PRODUCT_LINK_MOBILE_TEMPLATE, productId);
		return link;
	}
	/**
	 * 获取mobile上的产品链接
	 * @param productId
	 * @param productSkuId
	 * @return
	 */
	public static String getProductSkuLink4Mobile(int productId, int productSkuId){
		String link = String.format(PRODUCT_SKU_LINK_MOBILE_TEMPLATE, productId, productSkuId);
		return link;
	}
	
	
	/**
	 * 获取mobile上的tag链接
	 * @param tagId
	 * @return
	 */
	public static String getTagLink4Mobile(int tagId){
		String link = String.format(PRODUCT_TAG_LINK_MOBILE_TEMPLATE, tagId);
		return link;
	}
	

	/**
	 * 获取mobile上的category链接
	 * @param categoryId
	 * @return
	 */
	public static String getCategoryLink4Mobile(int categoryId){
		String link = String.format(PRODUCT_CATEGORY_LINK_MOBILE_TEMPLATE, categoryId);
		return link;
	}
	
	
	/**
	 * 获取mobile上的代付订单链接
	 * @param tradeNo
	 * @return
	 */
	public static String getPayTermsLink4Mobile(String tradeNo){
		String link = String.format(PAYTERMS_LINK_MOBILE_TEMPLATE, tradeNo);
		return link;
	}
	
//	
//	/**
//	 * 商品sku链接
//	 * @param articleId
//	 * @return
//	 */
//	public static String getProductSkuLink(int productSkuId){
//		String link = String.format(PRODUCT_SKU_URL, productSkuId);
//		return link;
//	}
	
	
	public static void resizeSlideImageList(List<SlideImage> slideImageList, int specWidth) {
		if(CollectionUtils.isNotEmpty(slideImageList)){
			for(int i=slideImageList.size()-1;i>=0;i--){
				SlideImage slideImage = slideImageList.get(i);
//				if(StringUtils.isBlank(slideImage.getImageUrl())){//图片为空
//					slideImageList.remove(i);
//				}
				String imageUrl = slideImage.getPicUrl();
				String resizedImageUrl = UploadUtil.getQiniuResizeImageUrl(imageUrl, specWidth, 0);
				slideImage.setPicUrl(resizedImageUrl);
			}
		}
	}
	
	
	
//	public static SlideImage buildSlideImage(String imageUrl, String link){
//		if(StringUtils.isBlank(link)){
//			link = "javascript:void(0)";
//		}
//		return new SlideImage(imageUrl, link);
//	}
//	
//	
//	public static class SlideImage{
//		private String imageUrl;
//		private String link;
//		
//		public SlideImage(String imageUrl, String link) {
//			this.imageUrl = imageUrl;
//			this.link = link;
//		}
//		public String getLink() {
//			return link;
//		}
//		public void setLink(String link) {
//			this.link = link;
//		}
//		public String getImageUrl() {
//			return imageUrl;
//		}
//		public void setImageUrl(String imageUrl) {
//			this.imageUrl = imageUrl;
//		}
//		
//	}
	
}
