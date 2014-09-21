package com.bruce.geekway.utils;


public class ProductLinkUtil {

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
	
}
