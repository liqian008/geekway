package com.bruce.geekway.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bruce.foundation.util.CookieUtils;
import com.bruce.foundation.util.DateUtil;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.WxProductCart;
import com.bruce.geekway.model.WxProductSku;

/**
 * 
 * @author liqian
 * 
 */
public class CartUtil {

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
	 * 从cookie中加载购物车
	 * 
	 * @param request
	 * @return
	 */
	public static WxProductCart loadCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart = null;
		// 检查cookie中的购物车信息
		String cartJson = CookieUtils.getCookie(request, ConstFront.COOKIE_KEY_WX_PRODUCT_CART);
		if (!StringUtils.isBlank(cartJson)) {
			try {
				cart = JsonUtil.gson.fromJson(cartJson, WxProductCart.class);
			} catch (Exception e) {
			}
		}
		if (cart == null) {
			cart = new WxProductCart();
			writeCartCookie(cart, response);
		}
		return cart;
	}

	
	/**
	 * 更新购物车中的商品
	 * @param productCart
	 * @param response
	 */
	public static  void writeCartCookie(WxProductCart productCart, HttpServletResponse response) {
		if (productCart != null) {
			// 将购物车写入cookie
			Cookie cookie = new Cookie(ConstFront.COOKIE_KEY_WX_PRODUCT_CART, JsonUtil.gson.toJson(productCart));
			cookie.setMaxAge((int) (DateUtil.TIME_UNIT_DAY/DateUtil.TIME_UNIT_SECOND));// 超时时间
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	
	/**
	 * 清空购物车
	 * @param response
	 */
	public static void clearCartCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(ConstFront.COOKIE_KEY_WX_PRODUCT_CART, null);
		cookie.setMaxAge(0);// 超时时间
		cookie.setPath("/");
		response.addCookie(cookie);
//		CartUtil.writeCartCookie(new WxProductCart(), response);
	}

}
