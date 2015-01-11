package com.bruce.geekway.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 * 
 * @author liqian
 * 
 */
public class ProductCart {

	private Map<Integer, Integer> itemMap = new HashMap<Integer, Integer>();

	public Map<Integer, Integer> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<Integer, Integer> itemMap) {
		this.itemMap = itemMap;
	}

	public void addItem(int productSkuId, int buyAmount) {
		if (itemMap != null) {
			itemMap.put(productSkuId, buyAmount);
		}
	}

	public void removeItem(int productSkuId) {
		if (itemMap != null) {
			itemMap.remove(productSkuId);
		}
	}

	public static class CartProductSku {

		private ProductSku productSku;
		private int amount;

		public CartProductSku() {
			super();
		}
		
		public CartProductSku(ProductSku productSku, int amount) {
			super();
			this.productSku = productSku;
			this.amount = amount;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public ProductSku getProductSku() {
			return productSku;
		}

		public void setProductSku(ProductSku productSku) {
			this.productSku = productSku;
		}

	}

//	public static void main(String[] args) {
//		String cartJson = "";
//		WxProductCart cart = JsonUtil.gson.fromJson(cartJson, WxProductCart.class);
//		System.out.println(cart);
//
//		cart = new WxProductCart();
//		System.out.println(JsonUtil.gson.toJson(cart));
//
//	}

}
